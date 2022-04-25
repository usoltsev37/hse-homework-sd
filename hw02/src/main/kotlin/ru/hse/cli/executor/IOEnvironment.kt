package ru.hse.cli.executor

import java.io.InputStream
import java.io.OutputStream

/**
 * Stores streams for an interaction between a command and IO.
 * @param inputStream stores a correct input.
 * @param outputStream stores a correct output.
 * @param errorStream stores errors occurred during execution.
 */
class IOEnvironment constructor(val inputStream: InputStream, val outputStream: OutputStream, val errorStream: OutputStream) {

    /**
     * Prints a current state of streams.
     * If there are no errors then prints output, errors otherwise.
     */
    fun printState() {
        if (errorStream.toString().isEmpty()) {
            println(outputStream.toString())
        } else {
            println("Error: ")
            println(errorStream.toString())
        }
    }
}
