package com.example.dogifykmmcompose

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.shared.domain.DogBreed
import com.example.shared.responsemodel.DogServiceKtor
import kotlinx.coroutines.launch

class MainViewModel(private val dogService: DogServiceKtor) : ViewModel() {
    /**
     *  Represents the state of the main screen
     */
    val mainScreenData: LiveData<MainScreenData>
        get() = _mainScreenData

    private val _mainScreenData = MutableLiveData<MainScreenData>()

    init {
        viewModelScope.launch {
            try {
                _mainScreenData.value = MainScreenData(emptyList(), true)
                val breeds = dogService.getBreeds().message.map {
                    DogBreed(it, dogService.getImage(it).message)
                }
                _mainScreenData.value = MainScreenData(breeds)
            } catch (e: Exception) {
                Log.e("MRF", "caught", e)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val dogService: DogServiceKtor) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(dogService) as T
        }

    }
}

data class MainScreenData(
    val dogBreeds: List<DogBreed>,
    val showLoading: Boolean = false,
)

