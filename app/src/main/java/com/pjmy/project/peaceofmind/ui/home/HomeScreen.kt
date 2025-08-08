// ui/home/HomeScreen.kt
package com.pjmy.project.peaceofmind.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pjmy.project.peaceofmind.data.model.ImageItem
import android.util.Log

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToDetail: (imageId: String) -> Unit,
    onNavigateToGallery: (category: String) -> Unit
) {
    // ViewModel의 단일 상태 객체인 uiState만 관찰합니다.
    val uiState by viewModel.uiState.collectAsState()

    // 화면 전체를 스크롤 가능하게 만듭니다.
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

        // TODO: 이어하기 섹션 (ContinueSection)
        // if (uiState.continueItems.isNotEmpty()) { ... }

        Spacer(modifier = Modifier.height(24.dp))

        // "회원님을 위한 맞춤 추천" 섹션
        // 이제 uiState를 통해 데이터에 접근합니다.
        if (uiState.recommendedItems.isNotEmpty()) {
            ImageCarouselSection(
                title = "회원님을 위한 맞춤 추천",
                images = uiState.recommendedItems,
                onImageClick = { imageId -> onNavigateToDetail(imageId) }
            )
            Spacer(modifier = Modifier.height(24.dp))
        }

        // "새로운 그림" 섹션 (NewImagesSection)
        if (uiState.newItems.isNotEmpty()) {
            ImageCarouselSection(
                title = "새로운 그림",
                images = uiState.newItems,
                onImageClick = { imageId -> onNavigateToDetail(imageId) }
            )
            Spacer(modifier = Modifier.height(24.dp))
        }

        // TODO: "명예의 전당" 섹션 (TopTenSection)
        // if (uiState.topTenItems.isNotEmpty()) { ... }

        // "카테고리별 탐색" 섹션
        CategorySection(
            onCategoryClick = { category -> onNavigateToGallery(category) }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

// Anvis: RecommendedSection의 이름을 ImageCarouselSection으로 변경하여 재사용성을 높였습니다.
@Composable
fun ImageCarouselSection(
    title: String,
    images: List<ImageItem>,
    onImageClick: (String) -> Unit
) {
    Log.d("Anvis_Debug", "[$title] 섹션에 들어온 이미지 개수: ${images.size}개")
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall
            // modifier = Modifier.padding(...) // Modifier.padding import 필요
        )
        Spacer(modifier = Modifier.height(8.dp))
        // ...
        LazyRow(/*...*/) {
            items(images) { image ->
                // 방금 만든 ImageThumbnail 컴포저블을 사용합니다.
                ImageThumbnail(
                    imageItem = image,
                    onClick = { onImageClick(image.id) }
                )
            }
        }
    }
}

@Composable
fun RecommendedSection(
    title: String,
    images: List<ImageItem>,
    onImageClick: (String) -> Unit
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall
            // modifier = Modifier.padding(...) // Modifier.padding import 필요
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(images) { image ->
                // TODO: ImageThumbnail 컴포저블을 만들어 그림 썸네일을 표시해야 합니다.
            }
        }
    }
}

@Composable
fun CategorySection(
    onCategoryClick: (String) -> Unit
) {
    val categories = listOf("명화", "만다라", "동물", "풍경")

    Column {
        Text(
            text = "카테고리별 탐색",
            style = MaterialTheme.typography.headlineSmall
            // modifier = Modifier.padding(...) // Modifier.padding import 필요
        )
        // TODO: 카테고리 버튼 UI 구현
    }
}