package com.byurin.trip.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.byurin.trip.R
import com.byurin.trip.databinding.ActivityMainBinding
import com.byurin.trip.ui.map.MapFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // BottomNavigationView에 대한 바인딩
        val bottomNavigationView = binding.bottomNavigation

        // FragmentContainerView에 대한 바인딩
        val fragmentContainerView = binding.mapFragment

        // bottomNavigationView 아이템 클릭 리스너 설정
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.bottom_map -> {
                    // map_item을 클릭할 때 맵 프래그먼트 표시
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.map_fragment, Fragment())
                        .commit()
                    true
                }
                // 다른 아이템에 대한 처리 추가
                else -> false
            }
        }
    }
}