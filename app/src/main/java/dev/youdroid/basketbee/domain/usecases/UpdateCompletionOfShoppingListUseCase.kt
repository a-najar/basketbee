package dev.youdroid.basketbee.domain.usecases

import dev.youdroid.basketbee.domain.repositories.ShoppingListRepository
import javax.inject.Inject

class UpdateCompletionOfShoppingListUseCase @Inject constructor(
    private val shoppingListRepository: ShoppingListRepository
) :
    suspend (Int, Boolean) -> Unit {
    override suspend fun invoke(id: Int, completed: Boolean) {
        val shoppingList = shoppingListRepository.findShoppingListById(id)
        shoppingListRepository.updateItem(shoppingList.copy(completed = completed))
    }
}