object CompilerMain {
    @JvmStatic fun main(args: Array<String>) {
        val fileName = "test/small"

//        val parse = CMinusParser(fileName)
//        val program = parse.parse()
//        parse.printAst(program)

        val compiler = CMinusCompiler()
        compiler.compile(fileName)
    }
}