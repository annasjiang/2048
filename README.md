Core Concepts

  2D ARRAY: I used a 2D array to store the tiles. Each tile had a value and I could
  move the tiles around the grid by changing the values at each index.

  COLLECTIONS: I stored each move in a LinkedList by making a copy of the board with
  every move. I used this to implement an undo function, which essentially removed 
  the most recently added node of the LinkedList and returned the board to its 
  previous state. 

  TESTING: I tested each function to make sure that they were working correctly

Implementation
  
  TILE: the tile class created a single tile with a value (with 0 for empty) and
  had methods for drawing each individual tile given a specific value
  
  TILEIMAGE: the tile image class basically just uploaded pictures for all of my
  tiles and let me set them according to value
  
  BOARD: the board class was a 2D array of tiles and had methods that involved
  moving tiles and checking for winning/losing
  
  GAMEBOARD: the game board class combined the tile and board class and included 
  conditions for how the game should act when the user is playing, such as the
  undo and reset functions, the instructions, and the win/lose screens
  
  GAME: the game class displayed the images of the game and made everything look
  pretty :)
