package services;


import models.Role;
import models.User;
import repository.UserInterface;
import utiles.DbConnection;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService implements UserInterface {

        private Connection cnx;

        public UserService() {
            cnx = DbConnection.getInstance().getCnx();
        }

        @Override
        public void createUser(User user) {
            String query = "INSERT INTO users (idUser, userName, email, number, status, resumeFile, image, skills, role) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement statement = cnx.prepareStatement(query)) {
                statement.setInt(1, user.getIdUser());
                statement.setString(2, user.getUserName());
                statement.setString(3, user.getEmail());
                statement.setInt(4, user.getNumber());
                statement.setString(5, user.getStatus());
                statement.setObject(6, user.getResumeFile());
                // statement.setBlob(7, user.getImage());
                statement.setString(8, user.getSkills());
                statement.setString(9, user.getRole().toString());

                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Error creating user", e);
            }
        }

    @Override
    public User getUserById(int userId) {
        return null;
    }

    @Override
        public List<User> getAllUsers() {
            List<User> userList = new ArrayList<>();
            String query = "SELECT * FROM users";

            try (PreparedStatement statement = cnx.prepareStatement(query)) {
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    userList.add(mapResultSetToUser(resultSet));
                }

            } catch (SQLException e) {
                throw new RuntimeException("Error retrieving users", e);
            }

            return userList;
        }

        @Override
        public void updateUser(User user) {
            String query = "UPDATE users SET userName=?, email=?, number=?, status=?, resumeFile=?, image=?, skills=?, role=? WHERE idUser=?";

            try (PreparedStatement statement = cnx.prepareStatement(query)) {
                statement.setString(1, user.getUserName());
                statement.setString(2, user.getEmail());
                statement.setInt(3, user.getNumber());
                statement.setString(4, user.getStatus());
                statement.setObject(5, user.getResumeFile());
                // statement.setBlob(6, user.getImage());
                statement.setString(7, user.getSkills());
                statement.setString(8, user.getRole().toString());
                statement.setInt(9, user.getIdUser());

                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Error updating user", e);
            }
        }

        @Override
        public void deleteUser(int userId) {
            String query = "DELETE FROM users WHERE idUser=?";
            try (PreparedStatement statement = cnx.prepareStatement(query)) {
                statement.setInt(1, userId);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Error deleting user", e);
            }
        }

        private User mapResultSetToUser(ResultSet resultSet) throws SQLException {
            return new User(
                    resultSet.getInt("idUser"),
                    resultSet.getString("userName"),
                    resultSet.getString("email"),
                    resultSet.getInt("number"),
                    resultSet.getString("status"),
                    (File) resultSet.getObject("resumeFile"),
                    null,
                    resultSet.getString("skills"),
                    Role.valueOf(resultSet.getString("role"))
            );
        }
    }


