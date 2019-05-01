package compiler.misc

import java.io.FileOutputStream

fun FileOutputStream.write(string: String) {
    return this.write(string.toByteArray())
}