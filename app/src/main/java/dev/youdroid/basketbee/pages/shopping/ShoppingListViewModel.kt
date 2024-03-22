package dev.youdroid.basketbee.pages.shopping

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.youdroid.basketbee.domain.ShoppingList
import dev.youdroid.basketbee.domain.usecases.CreateShoppingListItem
import dev.youdroid.basketbee.domain.usecases.DeleteShoppingListUseCase
import dev.youdroid.basketbee.domain.usecases.GetShoppingListUseCase
import dev.youdroid.basketbee.domain.usecases.UpdateCompletionOfShoppingListUseCase
import dev.youdroid.basketbee.pages.categoreis.plusAssign
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject


@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    getShoppingListUseCase: GetShoppingListUseCase,
    private val createShoppingListItem: CreateShoppingListItem,
    private val updateCompletionOfShoppingListUseCase: UpdateCompletionOfShoppingListUseCase,
    private val deleteShoppingListUseCase: DeleteShoppingListUseCase
) :
    ViewModel() {

    private val _state = MutableStateFlow(ShoppingListPageState())

    val state: StateFlow<ShoppingListPageState> = _state


    init {
        getShoppingListUseCase()
            .onEach { _state += ShoppingListPageState(it.mapToItemList()) }
            .catch { it.printStackTrace() }
            .launchIn(viewModelScope)
    }


    fun submitNewItem(item: String) = viewModelScope.launch {
        runCatching { createShoppingListItem(item) }
            .onSuccess { }
            .onFailure { }
    }

    fun updateStatus(id: Int, checked: Boolean) = viewModelScope.launch {
        runCatching { updateCompletionOfShoppingListUseCase(id, checked) }
            .onSuccess { }
            .onFailure { it.printStackTrace() }
    }

    fun deleteItem(id: Int) = viewModelScope.launch {
        runCatching { deleteShoppingListUseCase(id) }
            .onSuccess { }
            .onFailure { it.printStackTrace() }
    }

}

private fun List<ShoppingList>.mapToItemList(): List<ItemList> {
    return map { ItemList(it.id ?: 0, it.name.orEmpty(), it.createdAt.orEmpty(), it.completed) }
}


data class ShoppingListPageState(val items: List<ItemList> = emptyList())

data class ItemList(val id: Int, val item: String, val createDate: String, val completed: Boolean)

