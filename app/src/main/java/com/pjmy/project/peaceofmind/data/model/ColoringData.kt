// data/model/ColoringData.kt
package com.pjmy.project.peaceofmind.data.model

import kotlinx.serialization.Serializable
//색칠에 필요한 상세 데이터(영역, 팔레트)를 담는 클래스입니다
@Serializable
data class ColoringData(
    val imageId: String = "",
    val regions: List<ColoringRegion> = emptyList(),
    val palette: List<String> = emptyList()
)