package org.teamseven.tetris.block.generator;

import org.teamseven.tetris.block.*;
import org.teamseven.tetris.handler.PreferencesHandler;

import java.util.Random;

public class RouletteWheelGenerator implements BlockGenerator {

    protected double[] cumProb;

    public RouletteWheelGenerator() {
        cumProb = initProb();
    }

    private double[] initProb() {
        double[] cumProb = new double[7];
        cumProb[0] = (1 / 7.) * PreferencesHandler.getMode().blockProb();
        double otherProbs = getOtherProbs(cumProb[0]);

        for (int i = 1; i < 7; i++) {
            cumProb[i] = cumProb[i - 1] + otherProbs;
        }
        return cumProb;
    }

    private double getOtherProbs(double IBlockProb) {
        return (1 - IBlockProb) / 6;
    }

    @Override
    public Block generate() {
        Random random = new Random();
        double num = random.nextInt(100) / 100.;

        if (num < cumProb[0]) {
            return new IBlock();
        } else if (num < cumProb[1]) {
            return new OBlock();
        } else if (num < cumProb[2]) {
            return new TBlock();
        } else if (num < cumProb[3]) {
            return new JBlock();
        } else if (num < cumProb[4]) {
            return new LBlock();
        } else if (num < cumProb[5]) {
            return new SBlock();
        } else {
            return new ZBlock();
        }
    }
}
