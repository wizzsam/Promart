package conexion.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.User;

public class dao {

    private Connection cn;

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }

    // Llama al método Conectar en el constructor
    public dao() {
        Conectar();
    }

    public void Conectar() {
        String url = "jdbc:mysql://localhost:3306/capirent";
        String user = "root";
        String password = "abel";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url, user, password);
            cn.setAutoCommit(true);
            System.out.println("Conexión exitosa!");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver de MySQL");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión con la base de datos");
            e.printStackTrace();
        }
    }
 public void updateUser(User user) {
        String query = "UPDATE user SET username = ?, password = ?, email = ? WHERE id = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    public void addUser(User user) {
        String query = "INSERT INTO user (username, password, email) VALUES (?, ?, ?)";

        try {
            if (cn != null) {
                PreparedStatement ps = cn.prepareStatement(query);
                System.out.println("Inserting user: " + user.getUsername() + ", " + user.getPassword() + ", " + user.getEmail());
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());
                int rowsInserted = ps.executeUpdate();
                System.out.println("Rows inserted: " + rowsInserted);  // Mensaje de depuración
                ps.close();
            } else {
                System.out.println("La conexión es nula. No se puede ejecutar la consulta.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validateUser(String username, String password) {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        try {
            if (cn != null) {
                PreparedStatement ps = cn.prepareStatement(query);
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                boolean valid = rs.next(); // Si hay un resultado, las credenciales son válidas
                rs.close();
                ps.close();
                return valid;
            } else {
                System.out.println("La conexión es nula. No se puede ejecutar la consulta.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
       public void addProduct(Product product) {
        String query = "INSERT INTO products (nombre, marca, estado, precio, descripcion, image_url, palabraClave, categoria, uploaded_by, tipoAlquiler, pais, ciudad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setString(1, product.getNombre());
            ps.setString(2, product.getMarca());
            ps.setString(3, product.getEstado());
            ps.setDouble(4, product.getPrecio());
            ps.setString(5, product.getDescripcion());
            ps.setString(6, product.getImageUrl());
            ps.setString(7, product.getPalabraClave());
            ps.setString(8, product.getCategoria());
            ps.setString(9, product.getUploadedBy());
            ps.setString(10, product.getTipoAlquiler());
            ps.setString(11, product.getPais());
            ps.setString(12, product.getCiudad());
            ps.executeUpdate();
            System.out.println("Producto insertado en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al insertar el producto: " + e.getMessage());
            e.printStackTrace();
        }
    }



       public List<Product> findAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        try (PreparedStatement ps = cn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("marca"),
                        rs.getString("estado"),
                        rs.getDouble("precio"),
                        rs.getString("descripcion"),
                        rs.getString("image_url"),
                        rs.getString("palabraClave"),
                        rs.getString("categoria"),
                        rs.getString("uploaded_by"),
                        rs.getString("tipoAlquiler"),
                        rs.getString("pais"),
                        rs.getString("ciudad")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }


        public Product findProductById(int id) {
        String query = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("marca"),
                            rs.getString("estado"),
                            rs.getDouble("precio"),
                            rs.getString("descripcion"),
                            rs.getString("image_url"),
                            rs.getString("palabraClave"),
                            rs.getString("categoria"),
                            rs.getString("uploaded_by"),
                            rs.getString("tipoAlquiler"),
                            rs.getString("pais"),
                            rs.getString("ciudad")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteProduct(int id) {
        String query = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public void updateProduct(Product product) {
        String query = "UPDATE products SET nombre = ?, marca = ?, estado = ?, precio = ?, descripcion = ?, image_url = ?, palabraClave = ?, categoria = ?, uploaded_by = ?, tipoAlquiler = ?, pais = ?, ciudad = ? WHERE id = ?";
        try (PreparedStatement ps = cn.prepareStatement(query)) {
            ps.setString(1, product.getNombre());
            ps.setString(2, product.getMarca());
            ps.setString(3, product.getEstado());
            ps.setDouble(4, product.getPrecio());
            ps.setString(5, product.getDescripcion());
            ps.setString(6, product.getImageUrl());
            ps.setString(7, product.getPalabraClave());
            ps.setString(8, product.getCategoria());
            ps.setString(9, product.getUploadedBy());
            ps.setString(10, product.getTipoAlquiler());
            ps.setString(11, product.getPais());
            ps.setString(12, product.getCiudad());
            ps.setInt(13, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM user";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")
                );
                users.add(user);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

   public void deleteUser(String username) {
        String query = "DELETE FROM user WHERE username = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setString(1, username);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
