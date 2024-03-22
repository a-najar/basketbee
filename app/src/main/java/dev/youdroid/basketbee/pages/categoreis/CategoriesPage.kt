package dev.youdroid.basketbee.pages.categoreis

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.youdroid.basketbee.LocalNavController
import dev.youdroid.basketbee.NavigationScreen
import dev.youdroid.basketbee.NavigationScreen.CategoryList.route
import dev.youdroid.basketbee.R
import dev.youdroid.basketbee.ui.items.GridItem
import dev.youdroid.basketbee.ui.theme.BasketBeeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesPage(viewModel: CategoriesViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val navController = LocalNavController.current
    Scaffold(topBar = {
        Column {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null)
                    }
                },
                title = { Text(text = stringResource(id = NavigationScreen.CategoriesScreen.title)) })
            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.onPrimary)
        }
    }) {
        LazyVerticalGrid(columns = GridCells.Fixed(3), Modifier.padding(it)) {
            items(state.categories.size) { index ->
                GridItem(
                    icon = state.categories[index].imageUrl,
                    title = state.categories[index].name,
                    subtitle = stringResource(
                        id = R.string.text_items,
                        state.categories[index].itemCount.toString()
                    )
                ) {
                    navController.navigate(
                        route.replace("{name}", state.categories[index].name)
                            .replace("{categoryId}", state.categories[index].id.toString())
                            .replace("{shoppingListId}", viewModel.shoppingListId.toString())
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CategoriesPagePrev() {
    BasketBeeTheme {
        CategoriesPage()
    }
}