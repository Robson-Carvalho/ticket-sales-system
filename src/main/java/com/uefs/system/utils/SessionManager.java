package com.uefs.system.utils;

import com.google.gson.*;
import com.uefs.system.model.User;

import java.io.*;
import java.nio.file.*;
import java.util.UUID;

public class SessionManager {
    private static final String SESSION_FILE_PATH = PathsFile.getSession();

    public void saveUserSession(User user) {
        JsonObject sessionData = new JsonObject();

        sessionData.addProperty("id", user.getId().toString());
        sessionData.addProperty("login", user.getLogin());
        sessionData.addProperty("name", user.getName());
        sessionData.addProperty("cpf", user.getCpf());
        sessionData.addProperty("email", user.getEmail());
        sessionData.addProperty("isAdmin", user.isAdmin());

        try (FileWriter writer = new FileWriter(SESSION_FILE_PATH)) {
            writer.write(sessionData.toString());
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados da sessão: " + e.getMessage());
        }
    }

    public User loadUserSession() {
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(SESSION_FILE_PATH)));

            JsonObject sessionData = JsonParser.parseString(jsonString).getAsJsonObject();

            UUID id = UUID.fromString(sessionData.get("id").getAsString());
            String login = sessionData.get("login").getAsString();
            String name = sessionData.get("name").getAsString();
            String cpf = sessionData.get("cpf").getAsString();
            String email = sessionData.get("email").getAsString();
            Boolean isAdmin = sessionData.get("isAdmin").getAsBoolean();

            return new User(login, null, name, cpf, email, isAdmin);  // Senha não é salva por segurança
        } catch (IOException | JsonParseException e) {
            System.out.println("Erro ao carregar os dados da sessão: " + e.getMessage());
            return null;
        }
    }

    public void clearUserSession() {
        try {
            Files.deleteIfExists(Paths.get(SESSION_FILE_PATH));
        } catch (IOException e) {
            System.out.println("Erro ao apagar os dados da sessão: " + e.getMessage());
        }
    }

    public boolean isSessionActive() {
        return Files.exists(Paths.get(SESSION_FILE_PATH));
    }
}
