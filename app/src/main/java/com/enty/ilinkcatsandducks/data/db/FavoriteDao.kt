package com.enty.ilinkcatsandducks.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM fav_img")
    fun findImgs(): LiveData<List<ImageItemModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addImage(image: ImageItemModel)


}