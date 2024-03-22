package dev.youdroid.basketbee.domain.usecases

import dev.youdroid.basketbee.domain.ShoppingList
import dev.youdroid.basketbee.domain.repositories.ShoppingListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetShoppingListUseCase @Inject constructor(private val shoppingListRepository: ShoppingListRepository) :
        () -> Flow<List<ShoppingList>> {
    override fun invoke(): Flow<List<ShoppingList>> {
        return shoppingListRepository.getShoppingListItems()
    }
}