package com.mehedi.platzistore.model.data.product


import com.google.gson.annotations.SerializedName
import com.mehedi.platzistore.model.data.category.Category

data  class ResponseProductItem(
    @SerializedName("category")
    var category: Category?,
    @SerializedName("creationAt")
    var creationAt: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("id")
    var id: Int,
    @SerializedName("images")
    var images: List<String?>?,
    @SerializedName("price")
    var price: Int?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("updatedAt")
    var updatedAt: String?
)