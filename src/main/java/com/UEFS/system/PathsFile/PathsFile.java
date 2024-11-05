package main.java.com.UEFS.system.PathsFile;

import java.nio.file.Paths;

public class PathsFile {
    private static final String BASE_PATH = "src/main/resources/jsons/";

    public static String getUsersJSON() {
        return Paths.get(BASE_PATH, "users.json").toString();
    }

    public static String getCardsJSON() {
        return Paths.get(BASE_PATH, "cards.json").toString();
    }

    public static String getEventsJSON() {
        return Paths.get(BASE_PATH, "events.json").toString();
    }

    public static String getTicketsJSON() {
        return Paths.get(BASE_PATH, "tickets.json").toString();
    }

    public static String getMailsJSON() {
        return Paths.get(BASE_PATH, "mails.json").toString();
    }

    public static String getTransactionsJSON() {
        return Paths.get(BASE_PATH, "transactions.json").toString();
    }

    public static String getCommentsJSON() {
        return Paths.get(BASE_PATH, "comments.json").toString();
    }
}
