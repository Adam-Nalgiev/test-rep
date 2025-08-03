package ru.myapp.myapplication.task1.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.myapp.myapplication.task1.MainActivity
import ru.myapp.myapplication.databinding.Fragment1Binding
import ru.myapp.myapplication.task1.navigation.NavRoute

class Fragment1 : Fragment() {
    private var router: NavRoute? = null
    private var _binding: Fragment1Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = (activity as MainActivity).getRouter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Fragment1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttNext.setOnClickListener {
            router?.navigateForward()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}