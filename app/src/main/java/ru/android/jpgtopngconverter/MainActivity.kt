package ru.android.jpgtopngconverter

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import io.reactivex.disposables.CompositeDisposable
import moxy.MvpAppCompatActivity
import ru.android.jpgtopngconverter.Navigator.Navigation.navigatorHolder
import ru.android.jpgtopngconverter.Navigator.Navigation.router
import ru.android.jpgtopngconverter.presentation.converter.ConverterScreen

class MainActivity : MvpAppCompatActivity() {

    private val navigator = AppNavigator(this, android.R.id.content)

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        savedInstanceState ?: router.newRootScreen(ConverterScreen)

    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()

        disposables.dispose()
    }

}