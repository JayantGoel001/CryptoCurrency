package com.example.cryptocurrency

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptocurrency.NEWS.*

class Main3Activity : AppCompatActivity() {
    lateinit var sectionsPagerAdapter:SectionsPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        var viewPager: ViewPager
        viewPager = findViewById(R.id.view_pager)
        setupViewPager(viewPager)
        val tabs: TabLayout = findViewById<TabLayout>(R.id.tabs)
        tabs.setupWithViewPager(viewPager)


    }
    fun setupViewPager(viewPager:ViewPager)
    {
        sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        sectionsPagerAdapter.AddFragment(BITCOIN(),"BITCOIN")
        sectionsPagerAdapter.AddFragment(HeadLineFragment(),"Headline")
        sectionsPagerAdapter.AddFragment(BuisnessFragment(),"Business")
        sectionsPagerAdapter.AddFragment(TechnologyFragment(),"Technology")
        sectionsPagerAdapter.AddFragment(HealthFragment(),"Health")
        viewPager.adapter=sectionsPagerAdapter
    }
}