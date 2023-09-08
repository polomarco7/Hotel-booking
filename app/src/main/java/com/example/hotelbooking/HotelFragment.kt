package com.example.hotelbooking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.hotelbooking.HotelFragment.Companion.MAX_STEP
import com.example.hotelbooking.databinding.FragmentHotelBinding
import com.example.hotelbooking.databinding.IntroAppDesignBinding
import com.google.android.material.tabs.TabLayoutMediator

class HotelFragment : Fragment() {
    private var _binding: FragmentHotelBinding? = null

    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHotelBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //............................................................
        binding.viewPager2.adapter = AppIntroViewPager2Adapter()

        //............................................................
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
        }.attach()

        //............................................................
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if(position== MAX_STEP -1){

                }else{

                }
            }
        })


        //............................................................
    }
    companion object {
        const val MAX_STEP = 3
    }
}
//...............................................................................


//................................................................................
class AppIntroViewPager2Adapter : RecyclerView.Adapter<PagerVH2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH2 {
        return PagerVH2(
            IntroAppDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun getItemCount(): Int = 3
    override fun onBindViewHolder(holder: PagerVH2, position: Int) = holder.itemView.run {

        with(holder) {
            if (position == 0) {
                bindingDesign.introImage.setImageResource(R.drawable.ic_launcher_background)
            }
            if (position == 1) {
                bindingDesign.introImage.setImageResource(R.drawable.ic_launcher_background)
            }
            if (position == 2) {
                bindingDesign.introImage.setImageResource(R.drawable.ic_launcher_background)
            }
        }
    }
}
class PagerVH2(val bindingDesign: IntroAppDesignBinding) : RecyclerView.ViewHolder(bindingDesign.root)

