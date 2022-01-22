package csc439team1.cardgame;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Test file for the Deck class
 * @author Ian
 * @version 1.0
 */
public class DeckTest  {
    public Deck testDeck;
    public Deck copyDeck;
    @Before
    public void setUpDeck(){
        testDeck = new Deck();
        copyDeck= new Deck(testDeck);
    }
    @Test
    public void sizeTest(){
        assertEquals(52,testDeck.getSize());
    }
    @Test
    public void sizeCopyTest(){assertEquals(52,copyDeck.getSize());}
    @Test
    public void cardPullSizeCheck(){
        try {
            Card testCard= testDeck.pick();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        assertEquals(51,testDeck.getSize());
    }
    @Test
    public void cardPullValidCard(){
        Card testCard= new Card(0,0);
        try {
            testCard= testDeck.pick();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        assertTrue((testCard.getSuit()>0&&testCard.getSuit()<5)&&(testCard.getValue()>1&&testCard.getValue()<15));
        //making sure the card is a valid suit and value
    }
    @Test
    public void emptyingADeck(){
        try{
            for(int i=0;i<=52;i++){
                testDeck.pick();
            }
        }catch (noCardsLeftException e){
            assertEquals(0,testDeck.getSize());
            assertEquals("No cards remaining in the deck", e.getMessage());
        }
    }
    @Test
    public void getDeckChecksize(){
        assertEquals(52,testDeck.getDeck().size());
    }
}