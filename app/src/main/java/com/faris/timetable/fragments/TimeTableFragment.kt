package com.faris.timetable.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.faris.timetable.MainActivity
import com.faris.timetable.R
import com.faris.timetable.adapters.DaysPagerAdapter
import com.faris.timetable.databinding.FragmentHomeBinding
import com.faris.timetable.databinding.FragmentTimeTableBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TimeTableFragment : Fragment() {
    private var _binding: FragmentTimeTableBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTimeTableBinding.inflate(inflater, container, false)
        val view = binding.root

        val tabLayout = binding.tabLayout
        val viewPager = binding.pager
        viewPager.adapter = DaysPagerAdapter(activity as AppCompatActivity)
        viewPager.offscreenPageLimit = 2

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.apply {
                when (position) {
                    0 -> text = "Ponedeljak"
                    1 -> text = "Utorak"
                    2 -> text = "Srijeda"
                    3 -> text = "Cetvrtak"
                    4 -> text = "Petak"
                }
            }
        }.attach()


        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
//        (activity as MainActivity).hideActionBar()
        _binding = null
    }

}