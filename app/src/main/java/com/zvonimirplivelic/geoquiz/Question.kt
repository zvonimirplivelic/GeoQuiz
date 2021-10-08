package com.zvonimirplivelic.geoquiz

import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean, var isQuestionAnswered: Boolean = false)