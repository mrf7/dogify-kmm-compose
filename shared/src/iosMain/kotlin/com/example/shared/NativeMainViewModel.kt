package com.example.shared

import com.example.shared.domain.DogBreed
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class NativeMainViewModel(private val dogDataUpdate: (MainScreenData) -> Unit) {
    private val dogService = ServiceLocator.dogService
    private val scope = MainScope()

    fun observe() {
        scope.launch {
            try {
                dogDataUpdate(MainScreenData(emptyList(), true))
                val breeds = dogService.getBreeds().message.map {
                    DogBreed(it, dogService.getImage(it).message)
                }
                dogDataUpdate(MainScreenData(breeds))
            } catch (e: Exception) {
                //
            }
        }
    }
    
    fun unobserve() {
        scope.cancel()
    }
}
data class MainScreenData(
    val dogBreeds: List<DogBreed>,
    val showLoading: Boolean = false,
)
