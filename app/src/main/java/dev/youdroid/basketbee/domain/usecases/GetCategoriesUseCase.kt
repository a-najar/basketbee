package dev.youdroid.basketbee.domain.usecases

import dev.youdroid.basketbee.domain.ShoppingListCategory
import dev.youdroid.basketbee.domain.repositories.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) : (Int?) -> Flow<List<ShoppingListCategory>> {
    override fun invoke(shoppingListId: Int?): Flow<List<ShoppingListCategory>> {
        if (shoppingListId == null) throw Exception("shoppingListId is missing please don\'t send null id")
        return categoriesRepository.selectByShoppingListId(shoppingListId)
    }
}