package compiler.scanner

import compiler.scanner.Token.TokenType
import java.io.BufferedReader
import java.io.IOException

class CMinusScanner(private val inFile: BufferedReader) : Scanner {
    var lineNumber: Int = 0

    private var nextToken: Token? = null
    private val reservedWords = object : HashMap<String, TokenType>() {
        init {
            put("while", TokenType.WHILE_TOKEN)
            put("void", TokenType.VOID_TOKEN)
            put("if", TokenType.IF_TOKEN)
            put("else", TokenType.ELSE_TOKEN)
            put("return", TokenType.RETURN_TOKEN)
            put("int", TokenType.INT_TOKEN)
        }
    }

    init {
        lineNumber = 1
        try {
            nextToken = scanToken()
        } catch (ex: IOException) {
        }

    }

    @Throws(IOException::class)
    override fun scanToken(): Token {
        var currentToken = Token()
        var tokenBuilder = ""
        var state = StateType.START
        var save = true

        while (state != StateType.DONE) {
            inFile.mark(0)
            val nextChar = inFile.read()

            when (state) {
                CMinusScanner.StateType.START ->
                    if (Character.isDigit(nextChar)) {
                        state = StateType.INNUM
                        save = true
                    } else if (Character.isAlphabetic(nextChar)) {
                        state = StateType.INID
                        save = true
                    } else if (nextChar == ' '.toInt() || nextChar == '\t'.toInt() || nextChar == '\n'.toInt() || nextChar == '\r'.toInt()) {
                        if (nextChar == '\n'.toInt()) {
                            lineNumber++
                        }
                        save = false
                    } else {
                        save = true
                        when (nextChar) {
                            -1 -> {
                                save = false
                                currentToken.type = TokenType.EOF_TOKEN
                                state = StateType.DONE
                            }
                            '='.toInt() -> {
                                state = StateType.INOP
                                currentToken.type = TokenType.ASSIGN_TOKEN
                            }
                            '<'.toInt() -> {
                                state = StateType.INOP
                                currentToken.type = TokenType.LESS_THAN_TOKEN
                            }
                            '>'.toInt() -> {
                                state = StateType.INOP
                                currentToken.type = TokenType.GREATER_THAN_TOKEN
                            }
                            '+'.toInt() -> {
                                currentToken.type = TokenType.PLUS_TOKEN
                                state = StateType.DONE
                            }
                            '-'.toInt() -> {
                                currentToken.type = TokenType.MINUS_TOKEN
                                state = StateType.DONE
                            }
                            '!'.toInt() -> state = StateType.INOP
                            '*'.toInt() -> {
                                currentToken.type = TokenType.TIMES_TOKEN
                                state = StateType.DONE
                            }
                            '/'.toInt() -> state = StateType.CHECKSTARTCOMMENT
                            '('.toInt() -> {
                                currentToken.type = TokenType.LPAREN_TOKEN
                                state = StateType.DONE
                            }
                            ')'.toInt() -> {
                                currentToken.type = TokenType.RPAREN_TOKEN
                                state = StateType.DONE
                            }
                            '['.toInt() -> {
                                currentToken.type = TokenType.LBRACKET_TOKEN
                                state = StateType.DONE
                            }
                            ']'.toInt() -> {
                                currentToken.type = TokenType.RBRACKET_TOKEN
                                state = StateType.DONE
                            }
                            '{'.toInt() -> {
                                currentToken.type = TokenType.LCURLY_TOKEN
                                state = StateType.DONE
                            }
                            '}'.toInt() -> {
                                currentToken.type = TokenType.RCURLY_TOKEN
                                state = StateType.DONE
                            }
                            ';'.toInt() -> {
                                currentToken.type = TokenType.SEMI_TOKEN
                                state = StateType.DONE
                            }
                            ','.toInt() -> {
                                currentToken.type = TokenType.COMMA_TOKEN
                                state = StateType.DONE
                            }
                            else -> state = StateType.DONE
                        }
                    }
                CMinusScanner.StateType.CHECKSTARTCOMMENT ->
                    if (nextChar == '*'.toInt()) {
                        save = false
                        tokenBuilder = ""
                        state = StateType.INCOMMENT
                        currentToken.type = TokenType.COMMENT_TOKEN
                    } else {
                        unGetToken()

                        save = false
                        currentToken.type = TokenType.DIV_TOKEN
                        state = StateType.DONE
                    }
                CMinusScanner.StateType.INCOMMENT -> {
                    save = false
                    if (nextChar == -1) {
                        state = StateType.DONE
                        currentToken.type = TokenType.EOF_TOKEN
                    } else if (nextChar == '*'.toInt()) {
                        state = StateType.CHECKENDCOMMENT
                    }
                    if (nextChar == '\n'.toInt()) {
                        lineNumber++
                    }
                }
                CMinusScanner.StateType.CHECKENDCOMMENT ->
                    if (nextChar == '/'.toInt()) {
                        state = StateType.START
                        tokenBuilder = ""
                        currentToken = Token()
                    }
                CMinusScanner.StateType.INOP ->
                    if (nextChar != '='.toInt()) {
                        inFile.reset()
                        save = false
                        state = StateType.DONE
                    } else {
                        state = StateType.DONE
                        when (currentToken.type) {
                            Token.TokenType.GREATER_THAN_TOKEN -> currentToken.type = TokenType.GREATER_EQUAL_TOKEN
                            Token.TokenType.LESS_THAN_TOKEN -> currentToken.type = TokenType.LESS_EQUAL_TOKEN
                            Token.TokenType.ASSIGN_TOKEN -> currentToken.type = TokenType.EQUAL_TOKEN
                            else -> currentToken.type = TokenType.NOT_EQUAL_TOKEN
                        }
                    }
                CMinusScanner.StateType.INNUM ->
                    if (!Character.isDigit(nextChar) && !Character.isAlphabetic(nextChar)) {
                        inFile.reset()
                        save = false
                        state = StateType.DONE
                        currentToken.type = TokenType.NUM_TOKEN
                    } else if (Character.isAlphabetic(nextChar)) {
                        save = false
                        state = StateType.DONE
                        currentToken.type = TokenType.ERROR_TOKEN
                    }
                CMinusScanner.StateType.INID ->
                    if (!Character.isAlphabetic(nextChar)) {
                        save = false
                        state = StateType.DONE

                        if(Character.isDigit(nextChar) && nextChar != -1) {
                            currentToken.type = TokenType.ERROR_TOKEN
                        } else {
                            try {
                                inFile.reset()
                            } catch (ex: IOException) {
                                // EOF Reached
                            }
                            currentToken.type = TokenType.ID_TOKEN
                        }
                    }
                CMinusScanner.StateType.DONE -> {
                    //print error state
                    System.err.println("Scanner error state: $state")
                    state = StateType.DONE
                    currentToken.type = TokenType.ERROR_TOKEN
                }
            }

            if (save) {
                tokenBuilder = tokenBuilder.plus(Character.toString(nextChar.toChar()))
            }
            if (state == StateType.DONE) {
                when {
                    currentToken.type == TokenType.NUM_TOKEN -> currentToken.data = Integer.parseInt(tokenBuilder)
                    currentToken.type == TokenType.ERROR_TOKEN -> currentToken.data = lineNumber
                    else -> currentToken.data = tokenBuilder
                }
                if (currentToken.type == TokenType.ID_TOKEN) {
                    currentToken.type = checkReservedWord(tokenBuilder)
                }
            }
        }
        return currentToken
    }

    override fun getNextToken(): Token {
        val returnToken = nextToken
        if (nextToken!!.type != Token.TokenType.EOF_TOKEN) {
            try {
                nextToken = scanToken()
            } catch (ex: IOException) {
            }

        }

        return returnToken!!
    }

    override fun viewNextToken(): Token {
        return nextToken!!
    }

    private fun checkReservedWord(currentToken: String): TokenType {
        return if (reservedWords.containsKey(currentToken)) {
            reservedWords[currentToken]!!
        } else {
            TokenType.ID_TOKEN
        }
    }

    fun unGetToken() {
        inFile.reset()
    }

    enum class StateType {
        START,
        INNUM,
        INID,
        INOP,
        CHECKSTARTCOMMENT,
        CHECKENDCOMMENT,
        INCOMMENT,
        DONE
    }
}
