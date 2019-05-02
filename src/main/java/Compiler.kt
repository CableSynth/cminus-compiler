package compiler

import compiler.parser.CMinusParser

object Compiler {
    @JvmStatic fun main(args: Array<String>) {
        val fileName = "test/expression.c"
        val parse = CMinusParser(fileName)
        val program = parse.parse()
        parse.printAst(program)
    }
}