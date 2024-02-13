package com.nyan.twtest.domain.entity

data class HeroEntity(
    val id: Int,
    val name: String?,
    val photo: Int?,
    val rating: Float = 0f,
)