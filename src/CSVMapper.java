public interface CSVMapper<T> {
    T map(String[] csvData);
    String toCSV(T object);
}
