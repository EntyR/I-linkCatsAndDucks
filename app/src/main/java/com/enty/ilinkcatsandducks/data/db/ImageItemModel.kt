package com.enty.ilinkcatsandducks.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "fav_img")
data class ImageItemModel(
    var uri: String = ""
) {
    @PrimaryKey(autoGenerate = true)
    var key: Int? = null
}
