package com.faris.timetable.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.faris.timetable.fragments.DayOfTheWeekSubjectsFragment
import com.faris.timetable.fragments.HomeFragment
import com.faris.timetable.fragments.NotesFragment

class DaysPagerAdapter(fragment : FragmentActivity) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> DayOfTheWeekSubjectsFragment(1)
            1-> DayOfTheWeekSubjectsFragment(2)
            2 -> DayOfTheWeekSubjectsFragment(3)
            3 -> DayOfTheWeekSubjectsFragment(4)
            4 -> DayOfTheWeekSubjectsFragment(5)
            else -> DayOfTheWeekSubjectsFragment(6)
        }
    }
}