package ru.myapp.myapplication.task1.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.myapp.myapplication.databinding.Fragment3Binding
import ru.myapp.myapplication.task1.MainActivity
import ru.myapp.myapplication.task1.navigation.NavRoute
import ru.myapp.myapplication.task2_3.MainActivity2

class Fragment3 : Fragment() {
    private var router: NavRoute? = null
    private var _binding: Fragment3Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        router = (activity as MainActivity).getRouter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Fragment3Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttBack.setOnClickListener {
            router?.navigateBack()
        }
        binding.toNextTask.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity2::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}