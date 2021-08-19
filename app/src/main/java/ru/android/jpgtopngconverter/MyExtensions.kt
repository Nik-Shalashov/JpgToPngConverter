package ru.android.jpgtopngconverter

import android.view.View

fun View.click(click: () -> Unit) = setOnClickListener { click() }
