package dev.youdroid.basketbee.domain.usecases

import dev.youdroid.basketbee.domain.ShoppingList
import dev.youdroid.basketbee.domain.repositories.ShoppingListRepository
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

class CreateShoppingListItem @Inject constructor(private val shoppingListRepository: ShoppingListRepository) :
    suspend (String) -> Unit {
    override suspend fun invoke(item: String) {
        shoppingListRepository.insertShoppingListItem(
            ShoppingList(
                name = item,
                createdAt = getCurrentDate()
            )
        )
    }

    private fun getCurrentDate(): String {
        return SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.SHORT, SimpleDateFormat.SHORT)
            .format(Calendar.getInstance().time)
    }
}