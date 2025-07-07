// data/model/StrokeData.kt
package com.pjmy.project.peaceofmind.data.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
//영역 자유 색칠' 모드에서 사용자가 그은 획 하나의 정보를 담는 클래스
// Anvis: 이 클래스는 Room DB에 직접 저장하기 복잡하므로, 저장 시 JSON 문자열로 변환하는 TypeConverter가 필요합니다.
data class StrokeData(
    val path: Path,
    val color: Color,
    val strokeWidth: Float,
    val alpha: Float = 1.0f
)