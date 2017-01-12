import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.junit.Before;
import org.junit.Test;

public class PokerHandsTest {
	private String inputFile;
	
	@Before
	public void setup() throws Exception{
		inputFile = "test.txt";
	}

	@Test
	public void createCard() throws Exception{
		String input = "KD";
		Card card = new Card(input);
		assertEquals(13,card.getValue());
		assertEquals('D',card.getSuit());
	}
	@Test
	public void createHand() throws Exception{
		String input[] = {"2D","3D","4D","5D","6D"};
		Hand hand = new Hand (input);
		assertEquals('2',hand.getValue(0));
		assertEquals('3',hand.getValue(1));
		assertEquals('4',hand.getValue(2));
		assertEquals('5',hand.getValue(3));
		assertEquals('6',hand.getValue(4));
		
		assertEquals('D', hand.getSuit(0));
		assertEquals('D', hand.getSuit(1));
		assertEquals('D', hand.getSuit(2));
		assertEquals('D', hand.getSuit(3));
		assertEquals('D', hand.getSuit(4));
	}
	@Test
	public void isPair() throws Exception{
		String inputFile = "text.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
		String content = "Black: 2H 5D 6S KC KD  White: 2C 3H 4S 8C AH";
		writer.write(content);
		writer.close();
		PokerHands poker = new PokerHands(inputFile);
		poker.judgeHands();
		assertEquals(1, poker.player1.getCombination());
		assertEquals(13, poker.player1.getHighCard());
	}
	@Test
	public void isTwoPair() throws Exception {
		String inputFile = "text.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
		String content = "Black: 6H 7D 7S KC KD  White: 2C 3H 4S AC AH";
		writer.write(content);
		writer.close();
		PokerHands poker = new PokerHands(inputFile);
		poker.judgeHands();
		assertEquals(2, poker.player1.getCombination());
		assertEquals(13, poker.player1.getHighCard());
	}
	@Test
	public void threeOfKind() throws Exception {
		String inputFile = "text.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
		String content = "Black: 2H 5D 8S 8C 8D  White: 2C 3H 4S 8C AH";
		writer.write(content);
		writer.close();
		PokerHands poker = new PokerHands(inputFile);
		poker.judgeHands();
		assertEquals(3, poker.player1.getCombination());
		assertEquals(8, poker.player1.getHighCard());
		
	}
	@Test
	public void isStraight() throws Exception {
		String inputFile = "text.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
		String content = "Black: 3H 4D 5S 6C 7D  White: 2C 3H 4S 8C AH";
		writer.write(content);
		writer.close();
		PokerHands poker = new PokerHands(inputFile);
		poker.judgeHands();
		assertEquals(4, poker.player1.getCombination());
		assertEquals(7, poker.player1.getHighCard());
		
	}
	@Test
	public void isFlush() throws Exception { 
		String inputFile = "text.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
		String content = "Black: 2H 3H 4H 8H AH  White: 2C 3H 4S 8C AH";
		writer.write(content);
		writer.close();
		PokerHands poker = new PokerHands(inputFile);
		poker.judgeHands();
		assertEquals(5, poker.player1.getCombination());
		assertEquals(14,poker.player1.getHighCard());
	}
	@Test
	public void isFullHouse() throws Exception {
		String inputFile = "text.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
		String content = "Black: 2H 2D 3D 3C 3S  White: 2C 3H 4S 8C AH";
		writer.write(content);
		writer.close();
		PokerHands poker = new PokerHands(inputFile);
		poker.judgeHands();
		assertEquals(6, poker.player1.getCombination());
		assertEquals(3,poker.player1.getHighCard());
	}
	@Test
	public void isFourOfAKind() throws Exception {
		String inputFile = "text.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
		String content = "Black: 2H 3D 3D 3C 3S  White: 2C 3H 4S 8C AH";
		writer.write(content);
		writer.close();
		PokerHands poker = new PokerHands(inputFile);
		poker.judgeHands();
		assertEquals(7, poker.player1.getCombination());
		assertEquals(3,poker.player1.getHighCard());
	
	}
	@Test
	public void isStraightFlush() throws Exception {
		String inputFile = "text.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
		String content = "Black: 2H 3H 4H 5H 6H  White: 2C 3H 4S 8C AH";
		writer.write(content);
		writer.close();
		PokerHands poker = new PokerHands(inputFile);
		poker.judgeHands();
		assertEquals(8, poker.player1.getCombination());
		assertEquals(6,poker.player1.getHighCard());
	}
	
	@Test 
	public void getWinner() throws Exception {
		String inputFile = "text.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
		String content = "Black: 2H 3H 4H 5H 6H  White: 2C 3H 4S 8C AH";
		writer.write(content);
		writer.close();
		PokerHands poker = new PokerHands(inputFile);
		poker.player1.printCombination();
		poker.judgeHands();
		assertEquals("Black wins with a straight flush", poker.getWinner());
	}
	@Test
	public void getTie() throws Exception {
		String inputFile = "text.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
		String content = "Black: 2H 3H 4H 5H 6H  White: 2C 3C 4C 5C 6C";
		writer.write(content);
		writer.close();
		PokerHands poker = new PokerHands(inputFile);
		poker.judgeHands();
		assertEquals("Tie", poker.getWinner());
	}
}
