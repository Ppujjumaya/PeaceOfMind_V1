// MainActivity.kt
package com.pjmy.project.peaceofmind

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.pjmy.project.peaceofmind.data.repository.ImageRepositoryImpl
import com.pjmy.project.peaceofmind.ui.home.HomeScreen
//import com.pjmy.project.peaceofmind.ui.home.HomeViewModel
import com.pjmy.project.peaceofmind.ui.navigation.AppNavigation
import com.pjmy.project.peaceofmind.ui.theme.PeaceOfMindTheme

import com.pjmy.project.peaceofmind.data.repository.CommunityRepository
import com.pjmy.project.peaceofmind.data.repository.UserProgressRepository
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Anvis: Hilt를 위해 이 어노테이션을 추가해야 합니다.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PeaceOfMindTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

// Anvis: 임시로 사용할 더미 Repository들입니다. 나중에 실제 구현으로 교체해야 합니다.
class DummyUserProgressRepository : UserProgressRepository {
    override suspend fun getContinueColoringItems(limit: Int): List<com.pjmy.project.peaceofmind.data.model.UserProgress> {
        return emptyList()
    }

    override suspend fun getAllCompletedThemes(userId: String): List<String> {
        return emptyList()
    }

    override suspend fun getAllCompletedImageIds(userId: String): List<String> {
        return emptyList()
    }
}

class DummyCommunityRepository : CommunityRepository {
    override suspend fun getTopTenArtworks(): List<com.pjmy.project.peaceofmind.data.model.SharedArtwork> {
        return emptyList()
    }

    override suspend fun getSharedArtworks(sortBy: String): List<com.pjmy.project.peaceofmind.data.model.SharedArtwork> {
        return emptyList()
    }

    override suspend fun likeArtwork(artworkId: String, userId: String) {
        // Do nothing
    }
}