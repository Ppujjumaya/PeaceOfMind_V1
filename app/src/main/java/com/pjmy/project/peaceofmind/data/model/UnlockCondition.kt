// data/model/UnlockCondition.kt
package com.pjmy.project.peaceofmind.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@Serializable
@JsonClassDiscriminator("type") // Anvis: 이 한 줄이 핵심입니다! "type" 필드를 보고 구분하라고 지시.
sealed class UnlockCondition {

    @Serializable
    @SerialName("Free") // Anvis: JSON의 "type" 필드 값이 "Free"일 때 이 객체로 변환
    data object Free : UnlockCondition()

    @Serializable
    @SerialName("WatchAd") // Anvis: JSON의 "type" 필드 값이 "WatchAd"일 때 이 객체로 변환
    data object WatchAd : UnlockCondition()

    @Serializable
    @SerialName("Premium") // Anvis: JSON의 "type" 필드 값이 "Premium"일 때 이 객체로 변환
    data class Premium(val productId: String) : UnlockCondition()
}