package com.example.tablayoutexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class BottomNavWithTab : AppCompatActivity() {
    lateinit var leftFragment: LeftFragment
    lateinit var rightFragment: RightFragment
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav_with_tab)

        setupVariables()
        setupBottomNavigationBar()
    }

    private fun setupVariables() {
        leftFragment = LeftFragment()
        rightFragment = RightFragment()
        bottomNavigationView = findViewById(R.id.bottom_navigation)
    }

    private fun setupBottomNavigationBar() {
        setCurrentFragment(leftFragment)


        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.page_1->setCurrentFragment(leftFragment)
                R.id.page_2->setCurrentFragment(rightFragment)
            }
            true
        }
    }



    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view, fragment)
            commit()
        }


}