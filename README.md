# Chess 240
#### *CS 240 TA Dallin's journey through the new CS 240 project*

I'm a new TA for CS 240, and they changed the course project since I took the class. So I need to go through the project and figure it out. Here in this README file I'll log problems I run into and how I solved them so I can better help students with the same problems.

### Project Set-Up
- Hard to figure out lol
- actually super easy lol
- The "client" "server" and "shared" folders need to be at the root of the IntelliJ Project
- Download the starter code, and copy it into a blank folder that you generated when you did '/git clone' to pull your empty starter GitHub project into your system

### Phase 0
- Just define the board first
- Queen/Rook, and Queen/Bishop can share some logic
- I did functions that calculate vertical/horizontal moves, then another that did diagonal moves
- Make sure you're correctly calculating where the edge of the board is
- The interfaces deal in base 1, not base 0
- Would have probably been cleaner code to make a function that checks if the new move is on the board, and another function to check if the new move is running into another piece or capturing
- If you want to check if the space you're looking at is empty or an enemy piece in one if statement, check if the space is null first, the use double pipes "||" then check the color. Single pipe will throw an error
- If things look the same, but the passoff tests aren't saying theyre the same, make sure you overrode equals() and hash()
- THE GAMEBOARD CONSTRUCTOR SHOULD NOT PUT ANY PIECES ON THE BOARD! Only the function resetBoard() should set all the starting pieces up. The constructor should only set itself up with an empty array

### Phase 1
- Ran into a new runtime error: java.util.ConcurrentModificationException
  - fixed by making sure that I wasn't iterating through my hashset in validMoves() at the same time that I was removing invalid moves from that hashset
- Test cases assume that you aren't in check if there is no king on the board at all
- Remember to promote your pawns
- Test cases all you to pass with identical functioning of isInStalemate() and isInCheckmate()
  - don't be a loser. make sure you are checking if you're in check when doing isInCheckmate()

### Phase 3
- If you're having issues starting the server, add `Spark.init()` near the start of `run()`
- The red when it starts up are info statements, not errors



#
*Copyright (c) 2024 DallinFromEarth* 

*THE SOFTWARE AND THE CODING LOG IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.*