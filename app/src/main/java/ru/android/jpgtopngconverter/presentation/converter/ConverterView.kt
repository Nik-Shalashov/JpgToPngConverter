package ru.android.jpgtopngconverter.presentation.converter

import android.net.Uri
import moxy.viewstate.strategy.alias.AddToEndSingle
import ru.android.jpgtopngconverter.presentation.ScreenView

interface ConverterView: ScreenView {

    @AddToEndSingle
    fun showContent(uri: Uri?)

    @AddToEndSingle
    fun showLoading()
}