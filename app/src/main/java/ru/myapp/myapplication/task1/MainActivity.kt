package ru.myapp.myapplication.task1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.myapp.myapplication.R
import ru.myapp.myapplication.databinding.ActivityMainBinding
import ru.myapp.myapplication.task1.navigation.FragmentType
import ru.myapp.myapplication.task1.navigation.NavRoute

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var router: NavRoute

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        router = NavRoute(supportFragmentManager, R.id.container, FragmentType.FRAGMENT_1)
    }

    fun getRouter(): NavRoute = router
}