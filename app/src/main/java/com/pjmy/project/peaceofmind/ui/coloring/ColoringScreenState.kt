// ui/coloring/ColoringScreenState.kt
package com.pjmy.project.peaceofmind.ui.coloring

import androidx.compose.ui.geometry.Offset
import com.pjmy.project.peaceofmind.data.model.ColoringData
import com.pjmy.project.peaceofmind.data.model.ColoringMode
import com.pjmy.project.peaceofmind.data.model.StrokeData
import com.pjmy.project.peaceofmind.data.model.UserProgress

// UI 상태 클래스
data class ColoringUiState(
    val isLoading: Boolean = true,
    val coloringData: ColoringData? = null,
    val userProgress: UserProgress = UserProgress(""), // 초기값은 비어있는 progress
    val currentTool: DrawingTool? = null, // 현재 선택된 그리기 도구
    val isPenDetected: Boolean = false, // 펜 입력 감지 여부
    val zoomLevel: Float = 1.0f,
    val panOffset: Offset = Offset.Zero,
    val canUndo: Boolean = false,
    val canRedo: Boolean = false,
    val error: String? = null
)

// UI 이벤트 Sealed 클래스
sealed class ColoringEvent {
    data class LoadDrawing(val imageId: String, val mode: ColoringMode) : ColoringEvent()
    data class RegionTapped(val regionId: String) : ColoringEvent()
    data class ToolSelected(val tool: DrawingTool) : ColoringEvent()
    // 자유 그리기를 위한 상세 이벤트
    data class StrokeStarted(val point: Offset) : ColoringEvent()
    data class StrokeMoved(val point: Offset) : ColoringEvent()
    data class StrokeEnded(val finalStroke: StrokeData) : ColoringEvent()

    object Undo : ColoringEvent()
    object Redo : ColoringEvent()
}