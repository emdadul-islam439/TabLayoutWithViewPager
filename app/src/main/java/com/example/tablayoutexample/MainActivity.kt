package com.example.tablayoutexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private var homeFragment: HomeFragment? = null
    private var settingsFragment: SettingsFragment? = null
    private var chatsFragment: ChatsFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)

        homeFragment = HomeFragment()
        settingsFragment = SettingsFragment()
        chatsFragment = ChatsFragment()

        //create viewpager adapter
        val viewPagerAdapter: ViewPagerAdapter = ViewPagerAdapter(
            supportFragmentManager, this.lifecycle
        )

        //add fragments and set the adapter
        viewPagerAdapter.addFragment(homeFragment)
        viewPagerAdapter.addFragment(settingsFragment)
        viewPagerAdapter.addFragment(chatsFragment)
        viewPager.adapter = viewPagerAdapter

        val titleList = listOf("Home", "Settings", "")
        val iconList = listOf(
            AppCompatResources.getDrawable(this, R.drawable.android),
            AppCompatResources.getDrawable(this, R.drawable.google_play),
            AppCompatResources.getDrawable(this, R.drawable.heart),
        )
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = titleList[position]
            tab.icon = iconList[position]
        }.attach()

        //set the badge
        val badgeDrawable = tabLayout.getTabAt(0)!!.orCreateBadge
        badgeDrawable.isVisible = true
        badgeDrawable.number = 5
    }

    private inner class ViewPagerAdapter(fm: FragmentManager, lifeCycle: Lifecycle) :
        FragmentStateAdapter(fm, lifeCycle) {
        private val fragments: MutableList<Fragment> = ArrayList()

        //add fragment to the viewpager
        fun addFragment(fragment: Fragment?) {
            fragments.add(fragment!!)
        }

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }
}