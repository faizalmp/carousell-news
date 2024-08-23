package id.faizalempe.carousellnews.data.network

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @field:SerializedName("id")
    val id: String?,
    @field:SerializedName("title")
    val title: String?,
    @field:SerializedName("description")
    val desc: String?,
    @field:SerializedName("banner_url")
    val bannerUrl: String?,
    @field:SerializedName("time_created")
    val timeCreated: Long?,
    @field:SerializedName("rank")
    val rank: Long?
)