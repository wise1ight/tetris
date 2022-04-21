package org.teamseven.tetris.handler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.teamseven.tetris.Const.DEFAULT_SCORE;

class ScoreHandlerTest {

    ScoreHandler scoreHandler = new ScoreHandler();

    @Nested
    @DisplayName("점수 테스트")
    class ScoreTest {

        @Test
        @DisplayName("블럭 1칸 이동 시 점수 테스트")
        void basic_test() {
            scoreHandler.addScoreByMove(1);

            int score = scoreHandler.getScore();

            assertThat(score).isEqualTo(DEFAULT_SCORE);
        }

        @Test
        @DisplayName("블럭 여러 칸 이동 시 점수 테스트")
        void basic_test2() {
            scoreHandler.addScoreByMove(15);

            int score = scoreHandler.getScore();

            assertThat(score).isEqualTo(DEFAULT_SCORE * 15);
        }

        @Test
        @DisplayName("블럭 많을 경우 추가 점수")
        void additional_score_test() {
            scoreHandler.addAlphaScore();

            scoreHandler.addScoreByMove(1);
            int score = scoreHandler.getScore();

            assertThat(score).isEqualTo((int) (DEFAULT_SCORE * 1.1));
        }
    }
}