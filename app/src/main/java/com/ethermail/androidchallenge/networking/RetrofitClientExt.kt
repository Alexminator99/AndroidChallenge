package com.ethermail.androidchallenge.networking

import com.ethermail.androidchallenge.domain.util.DataError
import java.nio.channels.UnresolvedAddressException
import com.ethermail.androidchallenge.domain.util.Result
import kotlin.coroutines.cancellation.CancellationException
import retrofit2.Response

suspend inline fun <reified T> safeCall(execute: () -> Response<T>): Result<T, DataError.Network> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        e.printStackTrace()
        return Result.Error(DataError.Network.NO_INTERNET)
    } catch (e: Exception) {
        if (e is CancellationException) throw e
        e.printStackTrace()
        return Result.Error(DataError.Network.UNKNOWN)
    }

    return responseToResult(response)
}

fun <T> responseToResult(response: Response<T>): Result<T, DataError.Network> {
    if (response.isSuccessful) {
        return when {
            response.body() != null -> {
                Result.Success(response.body()!!)
            }

            else -> {
                Result.Error(DataError.Network.UNKNOWN)
            }
        }
    }

    return when (response.code()) {
        408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
        409 -> Result.Error(DataError.Network.CONFLICT)
        429 -> Result.Error(DataError.Network.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(DataError.Network.SERVER_ERROR)
        else -> Result.Error(DataError.Network.UNKNOWN)
    }
}