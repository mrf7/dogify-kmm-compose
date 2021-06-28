package com.example.shared.responsemodel

import kotlinx.serialization.Serializable

@Serializable
data class ImageResult(val status: String, val message: String)