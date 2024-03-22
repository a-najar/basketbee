package dev.youdroid.basketbee

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.youdroid.basketbee.domain.usecases.FetchCategoriesForFirstTimeUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val fetchCategoriesForFirstTimeUseCase: FetchCategoriesForFirstTimeUseCase) :
    ViewModel() {
    fun setup() {
        Log.d(
            MainActivityViewModel::class.java.simpleName,
            Firebase.auth.currentUser.toString()
        )
        if (Firebase.auth.currentUser == null) {
            Firebase.auth
                .signInAnonymously()
                .addOnSuccessListener {
                    Log.d(
                        MainActivityViewModel::class.java.simpleName,
                        it.user.toString()
                    )
                }
        }
        getCategories()
    }

    private fun getCategories() = viewModelScope.launch {
        runCatching { fetchCategoriesForFirstTimeUseCase() }
            .onSuccess { }
            .onFailure { it.printStackTrace() }
    }
}