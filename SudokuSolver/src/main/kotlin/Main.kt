package solution

class Sudoku(private val puzzle: Array<Array<Int>>) {
    private var iteration: Int = 0

    private fun nextCell(): Pair<Int, Int> {
        for (row in puzzle.indices) {
            for (col in puzzle[row].indices) {
                if (puzzle[row][col] == 0) {
                    return Pair(row, col)
                }
            }
        }
        return Pair(-1, -1)
    }

    private fun isValid(row: Int, col: Int, num: Int): Boolean {
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

    override fun toString(): String {
        return prettyPrintBoard()
    }

    fun solve(): Boolean {
        ++iteration
        val (row, col) = nextCell()
        if (row == -1 && col == -1) {
            println("all cells filled")
            return true;
        } else {
            for (num in 1..9) {
                if (isValid(row, col, num)) {
                    puzzle[row][col] = num
                    if (solve()) {
                        return true
                    }
                    puzzle[row][col] = 0
                }
            }
            return false
        }
    }


    private fun prettyPrintBoard(): String {
        val buffer: StringBuilder = StringBuilder();
        buffer.append("_______________________\n")
        for (i in puzzle.indices) {
            buffer.append("|")
            for (j in puzzle[i].indices) {
                buffer.append("${puzzle[i][j]} ")
                if (j % 3 == 2) {
                    buffer.append("|")
                }
            }
            if (i % 3 == 2) {
                buffer.append("\n______________________\n")
            } else {
                buffer.append("\n")
            }
        }
        return buffer.toString()
    }


    fun nbIterations(): Int {
        return iteration
    }

}

fun run(puzzle: Array<Array<Int>>) {
    val sudoku = Sudoku(puzzle)
    println("Initial Board")
    println(sudoku)

    val begin = System.currentTimeMillis();
    if (sudoku.solve()) {
        val end = System.currentTimeMillis();
        println("Solved Board")
        println(sudoku)
        println("Solved in ${sudoku.nbIterations()} iterations in ${end - begin} milliseconds")
    } else {
        println("failed to solve")
        println(sudoku)
    }
}

fun testDifficult1() {
    println("\n\nRunning Difficult Testcase 1 (Given in the sheet)")
    run(
        arrayOf(
            arrayOf(0, 0, 2, 0, 0, 0, 0, 4, 1),
            arrayOf(0, 0, 0, 0, 8, 2, 0, 7, 0),
            arrayOf(0, 0, 0, 0, 4, 0, 0, 0, 9),
            arrayOf(2, 0, 0, 0, 7, 9, 3, 0, 0),
            arrayOf(0, 1, 0, 0, 0, 0, 0, 8, 0),
            arrayOf(0, 0, 6, 8, 1, 0, 0, 0, 4),
            arrayOf(1, 0, 0, 0, 9, 0, 0, 0, 0),
            arrayOf(0, 6, 0, 4, 3, 0, 0, 0, 0),
            arrayOf(8, 5, 0, 0, 0, 0, 4, 0, 0)
        )
    )
}

fun testDifficult2() {
    println("\n\nRunning Difficult Testcase 2")
    run(
        arrayOf(
            arrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 2, 0, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 0, 3, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
        )
    )
}

fun testDifficult3() {
    println("\n\nRunning Difficult Testcase 3")
    run(
        arrayOf(
            arrayOf(0, 0, 2, 1, 7, 0, 8, 0, 0),
            arrayOf(0, 0, 0, 2, 0, 0, 0, 1, 0),
            arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 4),
            arrayOf(0, 0, 7, 0, 0, 2, 6, 0, 0),
            arrayOf(0, 0, 0, 0, 8, 7, 3, 0, 9),
            arrayOf(0, 4, 3, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 8, 0, 9, 0, 7, 0),
            arrayOf(1, 9, 0, 0, 0, 0, 0, 0, 0),
            arrayOf(0, 5, 0, 0, 0, 0, 0, 0, 6)
        )
    )
}

fun testDifficult4() {
    println("\n\nRunning Difficult Testcase 4")
    run(
        Array(9) {
            Array(9) { 0 }
        }
    )
}

fun testEasy() {
    println("\n\nRunning Easy Testcase (Given in the sheet)")
    run(
        arrayOf(
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
    )
}

fun testDiagonal() {
    println("Running diagonal matrix testcase")
    var array = Array(9) {
        Array(9) { 0 }
    }
    for (row in array.indices) {
        array[row][row] = row+1
    }
    run(array)
}


fun main() {
    testDiagonal()
    testEasy()
    testDifficult1()
    testDifficult2()
    testDifficult3()
    testDifficult4()
}