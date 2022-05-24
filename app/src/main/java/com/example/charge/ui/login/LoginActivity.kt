package com.example.charge.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.*
import com.example.charge.MainActivity
import com.example.charge.R
import com.example.charge.database.MyDatabaseHelper
import com.example.charge.ui.snackTools.Snack

class LoginActivity : AppCompatActivity() {

    private lateinit var account:EditText
    private var login_type:String = ""//account的类型
    private var user_id = -1
    private var user_account:String = ""
    private var user_password:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        account = findViewById<EditText>(R.id.signIn_account)
        var text_signIn = findViewById<TextView>(R.id.text_signIn)//标题
        var et_password = findViewById<EditText>(R.id.signIn_password)
        var forget = findViewById<TextView>(R.id.btn_forgetPassword)//忘记密码

        //界面初始化--账户输入框
        login_type = intent.getStringExtra("login_type").toString()
        when(login_type){
            "phone" -> {
                text_signIn.setText("手机号登录")
                account.hint = "请先输入手机账号"
                account.inputType = InputType.TYPE_CLASS_NUMBER
            }
            "email" -> {
                text_signIn.setText("邮箱登录")
                account.hint = "请先输入邮箱账号"
                account.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            }
        }

        var BackButton = findViewById<ImageView>(R.id.btn_back)//返回主界面
        BackButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        var goto_register = findViewById<TextView>(R.id.goto_register)//前往注册页面
        goto_register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        var btn_confirm = findViewById<Button>(R.id.btn_confirmLogin)
        btn_confirm.setOnClickListener {
            user_account = account.text.toString()
            user_password = et_password.text.toString()
            loginCheck()
            val toast: Toast =Toast.makeText(this,"z",Toast.LENGTH_SHORT)
            toast.show()

            if (user_id == -1)
                Snack.onSNACK(this.window.decorView,"账号密码错误")
            else{
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("user_id",user_id)
                startActivity(intent)
            }
        }
    }

    private fun loginCheck(){
        val dbHelper = MyDatabaseHelper(this, "User.db", 2)
        val db = dbHelper.writableDatabase

        val UserQuery = "select user_id from User WHERE user_" + login_type + "=" + user_account +
                " and user_password = " + user_password
        val cursor = db.rawQuery( UserQuery,null)
        user_id = -1
        if (cursor.getCount() != 0) {
            user_id = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"))
        }
        cursor.close()
    }
}