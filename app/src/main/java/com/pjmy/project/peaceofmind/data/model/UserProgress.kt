// data/model/UserProgress.kt
package com.pjmy.project.peaceofmind.data.model

//'이어하기' 기능의 시간 정보(lastModified)와 두 가지 색칠 모드를 모두 지원하기 위해 구조가 확장된 최종 버전
data class UserProgress(
    val imageId: String,
    // '이어하기' 섹션에서 "N일 전"을 표시하는 데 사용됩니다
    val lastModified: Long = System.currentTimeMillis(),
    // '숫자 지정 방식' 또는 '단색 채우기'용 맵
    val fillColors: Map<String, String> = emptyMap(), // <Region ID, Color Hex Code>
    // '영역 자유 색칠' 방식용 맵
    val freestyleStrokes: Map<String, List<StrokeData>> = emptyMap() // <Region ID, 획 목록>
)