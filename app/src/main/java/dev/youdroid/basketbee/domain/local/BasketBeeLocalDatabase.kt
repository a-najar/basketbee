package dev.youdroid.basketbee.domain.local

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import dev.youdroid.basketbee.domain.ShoppingList
import dev.youdroid.basketbee.domain.ShoppingListCategory
import dev.youdroid.basketbee.domain.ShoppingListCategoryItem
import dev.youdroid.basketbee.domain.repositories.CategoriesRepository
import dev.youdroid.basketbee.domain.repositories.ShoppingListCategoryItemsRepository
import dev.youdroid.basketbee.domain.repositories.ShoppingListRepository
import java.io.File


@Database(
    entities = [
        ShoppingList::class,
        ShoppingListCategory::class,
        ShoppingListCategoryItem::class
    ],
    version = 4,
    autoMigrations = [AutoMigration(3, 4)]
)
abstract class BasketBeeLocalDatabase : RoomDatabase() {


    abstract fun shoppingListRepository(): ShoppingListRepository

    abstract fun categoriesRepository(): CategoriesRepository

    abstract fun shoppingListCategoryItemsRepository(): ShoppingListCategoryItemsRepository

    fun getDatabaseBackup(context: Context): File {
        return context.getDatabasePath(BasketBeeLocalDatabase::class.java.simpleName)
    }
}