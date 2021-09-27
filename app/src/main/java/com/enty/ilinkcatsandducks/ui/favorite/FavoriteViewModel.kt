package com.enty.ilinkcatsandducks.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enty.ilinkcatsandducks.data.db.FavoriteDataBase
import com.enty.ilinkcatsandducks.data.db.ImageItemModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    val dataBase: FavoriteDataBase
    ): ViewModel() {

    var favImages: LiveData<List<ImageItemModel>> = dataBase.getDao().findImgs()




}