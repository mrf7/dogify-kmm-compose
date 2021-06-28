package com.example.shared

import com.example.shared.responsemodel.DogServiceKtor

/**
 * Singleton to build and provide dependencies. In a larger/real app this would be replaced with a proper DI framework
 */
object ServiceLocator {
    val dogService: DogServiceKtor by lazy {
        DogServiceKtor()
    }
}