package ru.hse.cli.parser.impl

import ru.hse.cli.parser.Lexer
import ru.hse.cli.parser.util.Token

/**
 * Base implementation of [Lexer].
 * @see [Token]
 */
class LexerImpl(private val input: String): Lexer {

    /**
     * Current position in input string
     */
    var pos: Int = 0

    private var isExhausted = input.isEmpty()

    override fun getNextToken(): Token {
        check(!isExhausted)

        isExhausted = true
        for (tok in Token.values()) {
            val endOfToken = tok.endOfMatch(input.substring(pos))
            if (endOfToken != -1) {
                pos += endOfToken
                isExhausted = pos >= input.length
                return tok
            }
        }

        throw IllegalStateException("Lexer Error: position $pos")
    }

    override fun isExhausted(): Boolean {
        return isExhausted
    }

    /**
     * @return input substring from start to pos
     */
    fun getInputSubstr(start: Int): String {
        return input.substring(start, pos)
    }
}
