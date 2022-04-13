package chat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserSettingsManager {

    private final File userSettingsFile;
    public static String userName;

    public UserSettingsManager() {
        this.userSettingsFile = new File("userName.nmf");
        load();
    }

    public void save() {
        try (PrintWriter printwriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(this.userSettingsFile), StandardCharsets.UTF_8))) {
            printwriter.println(userName);
        } catch (Exception exception) {}
    }

    public void load(){
        try {
            if (!this.userSettingsFile.exists()) {
                userName = "DefaultUser";
                return;
            }
            String content = new String(Files.readAllBytes(Paths.get("userName.nmf")));
            userName = content;
        } catch (Exception exception1) { }
    }

}
