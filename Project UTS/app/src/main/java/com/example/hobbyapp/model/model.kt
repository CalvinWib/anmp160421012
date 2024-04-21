package com.example.hobbyapp.model

import com.google.gson.annotations.SerializedName

data class Hobby(
    var id:String?,
    var gambar_url:String?,
    var judul:String?,
    var deskripsi:String?,
    var pembuat:User?
)

data class User(
    val id:String?,
    val username:String?,
    val email:String?,
    val namaDepan:String?,
    val namaBelakang:String?,
    val password:String?
)