package dev.youdroid.basketbee.pages.categoreis

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.youdroid.basketbee.domain.ShoppingListCategory
import dev.youdroid.basketbee.domain.usecases.GetCategoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    getCategoriesUseCase: GetCategoriesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(CategoriesPageState())
    val state: StateFlow<CategoriesPageState> = _state

    val shoppingListId: Int? = savedStateHandle.get<String>("shoppingListId")?.toInt()

    init {
        getCategoriesUseCase(shoppingListId)
            .onEach { _state += CategoriesPageState(it.mapToCategory()) }
            .catch { it.printStackTrace() }
            .launchIn(viewModelScope)
    }
}

private fun List<ShoppingListCategory>.mapToCategory(): List<Category> =
    map {
        Category(
            id = it.id ?: 0,
            name = if (Locale.getDefault().isArabic()) it.nameAr.orEmpty() else it.nameEn.orEmpty(),
            imageUrl = it.imageUrl.orEmpty(),
            itemCount = it.itemCount ?: 0
        )
    }


private fun Locale.isArabic() = language == "ar"

operator fun <T> MutableStateFlow<T>.plusAssign(item: T) {
    tryEmit(item)
}

data class CategoriesPageState(val categories: List<Category> = emptyList())


data class Category(val id: Int, val name: String, val imageUrl: String, val itemCount: Int)