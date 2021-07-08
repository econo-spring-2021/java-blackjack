package domain;

public enum CardLetterNumber {
    K(10),
    Q(10),
    J(10),
    A(1);

    private final int number;

    CardLetterNumber(int number){
        this.number = number;
    }

    public int getNumber(){
        return number;
    }
}
