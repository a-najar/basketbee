package dev.youdroid.basketbee.pages.items

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.youdroid.basketbee.domain.ShoppingListCategoryItem
import dev.youdroid.basketbee.domain.usecases.CreateShoppingListCategoryItemUseCase
import dev.youdroid.basketbee.domain.usecases.GetAllShoppingListCategoryItems
import dev.youdroid.basketbee.domain.usecases.UpdateShoppingListCategoryItem
import dev.youdroid.basketbee.pages.categoreis.plusAssign
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val createShoppingListCategoryItemUseCase: CreateShoppingListCategoryItemUseCase,
    private val getAllShoppingListCategoryItems: GetAllShoppingListCategoryItems,
    private val updateShoppingListCategoryItem: UpdateShoppingListCategoryItem
) :
    ViewModel() {

    private val _state = MutableStateFlow(
        CategoryListPageState(
            categoryName = savedStateHandle.get<String>("name").orEmpty()
        )
    )

    val state: StateFlow<CategoryListPageState> = _state

    init {
        getItems()
    }

    private fun getItems() {
        getAllShoppingListCategoryItems(
            savedStateHandle.get<String>("shoppingListId")?.toInt(),
            savedStateHandle.get<String>("categoryId")?.toInt(),
        )
            .onEach { items -> _state += currentState().copy(items = items.mapToItem()) }
            .catch { it.printStackTrace() }
            .launchIn(viewModelScope)
    }

    private fun currentState() = _state.value

    fun submitItem(name: String) = viewModelScope.launch {
        runCatching {
            createShoppingListCategoryItemUseCase(
                savedStateHandle.get<String>("shoppingListId")?.toInt(),
                savedStateHandle.get<String>("categoryId")?.toInt(),
                name
            )
        }
            .onSuccess { }
            .onFailure { it.printStackTrace() }
    }

    fun onCheckedChanged(id: Int, checked: Boolean) = viewModelScope.launch {
        runCatching { updateShoppingListCategoryItem(id, checked) }
            .onSuccess { }
            .onFailure { it.printStackTrace() }
    }

}

private fun List<ShoppingListCategoryItem>.mapToItem(): List<Item> {
    return map {
        Item(
            it.id ?: 0,
            name = it.name.orEmpty(),
            completed = it.completed
        )
    }
}


data class CategoryListPageState(
    val items: List<Item> = emptyList(),
    val categoryName: String = ""
)

data class Item(
    val id: Int,
    val name: String,
    val completed: Boolean
)
