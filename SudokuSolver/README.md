# Setup Instructions

1. Import the project in IntelliJ
2. Open the `Main.kt` file in `src/main/kotlin`.
3. Click on the run icon on the left of the main function.

# Architecture
- Algorithm was fairly straightforward out of the book. I have used a backtracking approach, where it backtracks if it finds a conflicting entry in the cell. An entry is conflicting if the same entry is present either in a row, a column, or a block.
- The design in also straightforward. It has a class `Sudoku` to represent the puzzle. All methods except the `run` method are private. The `run` method calls all other methods.
    * `isValid` checks if the sudoku board is valid.
    * `solve` performs the backtracking approach to solve sudoku.
    * To prevent array index overruns, I have used the safe `.indices` present in Kotlin which ensures that it does not go beyond the array size
    * 
- The two test cases are given in `test1` and `test2` functions.
- 
# Issues faced
- In my opinion none. The biggest issue I faced is setting up the kotlin as I am new to this language.