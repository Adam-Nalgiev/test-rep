package ru.myapp.myapplication.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.myapp.myapplication.MainViewModel
import ru.myapp.myapplication.R
import ru.myapp.myapplication.databinding.FragmentMainBinding
import ru.myapp.myapplication.request
import ru.myapp.myapplication.timer
import ru.myapp.myapplication.ui.adapter.Adapter
import java.util.concurrent.TimeUnit

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val adapter = Adapter { pos -> onCLick(pos) }

    private val vm: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycl.adapter = adapter

        /* Задача 1*/
        val requestView = binding.textView

        val result = lifecycleScope.async {
            request()
        }

        lifecycleScope.launch {
            requestView.text = result.await()
        }

        /* Задача 2 */
        val timerView = binding.timer

        timer()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { time ->
                    Log.d("TAHHHHHH", time.toString())
                    timerView.text = time.toString()
                },
                { err ->
                    Log.d("ERROR", err.message.toString())
                }
            )

        //Задача 4
        val obs = BehaviorSubject.create<String>()

        binding.editText.addTextChangedListener {
            obs.onNext(it.toString())
        }
        obs.debounce(3, TimeUnit.SECONDS).subscribe {
            Log.d("RESULT", it)
        }

        //Задача 5
        Single.zip(
            serv1().onErrorReturnItem(emptyList()), serv2().onErrorReturnItem(emptyList()),
            BiFunction { vals1, vals2 ->
                vals1 + vals2

            })
            .subscribe(
                { Log.d("RES5", it.toString()) },
                { Log.d("ERR5", it.message.toString()) }
            )
    }

    // задача 3
    private fun onCLick(id: String) {
        vm.obs.onNext(id)
        findNavController().navigate(R.id.action_mainFragment_to_navigation_home)
    }


    //Задача 5
    private fun serv1(): Single<List<String>> = Single.just(emptyList<String>())
    private fun serv2(): Single<List<String>> = Single.just(emptyList<String>())
}