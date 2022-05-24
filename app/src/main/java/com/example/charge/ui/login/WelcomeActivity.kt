package com.example.charge.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.charge.R
import com.example.charge.ui.snackTools.Snack.onSNACK


class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val btn_phone = findViewById<Button>(R.id.btn_phone)
        val btn_wechat = findViewById<ImageButton>(R.id.btn_wechat)
        val btn_qq = findViewById<ImageButton>(R.id.btn_qq)
        val btn_alipay = findViewById<ImageButton>(R.id.btn_alipay)
        val btn_email = findViewById<ImageButton>(R.id.btn_email)

        btn_phone.setOnClickListener { btn_action("phone") }
        btn_email.setOnClickListener { btn_action("email") }
//        btn_wechat.setOnClickListener { btn_action("wechat") }
//        btn_qq.setOnClickListener { btn_action("qq") }
//        btn_alipay.setOnClickListener { btn_action("alipay") }
    }

    fun btn_action(type:String){
        val agreement = findViewById<CheckBox>(R.id.agreement)
        if (agreement.isChecked == false)
            onSNACK(this.window.decorView,"请先勾选同意移动充电相关服务条款")
        else{
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("login_type",type)
            startActivity(intent)
        }
    }

}