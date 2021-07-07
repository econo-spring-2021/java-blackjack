package view;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {
    public static final String PLAYER_NAME_VIEW = "게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)";

    public static String playerNameView() throws IOException {
        System.out.println(PLAYER_NAME_VIEW);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }
}
