package dev.youdroid.basketbee.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.youdroid.basketbee.domain.local.BasketBeeLocalDatabase
import dev.youdroid.basketbee.domain.repositories.CategoriesRepository
import dev.youdroid.basketbee.domain.repositories.ShoppingListCategoryItemsRepository
import dev.youdroid.basketbee.domain.repositories.ShoppingListRepository
import dev.youdroid.basketbee.domain.repositories.SyncRepository
import dev.youdroid.basketbee.domain.repositories.SyncRepositoryImplantation
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object BasketBeeModule {


    @Singleton
    @Provides
    fun provideBasketBeeLocalDatabase(@ApplicationContext applicationContext: Context): BasketBeeLocalDatabase {
        return Room.databaseBuilder(
            applicationContext,
            BasketBeeLocalDatabase::class.java,
            BasketBeeLocalDatabase::class.java.simpleName
        ).build()
    }

    @Provides
    fun provideShoppingListRepository(basketBeeLocalDatabase: BasketBeeLocalDatabase): ShoppingListRepository {
        return basketBeeLocalDatabase.shoppingListRepository()
    }

    @Provides
    fun provideCategoriesRepository(basketBeeLocalDatabase: BasketBeeLocalDatabase): CategoriesRepository {
        return basketBeeLocalDatabase.categoriesRepository()
    }


    @Provides
    fun provideShoppingListCategoryItemRepository(basketBeeLocalDatabase: BasketBeeLocalDatabase): ShoppingListCategoryItemsRepository {
        return basketBeeLocalDatabase.shoppingListCategoryItemsRepository()
    }


    @Provides
    fun firebaseDatabase(): FirebaseDatabase = Firebase.database


    @Provides
    fun provideSyncRepository(firebaseDatabase: FirebaseDatabase): SyncRepository {
        return SyncRepositoryImplantation(firebaseDatabase)
    }

}