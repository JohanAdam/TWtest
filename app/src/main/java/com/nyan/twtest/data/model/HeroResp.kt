package com.nyan.twtest.data.model

import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName
import com.nyan.twtest.domain.entity.HeroEntity

data class HeroResp (
    @SerializedName("id") val id: Int,
    @SerializedName("heroName") val heroName: String? = null,
    @SerializedName("photo") val photoRes: Int? = null,
    @SerializedName("rating") val rating: Int = 0,
)

fun HeroResp.mapToDomain(): HeroEntity {
    return HeroEntity(
        id = this.id,
        name = this.heroName,
        photo = this.photoRes,
        rating = this.rating
    )
}