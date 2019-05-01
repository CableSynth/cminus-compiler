package compiler.parser.declaration

import compiler.misc.write
import java.io.FileOutputStream

class VarDeclaration(private var identifierName: String = "",
                     private var size: String = "") : Declaration() {

    override fun print(spacing: String, fos: FileOutputStream) {
        fos.write("$spacing VarDecl $identifierName $size\n")
    }
}
