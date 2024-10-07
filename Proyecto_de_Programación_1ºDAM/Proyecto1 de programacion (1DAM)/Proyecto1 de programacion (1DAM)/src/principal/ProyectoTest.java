package principal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.jupiter.api.Test;

class ProyectoTest {

	static String url = "jdbc:mysql://localhost:3306/proyectoprogramacion";
	static String root = "root";
	static String pass = "1234";

	@After
	public void eliminarComprador() throws SQLException {  //Para eliminar el comprador de prueba
		try (Connection c = DriverManager.getConnection(url, root, pass)) {
			try (PreparedStatement ps = c.prepareStatement("DELETE FROM Comprador WHERE idComprador = ?")) {
				ps.setString(1, "12345");
				ps.executeUpdate();
			}
		}
	}

	@After
	public void eliminarVendedor() throws SQLException {	//Para eliminar el vendedor de prueba
		try (Connection c = DriverManager.getConnection(url, root, pass)) {
			try (PreparedStatement ps = c.prepareStatement("DELETE FROM Vendedor WHERE idVendedor = ?")) {
				ps.setString(1, "123456");
				ps.executeUpdate();
			}
		}
	}

	@After
	public void eliminarProducto() throws SQLException {	//Para eliminar el Producto de prueba
		try (Connection c = DriverManager.getConnection(url, root, pass)) {
			try (PreparedStatement ps = c.prepareStatement("DELETE FROM Producto WHERE idproducto = ?")) {
				ps.setString(1, "123");
				ps.executeUpdate();
			}
		}
	}

	@Test
	public void testCompradorExiste() throws SQLException {  //Test que comprueba si un vendedor existe
		try (Connection c = DriverManager.getConnection(url, root, pass)) {
			try (PreparedStatement ps = c.prepareStatement(
					"INSERT INTO Comprador (idComprador, nombre, email, contraseña, saldo) VALUES (?, 'CR7', 'ElBicho@gmail.com', '1234', '80000')")) {
				ps.setString(1, "12345");
				ps.executeUpdate();
			} 
		}

		assertTrue(Main.CompradorExiste("12345")); // Devuelve True si el comprador existe

		eliminarComprador();
	}

	@Test
	public void testCrearProducto() throws SQLException {	//Test que comprueba si al crear un producto este no es nulo
		Producto producto = null;
		try (Connection c = DriverManager.getConnection(url, root, pass);
				PreparedStatement ps = c.prepareStatement(
						"INSERT INTO Producto (idproducto, nombre, descripcion, precio) VALUES (?, 'Cinturon Gucci', NULL, '300')")) {
			ps.setString(1, "123");
			ps.executeUpdate();
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					producto = new Producto(rs.getInt("idproducto"), rs.getString("descripcion"),
							rs.getString("nombre"), rs.getDouble("precio"));
					
					Main.showCreateProductoDialog();
					assertNotNull(producto);	//Devuelve NotNull si el producto se crea exitosamente
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		eliminarProducto();
	}

	@Test
	public void testActualizarSueldoVendedor() throws SQLException {	//Test que comprueba si se actualiza el sueldo de un vendedor
		try (Connection c = DriverManager.getConnection(url, root, pass);
				PreparedStatement ps = c.prepareStatement(
						"INSERT INTO Vendedor (IDVendedor, nombre, email, contraseña, Saldo, producto) VALUES (?, 'Benito', 'camela@gmail.com', '4321', ?, '1')")) {
			ps.setString(1, "123456");
			ps.setDouble(2, 50.0);
			ps.executeUpdate();

			PreparedStatement pss = c.prepareStatement("SELECT Saldo FROM Vendedor WHERE IDVendedor = ?");
			pss.setString(1, "123456");
			try (ResultSet rs = pss.executeQuery()) {
				if (rs.next()) {
					double saldo = rs.getDouble("Saldo");

					Main.showEditSaldo();
					assertEquals(50.0, saldo, 0.1);		//Comprueba si al insertar el saldo este se actualiza
				}
			}
		}

		eliminarVendedor();
	}    
}
