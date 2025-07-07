// data/repository/CommunityRepository.kt

import com.pjmy.project.peaceofmind.data.model.SharedArtwork

//다른 사용자들이 공유한 작품, '좋아요' 수, 랭킹 등 커뮤니티 관련 Firestore 데이터에 접근하는 역할을 합니다.
// Anvis: 이 인터페이스의 실제 구현체는 Firestore와 직접 통신하게 됩니다.
interface CommunityRepository {

    /**
     * 홈 화면의 '명예의 전당'에 표시될, '좋아요'를 가장 많이 받은 작품 목록을 가져옵니다.
     * @return SharedArtwork 리스트
     */
    suspend fun getTopTenArtworks(): List<SharedArtwork>

    /**
     * 커뮤니티 갤러리 화면에 표시될 공유 작품 목록을 가져옵니다. (정렬 기능 포함)
     * @param sortBy 정렬 기준 (예: "latest", "popular")
     * @return SharedArtwork 리스트
     */
    suspend fun getSharedArtworks(sortBy: String): List<SharedArtwork>

    /**
     * 특정 작품에 '좋아요'를 누릅니다. (중복 방지 로직 포함)
     * @param artworkId '좋아요'를 누를 작품의 ID
     * @param userId '좋아요'를 누른 사용자의 ID
     */
    suspend fun likeArtwork(artworkId: String, userId: String)
}