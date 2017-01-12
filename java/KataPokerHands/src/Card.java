
public class Card {
	
	private int value; 
	private char suit;
	private String name;
	
	public Card(String input){
		name = input;
		suit = input.charAt(input.length() - 1);
		String tmp = input.substring(0,input.length() - 1);
		
		if(Character.isDigit(tmp.charAt(0)))
			value = Integer.valueOf(tmp);
		else {
			switch(tmp.charAt(0)) {
			case 'J':
				value = 11;
				break;
			case 'Q':
				value = 12;
				break;
			case 'K':
				value = 13;
				break;
			case 'A':
				value = 14;
				break;
						
			}
		}
	}
	
	public int getValue(){
		return value;
	}
	
	public char getSuit(){
		return suit;
	}
	public String toString(){
		return name;
	}
}
