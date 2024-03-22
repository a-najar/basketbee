package dev.youdroid.basketbee.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "shopping_lists")
data class ShoppingList(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "created_at") val createdAt: String? = null,
    @ColumnInfo(name = "completed") val completed: Boolean = false
)


@Entity(tableName = "shopping_list_categories")
data class ShoppingListCategory(
    @PrimaryKey val id: Int? = null,
    @ColumnInfo(name = "nameAr") val nameAr: String? = null,
    @ColumnInfo(name = "nameEn") val nameEn: String? = null,
    @ColumnInfo(name = "imageUrl") val imageUrl: String? = null,
    @ColumnInfo(name = "itemCount") val itemCount: Int? = null
)


@Entity(
    tableName = "shopping_list_category_items", foreignKeys = [ForeignKey(
        entity = ShoppingList::class,
        parentColumns = ["id"],
        childColumns = ["shoppingListId"],
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = ShoppingListCategory::class,
        parentColumns = ["id"],
        childColumns = ["shoppingListCategoryId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ShoppingListCategoryItem(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "shoppingListId") val shoppingListId: Int? = null,
    @ColumnInfo(name = "shoppingListCategoryId") val shoppingListCategoryId: Int? = null,
    @ColumnInfo(name = "completed") val completed: Boolean = false,
    @ColumnInfo(name = "name") val name: String? = null
)