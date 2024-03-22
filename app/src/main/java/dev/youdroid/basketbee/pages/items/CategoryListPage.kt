package dev.youdroid.basketbee.pages.items

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import dev.youdroid.basketbee.R
import dev.youdroid.basketbee.ui.items.AddInputBoxWithAction
import dev.youdroid.basketbee.ui.items.CheckableListItem
import dev.youdroid.basketbee.ui.theme.BasketBeeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryListPage(viewModel: CategoryListViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    val navController = LocalNavController.current
    Scaffold(topBar = {
        Column {
            TopAppBar(navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Outlined.ArrowBack, contentDescription = null)
                }
            }, title = { Text(text = state.categoryName) })
            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.onPrimary)
        }
    }) {
        PageContent(
            modifier = Modifier
                .padding(it)
                .fillMaxHeight(),
            state = state,
            onAdded = viewModel::submitItem,
            onChecked = viewModel::onCheckedChanged
        )
    }
}

@Composable
fun PageContent(
    modifier: Modifier,
    state: CategoryListPageState,
    onAdded: (String) -> Unit,
    onChecked: (Int, Boolean) -> Unit
) {
    Column(modifier) {
        ItemsList(
            modifier = Modifier.weight(1f), items = state.items, onChecked = onChecked
        )
        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.onPrimary)
        AddInputBoxWithAction(
            Modifier
                .padding(16.dp)
                .imePadding(),
            placeHolder = stringResource(id = R.string.input_text_placeholder_name),
            label = stringResource(id = R.string.input_text_label_itemName),
            onTextChanged = onAdded
        )
    }
}

@Composable
fun ItemsList(modifier: Modifier = Modifier, items: List<Item>, onChecked: (Int, Boolean) -> Unit) {
    LazyColumn(modifier) {
        items(items.size) { index ->
            CheckableListItem(title = items[index].name,
                checked = items[index].completed,
                onCheckedChange = {
                    onChecked(items[index].id, it)
                })
        }
    }
}

@Preview
@Composable
private fun CategoryListPrev() {
    BasketBeeTheme {
        CategoryListPage()
    }
}