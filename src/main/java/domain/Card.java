package domain;

public class Card {
    public enum Type{
        클로버, 하트, 다이아몬드, 스페이드
    }

    private final Type type;
    private final String number;

    public Card(String number, Type type){
        this.type = type;
        this.number = number;
    }

    public String getCardName(){
        return number+type;
    }

    public int getNumber(){
        if(number.equals(CardLetterNumber.J) || number.equals(CardLetterNumber.Q) || number.equals(CardLetterNumber.K)){
            return CardLetterNumber.J.getNumber();
        }
        if(number.equals(CardLetterNumber.A)){
            return CardLetterNumber.A.getNumber();
        }
        return Integer.parseInt(number);
    }
}
