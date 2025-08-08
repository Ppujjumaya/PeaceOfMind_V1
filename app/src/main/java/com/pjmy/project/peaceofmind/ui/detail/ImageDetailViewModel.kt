package com.pjmy.project.peaceofmind.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pjmy.project.peaceofmind.data.model.ImageItem
import com.pjmy.project.peaceofmind.data.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// Anvis: 상세 화면의 UI 상태를 정의하는 데이터 클래스
data class ImageDetailUiState(
    val isLoading: Boolean = true,
    val imageItem: ImageItem? = null,
    val error: String? = null
)

@HiltViewModel
class ImageDetailViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
    // Anvis: Hilt가 내비게이션 경로로부터 imageId를 자동으로 주입해줍니다.
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(ImageDetailUiState())
    val uiState: StateFlow<ImageDetailUiState> = _uiState.asStateFlow()

    init {
        // ViewModel이 생성될 때, 전달받은 imageId로 데이터 로딩을 시작합니다.
        savedStateHandle.get<String>("imageId")?.let { imageId ->
            loadImageDetail(imageId)
        }
    }

    private fun loadImageDetail(imageId: String) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            try {
                val imageItem = imageRepository.getImageById(imageId)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        imageItem = imageItem,
                        error = if (imageItem == null) "이미지를 찾을 수 없습니다." else null
                    )
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "데이터를 불러오는 중 에러가 발생했습니다.") }
            }
        }
    }
}