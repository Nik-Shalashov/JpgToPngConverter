package ru.android.jpgtopngconverter

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class Navigator {

    companion object Navigation {

        private val cicerone : Cicerone<Router> by lazy {
            Cicerone.create()
        }

        val navigatorHolder = cicerone.getNavigatorHolder()
        val router = cicerone.router

    }
}