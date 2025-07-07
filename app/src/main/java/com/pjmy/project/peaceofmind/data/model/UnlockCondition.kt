// data/model/UnlockCondition.kt
package com.pjmy.project.peaceofmind.data.model

import kotlinx.serialization.Serializable
//그림의 잠금 해제 조건을 명확하게 모델링하는 Sealed Class입니다. 더 이상의 변경은 필요 없습니다
@Serializable
sealed class UnlockCondition {
    @Serializable
    data object Free : UnlockCondition()
    @Serializable
    data object WatchAd : UnlockCondition()
    @Serializable
    data class Premium(val productId: String) : UnlockCondition()
}