package controller;

import model.User;
import service.UserService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.io.IOException;
import jakarta.faces.context.FacesContext;
@Named
@RequestScoped
public class RegisterBean {
    private int id;
    private String username;
    private String password;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void register() throws IOException {
        UserService userService = new UserService();
        User user = new User(id, username, password, email);
        userService.registerUser(user);

        // Obtener el contexto de JSF y el bean de usuarios
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UserBean userBean = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{userBean}", UserBean.class);

        // Actualizar la lista de usuarios
        userBean.loadUsers(); // Actualizar la lista de usuarios aquí debería ser suficiente

        // Redirigir a una página de éxito
        FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
    }
}