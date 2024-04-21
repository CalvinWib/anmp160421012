package com.example.studentapp.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.studentapp.model.Student
import com.google.gson.Gson

class DetailViewModel:ViewModel() {
    val studentLD = MutableLiveData<Student>()

    fun fetch(id:String) {
//        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
//            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
//        studentLD.value = student1

        val url = "http://adv.jitusolution.com/student.php?id=$id"
        
//        val requestQueue = Volley.newRequestQueue()

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                // Parse JSON response using Gson
                val gson = Gson()
                val studentData = gson.fromJson(response.toString(), Student::class.java)

                // Update LiveData with the fetched data
                studentLD.value = studentData
            },
            Response.ErrorListener { error ->
                // Handle error
                Log.e("FetchError", "Error fetching data: ${error.message}")
            })

//        requestQueue.add(jsonObjectRequest)
    }

}