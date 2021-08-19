package ru.android.jpgtopngconverter.presentation.converter

import android.net.Uri
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import moxy.MvpPresenter
import ru.android.jpgtopngconverter.models.Converter
import ru.android.jpgtopngconverter.presentation.scheduler.Schedulers

class ConverterPresenter(
    private val converter: Converter,
    private val schedulers: Schedulers
): MvpPresenter<ConverterView>() {

    private val disposables = CompositeDisposable()

    fun convert(uri: Uri) {
        viewState.showContent(uri)
        viewState.showLoading()

        disposables +=
            converter
                .convert(uri)
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(
                    viewState::showContent,
                    viewState::showError
                )
    }

    fun cancel() {
        viewState.showContent(null)
        disposables.clear()
    }

    override fun onDestroy() {
        disposables.clear()
        super.onDestroy()
    }
}