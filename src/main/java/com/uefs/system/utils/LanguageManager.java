package com.uefs.system.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class LanguageManager {
    private JsonObject translations;
    private String currentLanguage;
    private String currentScreen;
    private Properties properties;

    public LanguageManager() {
        loadTranslations();
        loadConfig();
    }

    private void loadTranslations() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("src/main/resources/translations.json")));
            translations = JsonParser.parseString(content).getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadConfig() {
        properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get("src/main/resources/config.properties")));
            currentLanguage = properties.getProperty("language", "pt"); // Idioma padrão se não encontrado
        } catch (IOException e) {
            currentLanguage = "pt"; // Fallback para português se houver erro
            saveConfig();
        }
    }

    public void setLanguage(String language) {
        this.currentLanguage = language;
        saveConfig(); // Salva a configuração sempre que o idioma é alterado
    }

    public String getCurrentLanguage() {
        return currentLanguage; // Adicionado para obter o idioma atual
    }

    private void saveConfig() {
        properties.setProperty("language", currentLanguage);
        try {
            properties.store(Files.newOutputStream(Paths.get("src/main/resources/config.properties")), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setScreen(String screen) {
        this.currentScreen = screen;
    }

    public String getTranslation(String key) {
        if (currentScreen == null) {
            throw new IllegalStateException("Current screen not set. Please set the current screen before getting a translation.");
        }
        return translations.getAsJsonObject(currentScreen).getAsJsonObject(currentLanguage).get(key).getAsString();
    }

    public void toggleLanguage() {
        if (currentLanguage.equals("pt")) {
            setLanguage("en");
        } else {
            setLanguage("pt");
        }
    }
}