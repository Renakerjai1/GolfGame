package csc439team1.cardgame;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HandTest {
    private Hand hand;
    private Hand copyhand;
    @Before
    public void setUp() throws Exception {
        hand = new Hand();
        hand.addCard(new Card(3, 2));
        hand.addCard(new Card(1, 10));
        hand.addCard(new Card(2, 9));
        hand.addCard(new Card(4, 6));
        copyhand=new Hand(hand.getCards());
    }

    @Test
    public void addCard() {
        hand.addCard(new Card(2, 10));
        assertEquals("[Value: 2, Suit: Spades, Value: 10, Suit: Hearts, Value: 9, Suit: Diamonds, Value: 6, Suit: Clubs, Value: 10, Suit: Diamonds]", hand.toString());
    }
    @Test
    public void getCopyCards(){
        assertEquals("[Value: 2, Suit: Spades, Value: 10, Suit: Hearts, Value: 9, Suit: Diamonds, Value: 6, Suit: Clubs]", copyhand.getCards().toString());
    }
    @Test
    public void getCards() {
        assertEquals("[Value: 2, Suit: Spades, Value: 10, Suit: Hearts, Value: 9, Suit: Diamonds, Value: 6, Suit: Clubs]", hand.getCards().toString());
    }

    @Test
    public void getSize() {
        assertEquals(4, hand.getSize());
    }

    @Test
    public void testToString() {
        assertEquals("[Value: 2, Suit: Spades, Value: 10, Suit: Hearts, Value: 9, Suit: Diamonds, Value: 6, Suit: Clubs]", hand.toString());
    }
    @Test
    public void replaceTestReturn(){
        Card replacecard= new Card(1,2);
        assertEquals("Value: 10, Suit: Hearts",hand.replace(replacecard,1).toString());
    }
    @Test
    public void replaceTestvalue(){
        Card replacecard= new Card(1,2);
        hand.replace(replacecard,1);
        assertEquals("Value: 2, Suit: Hearts",hand.getCards().get(1).getFace());
    }
    @Test
    public void checkIsAllFaceUp(){
        copyhand.flipAllCardsUp();
        assertTrue(copyhand.isAllFaceUp());
    }
    @Test
    public void checkIsAllFaceUpFail(){
        for(int i=0;i<copyhand.getSize()-1;i++){
            copyhand.getCards().get(i).flipUp();
        }
        assertFalse(copyhand.isAllFaceUp());
    }
    @Test
    public void checkSetHand(){
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(1,4)); //4
        cards.add(new Card(2,14)); //1
        cards.add(new Card(4, 2)); //-2
        cards.add(new Card(3, 9)); //9
        cards.add(new Card(1, 5)); //5
        cards.add(new Card(2, 12)); //10
        Hand hand = new Hand(cards);
        hand.flipAllCardsUp();
        copyhand.setHand(hand);
        assertEquals(4,copyhand.getCards().get(0).getValue());
    }
}