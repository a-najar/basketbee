package dev.youdroid.basketbee.domain.usecases

import dev.youdroid.basketbee.domain.repositories.CategoriesRepository
import dev.youdroid.basketbee.domain.repositories.SyncRepository
import javax.inject.Inject

class FetchCategoriesForFirstTimeUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
    private val syncRepository: SyncRepository
) : suspend () -> Unit {

    override suspend fun invoke() {
        val categories = syncRepository.getCategories()
        categoriesRepository.insert(*categories.toTypedArray())
    }
}