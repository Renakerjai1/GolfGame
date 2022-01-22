package csc439team1.cardgame;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ShoeTest {
    Shoe testShoe;

    @Before
    public void setUpShoe() {
        testShoe = new Shoe(3);
    }

    @Test
    public void numdecks() {
        assertEquals(3, testShoe.numDecks());
    }

    @Test
    public void testShoeSize() {
        assertEquals(156, testShoe.size());
    }

    @Test
    public void testShoeSizeAfterAPick() {
        try {
            testShoe.pickCard();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(155, testShoe.size());
    }

    @Test
    public void pickCardValidCard() {
        Card testCard = new Card(0, 0);
        try {
            testCard = testShoe.pickCard();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertTrue((testCard.getSuit() > 0 && testCard.getSuit() < 5) && (testCard.getValue() > 1 && testCard.getValue() < 15));
    }

    @Test
    public void removingADeck() {
        int size=testShoe.size()+1;
        try{
            for(int i=0;i<size;i++){
                testShoe.pickCard();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        assertEquals(0,testShoe.numDecks());
    }
    @Test
    public void dealCardsTest(){
        Hand temphand= testShoe.dealCards();
        assertEquals(6,temphand.getSize());
    }
    @Test
    public void dealCardsTestException(){//rewrite
        testShoe = new Shoe(1);
        int size=testShoe.size();
        Hand temphand= new Hand();
        try{
            for(int i=0;i<size-3;i++){
                testShoe.pickCard();
            }
            temphand= testShoe.dealCards();//is used
        }catch (Exception e){
            System.out.println(e.getMessage());
            assertEquals(0,temphand.getSize());
        }
    }
}