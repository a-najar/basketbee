package dev.youdroid.basketbee.domain.usecases

import dev.youdroid.basketbee.domain.repositories.ShoppingListRepository
import javax.inject.Inject

class DeleteShoppingListUseCase @Inject constructor(private val shoppingListRepository: ShoppingListRepository) :
    suspend (Int) -> Unit {
    override suspend fun invoke(id: Int) {
        val shoppingListItem = shoppingListRepository.findShoppingListById(id)
        shoppingListRepository.deleteShoppingListItem(shoppingListItem)
    }
}