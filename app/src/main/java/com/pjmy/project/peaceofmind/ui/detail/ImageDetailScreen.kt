package com.pjmy.project.peaceofmind.ui.detail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.pjmy.project.peaceofmind.data.model.ColoringMode

@Composable
fun ImageDetailScreen(
    viewModel: ImageDetailViewModel = hiltViewModel(),
    onNavigateToColoring: (String, ColoringMode) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator()
        } else if (uiState.error != null) {
            Text(text = uiState.error!!)
        } else if (uiState.imageItem != null) {
            val image = uiState.imageItem!!

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 1. 큰 미리보기 이미지
                AsyncImage(
                    model = image.previewUrl,
                    contentDescription = image.title,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(MaterialTheme.shapes.medium)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 2. 그림 제목 및 정보
                Text(
                    text = image.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                if (image.artistName != null) {
                    Text(
                        text = "by ${image.artistName}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 3. 색칠 스타일 선택 버튼
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Button(
                        onClick = { onNavigateToColoring(image.id, ColoringMode.NUMBER) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("숫자로 색칠하기")
                    }
                    Button(
                        onClick = { onNavigateToColoring(image.id, ColoringMode.FREESTYLE) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("자유롭게 색칠하기")
                    }
                }
            }
        }
    }
}