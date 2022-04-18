package org.teamseven.tetris.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.teamseven.tetris.Const.SCORE_ROOT;

class ScoreHandlerTest {

    ScoreHandler handler = new ScoreHandler();
    String fileName = "test.csv";

    @Test
    @DisplayName("파일 저장 및 불러오기 테스트")
    void file_save_test() {
        int num = (new Random()).nextInt();
        Score score = new Score(num, "아아악" + num);

        List<Score> before = handler.getScores(fileName);
        handler.saveScoreFile(score, fileName);
        List<Score> after = handler.getScores(fileName);

        assertThat(after.size()).isEqualTo(before.size() + 1);
        assertThat(after.contains(score)).isTrue();
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