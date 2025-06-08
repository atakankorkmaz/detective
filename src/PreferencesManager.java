import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
public class PreferencesManager {
    static final String CONFIG_PATH = System.getProperty("user.home") + "/preferences.properties";
    public static final Properties properties = new Properties();

    static {
        try (FileInputStream in = new FileInputStream(CONFIG_PATH)) {
            properties.load(in);
        } catch (IOException e) {
        }
    }

    public static String get(String key) {
        String defaultValue = "null";
        return properties.getProperty(key, defaultValue);
    }

    public static void set(String key, String value) {
        properties.setProperty(key, value);
    }
    public static void save() {
        try (FileOutputStream out = new FileOutputStream(CONFIG_PATH)) {
            properties.store(out, "App Preferences");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
