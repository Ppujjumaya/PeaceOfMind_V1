// data/model/SharedArtwork.kt
package com.pjmy.project.peaceofmind.data.model

import kotlinx.serialization.Serializable
//커뮤니티 기능을 위한 공유 작품 정보 클래스입니다. '좋아요' 카운트(likeCount)까지 포함된 최종 버전
@Serializable
data class SharedArtwork(
    val id: String = "",
    val imageId: String = "",
    val originalTitle: String = "",
    val creatorUid: String = "",
    val creatorName: String = "",
    val completedImageUrl: String = "",
    val likeCount: Long = 0,
    val createdAt: Long = 0L
)