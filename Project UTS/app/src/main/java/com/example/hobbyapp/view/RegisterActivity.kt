package com.example.hobbyapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hobbyapp.R
import com.example.hobbyapp.databinding.ActivityRegisterBinding
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSumbit.setOnClickListener(){
            if(binding.txtPassword.text.toString() == binding.txtPasswordConfirm.text.toString()) {
                val url = "https://ubaya.me/native/160421012/registers.php"
                val q = Volley.newRequestQueue(this)
                val stringRequest = object : StringRequest(
                    Method.POST, url,
                    { response ->
                        Log.e("message", response.toString())
                        val obj = JSONObject(response)
                        if (obj.getString("result") == "OK" && obj.getString("message") == "Account has been created") {
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    },
                    { error ->
                        Log.e("message", error.message.toString())
                    }
                ) {
                    override fun getParams(): Map<String, String> {
                        val params = HashMap<String, String>()
                        params["username"] = binding.txtUsername.text.toString()
                        params["namaDepan"] = binding.txtNamaDepan.text.toString()
                        params["namaBelakang"] = binding.txtNamaBelakang.text.toString()
                        params["email"] = binding.txtEmail.text.toString()
                        params["password"] = binding.txtPassword.text.toString()
                        return params
                    }
                }
                q.add(stringRequest)
            }
            else
            {
                Toast.makeText(this,"password did not match confirm password", Toast.LENGTH_SHORT).show()
            }
        }

    }
}