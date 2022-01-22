package csc439team1.cardgame;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest extends TestCase {
    Player testPlayer;
    Hand hand = new Hand();

    @Before
    public void setUp(){
        hand.addCard(new Card(1,3));
        hand.addCard(new Card(1,4));
        hand.addCard(new Card(2,9));
        hand.addCard(new Card(1,10));
        hand.addCard(new Card(3,11));
        hand.addCard(new Card(4,13));
        testPlayer= new Player(hand, "Test Player");
    }
    @Test
    public void testGetHeldHand() {
        assertEquals(6,testPlayer.getHeldHand().getSize());
    }
    @Test
    public void testTestGetName() {
        assertEquals("Test Player",testPlayer.getName());
    }
    @Test
    public void testGetScore(){
        testPlayer.setScore(5);
        assertEquals(5,testPlayer.getScore());
    }
}