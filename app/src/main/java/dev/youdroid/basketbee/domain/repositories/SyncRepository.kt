package dev.youdroid.basketbee.domain.repositories


import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dev.youdroid.basketbee.domain.ShoppingListCategory
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

interface SyncRepository {
    suspend fun getCategories(): List<ShoppingListCategory>
}


class SyncRepositoryImplantation @Inject constructor(private val firebaseDatabase: FirebaseDatabase) :
    SyncRepository {
    override suspend fun getCategories(): List<ShoppingListCategory> = suspendCoroutine {
        firebaseDatabase.reference.child("categories")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    it.resumeWith(Result.success(snapshot.children.map { dataSnapShot ->
                        dataSnapShot.getValue(
                            ShoppingListCategory::class.java
                        )!!
                    }))
                }

                override fun onCancelled(error: DatabaseError) {
                    error.toException().printStackTrace()
                }

            })
    }

}