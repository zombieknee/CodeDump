public class PokerHands {
	public Hand player1, player2;
	private int numOfCards = 5;
	
	
	public PokerHands(String inputFile){
		String black = inputFile.substring(inputFile.indexOf(':')+1 , inputFile.indexOf("White:"));
		String white = inputFile.substring(inputFile.lastIndexOf(':')+1);
		String blackCards[] = black.trim().split(" ");
		String whiteCards[] = white.trim().split(" ");
		player1 = new Hand(blackCards);
		player2 = new Hand(whiteCards);
		
	}
	
	private void judgeHands(){

		player1.sort();
		player2.sort();

		getCombination(player1);
		getCombination(player2);
	}
		
	private void getCombination(Hand player){ 
		if (hasStraightFlush(player)){
			player.setCombination(8);
			if (player.getValue(4) == 14 && player.getValue(3) == 5)
					player.setHighCard(player.getValue(3));
			else
				player.setHighCard(player.getValue(4));
		}
		
		else if (hasFourOfAKind(player)){
			player.setCombination(7);
			player.setHighCard(player.getValue(1));
		}
		
		else if (hasFullHouse(player)){
			player.setCombination(6);
			player.setHighCard(player.getValue(2));
		}
		
		else if (hasFlush(player)) {
			player.setCombination(5);
			player.setHighCard(player.getValue(4));
			
		}
		else if(hasStraight(player)){
			player.setCombination(4);
			if (player.getValue(4) == 14 && player.getValue(3) == 5)
				player.setHighCard(player.getValue(3));
			else
				player.setHighCard(player.getValue(4));
		}
		
		else if(hasThreeOfAKind(player)){
			player.setCombination(3);
			player.setHighCard(player.getValue(2));
			System.out.println(player.getValue(2));
		}
		
		else if(hasTwaPair(player)){
			player.setCombination(2);
			player.setHighCard(player.getValue(3));
			
			
		}
		else if(hasPair(player)){
			player.setCombination(1);
			if(isPair(player.getCard(0),player.getCard(1)) || isPair(player.getCard(2), player.getCard(1)))
				player.setHighCard(player.getValue(1));
			else
				player.setHighCard(player.getValue(3));
		}
		else
			player.setHighCard(player.getValue(4));
	}
	
	private boolean hasFourOfAKind(Hand player) {
		for(int i = 0; i < numOfCards -3; i++) {
			if(isThreeOfAKind(player.getCard(i),player.getCard(i+1),player.getCard(i+2))
					&& isPair(player.getCard(i+2),player.getCard(i+3)))
				return true;
		}
		return false;
	}
	
	private boolean hasStraightFlush(Hand player) {
		return hasFlush(player) && hasStraight(player);
		
	}

	private boolean hasFullHouse(Hand player) {
		return (isPair(player.getCard(0), player.getCard(1)) &&
		isThreeOfAKind(player.getCard(2),player.getCard(3),player.getCard(4))) ||
		(isPair(player.getCard(3), player.getCard(4)) &&
		isThreeOfAKind(player.getCard(0),player.getCard(1),player.getCard(2)));

	}

	private boolean hasFlush(Hand player) {
		for(int i = 0; i< numOfCards -1; i++){
			if(player.getSuit(i) != player.getSuit(i+1))
				return false;
		}
		return true;
	}

	private boolean hasStraight(Hand player) {
		for(int i = numOfCards - 1; i >0; i--){
			int one = player.getValue(i);
			int two = player.getValue(i-1);
			if (one == 14 && two == 5)
				continue;
			if (one - two != 1)
				return false;
			
		}
		return true;
	}

	private boolean hasThreeOfAKind(Hand player) {
		for(int i = 0; i < numOfCards - 2; i++){
			if(isThreeOfAKind(player.getCard(i),player.getCard(i+1),player.getCard(i+2)))
				return true;
		}
		return false;
	}

	private boolean isThreeOfAKind(Card card1, Card card2, Card card3) {
		return card1.getValue() == card2.getValue() && card2.getValue() == card3.getValue();
		
	}

	private boolean hasTwaPair(Hand player) {
		for (int i = 0; i < numOfCards  - 3; i++)
		{
			if(isPair(player.getCard(i), player.getCard(i+1))){
				for( int j= i+2; j < numOfCards -1; j++){
					if(isPair(player.getCard(j), player.getCard(j+1)))
						return true;
					}
				}
			}
		return false;
	}

	private boolean isPair(Card card1, Card card2) {

		return card1.getValue() == card2.getValue();
	}
	
	private boolean hasPair(Hand player){
		for (int i = 0; i < numOfCards  - 1; i++)
		{
			if(isPair(player.getCard(i), player.getCard(i+1)))
				return true;
		}
		return false;
	}
	
	public String getWinner() {
		if(player1.getCombination() == player2.getCombination()){
			if (player1.getHighCard() == player2.getHighCard())
				return resolveTie();
			else
				return player1.getHighCard() > player2.getHighCard() ? "Black wins "+ player1.printCombination() : "White wins!" + player2.printCombination() ;
		}
		else
			return player1.getCombination() > player2.getCombination() ? "Black wins "+ player1.printCombination() : "White wins " + player2.printCombination() ;
	}
	
	private String resolveTie() {
		for (int i = numOfCards-1; i >= 0; i--){
			if(player1.getValue(i) == player2.getValue(i))
				continue;
			if(player1.getValue(i) > player2.getValue(i)){
				player1.setHighCard(player1.getValue(i));
				return "Black wins "+player1.printCombination();
			}
			else {
				player2.setHighCard(player2.getValue(i));
				return "White wins "+player2.printCombination();
			}
		}
		return "Tie";
	}
	
	public static void main(String args[]){
		PokerHands poker = new PokerHands(" Black: 2H 3H 4H 5H 6H  White: 2C 3H 4S 8C AH");
		PokerHands poker2 = new PokerHands("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH");
		PokerHands poker3 = new PokerHands("Black: 2H 4S 4C 2D 4H  White: 2C 8S AS QS 3S");
		PokerHands poker4 = new PokerHands("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C KH");
		PokerHands poker5 = new PokerHands("Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S KH");
		//poker.judgeHands();
		poker2.judgeHands();
		poker3.judgeHands();
		poker4.judgeHands();
		poker5.judgeHands();
		
		//System.out.println(poker.getWinner());
		System.out.println(poker2.getWinner());
		System.out.println(poker3.getWinner());
		System.out.println(poker4.getWinner());
		System.out.println(poker5.getWinner());
		
	}
}
