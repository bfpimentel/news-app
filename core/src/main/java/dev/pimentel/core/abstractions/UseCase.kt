package dev.pimentel.core.abstractions

interface UseCase<Params, Result> {
    operator fun invoke(params: Params): Result
}

object NoParams
