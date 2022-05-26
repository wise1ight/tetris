package org.teamseven.tetris.block.generator;

import org.junit.jupiter.api.*;
import org.teamseven.tetris.block.*;
import org.teamseven.tetris.enums.Mode;
import org.teamseven.tetris.handler.PreferencesHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

class RouletteWheelGeneratorEasyTest {

    RouletteWheelGenerator generator = new RouletteWheelGenerator();
    double errorRange = 0.05;
    int iterCnt = 100000;

    Method initProb;
    Method getOtherProbs;

    @Test
    @DisplayName("이지 모드 블럭 생성 확률 테스트")
    void roulette_wheel_easy_test() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        PreferencesHandler.setMode(Mode.EASY);
        initProb = generator.getClass().getDeclaredMethod("initProb");
        getOtherProbs = generator.getClass().getDeclaredMethod("getOtherProbs", double.class);
        initProb.setAccessible(true);
        getOtherProbs.setAccessible(true);
        int[] cnt = new int[7];
        double[] prob = (double[]) initProb.invoke(generator);
        double otherProbs = (double) getOtherProbs.invoke(generator, prob[0]);
        double res = 0;

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
        System.out.println("Expected IBlock num = " + prob[0] * iterCnt);
        System.out.println("Expected otherBlock num = " + otherProbs * iterCnt);
        System.out.println();
        System.out.println("Actual IBLock num= " + cnt[0]);
        System.out.println("0번째 블럭 오차 = " + Math.abs((cnt[0] - iterCnt * prob[0]) / (prob[0] * iterCnt)));
        System.out.println();
        res += Math.abs((cnt[0] - iterCnt * prob[0]) / (prob[0] * iterCnt));
        for (int i = 1; i < cnt.length; i++) {
            System.out.println("Actual otherBlock num = " + cnt[i]);
            System.out.println(i + "번째 블럭 오차 = " + Math.abs((cnt[i] - iterCnt * otherProbs) / (otherProbs * iterCnt)));
            System.out.println();
            res += Math.abs((cnt[i] - iterCnt * otherProbs) / (otherProbs * iterCnt));
        }
        System.out.println("최종 오차 = " + res / 7);
        assertThat(res / 7).isLessThan(errorRange);
    }
}