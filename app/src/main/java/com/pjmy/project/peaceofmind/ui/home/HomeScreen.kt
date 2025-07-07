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

// Anvis: Hilt/Koin 같은 DI 라이브러리를 사용하면 viewModel을 직접 주입받을 수 있습니다.
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onNavigateToDetail: (imageId: String) -> Unit,
    onNavigateToGallery: (category: String) -> Unit
) {
    // ViewModel의 각 StateFlow를 관찰하여 UI 상태를 얻습니다.
    val continueItems by viewModel.continueItems.collectAsState()
    val newItems by viewModel.newItems.collectAsState()
    val recommendedItems by viewModel.recommendedItems.collectAsState()
    // ... Top10, 카테고리 등 다른 상태들도 동일하게 관찰 ...

    // 화면 전체를 스크롤 가능하게 만듭니다.
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

        // TODO: 이어하기 섹션 (ContinueSection)
        // if (continueItems.isNotEmpty()) { ... }

        Spacer(modifier = Modifier.height(24.dp))

        // "회원님을 위한 맞춤 추천" 섹션
        if (recommendedItems.isNotEmpty()) {
            RecommendedSection(
                title = "회원님을 위한 맞춤 추천",
                images = recommendedItems,
                onImageClick = { imageId -> onNavigateToDetail(imageId) }
            )
            Spacer(modifier = Modifier.height(24.dp))
        }

        // TODO: "새로운 그림" 섹션 (NewImagesSection)
        // if (newItems.isNotEmpty()) { ... }

        // TODO: "명예의 전당" 섹션 (TopTenSection)

        // "카테고리별 탐색" 섹션
        CategorySection(
            onCategoryClick = { category -> onNavigateToGallery(category) }
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

/**
 * 이미지 목록을 가로 캐러셀 형태로 보여주는 재사용 가능한 컴포저블
 */
@Composable
fun RecommendedSection(
    title: String,
    images: List<ImageItem>,
    onImageClick: (String) -> Unit
) {
    Column {
        // 섹션 제목
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        // 가로로 스크롤되는 그림 목록 (캐러셀)
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(images) { image ->
                // TODO: ImageThumbnail 컴포저블을 만들어 그림 썸네일을 표시해야 합니다.
                // ImageThumbnail(
                //     imageItem = image,
                //     onClick = { onImageClick(image.id) }
                // )
            }
        }
    }
}

/**
 * 카테고리 버튼들을 보여주는 컴포저블
 */
@Composable
fun CategorySection(
    onCategoryClick: (String) -> Unit
) {
    val categories = listOf("명화", "만다라", "동물", "풍경") // 예시 카테고리

    Column {
        Text(
            text = "카테고리별 탐색",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        // TODO: LazyRow나 FlowRow를 사용하여 각 카테고리를 대표하는
        // 아이콘 버튼이나 이미지 버튼을 여기에 배치합니다.
        // 각 버튼 클릭 시 onCategoryClick(categoryName)을 호출합니다.
    }
}