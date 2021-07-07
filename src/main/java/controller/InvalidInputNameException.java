package controller;

public class InvalidInputNameException extends Exception{
    public static final String INVALID_INPUT_NAME_EXCEPTION = "쉼표를 기준으로 사람의 이름을 입력하세요.";

    public InvalidInputNameException(){
        super(INVALID_INPUT_NAME_EXCEPTION);
    }
}
