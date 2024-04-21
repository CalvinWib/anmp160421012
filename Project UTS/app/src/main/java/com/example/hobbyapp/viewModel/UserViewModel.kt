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

class UserViewModel(application: Application): AndroidViewModel(application) {
    var userListLD = MutableLiveData<ArrayList<User>>()
    val userLoadErrorLD = MutableLiveData<Boolean>()
    var loadingLD = MutableLiveData<Boolean>()
    val result = MutableLiveData<String>() //nyimpan hasil

    val TAG = "volleyUser"
    private var queue:RequestQueue? = null

    fun refresh(){
        userLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://ubaya.me/native/160421012/users.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {response ->
                loadingLD.value = false
                Log.d("show_volley: ", response)

                val sType = object : TypeToken<List<User>>() {}.type
                val result = Gson().fromJson<List<User>>(response, sType)

                userListLD.value = result as ArrayList<User>
            },
            {
                Log.d("showvoley", it.toString())
                userLoadErrorLD.value = false
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}