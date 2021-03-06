package org.teamseven.tetris.score;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.teamseven.tetris.handler.ScoreMemoryHandler;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.teamseven.tetris.Const.SCORE_ROOT;

class ScoreMemoryHandlerTest {

    ScoreMemoryHandler handler = new ScoreMemoryHandler();
    String fileName = "test.csv";

    @AfterEach
    void deleteAll() {
        File file = new File(SCORE_ROOT + fileName);
        file.delete();
    }

    @Test
    @DisplayName("파일 저장 및 불러오기 테스트")
    void file_save_test() {
        int num = (new Random()).nextInt();
        Score score = new Score(num, "아아악" + num, "NORMAL");

        List<Score> before = handler.getScores(fileName);
        handler.saveScoreFile(score, fileName);
        List<Score> after = handler.getScores(fileName);

        assertThat(after.size()).isEqualTo(before.size() + 1);
        assertThat(after.contains(score)).isTrue();
    }

    @Test
    @DisplayName("점수 내림차순 정렬 테스트")
    void score_descending_name() {
        Score score1 = new Score(100, "AAA", "NORMAL");
        Score score2 = new Score(200, "AAA", "NORMAL");
        Score score3 = new Score(100, "AAB", "NORMAL");

        handler.saveScoreFile(score1, fileName);
        handler.saveScoreFile(score2, fileName);
        handler.saveScoreFile(score3, fileName);
        List<Score> scores = handler.getScores(fileName);

        System.out.println(Arrays.toString(scores.get(0).toRow()));

        assertThat(scores.get(0)).isEqualTo(score2);
        assertThat(scores.get(1)).isEqualTo(score3);
        assertThat(scores.get(2)).isEqualTo(score1);
    }

    @Test
    @DisplayName("파일 없을 때 파일 생성")
    void load_when_no_file() {
        String noFile = "noFile.csv";
        List<Score> scores = handler.getScores(noFile);

        assertThat(scores.isEmpty()).isTrue();
        File file = new File(SCORE_ROOT + noFile);
        file.delete();
    }
}