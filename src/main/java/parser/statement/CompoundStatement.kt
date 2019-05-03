package parser.statement

import compiler.misc.write
import lowlevel.CodeItem
import lowlevel.Function
import parser.CMinusParser
import parser.declaration.Declaration
import parser.declaration.VarDeclaration
import scanner.Token
import scanner.advanceToken
import scanner.matchToken
import java.io.FileOutputStream

class CompoundStatement(private var localDeclarations: ArrayList<VarDeclaration> = ArrayList(),
                        var statementList: ArrayList<Statement> = ArrayList()) : Statement() {

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

    override fun genLLCode(function: Function) {
        if (localDeclarations.isNotEmpty()) {
            for (item in 0 until localDeclarations.size) {
                localDeclarations[item].genLLCode()
                function.table[localDeclarations[item].identifierName] = function.newRegNum
            }
        }

        if(statementList.isNotEmpty()){
            for (item in 0 until statementList.size) {
                statementList[item].genLLCode(function)
            }
        }
    }

    companion object {
        fun parse(): CompoundStatement {
            advanceToken()

            val localDeclarations = ArrayList<VarDeclaration>()
            while (CMinusParser.scanner!!.viewNextToken().type == Token.TokenType.INT_TOKEN) {
                val declaration = Declaration.parse() as VarDeclaration
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