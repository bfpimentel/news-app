package dev.pimentel.core.usecases

import dev.pimentel.core.abstractions.UseCase

class GetErrorMessage : UseCase<GetErrorMessageParams, String> {
    override fun invoke(params: GetErrorMessageParams) = ""
}

data class GetErrorMessageParams(
    val throwable: Throwable
)
