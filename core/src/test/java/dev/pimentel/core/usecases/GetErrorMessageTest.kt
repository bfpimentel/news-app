package dev.pimentel.core.usecases

import org.junit.Assert.assertEquals
import org.junit.Test

internal class GetErrorMessageTest {

    private val getErrorMessage = GetErrorMessage()

    @Test
    fun `should return empty string when getting error message`() {
        val params = GetErrorMessageParams(IllegalArgumentException())

        assertEquals(
            getErrorMessage(params),
            ""
        )
    }

    @Test
    fun `should find throwable inside params`() {
        val error = IllegalArgumentException()
        val params = GetErrorMessageParams(error)

        assertEquals(params.throwable, error)
    }
}
