package com.pjmy.project.peaceofmind.repository
// data/repository/UserProgressRepository.kt

import com.pjmy.project.peaceofmind.data.model.UserProgress

//사용자 개인의 색칠 진행 상황, 완료 목록 등 로컬 DB나 개인화된 서버 데이터에 접근하는 역할을 합니다.
// Anvis: 이 인터페이스의 실제 구현체는 Room DB나 DataStore를 사용하게 됩니다.
interface UserProgressRepository {

    /**
     * 홈 화면의 '이어하기' 섹션에 표시될, 최근에 작업한 항목 목록을 가져옵니다.
     * @param limit 가져올 최대 개수
     * @return UserProgress 리스트
     */
    suspend fun getContinueColoringItems(limit: Int): List<UserProgress>

    /**
     * 콘텐츠 기반 추천 로직을 위해, 사용자가 완성한 모든 그림의 테마 목록을 반환합니다.
     * @param userId 사용자 ID
     * @return 테마 이름 리스트 (예: ["동물", "만다라", "동물"])
     */
    suspend fun getAllCompletedThemes(userId: String): List<String>

    /**
     * 중복 추천을 방지하기 위해, 사용자가 이미 완성한 그림의 ID 목록을 가져옵니다.
     * @param userId 사용자 ID
     * @return 이미지 ID 리스트
     */
    suspend fun getAllCompletedImageIds(userId: String): List<String>

    // ... 특정 이미지의 진행 상황을 저장/로드하는 다른 함수들 ...
}