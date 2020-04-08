package dev.pimentel.core.usecases

import android.content.Context
import dev.pimentel.core.abstractions.UseCase
import java.io.IOException

class GetErrorMessage(
    private val context: Context
) : UseCase<GetErrorMessageParams, String> {

    override fun invoke(params: GetErrorMessageParams) = when (params.throwable) {
        is IOException -> "NoConnectionError"
        else -> "AnyError"
    }
}

data class GetErrorMessageParams(
    val throwable: Throwable
)
