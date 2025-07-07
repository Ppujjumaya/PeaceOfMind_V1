package com.pjmy.project.peaceofmind.repository
// data/repository/ImageRepository.kt

import com.pjmy.project.peaceofmind.data.model.ColoringData
import com.pjmy.project.peaceofmind.data.model.ImageItem

interface ImageRepository {

    /**
     * 특정 카테고리의 모든 이미지 목록을 가져옵니다. (갤러리 화면용)
     * @param category 필터링할 카테고리 이름 (예: "명화", "동물")
     * @return ImageItem 리스트
     */
    suspend fun getAllImages(category: String): List<ImageItem>

    /**
     * 홈 화면의 '새로운 그림' 섹션에 표시될, 최근에 추가된 항목 목록을 가져옵니다.
     * @param limit 가져올 최대 개수
     * @return ImageItem 리스트
     */
    suspend fun getNewImages(limit: Int): List<ImageItem>

    /**
     * 콘텐츠 기반 추천 로직에 따라, 사용자의 선호 테마와 일치하는 그림 목록을 가져옵니다.
     * @param favoriteThemes 사용자의 선호 테마 목록
     * @param completedImageIds 중복 추천을 방지하기 위한, 이미 완성한 그림 ID 목록
     * @param limit 가져올 최대 개수
     * @return ImageItem 리스트
     */
    suspend fun getRecommendationsByThemes(
        favoriteThemes: List<String>,
        completedImageIds: List<String>,
        limit: Int
    ): List<ImageItem>

    /**
     * 특정 ID를 가진 그림의 상세 데이터(ColoringData)를 가져옵니다.
     * @param dataUrl 상세 데이터가 저장된 URL
     * @return ColoringData
     */
    suspend fun getColoringData(dataUrl: String): ColoringData?

}