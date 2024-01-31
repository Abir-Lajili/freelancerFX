package repository;

import models.User;

import java.util.List;

public interface UserInterface {

    void createUser(User user);

    User getUserById(int userId);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(int userId);
}
