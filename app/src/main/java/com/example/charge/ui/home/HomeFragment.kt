package com.example.charge.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.charge.R
import com.example.charge.databinding.FragmentHomeBinding
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.indicator.RectangleIndicator


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        newBanner()
    }

    fun newBanner() {
        val arrayImage = arrayListOf(R.drawable.ad1,R.drawable.ad2,R.drawable.ad3)
        binding.banner.setAdapter(object : BannerImageAdapter<Int>(arrayImage) {
            override fun onBindView(
                holder: BannerImageHolder,
                data: Int,
                position: Int,
                size: Int
            ) {
                holder.imageView.setImageResource(data)
            }
        }).addBannerLifecycleObserver(this) //添加生命周期
            .setIndicator(RectangleIndicator(context)) //添加指示器
            .setBannerGalleryEffect(20, 20, 20) //添加抽屉模式
            .setIndicatorSelectedColor(Color.RED) //指示器选中的颜色
            .setIndicatorSpace(10) //设置指示器间隔
            .setIndicatorHeight(10) //设置指示器高度
            .setIndicatorWidth(5, 5)
    }
}



