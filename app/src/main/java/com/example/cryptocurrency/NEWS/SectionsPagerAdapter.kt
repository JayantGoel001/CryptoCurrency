package com.example.cryptocurrency.NEWS

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SectionsPagerAdapter(var fm:FragmentManager):FragmentPagerAdapter(fm) {
    var fragentList:ArrayList<Fragment> = ArrayList()
    var stringList:ArrayList<String> = java.util.ArrayList()

    override fun getItem(position: Int): Fragment =fragentList[position]

    override fun getCount(): Int =fragentList.size

    fun AddFragment(fragment: Fragment,string: String)
    {
        fragentList.add(fragment)
        stringList.add(string)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return stringList.get(position)
    }
}