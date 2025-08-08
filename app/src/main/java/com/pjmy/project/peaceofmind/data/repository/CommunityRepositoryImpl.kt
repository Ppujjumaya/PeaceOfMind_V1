package com.pjmy.project.peaceofmind.data.repository

import com.pjmy.project.peaceofmind.data.model.SharedArtwork

class CommunityRepositoryImpl : CommunityRepository {
    // TODO: 나중에 실제 구현으로 교체해야 함
    override suspend fun getTopTenArtworks(): List<SharedArtwork> {
        return emptyList()
    }
    // TODO: 나중에 실제 구현으로 교체해야 함
    override suspend fun getSharedArtworks(sortBy: String): List<SharedArtwork> {
        return emptyList()
    }
    // TODO: 나중에 실제 구현으로 교체해야 함
    override suspend fun likeArtwork(artworkId: String, userId: String) {
        // Do nothing
    }
}