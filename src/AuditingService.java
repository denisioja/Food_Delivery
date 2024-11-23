import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedWriter;

public class AuditingService {
    private static AuditingService instance;
    private final String filePath = "audit.csv";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private AuditingService() {
    }

    public static AuditingService getInstance() {
        if (instance == null) {
            instance = new AuditingService();
        }
        return instance;
    }

    public void logAction(String actionName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            String timestamp = LocalDateTime.now().format(formatter);
            bw.write(actionName + "," + timestamp);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
