package com.enty.ilinkcatsandducks.ui.cats_ducks

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enty.ilinkcatsandducks.common.Resource
import com.enty.ilinkcatsandducks.data.db.FavoriteDataBase
import com.enty.ilinkcatsandducks.data.db.ImageItemModel
import com.enty.ilinkcatsandducks.data.remote.CatsApi
import com.enty.ilinkcatsandducks.data.remote.DucksApi
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class CatOrDuckViewModel @Inject constructor(
    val catsApi: CatsApi,
    val ducksApi: DucksApi,
    val dataBase: FavoriteDataBase
): ViewModel() {

    private val _imageUrl = MutableLiveData<Resource<String>>()
    val imageUrl: LiveData<Resource<String>> = _imageUrl

    fun loadCat(){
        viewModelScope.launch {
            try {
                _imageUrl.postValue(Resource.Loading())
                val request = catsApi.getRandomCat()
                _imageUrl.postValue(Resource.Success(request.url))
            }
            catch (e: HttpException){
                _imageUrl.postValue(Resource.Error(e.message?: "An unexpected error occurred"))
                Log.e(TAG, "loadCat: ${e.message()}")
            }
            catch (e: IOException){
                _imageUrl.postValue(Resource.Error(e.message?: "Connection problem"))
                Log.e(TAG, "loadCat: connection error" )
            }
        }
    }

    fun loadDuck(){
        viewModelScope.launch {
            try {
                _imageUrl.postValue(Resource.Loading())
                val request = ducksApi.getRandomDuck()
                _imageUrl.postValue(Resource.Success(request.url))
            }
            catch (e: HttpException){
                _imageUrl.postValue(Resource.Error(e.message?: "An unexpected error occurred"))
            }
            catch (e: IOException){
                _imageUrl.postValue(Resource.Error(e.message?: "Connection problem"))
            }
        }
    }

    fun addAnimal(imageUrl: String){
        viewModelScope.launch(Dispatchers.IO){
            dataBase.getDao().addImage(ImageItemModel(imageUrl))
        }

    }
}