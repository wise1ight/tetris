package org.teamseven.tetris.block.generator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.teamseven.tetris.block.*;
import org.teamseven.tetris.enums.Mode;
import org.teamseven.tetris.handler.PreferencesHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

class RouletteWheelGeneratorTest {

    RouletteWheelGenerator generator = new RouletteWheelGenerator();
    double errorRange = 0.05;
    int iterCnt = 10000;

    Method initProb;
    Method getOtherProbs;

    @BeforeEach
    void initMethod() throws NoSuchMethodException {
        initProb = generator.getClass().getDeclaredMethod("initProb");
        getOtherProbs = generator.getClass().getDeclaredMethod("getOtherProbs", double.class);
        initProb.setAccessible(true);
        getOtherProbs.setAccessible(true);
    }

    @Test
    @DisplayName("이지 모드 블럭 생성 확률 테스트")
    void roulette_wheel_easy_test() throws InvocationTargetException, IllegalAccessException {
        PreferencesHandler.setMode(Mode.EASY);
        int[] cnt = new int[7];
        double[] prob = (double[]) initProb.invoke(generator);
        double otherProbs = (double) getOtherProbs.invoke(generator, prob[0]);

        System.out.println("prob[0] = " + prob[0]);
        System.out.println("otherProbs = " + otherProbs);

        for (int i = 0; i < iterCnt; i++) {
            Block block = generator.generate();

            if (block instanceof IBlock) {
                cnt[0]++;
            } else if (block instanceof OBlock) {
                cnt[1]++;
            } else if (block instanceof TBlock) {
                cnt[2]++;
            } else if (block instanceof JBlock) {
                cnt[3]++;
            } else if (block instanceof LBlock) {
                cnt[4]++;
            } else if (block instanceof SBlock) {
                cnt[5]++;
            } else if (block instanceof ZBlock) {
                cnt[6]++;
            }
        }

        assertThat(Math.abs((cnt[0] / (double) iterCnt) - prob[0])).isLessThan(errorRange);
        for (int i : cnt) {
            assertThat(Math.abs((i / (double) iterCnt) - otherProbs)).isLessThan(errorRange);
        }
    }

    @Test
    @DisplayName("노말 모드 블럭 생성 확률 테스트")
    void roulette_wheel_normal_test() throws InvocationTargetException, IllegalAccessException {
        PreferencesHandler.setMode(Mode.NORMAL);
        int[] cnt = new int[7];
        double[] prob = (double[]) initProb.invoke(generator);
        double otherProbs = (double) getOtherProbs.invoke(generator, prob[0]);

        System.out.println("prob[0] = " + prob[0]);
        System.out.println("otherProbs = " + otherProbs);

        for (int i = 0; i < iterCnt; i++) {
            Block block = generator.generate();

            if (block instanceof IBlock) {
                cnt[0]++;
            } else if (block instanceof OBlock) {
                cnt[1]++;
            } else if (block instanceof TBlock) {
                cnt[2]++;
            } else if (block instanceof JBlock) {
                cnt[3]++;
            } else if (block instanceof LBlock) {
                cnt[4]++;
            } else if (block instanceof SBlock) {
                cnt[5]++;
            } else if (block instanceof ZBlock) {
                cnt[6]++;
            }
        }

        assertThat(Math.abs((cnt[0] / (double) iterCnt) - prob[0])).isLessThan(errorRange);
        for (int i : cnt) {
            assertThat(Math.abs((i / (double) iterCnt) - otherProbs)).isLessThan(errorRange);
        }
    }

    @Test
    @DisplayName("하드 모드 블럭 생성 확률 테스트")
    void roulette_wheel_hard_test() throws InvocationTargetException, IllegalAccessException {
        PreferencesHandler.setMode(Mode.HARD);
        int[] cnt = new int[7];
        double[] prob = (double[]) initProb.invoke(generator);
        double otherProbs = (double) getOtherProbs.invoke(generator, prob[0]);

        System.out.println("prob[0] = " + prob[0]);
        System.out.println("otherProbs = " + otherProbs);

        for (int i = 0; i < iterCnt; i++) {
            Block block = generator.generate();

            if (block instanceof IBlock) {
                cnt[0]++;
            } else if (block instanceof OBlock) {
                cnt[1]++;
            } else if (block instanceof TBlock) {
                cnt[2]++;
            } else if (block instanceof JBlock) {
                cnt[3]++;
            } else if (block instanceof LBlock) {
                cnt[4]++;
            } else if (block instanceof SBlock) {
                cnt[5]++;
            } else if (block instanceof ZBlock) {
                cnt[6]++;
            }
        }

        assertThat(Math.abs((cnt[0] / (double) iterCnt) - prob[0])).isLessThan(errorRange);
        for (int i : cnt) {
            assertThat(Math.abs((i / (double) iterCnt) - otherProbs)).isLessThan(errorRange);
        }
    }
}