package ru.android.jpgtopngconverter.models

import android.content.Context

object ConverterFactory {

    fun create(context: Context): Converter {
        return ConverterImpl(context)
    }
}