// ui/navigation/AppNavigation.kt
package com.pjmy.project.peaceofmind.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pjmy.project.peaceofmind.ui.home.HomeScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.pjmy.project.peaceofmind.ui.home.HomeViewModel
import com.pjmy.project.peaceofmind.ui.detail.ImageDetailScreen // Anvis: 상세 화면 import 추가
import com.pjmy.project.peaceofmind.data.model.ColoringMode

// Anvis: 각 화면의 주소를 정의하는 sealed class입니다. 이렇게 하면 오타를 방지할 수 있습니다.
sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Detail : Screen("detail/{imageId}") {
        fun createRoute(imageId: String) = "detail/$imageId"
    }
    // Anvis: 색칠 화면 경로 추가
    data object Coloring : Screen("coloring/{imageId}/{mode}") {
        fun createRoute(imageId: String, mode: ColoringMode) = "coloring/$imageId/${mode.name}"
    }
    // TODO: 나중에  커뮤니티 화면 등을 여기에 추가

}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // NavHost는 화면들이 보여질 컨테이너 역할을 합니다.
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route // 앱 시작 시 보여줄 첫 화면
    ) {
        // 홈 화면 라우트 설정
        composable(Screen.Home.route) {
            // Anvis: 바로 이 부분입니다! hiltViewModel() 한 줄이면 끝납니다.
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                viewModel = homeViewModel,
                onNavigateToDetail = { imageId ->
                    navController.navigate(Screen.Detail.createRoute(imageId))
                },
                onNavigateToGallery = { /* TODO */ }
            )
        }

// 이미지 상세 화면 라우트
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("imageId") { type = NavType.StringType })
        ) {
            ImageDetailScreen(
                // Anvis: Hilt가 ViewModel을 자동으로 주입하고,
                // onNavigateToColoring 람다를 통해 내비게이션 로직을 전달합니다.
                onNavigateToColoring = { imageId, mode ->
                    navController.navigate(Screen.Coloring.createRoute(imageId, mode))
                }
            )
        }

        // 색칠 화면 라우트
        composable(
            route = Screen.Coloring.route,
            arguments = listOf(
                navArgument("imageId") { type = NavType.StringType },
                navArgument("mode") { type = NavType.EnumType(ColoringMode::class.java) }
            )
        ) { backStackEntry ->
            val imageId = backStackEntry.arguments?.getString("imageId")
            val mode = backStackEntry.arguments?.getSerializable("mode") as ColoringMode?
            // TODO: ColoringScreen(imageId = imageId, coloringMode = mode) 컴포저블 배치
        }
    }
}