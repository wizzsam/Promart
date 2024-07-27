/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import conexion.dao.dao;
import model.User;
import java.util.List;

public class UserService {
    private dao dao;

    public UserService() {
        this.dao = new dao();
    }

    public List<User> findAll() {
        return dao.findAllUsers();
    }

    public void updateUser(User user) {
        dao.updateUser(user);
    }

   public void deleteUser(User user) {
        dao.deleteUser(user.getUsername());
    }

    public void registerUser(User user) {
        dao.addUser(user);
    }

    public boolean validateUser(String username, String password) {
        return dao.validateUser(username, password);
    }
}
