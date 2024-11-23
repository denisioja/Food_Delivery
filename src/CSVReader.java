//////import java.io.BufferedReader;
//////import java.io.FileReader;
//////import java.io.IOException;
//////import java.util.ArrayList;
//////import java.util.List;
//////
//////public class CSVReader<T> {
//////    private static CSVReader instance;
//////
//////    private CSVReader() {
//////    }
//////
//////    public static CSVReader getInstance() {
//////        if (instance == null) {
//////            instance = new CSVReader();
//////        }
//////        return instance;
//////    }
//////
//////    public List<T> read(String filePath, CSVMapper<T> mapper) throws IOException {
//////        List<T> items = new ArrayList<>();
//////        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//////            String line;
//////            while ((line = br.readLine()) != null) {
//////                T item = mapper.map(line.split(","));
//////                items.add(item);
//////            }
//////        }
//////        return items;
//////    }
//////}
////import java.io.BufferedReader;
////import java.io.FileReader;
////import java.io.IOException;
////import java.util.ArrayList;
////import java.util.List;
////
////public class CSVReader {
////    private static CSVReader instance;
////
////    private CSVReader() {}
////
////    public static CSVReader getInstance() {
////        if (instance == null) {
////            instance = new CSVReader();
////        }
////        return instance;
////    }
////
////    public <T> List<T> read(String filePath, CSVMapper<T> mapper) throws IOException {
////        List<T> result = new ArrayList<>();
////        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
////            String line;
////            while ((line = br.readLine()) != null) {
////                // Debugging output
////                System.out.println("Reading line: " + line);
////
////                String[] fields = line.split(",");  // Ensure this splitting works correctly
////                result.add(mapper.map(fields));
////            }
////        }
////        return result;
////    }
////}
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CSVReader {
//    private static CSVReader instance;
//
//    private CSVReader() {}
//
//    public static CSVReader getInstance() {
//        if (instance == null) {
//            instance = new CSVReader();
//        }
//        return instance;
//    }
//
//    public <T> List<T> read(String filePath, CSVMapper<T> mapper) throws IOException {
//        List<T> result = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                // Debugging output
//                System.out.println("Reading line: " + line);
//
//                // Splitting the line into fields properly
//                String[] fields = parseCSVLine(line);
//
//                // Debugging output
//                System.out.println("Fields received for mapping: " + java.util.Arrays.toString(fields));
//
//                result.add(mapper.map(fields));
//            }
//        }
//        return result;
//    }
//
//    private String[] parseCSVLine(String line) {
//        List<String> result = new ArrayList<>();
//        boolean inQuotes = false;
//        StringBuilder currentField = new StringBuilder();
//
//        for (char c : line.toCharArray()) {
//            if (c == '"') {  // toggle inQuotes flag
//                inQuotes = !inQuotes;
//            } else if (c == ',' && !inQuotes) {  // comma outside of quotes
//                result.add(currentField.toString().trim());  // add current field to the result
//                currentField = new StringBuilder();  // reset current field
//            } else {
//                currentField.append(c);  // add character to the current field
//            }
//        }
//
//        // add the last field
//        result.add(currentField.toString().trim());
//
//        return result.toArray(new String[0]);
//    }
//}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private static CSVReader instance;

    private CSVReader() {}

    public static CSVReader getInstance() {
        if (instance == null) {
            instance = new CSVReader();
        }
        return instance;
    }

    public <T> List<T> read(String filePath, CSVMapper<T> mapper, String delimiter) throws IOException {
        List<T> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Reading line: " + line);

                String[] fields = parseCSVLine(line, delimiter);

                System.out.println("Fields received for mapping: " + java.util.Arrays.toString(fields));

                result.add(mapper.map(fields));
            }
        }
        return result;
    }

    private String[] parseCSVLine(String line, String delimiter) {
        List<String> result = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder currentField = new StringBuilder();

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == delimiter.charAt(0) && !inQuotes) {
                result.add(currentField.toString().trim());
                currentField = new StringBuilder();
            } else {
                currentField.append(c);
            }
        }

        result.add(currentField.toString().trim());

        return result.toArray(new String[0]);
    }
}
