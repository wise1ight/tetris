package org.teamseven.tetris.score;

import com.opencsv.bean.CsvDate;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.teamseven.tetris.Const.DEFAULT_NAME;

public class Score implements Comparable<Score> {

    private final Integer score;
    private final String name;
    @CsvDate("yyyy-MM-dd")
    private final LocalDate date;

    private final String mode;

    private final String uuid;
//    private final Level level;

    public Score(int score, String name, String mode) {
        this.score = score;
        this.name = Optional.ofNullable(name).orElse(DEFAULT_NAME);
        this.date = LocalDate.now();
        this.uuid = UUID.randomUUID().toString();
        this.mode = mode;
    }

    public Score(int score, String name, String mode, LocalDate date, String uuid) {
        this.score = score;
        this.name = Optional.ofNullable(name).orElse(DEFAULT_NAME);
        this.date = date;
        this.uuid = uuid;
        this.mode = mode;
    }

    public String[] toRow() {
        return new String[]{name, score.toString(), date.toString(), uuid, mode};
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score.toString();
    }

    public String getDate() {
        return date.toString();
    }

    public String getUuid() {
        return uuid;
    }

    public String getMode() {
        return mode;
    }

    @Override
    public int compareTo(Score s) {
        if (this.score.compareTo(s.score) < 0) {
            return 1;
        }
        return -1;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Score)) {
            return false;
        }

        Score s = (Score) obj;
        return this.uuid.equals(s.uuid);
    }
}
