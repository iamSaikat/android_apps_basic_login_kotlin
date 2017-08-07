package com.orionsols.mybasicapp

import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import org.jetbrains.anko.longToast

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginButton.setOnClickListener {
            val username  = editTextUserName.text.toString()
            val password = editTextPassword.text.toString()
            login(username,password)
        }

    }
    fun login(user: String, pass: String){
        //Fuel.post("http://httpbin.org/post", listOf("foo" to "foo", "bar" to "bar"))
        Fuel.post("http://192.168.1.116/mobileapp/login.inc.php").body("{ \"user\":\"$user\", \"pass\" : \"$pass\" }").response { request, response, result ->
            when (result) {

                is Result.Failure -> {
                    println("Login with Username: $user and Password: $pass")
                    longToast("Username or password incorrect")
                }
                is Result.Success -> {
                    val i = Intent(this@MainActivity, HomeActivity::class.java)
                    startActivity(i)
                    finish()
                    println("Login with Username: $user and Password: $pass")
                    longToast("Successfully Login")
                }
            }
        }
    }
}
