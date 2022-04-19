package org.teamseven.tetris.block.generator;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.teamseven.tetris.Board.GameBoard;
import org.teamseven.tetris.block.*;
import org.teamseven.tetris.block.handler.BlockMovementHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.teamseven.tetris.Const.EASY;

class RouletteWheelGeneratorTest {

    RouletteWheelGenerator generator = new RouletteWheelGenerator();
    Percentage closePercentage = Percentage.withPercentage(95);
    int iterCnt = 100000;

    @Test
    void roulette_wheel_test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getProb = generator.getClass().getDeclaredMethod("getProb", int.class);
        getProb.setAccessible(true);

        int[] cnt = new int[7];
        double[] prob = (double[]) getProb.invoke(generator, EASY);

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

        assertThat(cnt[0] / (double) iterCnt).isCloseTo(prob[0], closePercentage);
        assertThat(cnt[1] / (double) iterCnt).isCloseTo(prob[1], closePercentage);
        assertThat(cnt[2] / (double) iterCnt).isCloseTo(prob[2], closePercentage);
        assertThat(cnt[3] / (double) iterCnt).isCloseTo(prob[3], closePercentage);
        assertThat(cnt[4] / (double) iterCnt).isCloseTo(prob[4], closePercentage);
        assertThat(cnt[5] / (double) iterCnt).isCloseTo(prob[5], closePercentage);
        assertThat(cnt[6] / (double) iterCnt).isCloseTo(prob[6], closePercentage);
    }

}