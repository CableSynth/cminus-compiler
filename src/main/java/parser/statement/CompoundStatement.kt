package compiler.parser.statement

import compiler.misc.write
import compiler.parser.CMinusParser
import compiler.parser.declaration.Declaration
import compiler.scanner.Token
import compiler.scanner.advanceToken
import compiler.scanner.matchToken
import java.io.FileOutputStream

class CompoundStatement(private var localDeclarations: ArrayList<Declaration> = ArrayList(),
                        private var statementList: ArrayList<Statement> = ArrayList()) : Statement() {
    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing CmpStmt \n")

        if (!localDeclarations.isEmpty()) {
            fos.write("$spacing   LocalDecls \n")
            for (declaration in localDeclarations) {
                declaration.print("$spacing    ", fos)
            }
        }

        if (!statementList.isEmpty()) {
            fos.write("$spacing   StmtList \n")
            for (statement in statementList) {
                statement.print("$spacing    ", fos)
            }
        }
    }

    companion object {
        fun parse(): CompoundStatement {
            advanceToken()

            val localDeclarations = ArrayList<Declaration>()
            while (CMinusParser.scanner!!.viewNextToken().type == Token.TokenType.INT_TOKEN) {
                val declaration = Declaration.parse()
                localDeclarations.add(declaration)
            }

            val statements = ArrayList<Statement>()
            while (CMinusParser.scanner!!.viewNextToken().type != Token.TokenType.RCURLY_TOKEN) {
                val statement = Statement.parse()
                statements.add(statement)
            }

            matchToken(Token.TokenType.RCURLY_TOKEN)

            return CompoundStatement(localDeclarations, statements)
        }
    }
}