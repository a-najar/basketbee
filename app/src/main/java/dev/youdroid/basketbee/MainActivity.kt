package dev.youdroid.basketbee


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.youdroid.basketbee.NavigationScreen.CategoriesScreen
import dev.youdroid.basketbee.NavigationScreen.CategoryList
import dev.youdroid.basketbee.NavigationScreen.ShoppingList
import dev.youdroid.basketbee.pages.categoreis.CategoriesPage
import dev.youdroid.basketbee.pages.items.CategoryListPage
import dev.youdroid.basketbee.pages.shopping.ShoppingListPage

import dev.youdroid.basketbee.ui.theme.BasketBeeTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainActivityViewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewModel.setup()
        setContent {
            BasketBeeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val screens = listOf(
        CategoriesScreen,
        ShoppingList,
        CategoryList
    )
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(
            navController = navController,
            startDestination = ShoppingList.route
        ) {
            screens.forEach {
                when (it) {
                    is CategoriesScreen -> composable(it.route) { CategoriesPage() }
                    is ShoppingList -> composable(it.route) { ShoppingListPage() }
                    is CategoryList -> composable(it.route) { CategoryListPage() }
                }
            }

        }
    }

}


sealed class NavigationScreen(val route: String, val title: Int) {
    data object CategoriesScreen :
        NavigationScreen("categories/{shoppingListId}", R.string.text_screen_title_categories)

    data object ShoppingList :
        NavigationScreen("shoppingList", R.string.text_screen_title_shopping_list)

    data object CategoryList :
        NavigationScreen(
            "categoryList/{name}/{shoppingListId}/{categoryId}",
            R.string.text_screen_title_categories
        )

}

val LocalNavController = compositionLocalOf<NavController> { error("No NavController found!") }


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BasketBeeTheme {
        MainScreen()
    }
}