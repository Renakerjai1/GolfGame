package csc439team1.cardgame;

/**
 * This is the object model for a player. Holds the hand, name and score
 * for a player
 */
public class Player {
    Hand heldHand;
    String name;
    int score;

    /**
     * @param givenhand holds a player's hand
     * @param givenName holds the player's name
     */
    public Player(Hand givenhand, String givenName){
        heldHand=new Hand(givenhand.getCards());
        name=givenName;
        score=0;
    }

    /**
     * group of getter methods for the attributes of the
     * player class
     */
    public Hand getHeldHand() {
        return heldHand;
    }
    public String getName() {
        return name;
    }
    public int getScore(){return score;}
    public void setScore(int givenScore){score=givenScore;}
}


