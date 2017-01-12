import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> cards;
	private int combination;
	private int highCard;
	
	public Hand(String input[]){
		cards = new ArrayList<Card>(5);
		for (int i = 0; i < input.length; i++)
			cards.add(new Card(input[i]));	
	}
	
	public void sort() {
		
		for(int i = 0; i < cards.size(); i++){
			int indexOfMin = i, min = getValue(i);
			for(int j = i+ 1; j < cards.size(); j++){
				if(getValue(j) < min) {
					min = getValue(j);
					indexOfMin = j;
				}
			}
			Card tmp = getCard(i);
			cards.set(i, getCard(indexOfMin));
			cards.set(indexOfMin, tmp);
		}
	}
	
	public void printHand(){
		for(int i = 0; i<cards.size(); i++)
			System.out.println(getCard(i));
	}
	public int getValue(int i){
		return cards.get(i).getValue();
	}
	public char getSuit(int i){
		return cards.get(i).getSuit();
	}
	public int getCombination(){
		return combination;
	}
	public int getHighCard(){
		return highCard;
	}

	public String printCombination(){
		int HC = getHighCard();
		String alphaHC = " ";
		switch (HC){
			case 14: alphaHC = "A";
			case 13: alphaHC = "K";
			case 12: alphaHC = "Q";
			case 11: alphaHC = "J";

		}

		int combo = getCombination();
		switch (combo){
			case 0: return "with a highcard of " + alphaHC;
			case 1: return "with a pair";
			case 2: return "with two pair";
			case 3: return "with three of a kind";
			case 4: return "with a straight";
			case 5: return "with a flush";
			case 6: return "with a full house";
			case 7: return "with four of a kind";
			case 8: return "with a straight flush";
		
		}
		return null;
		
	}
	public Card getCard(int i){
		return cards.get(i);
		
	}
	public void setCombination(int combination) {
		this.combination = combination;		
	}
	public void setHighCard(int highCard) {
		this.highCard = highCard;
	}
}
