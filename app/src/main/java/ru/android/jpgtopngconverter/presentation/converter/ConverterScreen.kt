package ru.android.jpgtopngconverter.presentation.converter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

object ConverterScreen: FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return ConverterFragment.newInstance()
    }
}