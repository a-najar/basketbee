package dev.youdroid.basketbee.domain.repositories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.youdroid.basketbee.domain.ShoppingList
import kotlinx.coroutines.flow.Flow


@Dao
interface ShoppingListRepository {

    @Insert
    suspend fun insertShoppingListItem(vararg shoppingList: ShoppingList)

    @Query("select * from shopping_lists order by id and completed=0 desc")
    fun getShoppingListItems(): Flow<List<ShoppingList>>

    @Query("select * from shopping_lists where id=:id")
    suspend fun findShoppingListById(id: Int): ShoppingList

    @Update
    suspend fun updateItem(vararg shoppingList: ShoppingList)

    @Delete
    suspend fun deleteShoppingListItem(shoppingList: ShoppingList)
}