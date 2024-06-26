package com.example.studentapp.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.studentapp.model.Car
import com.example.studentapp.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListViewModel(application: Application): AndroidViewModel(application) {
    var studentListLD = MutableLiveData<ArrayList<Student>>()
    val studentLoadErrorLD = MutableLiveData<Boolean>()
    var loadingLD = MutableLiveData<Boolean>()
    var carListLD = MutableLiveData<ArrayList<Car>>()

    val TAG = "volleyTag"
    private var queue:RequestQueue? = null

    fun refresh(){
        //volley
        //query db sqllite
        //xml
        //shared preferance

//        val student1 =
//            Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100.jpg/cc0000/ffffff")
//
//        val student2 =
//            Student("13312","Rich","1994/12/14","3925444073","http://dummyimage.com/75x100.jpg/5fa2dd/ffffff")
//
//        val student3 =
//            Student("11204","Dinny","1994/10/07","6827808747","http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1")
//
//        val studentList:ArrayList<Student> = arrayListOf<Student>(student1, student2, student3)

        studentLoadErrorLD.value = false
        loadingLD.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object: TypeToken<List<Student>>() {}.type

                val result = Gson().fromJson<List<Student>>(it,sType)

                studentListLD.value = result as ArrayList<Student>?

                loadingLD.value = false
                Log.d("showvoley", it)
            },
            {
                Log.d("showvoley", it.toString())
                studentLoadErrorLD.value = false
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