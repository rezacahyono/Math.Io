package com.rchyn.mathio.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Subject(
    @DrawableRes
    val imageSubject: Int,
    @StringRes
    val title: Int
)
