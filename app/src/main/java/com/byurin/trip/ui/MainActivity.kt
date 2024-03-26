package com.byurin.trip.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.byurin.trip.R
import com.byurin.trip.databinding.ActivityMainBinding
import com.byurin.trip.ui.map.NaverMapFragment
import com.byurin.trip.ui.tasks.TasksFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(TasksFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.bottom_tasks -> replaceFragment(TasksFragment())
                R.id.bottom_map -> replaceFragment(NaverMapFragment())


                else -> {

                }
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_fragment_tasks, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                // search action
                true
            }
            R.id.action_edit -> {
                // edit action
                true
            }
            R.id.action_delete -> {
                // delete action
                true
            }
            R.id.action_share -> {
                // settings action
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_cv, fragment)
        fragmentTransaction.commit()
    }
}