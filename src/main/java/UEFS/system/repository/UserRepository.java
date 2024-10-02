package main.java.UEFS.system.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import main.java.UEFS.system.interfaces.IRepository;
import main.java.UEFS.system.model.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository implements IRepository<User> {
    private static final String FILE_PATH = "src/main/java/UEFS/system/storage/users.json";
    private final List<User> users;

    public UserRepository() {
        users = loadUsers();
    }

    private List<User> loadUsers() {
        List<User> userList = new ArrayList<>();
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<User>>() {}.getType();
            userList = new Gson().fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList != null ? userList : new ArrayList<>();
    }

    private void saveUsers() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            gson.toJson(users, writer);
        } catch (Exception e) {
            System.out.println("Error while saving users");
        }
    }

    @Override
    public User findById(UUID id) {
        return loadUsers().stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return loadUsers();
    }

    @Override
    public void save(User user) {
        users.add(user);
        saveUsers();
    }

    @Override
    public void update(User user) {
        delete(user.getId());
        users.add(user);
        saveUsers();
    }

    @Override
    public void delete(UUID id) {
        users.removeIf(user -> user.getId().equals(id));
        saveUsers();
    }

    public void deleteAll() {
        users.clear();
        saveUsers();
    }
}
