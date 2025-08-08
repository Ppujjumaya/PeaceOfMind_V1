// ui/home/HomeViewModel.kt
package com.pjmy.project.peaceofmind.ui.home

import androidx.lifecycle.viewModelScope
import com.pjmy.project.peaceofmind.data.model.ImageItem
import com.pjmy.project.peaceofmind.data.model.SharedArtwork
import com.pjmy.project.peaceofmind.data.model.UserProgress
import com.pjmy.project.peaceofmind.data.repository.CommunityRepository
import com.pjmy.project.peaceofmind.data.repository.ImageRepository
import com.pjmy.project.peaceofmind.data.repository.UserProgressRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
// ui/home/HomeViewModel.kt 파일 상단 또는 별도 파일에 정의
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


data class HomeUiState(
    val isLoading: Boolean = true,
    val continueItems: List<UserProgress> = emptyList(),
    val newItems: List<ImageItem> = emptyList(),
    val recommendedItems: List<ImageItem> = emptyList(),
    val topTenItems: List<SharedArtwork> = emptyList(),
    val error: String? = null
)

// Anvis: 실제로는 Hilt/Koin 같은 DI 라이브러리를 통해 Repository들을 주입받아야 합니다.
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
    private val userProgressRepository: UserProgressRepository,
    private val communityRepository: CommunityRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadHomeScreenData()
    }

    private fun loadHomeScreenData() {
        // 로딩 상태 시작
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            try {
                // Anvis: 추천 로직에 필요한 데이터를 먼저 가져옵니다.
                // 실제 앱에서는 로그인된 사용자 ID를 전달해야 합니다.
                val userId = "CURRENT_USER_ID"
                val completedThemes = userProgressRepository.getAllCompletedThemes(userId)
                val completedImageIds = userProgressRepository.getAllCompletedImageIds(userId)

                // 사용자가 가장 많이 완료한 테마 2개를 선호 테마로 선정합니다.
                val favoriteThemes = completedThemes
                    .groupingBy { it }
                    .eachCount()
                    .entries
                    .sortedByDescending { it.value }
                    .take(2)
                    .map { it.key }

                // Anvis: 이제 각 데이터를 동시에 비동기적으로 가져옵니다.
                val continueDeferred = async { userProgressRepository.getContinueColoringItems(limit = 5) }
                val newDeferred = async { imageRepository.getNewImages(limit = 10) }

                // Anvis: 아래 라인의 /*...*/ 부분을 실제 데이터로 채워줍니다.
                val recommendedDeferred = async {
                    imageRepository.getRecommendationsByThemes(
                        favoriteThemes = favoriteThemes,
                        completedImageIds = completedImageIds,
                        limit = 10
                    )
                }

                val topTenDeferred = async { communityRepository.getTopTenArtworks() }

                // 모든 비동기 작업이 끝날 때까지 기다립니다.
                val continueItems = continueDeferred.await()
                // ##moon
                val newItems = newDeferred.await()
                val recommendedItems = recommendedDeferred.await()
                val topTenItems = topTenDeferred.await()

                // 모든 데이터가 준비되면 UI 상태를 한 번에 업데이트합니다.
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        continueItems = continueItems,
                        newItems = newItems,
                        recommendedItems = recommendedItems,
                        topTenItems = topTenItems,
                        error = null
                    )
                }
            } catch (e: Exception) {
                // 데이터 로딩 중 에러 발생 시 처리
                _uiState.update { it.copy(isLoading = false, error = "데이터를 불러오는데 실패했습니다.") }
            }
        }
    }
}