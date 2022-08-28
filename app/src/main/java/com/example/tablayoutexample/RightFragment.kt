package com.example.tablayoutexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.tablayoutexample.databinding.FragmentRightBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.util.*

class RightFragment : Fragment() {
    lateinit var homeFragment: HomeFragment
    lateinit var settingsFragment: SettingsFragment
    lateinit var chatsFragment: ChatsFragment

    private var _binding: FragmentRightBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeFragment = HomeFragment()
        settingsFragment = SettingsFragment()
        chatsFragment = ChatsFragment()

        //create viewpager adapter
        val viewPagerAdapter: ViewPagerAdapter = ViewPagerAdapter(
            requireActivity().supportFragmentManager, this.lifecycle
        )

        //add fragments and set the adapter
        viewPagerAdapter.addFragment(homeFragment)
        viewPagerAdapter.addFragment(settingsFragment)
        viewPagerAdapter.addFragment(chatsFragment)
        binding.viewPager.adapter = viewPagerAdapter

        val titleList = listOf("Home", "Settings", "Chats")
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titleList[position]
        }.attach()

        //set the badge
        val badgeDrawable = binding.tabLayout.getTabAt(0)!!.orCreateBadge
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}