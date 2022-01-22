package csc439team1.cardgame;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ControllerTest {
    protected TestView view;
    protected Controller controller;

    @Before
    public void setUpView(){
        view= new TestView(1);
        controller= new Controller(view);
        try {
            controller.playGolf();
        }catch (Exception e){
            System.out.println("You have ran out of cards");
        }
    }
    @Test
    public void testGetPlayersTurn() {
        assertTrue((0==controller.getPlayersTurn())||(1==controller.getPlayersTurn()));
    }
    @Test
    public void testGetDiscardpileSize() {
        view= new TestView(1);
        controller= new Controller(view);
        Card testCard= new Card(1,5);
        controller.addToDiscard(testCard);
        assertEquals(1,controller.getDiscardpile().size());
    }
    @Test
    public void testGetDiscardpileFace(){
        assertTrue(!controller.getDiscardpile().peek().isFaceDown());
    }
    @Test
    public void testGetNumberOfPlayers() {
        assertEquals(2,controller.getNumberOfPlayers());
    }
    @Test
    public void testGetNumberOfDecks(){
        assertEquals(1,controller.getNumberOfDecks());
    }
    @Test
    public void testGetNumberOfDecksSecond(){
        view= new TestView(2);
        controller= new Controller(view);
        view.setQuitting(true);
        try {
            controller.playGolf();
        }catch (Exception e){
            System.out.println("You have ran out of cards");
        }
        assertEquals(2,controller.getNumberOfDecks());
    }
    @Test
    public void testGetCurrentPlayers(){
        assertEquals(6,controller.getCurrentPlayers().get(0).getHeldHand().getSize());
    }
    @Test
    public void testQuit(){
        view= new TestView(1);
        view.setQuitting(true);
        controller= new Controller(view);
        try {
            controller.playGolf();
        }catch (Exception e){
            System.out.println("You have ran out of cards");
        }
        assertEquals(1,controller.getDiscardpile().size());//1 card in the discard
        assertEquals(39,controller.getPlayingShoe().size());// 39 in the draw, leaves 12 for the two players.
    }

    @Test
    public void testScoring() {
        //create array of cards to be added to hand
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(1,4)); //4
        cards.add(new Card(2,14)); //1
        cards.add(new Card(4, 2)); //-2
        cards.add(new Card(3, 9)); //9
        cards.add(new Card(1, 5)); //5
        cards.add(new Card(2, 12)); //10
        Hand hand = new Hand(cards);
        hand.flipAllCardsUp();

        //Create player and add it to array to pass to controller
        ArrayList<Player> players = new ArrayList<>();
        Player player = new Player(hand, "testPlayer");
        players.add(player);

        controller.setPlayers(players);
        controller.tallyScore();

        assertEquals(27, controller.getCurrentPlayers().get(0).getScore());
    }

    @Test
    public void testScoringWithDupes() {
        //create array of cards to be added to hand
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(1,4)); //4
        cards.add(new Card(2,14)); //0 since dupe with index 4
        cards.add(new Card(4, 2)); //-2
        cards.add(new Card(3, 9)); //9
        cards.add(new Card(1, 14)); //0 since dupe with index 1
        cards.add(new Card(2, 12)); //10
        Hand hand = new Hand(cards);
        hand.flipAllCardsUp();

        //Create player and add it to array to pass to controller
        ArrayList<Player> players = new ArrayList<>();
        Player player = new Player(hand, "testPlayer");
        players.add(player);

        controller.setPlayers(players);
        controller.tallyScore();

        assertEquals(21, controller.getCurrentPlayers().get(0).getScore());
    }

}