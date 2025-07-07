// ui/coloring/ColoringScreen.kt
package com.pjmy.project.peaceofmind.ui.coloring

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.pjmy.project.peaceofmind.data.model.ColoringMode
import androidx.compose.foundation.layout.padding

//색칠 화면의 전체적인 레이아웃을 구성하는 최상위 컴포저블

@Composable
fun ColoringScreen(
    imageId: String,
    coloringMode: ColoringMode,
    viewModel: ColoringViewModel,
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    // 화면이 처음 구성될 때 데이터 로딩 이벤트 전달
    LaunchedEffect(key1 = imageId, key2 = coloringMode) {
        viewModel.onEvent(ColoringEvent.LoadDrawing(imageId, coloringMode))
    }

    Scaffold(
        topBar = {
            // TODO: 뒤로가기, 그림 제목, 공유 버튼 등이 포함된 TopAppBar 구현
        },
        bottomBar = {
            // TODO: 색칠 모드에 맞는 하단 바 (ColorPalette 또는 DrawingToolbar) 구현
            BottomBar(uiState = uiState, onEvent = viewModel::onEvent)
        }
    ) { paddingValues ->
        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (uiState.coloringData != null) {
            DrawingCanvas(
                modifier = Modifier.padding(paddingValues),
                uiState = uiState,
                onEvent = viewModel::onEvent
            )
        } else {
            // TODO: 에러 상태 표시 UI
        }
    }
}