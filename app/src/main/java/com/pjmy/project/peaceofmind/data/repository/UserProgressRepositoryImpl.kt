package com.pjmy.project.peaceofmind.data.repository

import com.pjmy.project.peaceofmind.data.model.UserProgress

class UserProgressRepositoryImpl : UserProgressRepository {
    // TODO: 나중에 실제 구현으로 교체해야 함
    override suspend fun getContinueColoringItems(limit: Int): List<UserProgress> {
        return emptyList()
    }
    // TODO: 나중에 실제 구현으로 교체해야 함
    override suspend fun getAllCompletedThemes(userId: String): List<String> {
        return emptyList()
    }
    // TODO: 나중에 실제 구현으로 교체해야 함
    override suspend fun getAllCompletedImageIds(userId: String): List<String> {
        return emptyList()
    }
}