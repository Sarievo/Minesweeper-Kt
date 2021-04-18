package minesweeper

import kotlin.random.Random
import java.util.Scanner
import kotlin.math.min

fun main() {
    val s = Scanner(System.`in`)

    val fieldRows = 9
    val fieldColumns = 9
    print("How many mines do you want on the field? > ")
    val mineNumber = readLine()!!.toInt()
    println()
    val mineField = Array(fieldRows) { CharArray(fieldColumns) { '.' } } // init minefield
    val displayField = Array(fieldRows) { CharArray(fieldColumns) { '.' } } // init display field(1)
    val marksField = Array(fieldRows) { CharArray(fieldColumns) { '.' } } // mines to marks exchange(2)

    var mineCount = 0;
    do {
        val randRow = Random.nextInt(fieldRows)
        val randColumn = Random.nextInt(fieldColumns)
        val c = mineField[randRow][randColumn]
        if (c == '.') {
            mineField[randRow][randColumn] = 'X'
            marksField[randRow][randColumn] = '*'
            mineCount++
        }
    } while (mineCount < mineNumber) // Init mines & marks

    var hasMine = 0
    for (row in mineField.indices) {
        for (col in mineField.indices) {
            if (mineField[row][col] == '.') {
                when {
                    row == 0 && col == 0 -> {
                        if (mineField[row][col + 1] == 'X') hasMine++
                        if (mineField[row + 1][col + 1] == 'X') hasMine++
                        if (mineField[row + 1][col] == 'X') hasMine++
                        if (hasMine != 0)
                            mineField[row][col] = (48 + hasMine).toChar(); hasMine = 0
                    } // Corners' Num
                    row == 0 && col == 8 -> {
                        if (mineField[row][col - 1] == 'X') hasMine++
                        if (mineField[row + 1][col - 1] == 'X') hasMine++
                        if (mineField[row + 1][col] == 'X') hasMine++
                        if (hasMine != 0)
                            mineField[row][col] = (48 + hasMine).toChar(); hasMine = 0
                    }
                    row == 8 && col == 0 -> {
                        if (mineField[row - 1][col] == 'X') hasMine++
                        if (mineField[row - 1][col + 1] == 'X') hasMine++
                        if (mineField[row][col + 1] == 'X') hasMine++
                        if (hasMine != 0)
                            mineField[row][col] = (48 + hasMine).toChar(); hasMine = 0
                    }
                    row == 8 && col == 8 -> {
                        if (mineField[row][col - 1] == 'X') hasMine++
                        if (mineField[row - 1][col - 1] == 'X') hasMine++
                        if (mineField[row - 1][col] == 'X') hasMine++
                        if (hasMine != 0)
                            mineField[row][col] = (48 + hasMine).toChar(); hasMine = 0
                    }
                    row == 0 -> {
                        if (mineField[row][col - 1] == 'X') hasMine++
                        if (mineField[row + 1][col - 1] == 'X') hasMine++
                        if (mineField[row + 1][col] == 'X') hasMine++
                        if (mineField[row + 1][col + 1] == 'X') hasMine++
                        if (mineField[row][col + 1] == 'X') hasMine++
                        if (hasMine != 0)
                            mineField[row][col] = (48 + hasMine).toChar(); hasMine = 0
                    } // Edges' Num
                    col == 0 -> {
                        if (mineField[row - 1][col] == 'X') hasMine++
                        if (mineField[row - 1][col + 1] == 'X') hasMine++
                        if (mineField[row][col + 1] == 'X') hasMine++
                        if (mineField[row + 1][col + 1] == 'X') hasMine++
                        if (mineField[row + 1][col] == 'X') hasMine++
                        if (hasMine != 0)
                            mineField[row][col] = (48 + hasMine).toChar(); hasMine = 0
                    }
                    col == 8 -> {
                        if (mineField[row - 1][col] == 'X') hasMine++
                        if (mineField[row - 1][col - 1] == 'X') hasMine++
                        if (mineField[row][col - 1] == 'X') hasMine++
                        if (mineField[row + 1][col - 1] == 'X') hasMine++
                        if (mineField[row + 1][col] == 'X') hasMine++
                        if (hasMine != 0)
                            mineField[row][col] = (48 + hasMine).toChar(); hasMine = 0
                    }
                    row == 8 -> {
                        if (mineField[row][col - 1] == 'X') hasMine++
                        if (mineField[row - 1][col - 1] == 'X') hasMine++
                        if (mineField[row - 1][col] == 'X') hasMine++
                        if (mineField[row - 1][col + 1] == 'X') hasMine++
                        if (mineField[row][col + 1] == 'X') hasMine++
                        if (hasMine != 0)
                            mineField[row][col] = (48 + hasMine).toChar(); hasMine = 0
                    }
                    else -> {
                        if (mineField[row - 1][col - 1] == 'X') hasMine++
                        if (mineField[row - 1][col] == 'X') hasMine++
                        if (mineField[row - 1][col + 1] == 'X') hasMine++
                        if (mineField[row][col + 1] == 'X') hasMine++
                        if (mineField[row + 1][col + 1] == 'X') hasMine++
                        if (mineField[row + 1][col] == 'X') hasMine++
                        if (mineField[row + 1][col - 1] == 'X') hasMine++
                        if (mineField[row][col - 1] == 'X') hasMine++
                        if (hasMine != 0)
                            mineField[row][col] = (48 + hasMine).toChar(); hasMine = 0
                    }
                }
            }
        }
    } // Generate numbers

    fun printField() {
        var i = 1
        var str1 = ""
        var str2 = ""
        for (j in 1..fieldColumns) {
            str1 += j
            str2 += "—"
        }
        println(" |$str1|")
        println("—│$str2│")
        for (row in displayField.indices) {
            print("$i|")
            for (col in displayField.indices) {
                print(displayField[row][col])
            }
            println("|")
            i++
        }
        println("—│$str2│")
    } // Print current player's field
    printField()

    var x = 0
    var y = 0
    var act = ""
    fun readInput() {
        x = s.nextInt() - 1
        y = s.nextInt() - 1
        act = s.next()
    } // Read user actions
    fun floodFill(pos_y: Int, pos_x: Int) {
        if (pos_y > fieldRows - 1 || pos_y < 0 || pos_x > fieldColumns - 1 || pos_x < 0) {
            return
        } else if (marksField[pos_y][pos_x] != '.') {
            return
        }

        marksField[pos_y][pos_x] = '/'
        displayField[pos_y][pos_x] = '/'
        if (mineField[pos_y][pos_x] != '.') {
            marksField[pos_y][pos_x] = mineField[pos_y][pos_x]
            displayField[pos_y][pos_x] = mineField[pos_y][pos_x]
        }

        floodFill(pos_y + 1, pos_x);  // then i can either go south
        floodFill(pos_y - 1, pos_x);  // or north
        floodFill(pos_y, pos_x + 1);  // or east
        floodFill(pos_y, pos_x - 1);  // or west
        return
    }

    var setFlagNumber = 0
    while (true) {
        print("Set/unset mines marks or claim a cell as free: > ")
        readInput()
        println()
        when (act) {
            "mine" -> {
                if (displayField[y][x] == '*') {
                    displayField[y][x] = '.'
                    setFlagNumber--
                } else if (displayField[y][x] != '.') {
                    println("There is a number here!")
                    println()
                } else {
                    displayField[y][x] = '*'
                    setFlagNumber++
                }
                printField()

                if (setFlagNumber == mineNumber) {
                    if (displayField.contentDeepEquals(marksField)) {
                        println("Congratulations! You found all the mines!")
                        break
                    }
                }
            } // Mark field

            "free" -> {
                if (mineField[y][x] == 'X') {
                    for (i in mineField.indices) {
                        for (j in mineField.indices) {
                            if (mineField[i][j] == 'X') {
                                displayField[i][j] = 'X'
                            }
                        }
                    }
                    printField()
                    println("You stepped on a mine and failed!")
                    break
                }
                floodFill(y, x)
                printField()
            }
        }
    }
}