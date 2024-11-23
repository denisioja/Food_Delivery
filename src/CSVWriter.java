import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter<T> {
    private static CSVWriter instance;

    private CSVWriter() {
    }

    public static CSVWriter getInstance() {
        if (instance == null) {
            instance = new CSVWriter();
        }
        return instance;
    }

    public void write(String filePath, List<T> items, CSVMapper<T> mapper) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (T item : items) {
                bw.write(mapper.toCSV(item));
                bw.newLine();
            }
        }
    }
}
