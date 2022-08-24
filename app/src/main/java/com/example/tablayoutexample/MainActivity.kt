package com.example.tablayoutexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    private lateinit var viewPager: ViewPager
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

        tabLayout.setupWithViewPager(viewPager)
        //create viewpager adapter
        //here we will create inner class for adapter
        val viewPagerAdapter: ViewPagerAdapter = ViewPagerAdapter(
            supportFragmentManager, 0
        )
        //add fragments and set the adapter
        viewPagerAdapter.addFragment(homeFragment, "")
        viewPagerAdapter.addFragment(settingsFragment, "")
        viewPagerAdapter.addFragment(chatsFragment, "")
        viewPager.adapter = viewPagerAdapter

        //set the icons
        tabLayout.getTabAt(0)?.setIcon(R.drawable.android)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.google_play)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.heart)

        //set the badge
        val badgeDrawable = tabLayout.getTabAt(0)!!.orCreateBadge
        badgeDrawable.isVisible = true
        badgeDrawable.number = 5
    }

    private inner class ViewPagerAdapter(fm: FragmentManager, behavior: Int) :
        FragmentPagerAdapter(fm, behavior) {
        private val fragments: MutableList<Fragment> = ArrayList()
        private val fragmentTitles: MutableList<String> = ArrayList()

        //add fragment to the viewpager
        fun addFragment(fragment: Fragment?, title: String) {
            fragments.add(fragment!!)
            fragmentTitles.add(title)
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        //to setup title of the tab layout
        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitles[position]
        }
    }
}