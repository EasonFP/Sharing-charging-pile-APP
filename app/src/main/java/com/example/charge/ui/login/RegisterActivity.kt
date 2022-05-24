package com.example.charge.ui.login

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.charge.MainActivity
import com.example.charge.R
import com.example.charge.database.MyDatabaseHelper

class RegisterActivity : AppCompatActivity() {

    private lateinit var phone:EditText
    private lateinit var email:EditText
    private lateinit var password:EditText
    private lateinit var password2:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        phone = findViewById<EditText>(R.id.register_phone)
        email = findViewById<EditText>(R.id.signIn_account)
        password = findViewById<EditText>(R.id.signIn_password)
        password2 = findViewById<EditText>(R.id.register_password2)

        var btn_next = findViewById<Button>(R.id.btn_next)
        btn_next.setOnClickListener {
            var errorInfo = ""
            if(phone.toString().length != 11)
                errorInfo = "错误！手机号不正确"
            else if (password.toString() != password2.toString())
                errorInfo = "错误！两次密码不一致"
            else{
                var flag_1 = false
                var flag_a = false
                for(i in password.toString().indices){
                    val c = password.toString()[i]
                    if((c>='a' && c<='z')|| c>='A' && c<='Z')flag_a =true
                    else if(c>='0' && c<='9')flag_1 = true
                }
                if(!flag_a || !flag_1 || password.toString().length < 6)
                    errorInfo = "错误！密码至少为6位，且必须包含数字和字母"
            }
            if(errorInfo == ""){//注册成功，跳转
                addToDateBase_User()//写入数据库
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else
                Toast.makeText(this, errorInfo,Toast.LENGTH_SHORT).show()
        }
    }

    private fun addToDateBase_User(){
        val dbHelper = MyDatabaseHelper(this,"BookStore.db",2)
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("user_nickname", "用户" + phone.toString())
            put("user_phone", phone.toString())
            put("user_email", email.toString())
            put("user_password", password.toString())
            put("user_vip", 0)
        }
        db.insert("User", null, values) // 插入一条数据
    }
}