package ru.android.jpgtopngconverter.models

import android.net.Uri

interface Converter {

    fun convert(uri: Uri): Single<Uri>
}