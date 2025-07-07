// data/model/ImageItem.kt
package com.pjmy.project.peaceofmind.data.model

import kotlinx.serialization.Serializable
//ImageItem은 갤러리에 표시될 그림의 모든 메타데이터를 담고 있습니다. 운영 및 프로모션에 필요한 tags, createdAt 등이 모두 포함된 최종 버전입니다.
@Serializable
data class ImageItem(
    val id: String = "",
    val title: String = "",
    val theme: String = "",
    val previewUrl: String = "",
    val dataUrl: String = "",
    val createdAt: Long = 0L,
    val tags: List<String> = emptyList(),
    val unlockCondition: UnlockCondition = UnlockCondition.Free,
    val artistName: String? = null,
    val artistUrl: String? = null
)