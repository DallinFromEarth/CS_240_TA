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
- 







#
*Copyright (c) 2024 DallinFromEarth* 

*THE SOFTWARE AND THE CODING LOG IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.*