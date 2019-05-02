/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner

interface Scanner {

    fun scanToken(): Token
    fun getNextToken(): Token
    fun viewNextToken(): Token
}
