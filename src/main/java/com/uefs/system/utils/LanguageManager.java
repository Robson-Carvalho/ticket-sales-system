package com.uefs.system.utils;

import com.google.gson.*;
import com.uefs.system.Interface.ILanguageObserver;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LanguageManager {
    private static final String lang_path = PathsFile.getLanguageJSON(); // Caminho para o arquivo único JSON
    private static final String lang_properties_path = PathsFile.getLanguagePropertiesJSON();
    private static JsonObject currentLanguage = new JsonObject();
    private final Properties properties = new Properties();

    private final List<ILanguageObserver> observers = new ArrayList<>();

    public LanguageManager() {
        loadLanguage();
    }

    public void addObserver(ILanguageObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (ILanguageObserver observer : observers) {
            observer.updateLanguage();
        }
    }

    public String getText(String keyPath) {
        try {
            return this.getNestedJsonValue(currentLanguage, keyPath);
        } catch (Exception e) {
            System.out.println("Text not found for key: " + keyPath + " - Error: " + e.getMessage());
            return "None";
        }
    }

    private String getNestedJsonValue(JsonObject jsonObject, @NotNull String keyPath) {
        String[] keys = keyPath.split("\\.");

        JsonElement currentElement = jsonObject;
        for (String key : keys) {
            if (currentElement == null || !currentElement.isJsonObject()) {
                return "None";
            }
            jsonObject = currentElement.getAsJsonObject();
            currentElement = jsonObject.get(key);
        }

        return currentElement != null && currentElement.isJsonPrimitive() ? currentElement.getAsString() : "None";
    }

    public String getLanguagePropertiesCurrent() {
        try (FileInputStream fis = new FileInputStream(lang_properties_path)) {
            properties.load(fis);
            return properties.getProperty("language");
        } catch (IOException e) {
            System.out.println("Error loading language properties");
        }
        return null;
    }

    public void setLanguagePropertiesCurrent() {
        String language = this.getLanguagePropertiesCurrent();

        if (language.equals("pt")) {
            properties.setProperty("language", "en");
        } else {
            properties.setProperty("language", "pt");
        }

        try (FileOutputStream fos = new FileOutputStream(lang_properties_path)) {
            properties.store(fos, null);
            loadLanguage();
            notifyObservers();
        } catch (IOException e) {
            System.out.println("Error saving language properties");
        }
    }

    public void loadLanguage() {
        String language = getLanguagePropertiesCurrent();

        try (FileReader reader = new FileReader(lang_path)) {
            JsonElement jsonElement = JsonParser.parseReader(reader);

            if (jsonElement.isJsonObject()) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();

                if (language.equals("pt")) {
                    currentLanguage = jsonObject.getAsJsonObject("pt_br");
                } else {
                    currentLanguage = jsonObject.getAsJsonObject("en_us");
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading language");
        }
    }


    public void toggleLanguage() {
        this.setLanguagePropertiesCurrent();
    }
}
