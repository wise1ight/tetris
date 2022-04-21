package org.teamseven.tetris.block.generator;

import org.teamseven.tetris.block.*;
import org.teamseven.tetris.enums.Mode;
import org.teamseven.tetris.handler.PreferencesHandler;

import java.util.Random;

import static org.teamseven.tetris.enums.Mode.EASY;

public class RouletteWheelGenerator implements BlockGenerator {

    private final double[] totalFitness = new double[] {65, 70, 75};
    private final double[] IBlockFitness = new double[] {15, 10, 5};
    private final double[] OBlockFitness = new double[] {10, 10, 10};
    private final double[] TBlockFitness = new double[] {10, 10, 10};
    private final double[] JBlockFitness = new double[] {10, 10, 10};
    private final double[] LBlockFitness = new double[] {10, 10, 10};
    private final double[] SBlockFitness = new double[] {5, 10, 15};
    private final double[] ZBlockFitness = new double[] {5, 10, 15};
    private double[] cumProb;

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
//
//    private double[] getCumProb(double[] prob) {
//        double[] cumProb = new double[7];
//
//        cumProb[0] = prob[0];
//        for (int i = 1; i < 7; i++) {
//            cumProb[i] = cumProb[i - 1] + prob[i];
//        }
//        return cumProb;
//    }
//
//    private double[] getProb(int level) {
//        return new double[] {
//                IBlockFitness[level] / totalFitness[level],
//                OBlockFitness[level] / totalFitness[level],
//                TBlockFitness[level] / totalFitness[level],
//                JBlockFitness[level] / totalFitness[level],
//                LBlockFitness[level] / totalFitness[level],
//                SBlockFitness[level] / totalFitness[level],
//                ZBlockFitness[level] / totalFitness[level]
//        };
//    }
}
