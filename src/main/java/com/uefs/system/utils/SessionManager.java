package com.uefs.system.utils;

import com.google.gson.*;
import com.uefs.system.controller.UserController;
import com.uefs.system.model.User;

import java.io.*;
import java.nio.file.*;
import java.util.UUID;

public class SessionManager {
    private static final String SESSION_FILE_PATH = PathsFile.getSession();
    private final UserController userController = new  UserController();

    public void saveUserSession(User user) {
        JsonObject sessionData = new JsonObject();
        sessionData.addProperty("id", user.getId().toString());
        sessionData.addProperty("login", user.getLogin());
        sessionData.addProperty("name", user.getName());
        sessionData.addProperty("cpf", user.getCpf());
        sessionData.addProperty("password", user.getPassword());
        sessionData.addProperty("email", user.getEmail());
        sessionData.addProperty("isAdmin", user.isAdmin());

        try (FileWriter writer = new FileWriter(SESSION_FILE_PATH)) {
            writer.write(sessionData.toString());
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados da sessão: " + e.getMessage());
        }
    }

    public String getID() {
        User user = loadUserSession();
        if (user != null && user.getId() != null) {
            return user.getId() != null ? user.getId().toString() : "";
        }
        return "";
    }

    public String getLogin() {
        User user = loadUserSession();
        if (user != null && user.getId() != null) {
            return user.getLogin() != null ? user.getLogin() : "";
        }
        return "";
    }

    public String getName() {
        User user = loadUserSession();
        if (user != null && user.getName() != null && !user.getName().isEmpty()) {
            return user.getName() != null ? user.getName() : "";
        }
        return "";
    }

    public String getCPF() {
        User user = loadUserSession();
        if (user != null && user.getId() != null) {
            return user.getCpf() != null ? user.getCpf() : "";
        }
        return "";
    }

    public String getEmail() {
        User user = loadUserSession();
        if (user != null && user.getId() != null) {
            return user.getEmail() != null ? user.getEmail() : "";
        }
        return "";
    }

    public String getPassword() {
        User user = loadUserSession();
        if (user != null && user.getId() != null) {
            return user.getPassword() != null ? user.getPassword() : "";
        }
        return "";
    }

    public Boolean getIsAdmin() {
        User user = loadUserSession();
        if (user != null && user.getId() != null) {
            return user.isAdmin() != null ? user.isAdmin() : null;
        }
        return null;
    }

    public void updateSession(String name, String email, String password) {
        JsonObject sessionData = new JsonObject();
        sessionData.addProperty("id", this.getID().toString());
        sessionData.addProperty("login", this.getLogin());
        sessionData.addProperty("name", name);
        sessionData.addProperty("cpf", this.getCPF());
        sessionData.addProperty("password", password);
        sessionData.addProperty("email", email);
        sessionData.addProperty("isAdmin", this.getIsAdmin());

        try (FileWriter writer = new FileWriter(SESSION_FILE_PATH)) {
            writer.write(sessionData.toString());
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados da sessão: " + e.getMessage());
        }
    }

    public User loadUserSession() {
        if (!Files.exists(Paths.get(SESSION_FILE_PATH))) {
            System.out.println("Arquivo de sessão não encontrado.");
            return null;
        }

        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(SESSION_FILE_PATH)));
            JsonObject sessionData = JsonParser.parseString(jsonString).getAsJsonObject();

            String idString = sessionData.has("id") ? sessionData.get("id").getAsString() : null;
            UUID id = null;

            if (idString != null && !idString.isEmpty()) {
                try {
                    id = UUID.fromString(idString);
                } catch (IllegalArgumentException e) {
                    System.out.println("ID inválido no arquivo de sessão: " + idString);
                    return null;
                }
            }

            if (!sessionData.has("login") || !sessionData.has("name") || !sessionData.has("cpf") || !sessionData.has("email") || !sessionData.has("isAdmin")) {
                System.out.println("Dados da sessão estão incompletos.");
                return null;
            }

            String login = sessionData.get("login").getAsString();
            String name = sessionData.get("name").getAsString();
            String cpf = sessionData.get("cpf").getAsString();
            String password = sessionData.get("password").getAsString();
            String email = sessionData.get("email").getAsString();
            Boolean isAdmin = sessionData.get("isAdmin").getAsBoolean();

            return new User(id, login, password, name, cpf, email, isAdmin);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de sessão: " + e.getMessage());
            return null;
        } catch (JsonParseException e) {
            System.out.println("Erro ao analisar o arquivo de sessão: " + e.getMessage());
            return null;
        }
    }

    public void clearUserSession() {
        JsonObject sessionData = new JsonObject();
        sessionData.addProperty("id", "");
        sessionData.addProperty("login", "");
        sessionData.addProperty("name", "");
        sessionData.addProperty("cpf", "");
        sessionData.addProperty("password", "");
        sessionData.addProperty("email", "");
        sessionData.addProperty("isAdmin", "");

        try (FileWriter writer = new FileWriter(SESSION_FILE_PATH)) {
            writer.write(sessionData.toString());
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados da sessão: " + e.getMessage());
        }
    }

    public boolean isSessionActive() {
        Path sessionFilePath = Paths.get(SESSION_FILE_PATH);

        if (!Files.exists(sessionFilePath)) {
            return false;
        }

        try {
            String jsonString = new String(Files.readAllBytes(sessionFilePath));
            JsonObject sessionData = JsonParser.parseString(jsonString).getAsJsonObject();

            String id = sessionData.get("id").getAsString();

            if(id == null || id.isEmpty()) return false;

            User user = userController.getById(UUID.fromString(id));

            if(user == null) clearUserSession();

            return user != null && !user.getLogin().isEmpty();
        } catch (IOException | JsonParseException e) {
            System.out.println("Erro ao verificar a sessão: " + e.getMessage());
            return false;
        }
    }

}
