public class Hooks {
    public static String[] stringSorter(String longString) {
        String cleanedLongString = longString.replaceAll("[^a-zA-Z0-9\\s]", " ");
        String[] parts = cleanedLongString.trim().toUpperCase().split("\\s+");
        return parts;
    }
}
