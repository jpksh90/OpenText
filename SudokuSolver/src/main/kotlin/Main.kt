package org.example

fun isValid(puzzle: Array<Array<Int>>, row: Int, col: Int, num: Int): Boolean {
    for (i in puzzle.indices) {
        if (puzzle[row][i] == num || puzzle[i][col] == num) {
            return false
        }
    }
    val startRow = 3 * (row / 3)
    val startCol = 3 * (col / 3)
    for (i in 0 until 3) {
        for (j in 0 until 3) {
            if (puzzle[startRow + i][startCol + j] == num) {
                return false
            }
        }
    }
    return true
}

fun solve(puzzle: Array<Array<Int>>) : Boolean {
    for (row in puzzle.indices) {
        for (col in puzzle[row].indices) {
            if (puzzle[row][col] == 0) {
                for (num in 1..9) {
                    if (isValid(puzzle, row, col, num)) {
                        // try with a number num
                        puzzle[row][col] = num
                        if (solve(puzzle)) {
                            return true
                        }
                        // else backtrack by setting board[i][j] = 0
                        puzzle[row][col] = 0
                    }
                }
                return false
            }
        }
    }
    return true
}

fun printPuzzle(board: Array<Array<Int>>) {
    for (i in board.indices) {
        for (j in board[i].indices) {
            print("${board[i][j]} ")
        }
        println()
    }
}

fun test1() {
    val puzzle = arrayOf(
        arrayOf(0, 0, 2, 0, 4, 1, 0, 0, 0),
        arrayOf(0, 0, 8, 2, 0, 7, 0, 0, 0),
        arrayOf(0, 0, 4, 0, 0, 0, 0, 0, 9),
        arrayOf(2, 0, 7, 9, 3, 0, 0, 0, 0),
        arrayOf(0, 1, 0, 0, 0, 8, 0, 0, 0),
        arrayOf(0, 0, 6, 8, 1, 0, 0, 0, 4),
        arrayOf(1, 0, 0, 0, 9, 0, 0, 0, 0),
        arrayOf(0, 6, 0, 4, 3, 0, 0, 0, 0),
        arrayOf(8, 5, 0, 0, 0, 0, 4, 0, 0)
    )
    solve(puzzle);
    printPuzzle(puzzle);
}

fun test2() {
    val puzzle = arrayOf(
        arrayOf(0, 1, 3, 8, 0, 0, 4, 0, 5),
        arrayOf(0, 2, 4, 6, 0, 5, 0, 0, 0),
        arrayOf(0, 8, 7, 0, 0, 0, 9, 3, 0),
        arrayOf(4, 9, 0, 3, 0, 6, 0, 0, 0),
        arrayOf(0, 0, 1, 0, 0, 0, 5, 0, 0),
        arrayOf(0, 0, 0, 7, 0, 1, 0, 9, 3),
        arrayOf(0, 6, 9, 0, 0, 0, 7, 4, 0),
        arrayOf(0, 0, 0, 2, 0, 7, 6, 8, 0),
        arrayOf(1, 0, 2, 0, 0, 8, 3, 5, 0)
    )
    solve(puzzle)
    printPuzzle(puzzle);
}

fun main() {
    val puzzle : Array<Array<Int>> = arrayOf(
        arrayOf(1,3,8,0,0,0,0,0,4),
        arrayOf(0,0,0,0,5,0,0,0,0),
        arrayOf(2,4,6,5,0,0,0,0,0),
        arrayOf(8,7,0,0,0,0,0,0,9),
        arrayOf(0,0,3,0,0,0,0,0,0),
        arrayOf(0,0,0,0,0,0,0,0,0),
        arrayOf(0,0,0,0,0,0,0,0,0),
        arrayOf(0,0,0,0,0,0,0,0,0),
        arrayOf(0,0,0,0,0,0,0,0,0)
    )
    solve(puzzle);
    printPuzzle(puzzle)
}