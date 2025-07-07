// ui/coloring/ColoringViewModel.kt
package com.pjmy.project.peaceofmind.ui.coloring

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pjmy.project.peaceofmind.data.model.ColoringMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ColoringViewModel(/* Repositories */) : ViewModel() {

    private val _uiState = MutableStateFlow(ColoringUiState())
    val uiState: StateFlow<ColoringUiState> = _uiState.asStateFlow()

    fun onEvent(event: ColoringEvent) {
        when (event) {
            is ColoringEvent.LoadDrawing -> loadDrawing(event.imageId, event.mode)
            is ColoringEvent.RegionTapped -> handleRegionTap(event.regionId)
            is ColoringEvent.ToolSelected -> selectTool(event.tool)
            // TODO: StrokeStarted, StrokeMoved, StrokeEnded 등 자유 그리기 이벤트 처리
            is ColoringEvent.StrokeStarted -> {
                // TODO: 자유 그리기 획 시작 로직
            }
            is ColoringEvent.StrokeMoved -> {
                // TODO: 자유 그리기 획 이동/그리는 중 로직
            }
            is ColoringEvent.StrokeEnded -> {
                // TODO: 자유 그리기 획 종료 로직 (UserProgress에 저장)
            }

            ColoringEvent.Undo -> undo()
            ColoringEvent.Redo -> redo()
        }
    }

    private fun loadDrawing(imageId: String, mode: ColoringMode) {
        viewModelScope.launch {
            // TODO: Repository에서 ColoringData, UserProgress 로드
            // 로드 완료 후, mode에 따라 초기 currentTool 설정
            // (예: NUMBER 모드면 NumberFill, FREESTYLE이면 Pencil)
            // 최종적으로 _uiState 업데이트
        }
    }

    private fun handleRegionTap(regionId: String) {
        // TODO: 현재 선택된 currentTool이 FillBucket 또는 NumberFill일 경우,
        // 해당 영역에 색을 채우는 로직 수행 (userProgress 업데이트)
        // 작업 이력을 undo 스택에 추가
    }

    private fun selectTool(tool: DrawingTool) {
        // TODO: _uiState의 currentTool을 업데이트
    }

    private fun undo() { /* ... */ }
    private fun redo() { /* ... */ }
}