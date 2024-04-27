# Setup Instructions

### Setup
You need JRE, Gradle, and Kotlin to run this program. I have bundled a gradle wrapper to run the script.

There are two ways run it

### Through IntelliJ

1. Import the project in IntelliJ
2. Open the `Main.kt` file in `src/main/kotlin`.
3. Click on the run icon on the left of the main function.

### Through gradle

1. Issue the command `./gradlew run` on terminal from the directory.

# Architecture

## Algorithm

The solution follows a backtracking which follows an exhaustive search on the possible configuration of sudoku. For a given state of the candidate solution, it checks if the candidate solution is valid. Then it proceeds to solve with the 
candidate solution. At some point, if it encounters that the solution is no more feasible, it backtracks and starts with another candidate solution.

## Design
- The design in also straightforward. It has a class `Sudoku` to represent the puzzle. 
    * `isValid` checks if the sudoku board is valid.
    * `solve` performs the backtracking approach to solve sudoku.
    * To prevent array index overruns, I have used the safe `.indices` present in Kotlin which ensures that it does not go beyond the array size
- The two test cases are given in `testEasy` and `testDifficult` functions. In addition, another test case `testDiagnonal` is also included in the program.
- The program has been modularized such that the developer can read the program to get the idea what is does. Variable names also follow a simple pattern
# Issues faced
- Choosing the right way to run this program. I wanted the reviewer to run the script on any platform. Therefore, I choose to stick to the native environment where the user can run it via a simple gradle run command.
- Initially, I had the impression that it will overflow for difficult cases. I mitigated it by only backtracking on the unfilled cells. I run it over 4 difficult test cases ans observed the runtimes to see if it is admissible. All experiments were tested on MacBook Pro M3 laptop with 18 GB of RAM.
