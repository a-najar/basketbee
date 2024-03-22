package dev.youdroid.basketbee.domain.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dev.youdroid.basketbee.domain.ShoppingListCategory
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoriesRepository {
    @Insert
    suspend fun insert(vararg shoppingListCategory: ShoppingListCategory)

    @Update
    suspend fun update(vararg shoppingListCategory: ShoppingListCategory)

    @Query("select * from shopping_list_categories where id=:id")
    suspend fun selectById(id: Int): ShoppingListCategory

    @Query("select * from shopping_list_categories order by id desc")
    fun select(): Flow<List<ShoppingListCategory>>

    @Query(
        "SELECT shopping_list_categories.*, COUNT(shopping_list_category_items.id) AS itemCount " +
                "FROM shopping_list_categories " +
                "LEFT JOIN shopping_list_category_items ON shopping_list_categories.id = shopping_list_category_items.shoppingListCategoryId " +
                "AND shopping_list_category_items.shoppingListId = :shoppingListId " +
                "GROUP BY shopping_list_categories.id order by itemCount desc"
    )
    fun selectByShoppingListId(shoppingListId: Int): Flow<List<ShoppingListCategory>>
}