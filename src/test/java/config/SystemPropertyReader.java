package config;

public class SystemPropertyReader {
    public static String readProperty() {
        return System.getProperty("selenoidUrl");
    }
}
