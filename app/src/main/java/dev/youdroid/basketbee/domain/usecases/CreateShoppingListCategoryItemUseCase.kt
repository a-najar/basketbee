package dev.youdroid.basketbee.domain.usecases

import dev.youdroid.basketbee.domain.ShoppingListCategoryItem
import dev.youdroid.basketbee.domain.repositories.ShoppingListCategoryItemsRepository
import javax.inject.Inject

class CreateShoppingListCategoryItemUseCase @Inject constructor(
    private val shoppingListCategoryItemsRepository: ShoppingListCategoryItemsRepository
) : suspend (Int?, Int?, String) -> Unit {

    override suspend fun invoke(shoppingListItemId: Int?, categoryItemId: Int?, name: String) {
        shoppingListCategoryItemsRepository.create(
            ShoppingListCategoryItem(
                name = name,
                shoppingListCategoryId = categoryItemId,
                shoppingListId = shoppingListItemId
            )
        )
    }
}