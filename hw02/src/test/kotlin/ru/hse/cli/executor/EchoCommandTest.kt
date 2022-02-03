package ru.hse.cli.executor

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.hse.cli.executor.commands.EchoCommand
import java.io.ByteArrayOutputStream
import java.util.*

internal class EchoCommandTest {
    @Test
    fun executeCorrectOneArgument() {
        val echoCommand = EchoCommand()
        val arg = "Die, die, die my darling"
        val ioEnvironment = IOEnvironment(ByteArrayOutputStream(), ByteArrayOutputStream())

        assertEquals(0, echoCommand.execute(Arrays.asList(arg), ioEnvironment))
        assertEquals(arg, ioEnvironment.outputStream.toString())
    }

    @Test
    fun executeCorrectSomeArguments() {
        val echoCommand = EchoCommand()
        val arg1 = "So don't cry to me"
        val arg2 = "oh baby Your future's"
        val arg3 = "in an oblong box"
        val ioEnvironment = IOEnvironment(ByteArrayOutputStream(), ByteArrayOutputStream())

        assertEquals(0, echoCommand.execute(Arrays.asList(arg1, arg2, arg3), ioEnvironment))
        assertEquals(arg1 + " " + arg2 + " " + arg3, ioEnvironment.outputStream.toString())
    }

}
