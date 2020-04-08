package dev.pimentel.core.usecases

import android.content.Context
import dev.pimentel.core.R
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

internal class GetErrorMessageTest {

    private val context = mockk<Context>(relaxed = true)
    private val getErrorMessage = GetErrorMessage(context)

    @Test
    fun `should return no connection message when error is an instance of IOException`() {
        val errorMessage = "noConnectionError"
        val params = GetErrorMessageParams(IOException())

        every { context.getString(R.string.error_message_no_connection) } returns errorMessage

        assertEquals(
            getErrorMessage(params),
            errorMessage
        )

        verify { context.getString(R.string.error_message_no_connection) }
        confirmVerified(context)
    }

    @Test
    fun `should return no connection message when error is not an instance of IOException`() {
        val errorMessage = "defaultError"
        val params = GetErrorMessageParams(IllegalArgumentException())

        every { context.getString(R.string.error_message_default) } returns errorMessage

        assertEquals(
            getErrorMessage(params),
            errorMessage
        )

        verify { context.getString(R.string.error_message_default) }
        confirmVerified(context)
    }

    @Test
    fun `should find throwable inside params`() {
        val error = IllegalArgumentException()
        val params = GetErrorMessageParams(error)

        assertEquals(params.throwable, error)
    }
}
