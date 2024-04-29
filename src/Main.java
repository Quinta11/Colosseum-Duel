// *********************************************************
// Class: Main
// Name: Adrian Quintero
// Date: 12/12/2022
//
// Purpose: Allows the game to run
//
// Attributes: 	N/A
//
// Methods: 	+main(String[] args): void
//
//*************************************************************

public class Main {
    public static void main(String[] args) {
        GameController rGame = new GameController();
        int i = 0;
        do {
            rGame.eventOrder(i);
            i++;
        } while (rGame.playerStatus() && i < 5);
        rGame.endGame();
    }
}