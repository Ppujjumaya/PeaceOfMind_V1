// data/repository/ImageRepositoryImpl.kt
package com.pjmy.project.peaceofmind.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
//import com.google.firebase.firestore.toObject
import com.pjmy.project.peaceofmind.data.model.ColoringData
import com.pjmy.project.peaceofmind.data.model.ImageItem
import com.pjmy.project.peaceofmind.data.model.UnlockCondition
import kotlinx.coroutines.tasks.await
import android.util.Log

private const val TAG = "ImageRepositoryImpl"


class ImageRepositoryImpl(
    private val firestore: FirebaseFirestore
) : ImageRepository {

    /*
    *  Firestore의 toObject() 함수는 이 어노테이션들을 직접 읽지 않고, Firestore 자체의 규칙에 따라 데이터를 객체로 변환합니다.
        문제는, Firestore의 기본 변환 기능이 UnlockCondition 같은 복잡한 sealed class 구조를 자동으로 처리하지 못한다는 것입니다.
    * */
    override suspend fun getNewImages(limit: Int): List<ImageItem> {
        return try {
            val snapshot = firestore.collection("images")
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .limit(limit.toLong())
                .get()
                .await()

            // Anvis: toObject() 대신, 문서를 수동으로 파싱하여 ImageItem으로 변환합니다.
            val imageItems = snapshot.documents.mapNotNull { document ->
                // unlockCondition 맵을 먼저 가져옵니다.
                val unlockConditionMap = document.get("unlockCondition") as? Map<String, Any>
                val type = unlockConditionMap?.get("type") as? String

                // 'type'에 따라 올바른 UnlockCondition 객체를 생성합니다.
                val unlockCondition = when (type) {
                    "Free" -> UnlockCondition.Free
                    "WatchAd" -> UnlockCondition.WatchAd
                    "Premium" -> {
                        val productId = unlockConditionMap["productId"] as? String ?: ""
                        UnlockCondition.Premium(productId)
                    }
                    else -> null // 알 수 없는 타입이면 null 처리
                }

                // unlockCondition이 null이 아닐 경우에만 ImageItem 객체를 생성합니다.
                if (unlockCondition != null) {
                    ImageItem(
                        id = document.id,
                        title = document.getString("title") ?: "",
                        theme = document.getString("theme") ?: "",
                        previewUrl = document.getString("previewUrl") ?: "",
                        dataUrl = document.getString("dataUrl") ?: "",
                        createdAt = document.getLong("createdAt") ?: 0L,
                        tags = document.get("tags") as? List<String> ?: emptyList(),
                        artistName = document.getString("artistName"),
                        artistUrl = document.getString("artistUrl"),
                        unlockCondition = unlockCondition
                    )
                } else {
                    null
                }
            }
            Log.d(TAG, "getNewImages success: Fetched ${imageItems.size} items.")
            imageItems

        } catch (e: Exception) {
            Log.e(TAG, "Error fetching new images", e)
            emptyList()
        }
    }

    override suspend fun getAllImages(category: String): List<ImageItem> {
        // TODO: 나중에 실제 구현으로 교체해야 함
        return emptyList()
    }

    override suspend fun getRecommendationsByThemes(
        favoriteThemes: List<String>,
        completedImageIds: List<String>,
        limit: Int
    ): List<ImageItem> {
        // TODO: 나중에 실제 구현으로 교체해야 함
        return emptyList()
    }

    // Anvis: 아래 빠져있던 함수를 추가해주세요.
    override suspend fun getColoringData(dataUrl: String): ColoringData? {
        // TODO: 나중에 실제 구현으로 교체해야 함
        // (Firebase Storage에서 dataUrl을 사용해 JSON을 다운로드하고 파싱하는 로직)
        return null
    }

    // Anvis: 이 코드는 Firestore에 imageId와 일치하는 단 하나의 문서를 직접 요청하게 됩니다.
    override suspend fun getImageById(imageId: String): ImageItem? {
        return try {
            val document = firestore.collection("images")
                .document(imageId) // Anvis: 특정 문서 ID를 직접 지정
                .get()
                .await()

            // Anvis: toObject() 대신 수동으로 파싱하는 동일한 로직을 재사용합니다.
            //       나중에는 이 로직을 별도의 함수로 분리하여 코드 중복을 줄일 수 있습니다.
            if (document.exists()) {
                val unlockConditionMap = document.get("unlockCondition") as? Map<String, Any>
                val type = unlockConditionMap?.get("type") as? String
                val unlockCondition = when (type) {
                    "Free" -> UnlockCondition.Free
                    "WatchAd" -> UnlockCondition.WatchAd
                    "Premium" -> {
                        val productId = unlockConditionMap["productId"] as? String ?: ""
                        UnlockCondition.Premium(productId)
                    }
                    else -> null
                }

                if (unlockCondition != null) {
                    Log.d(TAG, "getImageById success: Found document ${document.id}")
                    ImageItem(
                        id = document.id,
                        title = document.getString("title") ?: "",
                        theme = document.getString("theme") ?: "",
                        previewUrl = document.getString("previewUrl") ?: "",
                        dataUrl = document.getString("dataUrl") ?: "",
                        createdAt = document.getLong("createdAt") ?: 0L,
                        tags = document.get("tags") as? List<String> ?: emptyList(),
                        artistName = document.getString("artistName"),
                        artistUrl = document.getString("artistUrl"),
                        unlockCondition = unlockCondition
                    )
                } else {
                    null
                }
            } else {
                Log.w(TAG, "getImageById: No document found with id $imageId")
                null
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching image by id: $imageId", e)
            null
        }
    }
}