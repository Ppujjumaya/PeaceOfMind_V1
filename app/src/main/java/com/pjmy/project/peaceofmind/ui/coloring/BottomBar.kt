// ui/coloring/BottomBar.kt
package com.pjmy.project.peaceofmind.ui.coloring

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.pjmy.project.peaceofmind.data.model.ColoringMode

@Composable
fun BottomBar(
    uiState: ColoringUiState,
    onEvent: (ColoringEvent) -> Unit
) {
    // Anvis: 현재 색칠 모드를 확인하여 그에 맞는 UI를 보여줍니다.
    val mode = (uiState.currentTool as? DrawingTool.NumberFill)?.let { ColoringMode.NUMBER } ?: ColoringMode.FREESTYLE

    Column {
        when (mode) {
            ColoringMode.NUMBER -> {
                NumberPalette(
                    uiState = uiState,
                    onEvent = onEvent
                )
            }
            ColoringMode.FREESTYLE -> {
                FreestyleToolbar(
                    uiState = uiState,
                    onEvent = onEvent
                )
            }
        }
    }
}

@Composable
fun NumberPalette(
    uiState: ColoringUiState,
    onEvent: (ColoringEvent) -> Unit
) {
    // TODO: '숫자 지정 방식'일 때의 색상 팔레트 UI 구현
    // LazyRow를 사용하여 각 번호와 색상이 있는 버튼들을 여기에 배치합니다.
    // 각 버튼 클릭 시 onEvent(ColoringEvent.ToolSelected(DrawingTool.NumberFill(...))) 호출
    Row {
        Text("숫자 팔레트 영역")
    }
}

@Composable
fun FreestyleToolbar(
    uiState: ColoringUiState,
    onEvent: (ColoringEvent) -> Unit
) {
    // TODO: '자유 색칠 방식'일 때의 도구 모음 UI 구현
    // 브러시/지우개/채우기 선택 버튼, 색상 피커, 두께 조절 슬라이더 등을 여기에 배치합니다.
    // 도구 선택 시 onEvent(ColoringEvent.ToolSelected(...)) 호출
    Row {
        Text("자유 그리기 도구 모음 영역")
    }
}