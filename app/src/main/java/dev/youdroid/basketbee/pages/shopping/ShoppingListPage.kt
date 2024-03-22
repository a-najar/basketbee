package dev.youdroid.basketbee.pages.shopping

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CloudSync
import androidx.compose.material.icons.outlined.DeleteForever
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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.youdroid.basketbee.LocalNavController
import dev.youdroid.basketbee.NavigationScreen
import dev.youdroid.basketbee.R
import dev.youdroid.basketbee.ui.items.AddInputBoxWithAction
import dev.youdroid.basketbee.ui.items.CheckableListItem
import dev.youdroid.basketbee.ui.theme.BasketBeeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListPage(viewModel: ShoppingListViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    Scaffold(topBar = {
        Column {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name)
                    )
                },
                actions = {
                    IconButton(
                        onClick = { }) {
                        Icon(Icons.Outlined.CloudSync, contentDescription = null)
                    }
                }
            )
            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.onPrimary)
        }
    }

    ) {
        ShoppingListPageContent(
            modifier = Modifier
                .fillMaxHeight()
                .padding(it),
            navController = navController,
            viewModel = viewModel
        )
    }
}

@Composable
fun ShoppingListPageContent(
    modifier: Modifier = Modifier,
    navController: NavController? = null,
    viewModel: ShoppingListViewModel
) {
    val state by viewModel.state.collectAsState()
    Column {
        ShoppingListItems(
            modifier = modifier.weight(1f), state.items,
            navController = navController,
            onDelete = viewModel::deleteItem,
            onCheckedChange = viewModel::updateStatus
        )
        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.onPrimary)
        AddInputBoxWithAction(
            Modifier.padding(16.dp),
            placeHolder = stringResource(id = R.string.input_text_placeholder_name),
            label = stringResource(id = R.string.input_text_label_shoppingList)
        ) {
            if (it.isEmpty()) return@AddInputBoxWithAction
            viewModel.submitNewItem(it)
        }
    }
}

@Composable
private fun ShoppingListItems(
    modifier: Modifier,
    itemsList: List<ItemList>,
    navController: NavController?,
    onDelete: (Int) -> Unit,
    onCheckedChange: (id: Int, isChecked: Boolean) -> Unit
) {
    LazyColumn(
        modifier,
        contentPadding = PaddingValues(bottom = 100.dp, top = 24.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(itemsList.size) { index ->
            CheckableListItem(
                modifier = Modifier.clickable {
                    navController?.navigate(
                        NavigationScreen.CategoriesScreen.route.replace(
                            "{shoppingListId}",
                            itemsList[index].id.toString()
                        )
                    )
                },
                title = itemsList[index].item,
                subtitle = itemsList[index].createDate,
                endIcon = {
                    IconButton(onClick = { onDelete(itemsList[index].id) }) {
                        Icon(Icons.Outlined.DeleteForever, contentDescription = null)
                    }
                },
                checked = itemsList[index].completed,
                onCheckedChange = { onCheckedChange(itemsList[index].id, it) }
            )
        }
    }
}


@Preview(
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    device = Devices.NEXUS_5X
)
@Composable
private fun ShoppingListPrev() {
    BasketBeeTheme {
        ShoppingListPage()
    }
}