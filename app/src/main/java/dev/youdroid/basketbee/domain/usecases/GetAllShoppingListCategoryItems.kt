package dev.youdroid.basketbee.domain.usecases

import dev.youdroid.basketbee.domain.ShoppingListCategoryItem
import dev.youdroid.basketbee.domain.repositories.ShoppingListCategoryItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllShoppingListCategoryItems @Inject constructor(
    private val shoppingListCategoryItemsRepository: ShoppingListCategoryItemsRepository
) : (Int?, Int?) -> Flow<List<ShoppingListCategoryItem>> {

    override fun invoke(
        shoppingListId: Int?,
        shoppingListCategoryItemId: Int?
    ): Flow<List<ShoppingListCategoryItem>> {
        if (shoppingListId == null) throw Exception("Cant pass null for shoppingListId")
        if (shoppingListCategoryItemId == null) throw Exception("Cant pass null for shoppingListCategoryItemId")
        return shoppingListCategoryItemsRepository.getShoppingListCategoryItems(
            shoppingListId,
            shoppingListCategoryItemId
        )
    }
}