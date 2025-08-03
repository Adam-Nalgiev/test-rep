package ru.myapp.myapplication.task1.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.myapp.myapplication.task1.fragment.Fragment1
import ru.myapp.myapplication.task1.fragment.Fragment2
import ru.myapp.myapplication.task1.fragment.Fragment3

class NavRoute(
    private val fragmentManager: FragmentManager,
    private val containerId: Int,
    startDestination: FragmentType
) {
    private var currentDestination = startDestination

    init {
        showFragment(startDestination, true)
    }

    fun navigateTo(fragmentType: FragmentType){
        if (fragmentType == currentDestination) return

        showFragment(fragmentType, true)
        currentDestination= fragmentType
    }

    fun navigateBack() {
        when(currentDestination) {
            FragmentType.FRAGMENT_1 -> return
            FragmentType.FRAGMENT_2 -> navigateTo(FragmentType.FRAGMENT_1)
            FragmentType.FRAGMENT_3 -> navigateTo(FragmentType.FRAGMENT_2)
        }

    }

    fun navigateForward() {
        when(currentDestination) {
            FragmentType.FRAGMENT_1 -> navigateTo(FragmentType.FRAGMENT_2)
            FragmentType.FRAGMENT_2 -> navigateTo(FragmentType.FRAGMENT_3)
            FragmentType.FRAGMENT_3 -> return
        }
    }

    private fun showFragment(fragmentType: FragmentType, addToBackStack: Boolean){
        val fragment = createFragment(fragmentType)
        val transaction = fragmentManager.beginTransaction()

        transaction.replace(containerId, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(fragmentType.name)
        }

        transaction.commit()
    }

    private fun createFragment(fragmentType: FragmentType): Fragment {
        return when(fragmentType){
            FragmentType.FRAGMENT_1 -> Fragment1()
            FragmentType.FRAGMENT_2 -> Fragment2()
            FragmentType.FRAGMENT_3 -> Fragment3()
        }
    }
}