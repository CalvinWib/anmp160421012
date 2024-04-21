package com.example.hobbyapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hobbyapp.model.Hobby
import com.example.hobbyapp.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel(application: Application): AndroidViewModel(application) {
    var hobbyListLD = MutableLiveData<ArrayList<Hobby>>()
    val hobbyLoadErrorLD = MutableLiveData<Boolean>()
    var loadingLD = MutableLiveData<Boolean>()
    var userListLD = MutableLiveData<ArrayList<User>>()

    val TAG = "volleyTag"
    private var queue:RequestQueue? = null

    fun refresh(){
        hobbyLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://localhost/ANMP/hobbys.json"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object: TypeToken<List<Hobby>>() {}.type

                val result = Gson().fromJson<List<Hobby>>(it,sType)

                hobbyListLD.value = result as ArrayList<Hobby>?

                loadingLD.value = false
                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", it.toString())
                hobbyLoadErrorLD.value = false
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }

//    fun refresh(){
//        studentLoadErrorLD.value = false
//        loadingLD.value = true
//
//        Log.d("CEKMASUK","masukVolley")
//        queue = Volley.newRequestQueue(getApplication())
//
//        val url = "http://10.0.2.2/ANMP/cars.json"
//
//        val stringRequest = StringRequest(
//            Request.Method.GET, url,
//            {
//                val sType = object: TypeToken<List<Car>>() {}.type
//
//                val result = Gson().fromJson<List<Car>>(it,sType)
//
//                carListLD.value = result as ArrayList<Car>?
//
//                loadingLD.value = false
//                Log.d("showvoley", it)
//            },
//            {
//                Log.d("showvoley", it.toString())
//                studentLoadErrorLD.value = false
//                loadingLD.value = false
//            })
//
//        stringRequest.tag = TAG
//        queue?.add(stringRequest)
//    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}