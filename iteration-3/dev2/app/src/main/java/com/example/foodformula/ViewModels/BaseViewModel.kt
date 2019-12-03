package com.example.foodformula.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodformula.ApiConnection.ApiFunctions
import com.example.foodformula.ApiConnection.Event
import com.example.foodformula.ApiConnection.NetworkService
import com.example.foodformula.ApiConnection.ResponseWrapperRecipes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    var api: ApiFunctions = NetworkService.retrofitService()

    fun <T> requestWithLiveData(
        liveData: MutableLiveData<Event<T>>,
        request: suspend () -> ResponseWrapperRecipes<T>
    ) {

        liveData.postValue(Event.loading())

        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = request.invoke()
                if (response.data != null) liveData.postValue(Event.success(response.data[0]))
                else if (response.error != null) liveData.postValue(Event.error(response.error))
            } catch (e: Exception) {
                e.printStackTrace()
                liveData.postValue(Event.error(null))
            }
        }
    }

    fun <T> requestWithLiveDataWithoutWraper(
        liveData: MutableLiveData<Event<T>>,
        request: suspend () -> T
    ) {

        liveData.postValue(Event.loading())
        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = request.invoke()
                // Сетим в лайвдату командой postValue в IO потоке
                if (response != null) liveData.postValue(Event.success(response))
                else if (response != null) liveData.postValue(Event.error(response))
            } catch (e: Exception) {
                e.printStackTrace()
                liveData.postValue(Event.error(null))
            }
        }
    }

    fun <T> requestWithCallback(
        request: suspend () -> List<T?>?,
        response: (Event<List<T?>>?) -> Unit
    ) {

        response(Event.loading())

        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val res = request.invoke()

                // здесь все аналогично, но полученные данные
                // сетим в колбек уже в главном потоке, чтобы
                // избежать конфликтов с
                // последующим использованием данных
                // в context классах
                launch(Dispatchers.Main) {
                    if (res != null) response(Event.successArray(res))
                    else response(Event.error(res))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // UPD (подсказали в комментариях) В блоке catch ивент передаем тоже в Main потоке
                launch(Dispatchers.Main) {
                    response(Event.error(null))
                }
            }
        }
    }


}