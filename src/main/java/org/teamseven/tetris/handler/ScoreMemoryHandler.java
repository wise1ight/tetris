package org.teamseven.tetris.handler;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.teamseven.tetris.score.Score;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.teamseven.tetris.Const.*;

public class ScoreMemoryHandler {

    public void saveScoreFile(Score score, String fileName) {
        File file = new File(SCORE_ROOT + fileName);

        try {
            CSVWriter writer = new CSVWriter(new FileWriter(file, true));

            String[] row = score.toRow();
            writer.writeNext(row);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Score> getScores(String fileName) {
        File file = new File(SCORE_ROOT + fileName);
        List<Score> list = new ArrayList<>();

        try {
            if (!(file.exists() || file.createNewFile())) {
                throw new RuntimeException();
            }

            CSVReader reader = new CSVReader(new FileReader(file));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String name = nextLine[0];
                Integer score = new Integer(nextLine[1]);
                LocalDate date = LocalDate.parse(nextLine[2]);
                String uuid = nextLine[3];
                String mode = nextLine[4];
                list.add(new Score(score, name, mode, date, uuid));
            }
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
        return list.stream().sorted().collect(Collectors.toList());
    }

    public void clear() {
        File normal = new File(SCORE_ROOT + SCORE_NORMAL_FILE);
        normal.delete();
        File item = new File(SCORE_ROOT + SCORE_ITEM_FILE);
        item.delete();
    }
}
