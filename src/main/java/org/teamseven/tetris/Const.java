package org.teamseven.tetris;

import org.teamseven.tetris.score.ScoreHandler;

import java.awt.*;

public class Const {

    public static int HEIGHT = 20;
    public static int WIDTH = 10;
   // public static String SCORE_ROOT = "score/";
   public static String currentDir = System.getProperty("user.dir");
     public static String SCORE_ROOT = currentDir + "./";

   // public static String SCORE_ROOT = "C:\\Users\\Park sung hyun\\Desktop\\tetris\\src\\main\\java\\org\\teamseven\\tetris\\score\\";

    public static String SCORE_FILE = "score.csv";
    public static String DEFAULT_NAME = "AAA";
}
