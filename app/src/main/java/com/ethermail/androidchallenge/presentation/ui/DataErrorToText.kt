package com.ethermail.androidchallenge.presentation.ui

import com.ethermail.androidchallenge.R
import com.ethermail.androidchallenge.domain.util.DataError

fun DataError.asUiText(): UiText {
    return when(this) {
        DataError.Network.REQUEST_TIMEOUT -> UiText.StringResource(
            R.string.error_request_timeout
        )
        DataError.Network.TOO_MANY_REQUESTS -> UiText.StringResource(
            R.string.error_too_many_requests
        )
        DataError.Network.NO_INTERNET -> UiText.StringResource(
            R.string.error_no_internet
        )
        DataError.Network.SERVER_ERROR -> UiText.StringResource(
            R.string.error_server_error
        )
        else -> UiText.StringResource(R.string.error_unknown)
    }
}