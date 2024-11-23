

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static UserService instance;
    private List<User> users;
    private final String filePath = "data/users.csv";
    private final CSVMapper<User> userMapper = new UserCSVMapper();

    private UserService() {
        loadUsers();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private void loadUsers() {
        try {
            users = CSVReader.getInstance().read(filePath, userMapper, ";");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void createUser(User user) {
        users.add(user);
        saveUsers();
        AuditingService.getInstance().logAction("Create User");
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public void updateUser(String username, User updatedUser) {
        User existingUser = getUserByUsername(username);
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setAddress(updatedUser.getAddress());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setOrders(updatedUser.getOrders());

            saveUsers();
            AuditingService.getInstance().logAction("Update User");
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    public void deleteUser(String username) {
        User userToRemove = getUserByUsername(username);
        if (userToRemove != null) {
            users.remove(userToRemove);
            saveUsers();
            AuditingService.getInstance().logAction("Delete User");
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    private void saveUsers() {
        try {
            CSVWriter.getInstance().write("users.csv", users, new UserCSVMapper());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
