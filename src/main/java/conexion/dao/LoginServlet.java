package conexion.dao;

import service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = new UserService();  // Inicializar el servicio de usuario
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los datos del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validar el usuario utilizando el servicio
        boolean valid = userService.validateUser(username, password);

        if (valid) {
            // Redirigir a una página de éxito
            response.sendRedirect("welcome.xhtml");
        } else {
            // Redirigir a una página de error
            response.sendRedirect("loginError.xhtml");
        }
    }
}