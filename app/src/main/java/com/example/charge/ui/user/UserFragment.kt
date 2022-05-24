package com.example.charge.ui.user

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.charge.databinding.FragmentUserBinding
import com.example.charge.ui.login.WelcomeActivity

class UserFragment : Fragment() {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!
    //private var fragmentList: MutableList<Fragment> = ArrayList()
    private var login_check:Boolean = false //false未登录
    private var user_name = ""
    private var user_ID = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        val root = binding.root
        if(login_check == false){
            binding.username.text = "请登录/注册"
            binding.VIPMedal.visibility = View.GONE
        }else{
            binding.username.text=user_name
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.UserInfoShortLayout.setOnClickListener {
            when(login_check){
                false -> {
                    val intent = Intent(context, WelcomeActivity::class.java)
                    context?.startActivity(intent)}
            }

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
