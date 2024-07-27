/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import conexion.dao.dao;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import model.User;
import service.UserService;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {
    private List<User> users;
    private dao dao;
    private User selectedUser;
    private UserService userService;

    public UserBean() {
        dao = new dao();
    }

     @PostConstruct
    public void init() {
        userService = new UserService();
        loadUsers();
    }

    public dao getDao() {
        return dao;
    }

    public void setDao(dao dao) {
        this.dao = dao;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    public void loadUsers() {
        users = dao.findAllUsers();
    }
     public void loadUserForEdit(User user) {
        selectedUser = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }
     public void updateUser() {
        userService.updateUser(selectedUser);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario actualizado con éxito"));
        loadUsers();
    }
    public void editUser(User user) {
        selectedUser = user;
        // Lógica para editar usuario
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario editado: " + user.getUsername()));
    }

   public void deleteUser(User user) {
        userService.deleteUser(user);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario eliminado con éxito"));
        loadUsers(); // Recargar la lista de usuarios después de eliminar
    }

    // Método para registrar un nuevo usuario y actualizar la lista
    public void registerUser(User user) {
        dao.addUser(user);
        loadUsers();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario registrado: " + user.getUsername()));
    }
}