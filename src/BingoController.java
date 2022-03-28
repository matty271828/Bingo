import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class BingoController {

    private final String[] mainMenuItems = {"Exit",
            "Play bingo",
            "Set number separator",
            "Create a bingo card",
            "List existing cards",
            "Set bingo card size"};

    /*
          complete constants attached to mainMenuItems
     */
    private final String OPTION_EXIT = " 0: ";
    private final String OPTION_PLAY = " 1: ";
    private final String OPTION_SEPARATOR = " 2: ";
    private final String OPTION_CREATE_CARD = " 3: ";
    private final String OPTION_LIST_CARDS = " 4: ";
    private final String OPTION_SIZE = " 5: ";

    /*
          complete default size of rows / columns as specified in the Defaults class
          create an arraylist of BingoCard cards
          include getters and setters for row / column sizes
     */
    private int currentRowSize = Defaults.DEFAULT_NUMBER_OF_ROWS;
    private int currentColumnSize = Defaults.DEFAULT_NUMBER_OF_COLUMNS;

    /* create an ArrayList of BingoCard cards*/
    ArrayList<BingoCard> cards = new ArrayList<>();

    /*
          implement getters and setters for currentRowSize / currentColumnSize
     */
    public int getCurrentRowSize() {
        /*
              change the return from 0 to the appropriate return
     */
        return currentRowSize;
    }

    public void setCurrentRowSize(int currentRowSize) {
        this.currentRowSize = currentRowSize;
    }

    public int getCurrentColumnSize() {
        /*
              change the return from 0 to the appropriate return
     */
        return currentColumnSize;
    }

    public void setCurrentColumnSize(int currentColumnSize) {
        this.currentColumnSize = currentColumnSize;
    }

    /*
          add a new BingoCard card
     */
    public void addNewCard(BingoCard card) {
        cards.add(card);
    }

    /*
          include an appropriate message to the the number of rows as well as the number of columns
     */
    public void setSize() {
        setCurrentRowSize(parseInt(Toolkit.getInputForMessage(
                "Enter the number of rows for the card")));
        setCurrentColumnSize(parseInt(Toolkit.getInputForMessage(
                "Enter the number of columns for the card")));
        System.out.printf("The bingo card size is set to %d rows X %d columns%n",
                getCurrentRowSize(),
                getCurrentColumnSize());
    }

    /*
           ensure that the correct amount of numbers are entered
           print a message that shows the numbers entered using Toolkit.printArray(numbers)
           create, setCardNumbers and add the card as a BingoCard
     */
    public void createCard() {
        /*
            Calculate how many numbers are required to be entered based on the number of rows / columns
        */
        int numRows = this.currentRowSize;
        int numColumns = this.currentColumnSize;
        int numbersRequired = numRows * numColumns;

        String[] numbers;

        boolean correctAmountOfNumbersEntered = false;

        do {
            numbers = Toolkit.getInputForMessage(
                            String.format(
                                    "Enter %d numbers for your card (separated by " +
                                            "'%s')",
                                    numbersRequired,
                                    Defaults.getNumberSeparator()))
                    .trim()
                    .split(Defaults.getNumberSeparator());

        /*
            Verify if the correctAmountOfNumbersEntered is true or false dependant on the numbersRequired calculation
        */
            if (numbersRequired == numbers.length) {
                correctAmountOfNumbersEntered = true; //changes according to calculation inserted here
            }
        /*
            verify whether the numbers entered is not correct by printing an appropriate message
            verify against the expected output files
        */
            else {
                System.out.printf("Try again: you entered %d numbers instead of %d%n", numbers.length, numbersRequired);
            }
        } while (!correctAmountOfNumbersEntered);

        /*
            Print an appropriate message using ToolKit.printArray() to show the numbers entered
        */
        System.out.println("You entered");
        System.out.println(Toolkit.printArray(numbers));
        /*
              create new BingoCard
         */
        BingoCard card = new BingoCard(numRows, numColumns);
        /*
              setCardNumbers for the new card
         */
        card.setCardNumbers(numbers);
        /*
              add the card to the ArrayList
         */
        addNewCard(card);
    }

    /*
         this method goes with printCardAsGrid() seen below
         when option 4 is chosen to list existing cards it prints each card accordingly
         for example, it should show the following
         Card 0 numbers:
         1  2
         3  4 (check with expected output files)
    */
    public void listCards() {
        /*
              insert code here to find all cards to be printed accordingly
         */
        /*
              call printCardAsGrid() method here, Hint: use getCardNumbers() when getting cards
         */
        // Loop through ArrayList data structure and call getCardNumbers() for each card
        for (int i = 0; i < cards.size(); ++i){
            System.out.printf("Card %2d numbers:%n", i);
            String numbers = cards.get(i).getCardNumbers();

            // Pass return value of getCardNumbers() to printCardAsGrid()
            printCardAsGrid(numbers);
        }
    }

    /*
          this is for option 4, list existing cards where all the cards are printed as a grid
          of rows / columns, so numbers 3 4 5 6 will be printed as follows:
          3  4
          5  6
          it is a follow on method from listCards() and ensures that the grid structure is printed
     */
    public void printCardAsGrid(String numbers) {
        // Variables
        int columnCount = 0;
        boolean separatorIdentified;
        StringBuilder currentNum = new StringBuilder();

        // Loop through numbers string
        for (int i = 0; i < numbers.length(); ++i){
            // Look for number separator to identify integers
            if (!Character.toString(numbers.charAt(i)).equals(Defaults.getNumberSeparator())){
                separatorIdentified = false;
                currentNum.append(numbers.charAt(i));
            }
            else {
                separatorIdentified = true;
            }

            // Separator identified
            if (separatorIdentified || i == numbers.length() - 1){
                // Check whether end of row has been reached
                if ((columnCount + 1) / currentColumnSize == 1){
                    // Print number
                    System.out.printf("%2s",currentNum);

                    // move to next row and back to first column
                    System.out.println();
                    columnCount = 0;
                }
                // Move to next column
                else {
                    // Print number
                    System.out.printf("%2s%s",currentNum,Defaults.getNumberSeparator());
                    ++columnCount;
                }
                // Reset StringBuilder for next pass
                currentNum.setLength(0);
            }
        }
    }

    /*
          use Toolkit.getInputForMessage to enter a new separator
          print a message what the new separator is
     */
    public void setSeparator() {
        String sep = Toolkit.getInputForMessage("Enter the new separator");
        /*
              make use of setNumberSeparator() and getNumberSeparator()
         */
        Defaults.setNumberSeparator(sep);
        System.out.printf("Separator is '%s'%n", Defaults.getNumberSeparator());
    }

    /*
         reset all BingoCards using resetMarked (to false)
     */
    public void resetAllCards() {
        // Loop through cards ArrayList and call resetMarked() method on each card
        for (BingoCard card : cards) {
            card.resetMarked();
        }
    }

    /*
          mark off a number that was called when it equals one of the numbers on the BingoCard
     */
    public void markNumbers(int number) {
        // Loop through each card in cards ArrayList
        for (int i = 0; i < cards.size(); ++i){
            // Call markNumber method on each card
            System.out.printf("Checking card %d for %d%n", i, number);
            cards.get(i).markNumber(number);
        }
    }

   /*
         make use of isWinner() to determine who the winner is
         the method should return the index of who the winner is
    */
    public int getWinnerId() {
        // Loop through cards ArrayList and call isWinner()
        for (int i = 0; i < cards.size(); ++i){
            if (cards.get(i).isWinner())
                return i;
        }
        return Defaults.NO_WINNER;
    }

    /*
          please take note that the game will not end until there is a winning sequence
     */
    public void play() {
        System.out.println("Eyes down, look in!");
        resetAllCards();

        boolean weHaveAWinner;
        do {
            markNumbers(parseInt(
                    Toolkit.getInputForMessage("Enter the next number")
                            .trim()));

            int winnerID = getWinnerId();
            weHaveAWinner = winnerID != Defaults.NO_WINNER;
            if (weHaveAWinner)
                System.out.printf("And the winner is card %d%n", winnerID);
        } while (!weHaveAWinner);
    }

    public String getMenu(String[] menuItems) {
    /* method prints a proper numbered menu*/
        StringBuilder menuText = new StringBuilder();

        String[] menuConstants = {OPTION_EXIT,OPTION_PLAY,OPTION_SEPARATOR,OPTION_CREATE_CARD,OPTION_LIST_CARDS,OPTION_SIZE};

        // Loop to build menuText
        for (int i = 0; i < menuItems.length; ++i){
            menuText.append(menuConstants[i]).append(menuItems[i]);

            menuText.append(System.getProperty("line.separator"));
        }
        return menuText.toString();
    }
    /*
          complete the menu using switch to call the appropriate method calls
     */
    public void run() {
        boolean finished = false;
        do {
            switch (Toolkit.getInputForMessage(getMenu(mainMenuItems))) {
                // Exit program
                case "0":
                    finished = true;
                    break;
                // Play bingo
                case "1":
                    play();
                    break;
                // Set number separator
                case "2":
                    setSeparator();
                    break;
                // Create a bingo card
                case "3":
                    createCard();
                    break;
                // List existing cards
                case "4":
                    listCards();
                    break;
                // Set bingo card size
                case "5":
                    setSize();
                    break;
            }
        } while (!finished);
    }
}
