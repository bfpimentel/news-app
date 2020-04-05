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
}
