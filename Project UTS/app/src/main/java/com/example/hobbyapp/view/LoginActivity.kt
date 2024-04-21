package com.example.hobbyapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.ActivityLoginBinding
import com.example.hobbyapp.model.User
import com.google.gson.Gson
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtSignUp.setOnClickListener() {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnSumbit.setOnClickListener() {
            val url = "https://ubaya.me/native/160421012/logins.php"
            val q = Volley.newRequestQueue(this)

            val stringRequest = object : StringRequest(
                Method.POST, url,
                { response ->
                    Log.e("message", response.toString())
                    val obj = JSONObject(response)
                    if (obj.getString("result") == "OK") {
                        val data = obj.getJSONArray("data")
                        val sType = object : com.google.gson.reflect.TypeToken<List<User>>() { }.type
                        val users = Gson().fromJson(data.toString(), sType) as ArrayList<User>
                        Log.e("message", users[0].toString())
                        if(users[0].toString() != null){
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                    else
                    {
                        Toast.makeText(this,"username or password is wrong", Toast.LENGTH_SHORT).show()
                    }
                },
                { error ->
                    Log.e("messasge", error.message.toString())
                }
            ) {
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params["username"] = binding.txtUsername.text.toString()
                    params["password"] = binding.txtPassword.text.toString()
                    return params
                }
            }
            q.add(stringRequest)
        }
    }
}