package com.uefs.system.utils;

import java.nio.file.Paths;

public class PathsFile {
    private static final String STORAGE_BASE_PATH = "src/main/resources/jsons/";
    private static final String LANGUAGE_BASE_PATH = "src/main/resources/languages/";

    public static String getUsersJSON() {return Paths.get(STORAGE_BASE_PATH, "users.json").toString();}

    public static String getCardsJSON() {
        return Paths.get(STORAGE_BASE_PATH, "cards.json").toString();
    }

    public static String getEventsJSON() {
        return Paths.get(STORAGE_BASE_PATH, "events.json").toString();
    }

    public static String getTicketsJSON() {
        return Paths.get(STORAGE_BASE_PATH, "tickets.json").toString();
    }

    public static String getMailsJSON() {
        return Paths.get(STORAGE_BASE_PATH, "mails.json").toString();
    }

    public static String getTransactionsJSON() {return Paths.get(STORAGE_BASE_PATH, "transactions.json").toString();}

    public static String getCommentsJSON() {
        return Paths.get(STORAGE_BASE_PATH, "comments.json").toString();
    }

    public static String getLanguagePTJSON() {return  Paths.get(LANGUAGE_BASE_PATH, "lang_pt.json").toString();}

    public static String getLanguageENJSON() {return  Paths.get(LANGUAGE_BASE_PATH, "lang_en.json").toString();}

    public static String getLanguagePropertiesJSON() {return  Paths.get(LANGUAGE_BASE_PATH, "language.properties").toString();}
}
