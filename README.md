# Bingo
University assignment to create a menu-driven text based implementation of a bingo game - Implemented in Java - Scored 100%. The project makes use of Object Orientated Programming and comprises the below classes: 

#### BingoRunner
BingoRunner provides entry to the program by creating and executing an instance of the class BingoController.

#### BingoCard
The BingoCard class represents a bingo card and provides getters and setters for its size and values. Other methods are able to: Mark numbers, identify if the card is a winner, and reset the card for a new game.

#### BingoController
The BingoController is responsible for displaying the menu, and reacting to user input and acting upon it. Additional methods can: Set the size of the required BingoCards, maintain a data structure for storing all player cards, identify the winner, and finally reset all cards when restarting the game.

#### Defaults
The defaults class provides a place for definition of constants and certain getters and setters

#### Toolkit
Toolkit primarily handles input and output. Raw input is trimmed and of leading and trailing white space, and then returned.
