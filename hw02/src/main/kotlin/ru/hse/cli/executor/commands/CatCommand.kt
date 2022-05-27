package ru.hse.cli.executor.commands

import org.apache.commons.io.IOUtils
import ru.hse.cli.executor.IOEnvironment
import java.io.File
import java.io.FileNotFoundException
import kotlin.io.path.readBytes
import kotlin.io.path.writeText
import java.nio.charset.StandardCharsets

/**
 * Represents the command [cat] which returns the contents of a file.
 */
class CatCommand : AbstractCommand {

    private fun getData(fileName: String): ByteArray {
        return File(fileName).readBytes()
    }

    private fun forEachWrite(args: List<String>, ioEnvironment: IOEnvironment): Int {
        args.forEach {
            try {
                ioEnvironment.outputStream.write(getData(it))
            } catch (e: FileNotFoundException) {
                ioEnvironment.errorStream.write("File '$it' does not exist!".toByteArray())
                return -1
            }
        }
        return 0
    }

    /**
     * Execute [cat] command with arguments [args] and IO environment [ioEnvironment].
     * Execution can be unsuccessful if at least one file doesn't exitst.
     * @param args file names to process.
     * @param ioEnvironment stores output and error streams to print a result or error message during execution.
     * @return 0 if execution was successful, -1 otherwise.
     */
    override fun execute(args: List<String>, ioEnvironment: IOEnvironment): Int {
        var result = 0
        if (args.isEmpty()) {
            val content = IOUtils.toString(ioEnvironment.inputStream, StandardCharsets.UTF_8)
            ioEnvironment.outputStream.write(content.toByteArray())
        } else {
            ioEnvironment.inputStream.reset()
            result = if (forEachWrite(args, ioEnvironment) == -1) -1 else result
        }

        return result
    }
}
