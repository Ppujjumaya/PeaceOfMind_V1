// data/model/ColoringRegion.kt
package com.pjmy.project.peaceofmind.data.model

import kotlinx.serialization.Serializable
//색칠할 개별 영역의 정보를 담는 클래스입니다
@Serializable
data class ColoringRegion(
    val id: String = "",
    val number: Int = 0,
    val pathData: String = ""
)