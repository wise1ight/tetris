package org.teamseven.tetris.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.teamseven.tetris.enums.Mode;

import javax.swing.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.teamseven.tetris.Const.DEFAULT_SCORE;
import static org.teamseven.tetris.Const.INIT_DELAY;

class GameHandlerTest {

    GameHandler gameHandler;
    Timer timer;

    @BeforeEach
    void init() {
        gameHandler = new GameHandler(false);
        timer = new Timer(INIT_DELAY, null);
    }

    @Nested
    @DisplayName("블럭 수에 따른 속도 테스트")
    class BlockCntSpeedUpTest {
        @BeforeEach
        void makeBlock() {
            for (int i = 0; i < 10; i++) {
                gameHandler.addBlockCnt();
            }
        }

        @Test
        @DisplayName("이지모드 속도 증가 테스트")
        void speedup_easy_mode_test() {
            PreferencesHandler.setMode(Mode.EASY);

            gameHandler.speedUp(timer);
            System.out.println("timer.getDelay() = " + timer.getDelay());

            assertThat(timer.getDelay()).isLessThan(INIT_DELAY);
        }

        @Test
        @DisplayName("노말모드 속도 증가 테스트")
        void speedup_normal_mode_test() {
            PreferencesHandler.setMode(Mode.NORMAL);

            gameHandler.speedUp(timer);
            System.out.println("timer.getDelay() = " + timer.getDelay());

            assertThat(timer.getDelay()).isLessThan(INIT_DELAY);
        }

        @Test
        @DisplayName("하드모드 속도 증가 테스트")
        void speedup_hard_mode_test() {
            PreferencesHandler.setMode(Mode.HARD);

            gameHandler.speedUp(timer);
            System.out.println("timer.getDelay() = " + timer.getDelay());

            assertThat(timer.getDelay()).isLessThan(INIT_DELAY);
        }

        @Test
        @DisplayName("모드의 속도 차이 테스트")
        void mode_speed_test() {
            Timer timer1 = new Timer(INIT_DELAY, null);
            PreferencesHandler.setMode(Mode.EASY);
            gameHandler.speedUp(timer1);
            int delay1 = timer1.getDelay();

            Timer timer2 = new Timer(INIT_DELAY, null);
            PreferencesHandler.setMode(Mode.NORMAL);
            gameHandler.speedUp(timer2);
            int delay2 = timer2.getDelay();

            Timer timer3 = new Timer(INIT_DELAY, null);
            PreferencesHandler.setMode(Mode.HARD);
            gameHandler.speedUp(timer3);
            int delay3 = timer3.getDelay();

            assertThat(delay1).isGreaterThan(delay2);
            assertThat(delay2).isGreaterThan(delay3);
        }
    }

    @Nested
    @DisplayName("랴인 재웠을 때 테스트")
    class LineEraseTest {

        @Test
        @DisplayName("이지모드 속도 증가 테스트")
        void speedup_easy_mode_test() {
            gameHandler.setErasedLines(1);
            PreferencesHandler.setMode(Mode.EASY);

            gameHandler.speedUp(timer);

            System.out.println("timer.getDelay() = " + timer.getDelay());
            assertThat(timer.getDelay()).isLessThan(INIT_DELAY);
        }

        @Test
        @DisplayName("노말모드 속도 증가 테스트")
        void speedup_normal_mode_test() {
            gameHandler.setErasedLines(1);
            PreferencesHandler.setMode(Mode.NORMAL);

            gameHandler.speedUp(timer);

            System.out.println("timer.getDelay() = " + timer.getDelay());
            assertThat(timer.getDelay()).isLessThan(INIT_DELAY);
        }

        @Test
        @DisplayName("하드모드 속도 증가 테스트")
        void speedup_hard_mode_test() {
            gameHandler.setErasedLines(1);
            PreferencesHandler.setMode(Mode.HARD);

            gameHandler.speedUp(timer);

            System.out.println("timer.getDelay() = " + timer.getDelay());
            assertThat(timer.getDelay()).isLessThan(INIT_DELAY);
        }
    }

    @Nested
    @DisplayName("점수 테스트")
    class ScoreTest {

        @Test
        @DisplayName("블럭 1칸 이동 시 점수 테스트")
        void basic_test() {
            gameHandler.addScoreByMove(1);

            int score = gameHandler.getScore();

            assertThat(score).isEqualTo(DEFAULT_SCORE);
        }

        @Test
        @DisplayName("블럭 여러 칸 이동 시 점수 테스트")
        void basic_test2() {
            gameHandler.addScoreByMove(15);

            int score = gameHandler.getScore();

            assertThat(score).isEqualTo(DEFAULT_SCORE * 15);
        }

        @Test
        @DisplayName("블럭 많을 경우 추가 점수")
        void additional_score_test() {
            for (int i = 0; i < 10; i++) {
                gameHandler.addBlockCnt();
            }
            gameHandler.addScoreByMove(1);

            int score = gameHandler.getScore();

            assertThat(score).isEqualTo(DEFAULT_SCORE * 1.1);
        }
    }
}