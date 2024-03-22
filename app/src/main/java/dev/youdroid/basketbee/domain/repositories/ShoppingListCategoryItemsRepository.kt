package dev.youdroid.basketbee.domain.repositories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.youdroid.basketbee.domain.ShoppingListCategoryItem
import kotlinx.coroutines.flow.Flow


@Dao
interface ShoppingListCategoryItemsRepository {

    @Insert
    suspend fun create(vararg shoppingListCategoryItem: ShoppingListCategoryItem)

    @Delete
    suspend fun delete(vararg shoppingListCategoryItem: ShoppingListCategoryItem)

    @Query("select * from shopping_list_category_items " +
            "where shoppingListId=:shoppingListId " +
            "and shoppingListCategoryId=:shoppingListCategoryId order by completed asc")
    fun getShoppingListCategoryItems(
        shoppingListId: Int,
        shoppingListCategoryId: Int
    ): Flow<List<ShoppingListCategoryItem>>

    @Query("select * from shopping_list_category_items where id=:id")
    suspend fun findById(id: Int): ShoppingListCategoryItem

    @Update
    suspend fun update(vararg shoppingListCategoryItem: ShoppingListCategoryItem)
}