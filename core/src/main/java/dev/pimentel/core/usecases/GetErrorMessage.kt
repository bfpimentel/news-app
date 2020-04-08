package dev.pimentel.core.usecases

import android.content.Context
import dev.pimentel.core.R
import dev.pimentel.core.abstractions.UseCase
import java.io.IOException

class GetErrorMessage(
    private val context: Context
) : UseCase<GetErrorMessageParams, String> {

    override fun invoke(params: GetErrorMessageParams) = when (params.throwable) {
        is IOException -> context.getString(R.string.error_message_no_connection)
        else -> context.getString(R.string.error_message_default)
    }
}

data class GetErrorMessageParams(
    val throwable: Throwable
)
