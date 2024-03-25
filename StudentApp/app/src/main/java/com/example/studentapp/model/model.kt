package com.example.studentapp.model

import com.google.gson.annotations.SerializedName

data class Student(
    var id:String?,
    @SerializedName("student_name")
    var name:String?,
    @SerializedName("birth_of_date")
    var dob:String?,
    var phone:String?,
    @SerializedName("photo_url")
    var photoUrl:String?
)

data class Car(
    val id:String?,
    val name:String?,
    val brand:CarBranded?,
    val year:Int?,
    val colors:List<String>?,
    val images:String?
)

data class CarBranded(
    val name:String?,
    val country:String?
)