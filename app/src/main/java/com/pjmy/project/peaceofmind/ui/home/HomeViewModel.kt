// ui/home/HomeViewModel.kt
package com.pjmy.project.peaceofmind.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pjmy.project.peaceofmind.data.model.ImageItem
import com.pjmy.project.peaceofmind.data.model.SharedArtwork
import com.pjmy.project.peaceofmind.data.model.UserProgress
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// ui/home/HomeViewModel.kt 파일 상단 또는 별도 파일에 정의

data class HomeUiState(
    val isLoading: Boolean = true,
    val continueItems: List<UserProgress> = emptyList(),
    val newItems: List<ImageItem> = emptyList(),
    val recommendedItems: List<ImageItem> = emptyList(),
    val topTenItems: List<SharedArtwork> = emptyList(),
    val error: String? = null
)

// Anvis: 실제로는 Hilt/Koin 같은 DI 라이브러리를 통해 Repository들을 주입받아야 합니다.
class HomeViewModel(
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
                // Anvis: 각기 다른 데이터들을 동시에 비동기적으로 가져와 로딩 시간을 단축합니다. (성능 최적화)
                val continueDeferred = async { userProgressRepository.getContinueColoringItems(limit = 5) }
                val newDeferred = async { imageRepository.getNewImages(limit = 10) }
                val recommendedDeferred = async { imageRepository.getRecommendationsByThemes(/*...*/) }
                val topTenDeferred = async { communityRepository.getTopTenArtworks() }

                // 모든 비동기 작업이 끝날 때까지 기다립니다.
                val continueItems = continueDeferred.await()
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