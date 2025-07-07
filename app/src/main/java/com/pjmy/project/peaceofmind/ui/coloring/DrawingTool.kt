// ui/coloring/DrawingTool.kt
package com.pjmy.project.peaceofmind.ui.coloring

import androidx.compose.ui.graphics.Color
//색칠 화면에서 사용될 도구들을 명확하게 모델링하기 위해 sealed class를 새로 정의합니다. 상태 관리가 훨씬 깔끔해집니다.

sealed class DrawingTool {
    // 숫자 지정 모드에서 사용될 채우기 도구
    data class NumberFill(val number: Int, val color: Color) : DrawingTool()

    // 자유 색칠 모드에서 사용될 연필 도구
    data class Pencil(val color: Color, val strokeWidth: Float) : DrawingTool()

    // 자유 색칠 모드에서 사용될 채우기 도구
    data class FillBucket(val color: Color) : DrawingTool()

    // 지우개 도구
    data class Eraser(val strokeWidth: Float) : DrawingTool()
}