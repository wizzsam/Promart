package controller;

import conexion.dao.dao;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.SessionScoped;
import java.io.Serializable;
import java.io.IOException;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;

    private dao dao;
    private UserSessionBean userSessionBean;

    public LoginBean() {
        dao = new dao();
        userSessionBean = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{userSessionBean}", UserSessionBean.class);
    }

    public void login() {
        if (dao.validateUser(username, password)) {
            userSessionBean.setUsername(username);
            userSessionBean.setLoggedIn(true);
            try {
                if ("Admin".equals(username) && "admin12345".equals(password)) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml");
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username or password", null));
        }
    }

    public void logout() {
        userSessionBean.setUsername(null);
        userSessionBean.setLoggedIn(false);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters and setters for username and password
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
}
