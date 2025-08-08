// ui/home/ImageThumbnail.kt
package com.pjmy.project.peaceofmind.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pjmy.project.peaceofmind.data.model.ImageItem

@Composable
fun ImageThumbnail(
    modifier: Modifier = Modifier,
    imageItem: ImageItem,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .width(150.dp) // 가로 크기 고정
            .height(190.dp) // Anvis: ✨ 세로 크기를 여기에 고정합니다!
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            AsyncImage(
                model = imageItem.previewUrl,
                contentDescription = imageItem.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f) // 1:1 비율 이미지
            )

            Spacer(modifier = Modifier.weight(1f)) // Anvis: ✨ 텍스트를 아래로 밀어냅니다.

            Text(
                text = imageItem.title,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp),
                maxLines = 1, // Anvis: ✨ 텍스트를 최대 한 줄로 제한
                overflow = TextOverflow.Ellipsis // Anvis: ✨ 길면 ... 으로 표시
            )

            Spacer(modifier = Modifier.height(4.dp)) // Anvis: ✨ 하단 여백 추가
        }
    }
}