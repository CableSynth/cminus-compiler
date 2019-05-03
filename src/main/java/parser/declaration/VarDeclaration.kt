package parser.declaration

import compiler.misc.write
import lowlevel.CodeItem
import lowlevel.Data
import lowlevel.Data.TYPE_INT
import parser.declaration.Declaration
import java.io.FileOutputStream

class VarDeclaration(var identifierName: String = "",
                     private var size: String = "") : Declaration() {

    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing VarDecl $identifierName $size\n")
    }

    override fun genLLCode(): CodeItem {
        return if (size == "") {
            Data(TYPE_INT, identifierName)
        } else {
            Data(TYPE_INT, identifierName, true, size.toInt())
        }
    }
}
