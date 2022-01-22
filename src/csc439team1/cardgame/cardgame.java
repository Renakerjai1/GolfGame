package csc439team1.cardgame;

public class cardgame {

    public static void main(String[] args){
        //basic items needed to start the game
        CLIView view= new CLIView();
        Controller controller= new Controller(view);

        try {
            controller.playGolf();
        } catch (Exception e){
            System.out.println("There are no cards remaining in the deck");
        }
    }
}
