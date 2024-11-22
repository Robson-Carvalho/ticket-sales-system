package com.uefs.system.utils;

import com.google.gson.*;
import com.uefs.system.model.User;

import java.io.*;
import java.nio.file.*;
import java.util.UUID;

public class SessionManager {
    private static final String SESSION_FILE_PATH = PathsFile.getSession();

    // Salva os dados do usuário na sessão
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

    // Carrega os dados do usuário da sessão, caso existam
    public User loadUserSession() {
        if (!Files.exists(Paths.get(SESSION_FILE_PATH))) {
            System.out.println("Arquivo de sessão não encontrado.");
            return null;
        }

        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(SESSION_FILE_PATH)));
            JsonObject sessionData = JsonParser.parseString(jsonString).getAsJsonObject();

            // Verifica se "id" existe e é um UUID válido
            String idString = sessionData.has("id") ? sessionData.get("id").getAsString() : null;
            UUID id = null;

            if (idString != null && !idString.isEmpty()) {
                try {
                    id = UUID.fromString(idString);
                } catch (IllegalArgumentException e) {
                    System.out.println("ID inválido no arquivo de sessão: " + idString);
                    return null; // Ou trate de outra forma, dependendo do contexto
                }
            }

            // Valida outros campos obrigatórios
            if (!sessionData.has("login") || !sessionData.has("name") || !sessionData.has("cpf") || !sessionData.has("email") || !sessionData.has("isAdmin")) {
                System.out.println("Dados da sessão estão incompletos.");
                return null;
            }

            String login = sessionData.get("login").getAsString();
            String name = sessionData.get("name").getAsString();
            String cpf = sessionData.get("cpf").getAsString();
            String email = sessionData.get("email").getAsString();
            Boolean isAdmin = sessionData.get("isAdmin").getAsBoolean();

            return new User(id, login, null, name, cpf, email, isAdmin);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo de sessão: " + e.getMessage());
            return null;
        } catch (JsonParseException e) {
            System.out.println("Erro ao analisar o arquivo de sessão: " + e.getMessage());
            return null;
        }
    }


    public String getName() {
        User user = loadUserSession();
        if (user != null && user.getLogin() != null && !user.getLogin().isEmpty()) {
            return user.getName() != null ? user.getName().split(" ")[0] : "Usuário";
        }
        return "Usuário";  // Retorna um nome padrão se o usuário não estiver logado ou se o nome for nulo
    }

    // Limpa os dados da sessão
    public void clearUserSession() {
        JsonObject sessionData = new JsonObject();
        sessionData.addProperty("id", "");
        sessionData.addProperty("login", "");
        sessionData.addProperty("name", "");
        sessionData.addProperty("cpf", "");
        sessionData.addProperty("email", "");
        sessionData.addProperty("isAdmin", "");

        try (FileWriter writer = new FileWriter(SESSION_FILE_PATH)) {
            writer.write(sessionData.toString());
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados da sessão: " + e.getMessage());
        }
    }

    // Verifica se a sessão está ativa (arquivo existe e o login não é nulo)
    public boolean isSessionActive() {
        Path sessionFilePath = Paths.get(SESSION_FILE_PATH);

        if (!Files.exists(sessionFilePath)) {
            return false;  // Arquivo de sessão não existe
        }

        try {
            String jsonString = new String(Files.readAllBytes(sessionFilePath));
            JsonObject sessionData = JsonParser.parseString(jsonString).getAsJsonObject();

            // Verifica se o login está presente e não é nulo
            String login = sessionData.get("login").getAsString();
            return login != null && !login.isEmpty();  // Retorna true se o login não for nulo ou vazio
        } catch (IOException | JsonParseException e) {
            System.out.println("Erro ao verificar a sessão: " + e.getMessage());
            return false;  // Caso ocorra algum erro, assume que a sessão não está ativa
        }
    }

}
