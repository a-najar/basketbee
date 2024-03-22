package dev.youdroid.basketbee.domain.usecases

import dev.youdroid.basketbee.domain.repositories.ShoppingListCategoryItemsRepository
import javax.inject.Inject

class UpdateShoppingListCategoryItem @Inject constructor(
    private val shoppingListCategoryItemsRepository: ShoppingListCategoryItemsRepository
) : suspend (Int, Boolean) -> Unit {
    override suspend fun invoke(itemId: Int, isChecked: Boolean) {
        val item = shoppingListCategoryItemsRepository.findById(itemId)
        shoppingListCategoryItemsRepository.update(item.copy(completed = isChecked))
    }
}