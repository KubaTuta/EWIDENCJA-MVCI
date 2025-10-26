package utils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    private static void loopRemovingLineBreaks(String[] carAttributes) {
        for (int i = 0; i < carAttributes.length; i++) {
            carAttributes[i] =
                    (carAttributes[i] == null) ? "" : carAttributes[i].replaceAll("//R+", " ").trim();
        }
    }

    public static List<String[]> readCsvFile(Path path) {
        List<String> rows = null;
        try {
            rows = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<String[]> newRows = new ArrayList<>();
        for (String row : rows) {
            String[] carAttributes = row.split(";", -1);

            loopRemovingLineBreaks(carAttributes);

            newRows.add(carAttributes);
        }
        return newRows;
    }
}
