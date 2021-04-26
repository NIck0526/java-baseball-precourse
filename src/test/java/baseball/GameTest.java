package baseball;

import baseball.player.Computer;
import baseball.player.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Computer computer;
    private User user;
    private Score score;
    private Game game;
    private int ballCount;

    @BeforeEach
    void init() {
        computer = new Computer();
        user = new User();
        score = new Score();
        game = new Game();
        ballCount = 3;
    }

    @DisplayName("비교한 게임의 결과")
    @Test
    void 게임결과() {

        //given
        // 3 strike
        List<Integer> comTest1 = new ArrayList<>();
        comTest1.add(1);
        comTest1.add(2);
        comTest1.add(9);

        // 1 strike 2 ball
        List<Integer> comTest2 = new ArrayList<>();
        comTest2.add(6);
        comTest2.add(8);
        comTest2.add(3);

        int[] userTest1 = {1, 2, 9};
        int[] userTest2 = {8, 6, 3};
        List<Integer> strikeResult = new ArrayList<>();
        List<Integer> ballResult = new ArrayList<>();

        //when
        score.init();
        for (int i = 0; i < ballCount; i++) {
            game.isStrike(comTest1.get(i), userTest1[i], score);
            game.isBall(comTest1, userTest1[i], score, i);
        }
        strikeResult.add(score.getStrike());
        ballResult.add(score.getBall());

        score.init();
        for (int i = 0; i < ballCount; i++) {
            game.isStrike(comTest2.get(i), userTest2[i], score);
            game.isBall(comTest2, userTest2[i], score, i);
        }
        strikeResult.add(score.getStrike());
        ballResult.add(score.getBall());

        //then
        assertThat(strikeResult.get(0)).isEqualTo(3);
        assertThat(ballResult.get(0)).isEqualTo(0);

        assertThat(strikeResult.get(1)).isEqualTo(1);
        assertThat(ballResult.get(1)).isEqualTo(2);
    }

}