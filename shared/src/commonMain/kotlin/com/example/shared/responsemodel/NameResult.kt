package com.example.shared.responsemodel

import kotlinx.serialization.Serializable

@Serializable
data class NameResult(val status: String, val message: List<String>)