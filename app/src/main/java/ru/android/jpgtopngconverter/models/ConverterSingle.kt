package ru.android.jpgtopngconverter.models

import android.content.Context
import android.net.Uri
import io.reactivex.Single
import io.reactivex.SingleObserver

class ConverterSingle(
    private val context: Context,
    private val uri: Uri
): Single<Uri>() {

    override fun subscribeActual(observer: SingleObserver<in Uri>) {
        val listener = ConverterListener(context, uri, observer)
        observer.onSubscribe(listener)
        listener.convert()
    }

}