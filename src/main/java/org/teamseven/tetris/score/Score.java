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
    private final String uuid;

    public Score(int score, String name) {
        this.score = score;
        this.name = Optional.ofNullable(name).orElse(DEFAULT_NAME);
        this.date = LocalDate.now();
        this.uuid = UUID.randomUUID().toString();
    }

    public Score(int score, String name, LocalDate date, String uuid) {
        this.score = score;
        this.name = Optional.ofNullable(name).orElse(DEFAULT_NAME);
        this.date = date;
        this.uuid = uuid;
    }

    public String[] toRow() {
        return new String[]{name, score.toString(), date.toString(), uuid};
    }

    @Override
    public int compareTo(Score s) {
        if (this.score.compareTo(s.score) > 0) {
            return 1;
        } else if (this.name.compareTo(s.name) > 0) {
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
        System.out.println("s.uuid = " + s.uuid + " | this.uuid = " + uuid);
        return this.uuid.equals(s.uuid);
    }
}
