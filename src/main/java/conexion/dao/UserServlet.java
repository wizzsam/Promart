package conexion.dao;

import controller.UserBean;
import jakarta.faces.context.FacesContext;
import model.User;
import service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();  // Inicializar el servicio de usuario
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los datos del formulario
        String idParam = request.getParameter("id");
        int id = Integer.parseInt(idParam);  // Convertir el parámetro id a entero
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Crear un nuevo objeto User
        User user = new User(id, username, password, email);

        // Registrar el usuario utilizando el servicio
        userService.registerUser(user);

        // Obtener el contexto de JSF y el bean
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UserBean userBean = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{userBean}", UserBean.class);

        // Actualizar la lista de usuarios
        userBean.loadUsers();

        // Enviar una respuesta de éxito al cliente
        response.getWriter().println("User registered successfully!");

        // Verificar los datos recibidos y la inserción
        System.out.println("User registered: " + username + ", " + email);
    }
}