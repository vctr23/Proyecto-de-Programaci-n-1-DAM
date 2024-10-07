package principal;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;

public class Main {
	static String url = "jdbc:mysql://localhost:3306/proyectoprogramacion";
	static String root = "root";
	static String pass = "1234";

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Main::createAndShowGUI);
	}
///////////////////////////////////////////////////////////////////
	static void createAndShowGUI() {
		JFrame frame = new JFrame("Sistema de gestión de base de datos");

		frame.setSize(400, 300);
		frame.setLocation(500, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		// panel.setBackground(new Color(102, 0, 102));
		frame.add(panel);
		placeComponents(panel);
		frame.setVisible(true);
	}
///////////////////////////////////////////////////////////////////	
	static void placeComponents(JPanel panel) {
		panel.setLayout(null);
		panel.setBackground(new Color(0, 0, 0));

		JLabel titleLabel = new JLabel("SecondHand Shop");
		titleLabel.setBounds(50, 20, 300, 25);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setForeground(new Color(0, 102, 204));
		panel.add(titleLabel);

		JButton productInfoButton = new JButton("Productos");
		productInfoButton.setBounds(50, 90, 300, 25);
		productInfoButton.setBackground(new Color(0, 153, 76));
		productInfoButton.setForeground(Color.WHITE);
		productInfoButton.setFocusPainted(false);
		panel.add(productInfoButton);
		productInfoButton.addActionListener(e -> showProduct());

		JButton salesInfoButton = new JButton("Ventas");
		salesInfoButton.setBounds(50, 120, 300, 25);
		salesInfoButton.setBackground(new Color(0, 153, 76));
		salesInfoButton.setForeground(Color.WHITE);
		salesInfoButton.setFocusPainted(false);
		panel.add(salesInfoButton);
		salesInfoButton.addActionListener(e -> showVentas());

		JButton newUserButton = new JButton("Usuario");
		newUserButton.setBounds(50, 150, 300, 25);
		newUserButton.setBackground(new Color(0, 153, 76));
		newUserButton.setForeground(Color.WHITE);
		newUserButton.setFocusPainted(false);
		panel.add(newUserButton);
		newUserButton.addActionListener(e -> showUserDialog(panel));

	}
///////////////////////////////////////////////////////////////////	
	static void showUserDialog(JPanel panel) {
		JDialog dialog = new JDialog();
		dialog.setTitle("Gestión de Usuarios");
		dialog.setSize(400, 300);
		dialog.setLocation(500, 250);
		dialog.setLayout(new GridLayout(4, 1));

		JButton userInfoButton = new JButton("Informacion");
		userInfoButton.setBackground(new Color(0, 0, 0));
		userInfoButton.setForeground(Color.WHITE);
		userInfoButton.setFocusPainted(false);
		userInfoButton.addActionListener(e -> showUserInfoMenu());
		dialog.add(userInfoButton);

		JButton newComButton = new JButton("Crear");
		newComButton.setBackground(new Color(0, 0, 0));
		newComButton.setForeground(Color.WHITE);
		newComButton.setFocusPainted(false);
		newComButton.addActionListener(e -> showCreateUserDialog(panel));
		dialog.add(newComButton);

		JButton newEditButton = new JButton("Editar");
		newEditButton.setBackground(new Color(0, 0, 0));
		newEditButton.setForeground(Color.WHITE);
		newEditButton.setFocusPainted(false);
		newEditButton.addActionListener(e -> showEditMenu());
		dialog.add(newEditButton);

		JButton newVenButton = new JButton("Borrar");
		newVenButton.setBackground(new Color(0, 0, 0));
		newVenButton.setForeground(Color.WHITE);
		newVenButton.setFocusPainted(false);
		newVenButton.addActionListener(e -> showDeleteUserDialog());
		dialog.add(newVenButton);

		dialog.setVisible(true);
	}
///////////////////////////////////////////////////////////////////	
	static void showUserInfoMenu() {
		JDialog dialog = new JDialog();
		dialog.setTitle("Información Usuario");
		dialog.setSize(400, 300);
		dialog.setLocation(500, 250);
		dialog.setLayout(new GridLayout(3, 1));

		JButton newComButton = new JButton("Comprador");
		newComButton.setBackground(new Color(0, 0, 0));
		newComButton.setForeground(Color.WHITE);
		newComButton.setFocusPainted(false);
		newComButton.addActionListener(e -> buscarComprador());
		dialog.add(newComButton);

		JButton newVenButton = new JButton("Vendedor");
		newVenButton.setBackground(new Color(0, 0, 0));
		newVenButton.setForeground(Color.WHITE);
		newVenButton.setFocusPainted(false);
		newVenButton.addActionListener(e -> buscarVendedor());
		dialog.add(newVenButton);

		JButton newAllButton = new JButton("Todos");
		newAllButton.setBackground(new Color(0, 0, 0));
		newAllButton.setForeground(Color.WHITE);
		newAllButton.setFocusPainted(false);
		newAllButton.addActionListener(e -> showUserInfo());
		dialog.add(newAllButton);
		dialog.setVisible(true);
	}
///////////////////////////////////////////////////////////////////	
	static void showEditMenu() {
		JDialog dialog = new JDialog();
		dialog.setTitle("Editar Usuario");
		dialog.setSize(400, 300);
		dialog.setLocation(500, 250);
		dialog.setLayout(new GridLayout(3, 1));

		JButton newComButton = new JButton("Comprador");
		newComButton.setBackground(new Color(0, 0, 0));
		newComButton.setForeground(Color.WHITE);
		newComButton.setFocusPainted(false);
		newComButton.addActionListener(e -> editComprador());
		dialog.add(newComButton);

		JButton newVenButton = new JButton("Vendedor");
		newVenButton.setBackground(new Color(0, 0, 0));
		newVenButton.setForeground(Color.WHITE);
		newVenButton.setFocusPainted(false);
		newVenButton.addActionListener(e -> editVendedor());
		dialog.add(newVenButton);

		JButton newSaldoButton = new JButton("Saldo");
		newSaldoButton.setBackground(new Color(0, 0, 0));
		newSaldoButton.setForeground(Color.WHITE);
		newSaldoButton.setFocusPainted(false);
		newSaldoButton.addActionListener(e -> showEditSaldo());
		dialog.add(newSaldoButton);

		dialog.setVisible(true);
	}
///////////////////////////////////////////////////////////////////
	static void showProduct() {
		JDialog dialog = new JDialog();
		dialog.setTitle("Gestión de Producto");
		dialog.setSize(400, 300);
		dialog.setLocation(500, 250);
		dialog.setLayout(new GridLayout(3, 1));

		JButton ProductoInfoButton = new JButton("Informacion");
		ProductoInfoButton.setBackground(new Color(0, 0, 0));
		ProductoInfoButton.setForeground(Color.WHITE);
		ProductoInfoButton.setFocusPainted(false);
		ProductoInfoButton.addActionListener(e -> showProductInfo());
		dialog.add(ProductoInfoButton);

		JButton newCreateButton = new JButton("Crear");
		newCreateButton.setBackground(new Color(0, 0, 0));
		newCreateButton.setForeground(Color.WHITE);
		newCreateButton.setFocusPainted(false);
		newCreateButton.addActionListener(e -> showCreateProductoDialog());
		dialog.add(newCreateButton);

		JButton newDelButton = new JButton("Borrar");
		newDelButton.setBackground(new Color(0, 0, 0));
		newDelButton.setForeground(Color.WHITE);
		newDelButton.setFocusPainted(false);
		newDelButton.addActionListener(e -> showDeleteProducto());
		dialog.add(newDelButton);

		dialog.setVisible(true);
	}
///////////////////////////////////////////////////////////////////	
	static void showVentas() {
		JDialog dialog = new JDialog();
		dialog.setTitle("Gestión de Ventas");
		dialog.setSize(400, 300);
		dialog.setLocation(500, 250);
		dialog.setLayout(new GridLayout(4, 1));

		JButton newVentaButton = new JButton("Crear");
		newVentaButton.setBackground(new Color(0, 0, 0));
		newVentaButton.setForeground(Color.WHITE);
		newVentaButton.setFocusPainted(false);
		newVentaButton.addActionListener(e -> showSalesCreate());
		dialog.add(newVentaButton);

		JButton newDelVenta = new JButton("Borrar");
		newDelVenta.setBackground(new Color(0, 0, 0));
		newDelVenta.setForeground(Color.WHITE);
		newDelVenta.setFocusPainted(false);
		newDelVenta.addActionListener(e -> showDeleteVenta());
		dialog.add(newDelVenta);
		
		JButton mostrarInfoVenta = new JButton("Información");
		mostrarInfoVenta.setBackground(new Color(0, 0, 0));
		mostrarInfoVenta.setForeground(Color.WHITE);
		mostrarInfoVenta.setFocusPainted(false);
		mostrarInfoVenta.addActionListener(e -> showSalesInfo());
		dialog.add(mostrarInfoVenta);

		JButton buscarId = new JButton("Buscar Venta por ID");
		buscarId.setBackground(new Color(0, 0, 0));
		buscarId.setForeground(Color.WHITE);
		buscarId.setFocusPainted(false);
		buscarId.setBounds(50, 90, 300, 25);
		buscarId.addActionListener(e -> buscarVenta());
		dialog.add(buscarId);

		dialog.setVisible(true);
	}
///////////////////////////////////////////////////////////////////
	static void showDeleteUserDialog() {
		// Implementar lógica para borrar usuarios
		JDialog dialog = new JDialog();
		dialog.setTitle("Borrar Usuario");
		dialog.setSize(400, 300);
		dialog.setLocation(500, 250);
		dialog.setLayout(new GridLayout(3, 1));

		JButton newComButton = new JButton("Comprador");
		newComButton.setBackground(new Color(0, 0, 0));
		newComButton.setForeground(Color.WHITE);
		newComButton.setFocusPainted(false);
		newComButton.addActionListener(e -> showDeleteUserDialogComprador());
		dialog.add(newComButton);

		JButton newVenButton = new JButton("Vendedor");
		newVenButton.setBackground(new Color(0, 0, 0));
		newVenButton.setForeground(Color.WHITE);
		newVenButton.setFocusPainted(false);
		newVenButton.addActionListener(e -> showDeleteUserDialogVendedor());
		dialog.add(newVenButton);

		dialog.setVisible(true);

	}
///////////////////////////////////////////////////////////////////	
	static void showCreateUserDialog(JPanel panel) {
		JDialog dialog = new JDialog();
		dialog.setTitle("Gestión de Usuarios");
		dialog.setSize(400, 300);
		dialog.setLocation(500, 250);
		dialog.setLayout(new GridLayout(3, 1));

		JButton newComButton = new JButton("Crear Comprador");
		newComButton.setBackground(new Color(0, 0, 0));
		newComButton.setForeground(Color.WHITE);
		newComButton.setFocusPainted(false);
		newComButton.addActionListener(e -> showNewUserDialogComprador());
		dialog.add(newComButton);

		JButton newVenButton = new JButton("Crear Vendedor");
		newVenButton.setBackground(new Color(0, 0, 0));
		newVenButton.setForeground(Color.WHITE);
		newVenButton.setFocusPainted(false);
		newVenButton.addActionListener(e -> showNewUserDialogVendedor());
		dialog.add(newVenButton);

		dialog.setVisible(true);
	}
///////////////////////////////////////////////////////////////////	
	static void showCreateProductoDialog() {
		JDialog dialog = new JDialog();
		dialog.setTitle("Nuevo Producto");
		dialog.setSize(400, 300);
		dialog.setLocation(500, 250);

		JPanel mainPanel = new JPanel(new BorderLayout());
		dialog.add(mainPanel);

		JPanel inputPanel = new JPanel(new GridLayout(6, 2));
		JTextField descpField = new JTextField();
		JTextField idField = new JTextField();
		JTextField nameField = new JTextField();
		JTextField precioField = new JTextField();

		inputPanel.add(new JLabel("Nombre:"));
		inputPanel.add(nameField);
		inputPanel.add(new JLabel("ID:"));
		inputPanel.add(idField);
		inputPanel.add(new JLabel("Descripcion:"));
		inputPanel.add(descpField);
		inputPanel.add(new JLabel("Precio:"));
		inputPanel.add(precioField);

		mainPanel.add(inputPanel, BorderLayout.CENTER);

		JButton saveButton = new JButton("Guardar");
		saveButton.addActionListener(e -> {
			try {
				String nombre = nameField.getText();
				int id = Integer.parseInt(idField.getText());
				String descripcion = descpField.getText();
				double precio = Double.parseDouble(precioField.getText());

				if (precio < 0) {
					throw new NumberFormatException("El precio no puede ser negativo");
				}

				Producto P0 = new Producto(id, nombre, descripcion, precio);

				try (Connection c = DriverManager.getConnection(url, root, pass)) {
					String insertQuery = "INSERT INTO Producto (idproducto, nombre, descripcion, precio) VALUES (?, ?, ?, ?)";
					try (PreparedStatement ps = c.prepareStatement(insertQuery)) {
						ps.setInt(1, P0.getId());
						ps.setString(2, P0.getNombre());
						ps.setString(3, P0.getDescripcion());
						ps.setDouble(4, P0.getPrecio());
						ps.executeUpdate();
						JOptionPane.showMessageDialog(dialog, "Producto creado exitosamente");
						dialog.dispose();
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(dialog,
								"Error a la hora de crear el producto: " + ex.getMessage());
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(dialog, "Error de conexión a la base de datos: " + ex.getMessage());
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(dialog, "Por favor, introduce un precio válido y positivo.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		
		mainPanel.add(saveButton, BorderLayout.SOUTH);
		dialog.setVisible(true);
	}
///////////////////////////////////////////////////////////////////	
	static void showProductInfo() {
		JFrame frame = new JFrame("Datos Producto");
		frame.setSize(600, 400);
		frame.setLocation(500, 250);
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		try (Connection conn = DriverManager.getConnection(url, root, pass);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM producto")) {
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			// Agregar nombres de columnas al modelo
			for (int i = 1; i <= columnCount; i++) {
				model.addColumn(rsmd.getColumnName(i));
			}
			// Agregar filas al modelo
			while (rs.next()) {
				Object[] row = new Object[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					row[i - 1] = rs.getObject(i);
				}
				model.addRow(row);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al obtener datos de la base de datos: " + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace(); // Agrega esta línea para imprimir la traza del error y obtener más detalles
		}
		frame.setVisible(true);

	}
///////////////////////////////////////////////////////////////////	
	static void showSalesInfo() {
		JFrame frame = new JFrame("Datos Ventas");
		frame.setSize(600, 400);
		frame.setLocation(500, 250);
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.add(scrollPane, BorderLayout.CENTER);
		try (Connection conn = DriverManager.getConnection(url, root, pass);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM Venta")) {
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			// Agregar nombres de columnas al modelo
			for (int i = 1; i <= columnCount; i++) {
				model.addColumn(rsmd.getColumnName(i));
			}
			// Agregar filas al modelo
			while (rs.next()) {
				Object[] row = new Object[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					row[i - 1] = rs.getObject(i);
				}
				model.addRow(row);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al obtener datos de la base de datos: " + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace(); // Agrega esta línea para imprimir la traza del error y obtener más detalles
		}
		frame.setVisible(true);
	}
///////////////////////////////////////////////////////////////////	
	static void showUserInfo() {
		JFrame frame = new JFrame("Datos Usuarios");

		frame.setSize(800, 600);
		frame.setLocation(500, 250);
		JPanel mainPanel = new JPanel(new GridLayout(2, 1)); // Dos filas para cada tabla
		frame.add(mainPanel);
		// Panel para la tabla de Comprador
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.setBorder(BorderFactory.createTitledBorder("Datos Comprador"));
		mainPanel.add(panel1);
		DefaultTableModel model1 = new DefaultTableModel();
		JTable table1 = new JTable(model1);
		JScrollPane scrollPane1 = new JScrollPane(table1);
		panel1.add(scrollPane1, BorderLayout.CENTER);
		// Panel para la tabla de Vendedor
		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.setBorder(BorderFactory.createTitledBorder("Datos Vendedor"));
		mainPanel.add(panel2);
		DefaultTableModel model2 = new DefaultTableModel();
		JTable table2 = new JTable(model2);
		JScrollPane scrollPane2 = new JScrollPane(table2);
		panel2.add(scrollPane2, BorderLayout.CENTER);
		try (Connection conn = DriverManager.getConnection(url, root, pass)) {
			// Consulta y llenado de datos para Comprador
			try (Statement stmt1 = conn.createStatement();
					ResultSet rs1 = stmt1.executeQuery("SELECT * FROM Comprador")) {
				ResultSetMetaData rsmd1 = rs1.getMetaData();
				int columnCount1 = rsmd1.getColumnCount();
				for (int i = 1; i <= columnCount1; i++) {
					model1.addColumn(rsmd1.getColumnName(i));
				}
				while (rs1.next()) {
					Object[] row = new Object[columnCount1];
					for (int i = 1; i <= columnCount1; i++) {
						row[i - 1] = rs1.getObject(i);
					}
					model1.addRow(row);
				}
			}
			// Consulta y llenado de datos para Vendedor
			try (Statement stmt2 = conn.createStatement();
					ResultSet rs2 = stmt2.executeQuery("SELECT * FROM Vendedor")) {
				ResultSetMetaData rsmd2 = rs2.getMetaData();
				int columnCount2 = rsmd2.getColumnCount();
				for (int i = 1; i <= columnCount2; i++) {
					model2.addColumn(rsmd2.getColumnName(i));
				}
				while (rs2.next()) {
					Object[] row = new Object[columnCount2];
					for (int i = 1; i <= columnCount2; i++) {
						row[i - 1] = rs2.getObject(i);
					}
					model2.addRow(row);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al obtener datos de la base de datos: " + e.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}

		frame.setVisible(true);

	}
///////////////////////////////////////////////////////////////////			
	static void showDeleteProducto() {
        JDialog dialog = new JDialog();
        dialog.setTitle("Borrar Producto");
        dialog.setSize(400, 300);
        dialog.setLocation(500, 250);
        JPanel mainPanel = new JPanel(new BorderLayout());
        dialog.add(mainPanel);
        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        JTextField idField = new JTextField();
        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        JButton saveButton = new JButton("Borrar");
        saveButton.addActionListener(e -> {
            try {
                int IdProducto = Integer.parseInt(idField.getText());
                // Verificar la existencia del producto antes de eliminarlo
                if (!productoExiste(IdProducto)) {
                    JOptionPane.showMessageDialog(dialog, "El producto con el ID proporcionado no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try (Connection c = DriverManager.getConnection(url, root, pass)) {
                    String deleteQuery = "DELETE FROM Producto WHERE idProducto = ?";
                    try (PreparedStatement ps = c.prepareStatement(deleteQuery)) {
                        ps.setInt(1, IdProducto);
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(dialog, "Producto eliminado exitosamente");
                        dialog.dispose();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(dialog, "Error a la hora de eliminar el producto: " + ex.getMessage());
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(dialog, "Error de conexión a la base de datos A: " + ex.getMessage());
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Por favor, introduce un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        mainPanel.add(saveButton, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
///////////////////////////////////////////////////////////////////			
	static void showDeleteUserDialogVendedor() {
		JDialog dialog = new JDialog();
		dialog.setTitle("Borrar Vendedor");
		dialog.setSize(400, 300);
		dialog.setLocation(500, 250);
		JPanel mainPanel = new JPanel(new BorderLayout());
		dialog.add(mainPanel);

		JPanel inputPanel = new JPanel(new GridLayout(6, 2));
		JTextField idField = new JTextField();

		inputPanel.add(new JLabel("ID:"));
		inputPanel.add(idField);

		mainPanel.add(inputPanel, BorderLayout.CENTER);

		JButton saveButton = new JButton("Borrar");
		saveButton.addActionListener(e -> {
			try {
				String id = idField.getText();

				// Verificar la existencia del usuario
				if (!VendedorExiste(id)) {
					JOptionPane.showMessageDialog(dialog, "El Vendedor con el ID proporcionado no existe.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				Vendedor V0 = new Vendedor(id);

				try (Connection c = DriverManager.getConnection(url, root, pass)) {
					String insertQuery = "Delete from Vendedor WHERE idvendedor = " + V0.getIDUsuario();
					try (PreparedStatement ps = c.prepareStatement(insertQuery)) {
						ps.executeUpdate();
						JOptionPane.showMessageDialog(dialog, "Vendedor eliminado exitosamente");
						dialog.dispose();
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(dialog,
								"Error a la hora de eliminar el Vendedor: " + ex.getMessage());
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(dialog, "Error de conexión a la base de datos: " + ex.getMessage());
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(dialog, "Por favor, introduce un saldo válido y positivo.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		mainPanel.add(saveButton, BorderLayout.SOUTH);
		dialog.setVisible(true);

	}
///////////////////////////////////////////////////////////////////	
	static void showDeleteUserDialogComprador() {
		JDialog dialog = new JDialog();
		dialog.setTitle("Borrar Comprador");
		dialog.setSize(400, 300);
		dialog.setLocation(500, 250);
		JPanel mainPanel = new JPanel(new BorderLayout());
		dialog.add(mainPanel);

		JPanel inputPanel = new JPanel(new GridLayout(6, 2));
		JTextField idField = new JTextField();

		inputPanel.add(new JLabel("ID:"));
		inputPanel.add(idField);

		mainPanel.add(inputPanel, BorderLayout.CENTER);

		JButton saveButton = new JButton("Borrar");
		saveButton.addActionListener(e -> {

			String id = idField.getText();

			// Verificar la existencia del usuario
			if (!CompradorExiste(id)) {
				JOptionPane.showMessageDialog(dialog, "El Comprador con el ID proporcionado no existe.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			Vendedor V0 = new Vendedor(id);

			try (Connection c = DriverManager.getConnection(url, root, pass)) {
				String insertQuery = "Delete from comprador WHERE idComprador = " + V0.getIDUsuario();
				try (PreparedStatement ps = c.prepareStatement(insertQuery)) {
					ps.executeUpdate();
					JOptionPane.showMessageDialog(dialog, "Comprador eliminado exitosamente");
					dialog.dispose();
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(dialog,
							"Error a la hora de eliminar el Comprador: " + ex.getMessage());
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(dialog, "Error de conexión a la base de datos: " + ex.getMessage());
			}
		});

		mainPanel.add(saveButton, BorderLayout.SOUTH);
		dialog.setVisible(true);

	}
///////////////////////////////////////////////////////////////////	
	static void showDeleteVenta() {
		JDialog dialog = new JDialog();
		dialog.setTitle("Borrar Venta");
		dialog.setSize(400, 300);
		dialog.setLocation(500, 250);
		JPanel mainPanel = new JPanel(new BorderLayout());
		dialog.add(mainPanel);

		JPanel inputPanel = new JPanel(new GridLayout(6, 2));
		JTextField idField = new JTextField();

		inputPanel.add(new JLabel("ID:"));
		inputPanel.add(idField);

		mainPanel.add(inputPanel, BorderLayout.CENTER);

		JButton saveButton = new JButton("Borrar");
		saveButton.addActionListener(e -> {

			String id = idField.getText();

			// Verificar la existencia del usuario
			if (!ventaExiste(id)) {
				JOptionPane.showMessageDialog(dialog, "La venta con el ID proporcionado no existe.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			Venta V = new Venta(id);

			try (Connection c = DriverManager.getConnection(url, root, pass)) {
				String insertQuery = "Delete from venta WHERE idVenta = " + V.getIdVenta();
				try (PreparedStatement ps = c.prepareStatement(insertQuery)) {
					ps.executeUpdate();
					JOptionPane.showMessageDialog(dialog, "Venta eliminada exitosamente");
					dialog.dispose();
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(dialog,
							"Error a la hora de eliminar la Venta: " + ex.getMessage());
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(dialog, "Error de conexión a la base de datos: " + ex.getMessage());
			}
		});

		mainPanel.add(saveButton, BorderLayout.SOUTH);
		dialog.setVisible(true);
	}
///////////////////////////////////////////////////////////////////			
	static boolean VendedorExiste(String idVendedor) {
		String query = "SELECT COUNT(*) FROM Vendedor WHERE idVendedor = ?";
		try (Connection c = DriverManager.getConnection(url, root, pass);
				PreparedStatement ps = c.prepareStatement(query)) {
			ps.setString(1, idVendedor);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al verificar la existencia del Vendedor: " + ex.getMessage());
		}
		return false;
	}
///////////////////////////////////////////////////////////////////		
	static boolean CompradorExiste(String idComprador) {
		String query = "SELECT COUNT(*) FROM Comprador WHERE idComprador = ?";
		try (Connection c = DriverManager.getConnection(url, root, pass);
				PreparedStatement ps = c.prepareStatement(query)) {
			ps.setString(1, idComprador);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al verificar la existencia del Comprador: " + ex.getMessage());
		}
		return false;
	}
///////////////////////////////////////////////////////////////////	
///////////////////////////////////////////////////////////////////			
	static boolean productoExiste(int idProducto) {
		String query = "SELECT COUNT(*) FROM Producto WHERE idproducto = ?";
		try (Connection c = DriverManager.getConnection(url, root, pass);
				PreparedStatement ps = c.prepareStatement(query)) {
			ps.setInt(1, idProducto);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al verificar la existencia del producto: " + ex.getMessage());
		}
		return false;
	}
///////////////////////////////////////////////////////////////////	
///////////////////////////////////////////////////////////////////			
	static boolean ventaExiste(String idVenta) {
		// TODO Auto-generated method stub
		String query = "SELECT COUNT(*) FROM Venta WHERE idVenta = ?";
		try (Connection c = DriverManager.getConnection(url, root, pass);
				PreparedStatement ps = c.prepareStatement(query)) {
			ps.setString(1, idVenta);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error al verificar la existencia del Vendedor: " + ex.getMessage());
		}
		return false;
	}
///////////////////////////////////////////////////////////////////			
///////////////////////////////////////////////////////////////////			
	static void showNewUserDialogVendedor() {
		JDialog dialog = new JDialog();
		dialog.setTitle("Nuevo Vendedor");
		dialog.setSize(400, 300);
		dialog.setLocation(500, 250);

		JPanel mainPanel = new JPanel(new BorderLayout());
		dialog.add(mainPanel);

		JPanel inputPanel = new JPanel(new GridLayout(6, 2));
		JTextField nameField = new JTextField();
		JTextField idField = new JTextField();
		JTextField passwordField = new JTextField();
		JTextField emailField = new JTextField();
		JTextField saldoField = new JTextField();
		JTextField ProductoField = new JTextField();

		inputPanel.add(new JLabel("Nombre:"));
		inputPanel.add(nameField);
		inputPanel.add(new JLabel("ID:"));
		inputPanel.add(idField);
		inputPanel.add(new JLabel("Contraseña:"));
		inputPanel.add(passwordField);
		inputPanel.add(new JLabel("Email:"));
		inputPanel.add(emailField);
		inputPanel.add(new JLabel("Saldo:"));
		inputPanel.add(saldoField);
		inputPanel.add(new JLabel("Producto:"));
		inputPanel.add(ProductoField);

		mainPanel.add(inputPanel, BorderLayout.CENTER);

		JButton saveButton = new JButton("Guardar");
		saveButton.addActionListener(e -> {
			String nombre = nameField.getText();
			String id = idField.getText();
			String password = passwordField.getText();
			String email = emailField.getText();
			String saldoText = saldoField.getText().trim();
			String productoText = ProductoField.getText();

			if (nombre.isEmpty() || id.isEmpty() || password.isEmpty() || email.isEmpty() || saldoText.isEmpty()
					|| productoText.isEmpty()) {
				throw new IllegalArgumentException("Todos los campos deben estar llenos");
			}

			double saldo = Double.parseDouble(saldoText);
			if (saldo <= 0) {
				throw new NumberFormatException("El saldo no puede ser negativo");
			}

			int IdProducto = Integer.parseInt(ProductoField.getText());

			// Verificar la existencia del producto antes de crear el vendedor
			if (!productoExiste(IdProducto)) {
				JOptionPane.showMessageDialog(dialog, "El producto con el ID proporcionado no existe.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			Vendedor V0 = new Vendedor(nombre, id, password, email, saldo, IdProducto);

			try (Connection c = DriverManager.getConnection(url, root, pass)) {
				String insertQuery = "INSERT INTO Vendedor (idvendedor, Nombre, Email, Contraseña, Saldo, Producto) VALUES (?, ?, ?, ?, ?, ?)";
				try (PreparedStatement ps = c.prepareStatement(insertQuery)) {
					ps.setString(1, V0.getIDUsuario());
					ps.setString(2, V0.getNombre());
					ps.setString(3, V0.getEmail());
					ps.setString(4, V0.getPassword());
					ps.setDouble(5, V0.getSaldo());
					ps.setInt(6, V0.getProducto());
					ps.executeUpdate();
					JOptionPane.showMessageDialog(dialog, "Usuario creado exitosamente");
					dialog.dispose();
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(dialog, "Error a la hora de crear el usuario: " + ex.getMessage());
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(dialog, "Error de conexión a la base de datos: " + ex.getMessage());
			}

		});
		mainPanel.add(saveButton, BorderLayout.SOUTH);
		dialog.setVisible(true);
	}
///////////////////////////////////////////////////////////////////	
///////////////////////////////////////////////////////////////////			
	static void showNewUserDialogComprador() {
		JDialog dialog = new JDialog();
		dialog.setTitle("Nuevo Comprador");
		dialog.setSize(400, 300);
		dialog.setLocation(500, 250);

		JPanel mainPanel = new JPanel(new BorderLayout());
		dialog.add(mainPanel);

		JPanel inputPanel = new JPanel(new GridLayout(6, 2));
		JTextField nameField = new JTextField();
		JTextField idField = new JTextField();
		JTextField passwordField = new JTextField();
		JTextField emailField = new JTextField();
		JTextField saldoField = new JTextField();

		inputPanel.add(new JLabel("Nombre:"));
		inputPanel.add(nameField);
		inputPanel.add(new JLabel("ID:"));
		inputPanel.add(idField);
		inputPanel.add(new JLabel("Contraseña:"));
		inputPanel.add(passwordField);
		inputPanel.add(new JLabel("Email:"));
		inputPanel.add(emailField);
		inputPanel.add(new JLabel("Saldo:"));
		inputPanel.add(saldoField);

		mainPanel.add(inputPanel, BorderLayout.CENTER);

		JButton saveButton = new JButton("Guardar");
		saveButton.addActionListener(e -> {
			try {
				String nombre = nameField.getText();
				String id = idField.getText();
				String password = passwordField.getText();
				String email = emailField.getText();
				double saldo = Double.parseDouble(saldoField.getText());

				if (saldo < 0) {
					throw new NumberFormatException("El saldo no puede ser negativo");
				}

				Comprador C0 = new Comprador(nombre, id, password, email, saldo);

				try (Connection c = DriverManager.getConnection(url, root, pass)) {
					String insertQuery = "INSERT INTO Comprador (IDComprador, nombre, Email, Contraseña, Saldo) VALUES (?, ?, ?, ?, ?)";
					try (PreparedStatement ps = c.prepareStatement(insertQuery)) {
						ps.setString(1, C0.getIDUsuario());
						ps.setString(2, C0.getNombre());
						ps.setString(3, C0.getEmail());
						ps.setString(4, C0.getPassword());
						ps.setDouble(5, C0.getSaldo());
						ps.executeUpdate();
						JOptionPane.showMessageDialog(dialog, "Usuario creado exitosamente");
						dialog.dispose();
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(dialog,
								"Error a la hora de crear el usuario: " + ex.getMessage());
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(dialog, "Error de conexión a la base de datos: " + ex.getMessage());
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(dialog, "Por favor, introduce un saldo válido y positivo.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		mainPanel.add(saveButton, BorderLayout.SOUTH);
		dialog.setVisible(true);
	}
///////////////////////////////////////////////////////////////////		
///////////////////////////////////////////////////////////////////				
	static void buscarVenta() {
		JDialog dialog = new JDialog();
		dialog.setTitle("Buscar Venta");
		dialog.setSize(400, 300);
		dialog.setLocation(500, 250);

		JPanel mainPanel = new JPanel(new BorderLayout());
		dialog.add(mainPanel);

		JPanel inputPanel = new JPanel(new GridLayout(6, 2));
		JTextField idField = new JTextField();

		inputPanel.add(new JLabel("ID:"));
		inputPanel.add(idField);

		mainPanel.add(inputPanel, BorderLayout.CENTER);

		JButton saveButton = new JButton("Aceptar");
		saveButton.addActionListener(e -> {

			String id = idField.getText();

			// Verificar la existencia de la venta
			if (!ventaExiste(id)) {
				JOptionPane.showMessageDialog(dialog, "La venta con el ID proporcionado no existe.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			Venta V0 = new Venta(id);

			int idVenta = Integer.parseInt(id.trim());

			try (Connection conn = DriverManager.getConnection(url, root, pass);
					PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Venta WHERE idVenta = ?")) {

				pstmt.setInt(1, idVenta);
				ResultSet rs = pstmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();

				JFrame frame = new JFrame("Datos Ventas");

				frame.setSize(600, 400);
				frame.setLocation(500, 250);
				DefaultTableModel model = new DefaultTableModel();
				JTable table = new JTable(model);
				JScrollPane scrollPane = new JScrollPane(table);
				frame.add(scrollPane, BorderLayout.CENTER);

				// Agregar nombres de columnas al modelo
				for (int i = 1; i <= columnCount; i++) {
					model.addColumn(rsmd.getColumnName(i));
				}
				// Agregar filas al modelo
				while (rs.next()) {
					Object[] row = new Object[columnCount];
					for (int i = 1; i <= columnCount; i++) {
						row[i - 1] = rs.getObject(i);
					}
					model.addRow(row);
				}

				frame.setVisible(true);

			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Error al obtener datos de la base de datos: " + ex.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		mainPanel.add(saveButton, BorderLayout.SOUTH);
		dialog.setVisible(true);
	}
///////////////////////////////////////////////////////////////////		
///////////////////////////////////////////////////////////////////			
	static void buscarComprador() {
		JDialog dialog = new JDialog();
		dialog.setTitle("Buscar Comprador con ID");
		dialog.setSize(400, 300);
		dialog.setLocation(500, 250);

		JPanel mainPanel = new JPanel(new BorderLayout());
		dialog.add(mainPanel);

		JPanel inputPanel = new JPanel(new GridLayout(6, 2));
		JTextField idField = new JTextField();

		inputPanel.add(new JLabel("ID:"));
		inputPanel.add(idField);

		mainPanel.add(inputPanel, BorderLayout.CENTER);

		JButton saveButton = new JButton("Aceptar");
		saveButton.addActionListener(e -> {

			String id = idField.getText();

			// Verificar la existencia de la venta
			if (!CompradorExiste(id)) {
				JOptionPane.showMessageDialog(dialog, "El Comprador con el ID proporcionado no existe.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			Comprador c = new Comprador(id);

			int idComprador = Integer.parseInt(id.trim());

			try (Connection conn = DriverManager.getConnection(url, root, pass);
					PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Comprador WHERE idComprador = ?")) {

				pstmt.setInt(1, idComprador);
				ResultSet rs = pstmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();

				JFrame frame = new JFrame("Datos Comprador");

				frame.setSize(600, 400);
				frame.setLocation(500, 250);
				DefaultTableModel model = new DefaultTableModel();
				JTable table = new JTable(model);
				JScrollPane scrollPane = new JScrollPane(table);
				frame.add(scrollPane, BorderLayout.CENTER);

				// Agregar nombres de columnas al modelo
				for (int i = 1; i <= columnCount; i++) {
					model.addColumn(rsmd.getColumnName(i));
				}
				// Agregar filas al modelo
				while (rs.next()) {
					Object[] row = new Object[columnCount];
					for (int i = 1; i <= columnCount; i++) {
						row[i - 1] = rs.getObject(i);
					}
					model.addRow(row);
				}

				frame.setVisible(true);

			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Error al obtener datos de la base de datos: " + ex.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		mainPanel.add(saveButton, BorderLayout.SOUTH);
		dialog.setVisible(true);
	}
///////////////////////////////////////////////////////////////////	
///////////////////////////////////////////////////////////////////			
	static void buscarVendedor() {
		JDialog dialog = new JDialog();
		dialog.setTitle("Buscar Vendedor con ID");
		dialog.setSize(400, 300);
		dialog.setLocation(500, 250);

		JPanel mainPanel = new JPanel(new BorderLayout());
		dialog.add(mainPanel);

		JPanel inputPanel = new JPanel(new GridLayout(6, 2));
		JTextField idField = new JTextField();

		inputPanel.add(new JLabel("ID:"));
		inputPanel.add(idField);

		mainPanel.add(inputPanel, BorderLayout.CENTER);

		JButton saveButton = new JButton("Aceptar");
		saveButton.addActionListener(e -> {

			String id = idField.getText();

			// Verificar la existencia de la venta
			if (!VendedorExiste(id)) {
				JOptionPane.showMessageDialog(dialog, "El Vendedor con el ID proporcionado no existe.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			Vendedor v = new Vendedor(id);

			int idVendedor = Integer.parseInt(id.trim());

			try (Connection conn = DriverManager.getConnection(url, root, pass);
					PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Vendedor WHERE idVendedor = ?")) {

				pstmt.setInt(1, idVendedor);
				ResultSet rs = pstmt.executeQuery();
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();

				JFrame frame = new JFrame("Datos Vendedor");
				frame.setSize(600, 400);
				frame.setLocation(500, 250);
				DefaultTableModel model = new DefaultTableModel();
				JTable table = new JTable(model);
				JScrollPane scrollPane = new JScrollPane(table);
				frame.add(scrollPane, BorderLayout.CENTER);

				// Agregar nombres de columnas al modelo
				for (int i = 1; i <= columnCount; i++) {
					model.addColumn(rsmd.getColumnName(i));
				}
				// Agregar filas al modelo
				while (rs.next()) {
					Object[] row = new Object[columnCount];
					for (int i = 1; i <= columnCount; i++) {
						row[i - 1] = rs.getObject(i);
					}
					model.addRow(row);
				}

				frame.setVisible(true);

			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Error al obtener datos de la base de datos: " + ex.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		mainPanel.add(saveButton, BorderLayout.SOUTH);
		dialog.setVisible(true);
	}
///////////////////////////////////////////////////////////////////	
///////////////////////////////////////////////////////////////////			
	static void editComprador() {
		JDialog dialog = new JDialog();
		dialog.setTitle("Editar Comprador");
		dialog.setSize(400, 300);
		dialog.setLocation(500, 250);
		

		JPanel mainPanel = new JPanel(new BorderLayout());
		dialog.add(mainPanel);

		JPanel inputPanel = new JPanel(new GridLayout(6, 2));
		JTextField idField = new JTextField();

		inputPanel.add(new JLabel("ID:"));
		inputPanel.add(idField);

		mainPanel.add(inputPanel, BorderLayout.CENTER);

		JButton saveButton = new JButton("Aceptar");
		saveButton.addActionListener(e -> {

			String id = idField.getText();

			// Verificar la existencia del usuario
			if (!CompradorExiste(id)) {
				JOptionPane.showMessageDialog(dialog, "El Comprador con el ID proporcionado no existe.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				JDialog dialogo = new JDialog();
				dialogo.setTitle("Editar Comprador");
				dialogo.setSize(400, 300);
				dialogo.setLocation(500, 250);

				JPanel mainPanel1 = new JPanel(new BorderLayout());
				dialogo.add(mainPanel1);

				JPanel inputPanel1 = new JPanel(new GridLayout(6, 2));
				JTextField nameField = new JTextField();
				JTextField passwordField = new JTextField();
				JTextField emailField = new JTextField();

				inputPanel1.add(new JLabel("Nombre:"));
				inputPanel1.add(nameField);
				inputPanel1.add(new JLabel("Contraseña:"));
				inputPanel1.add(passwordField);
				inputPanel1.add(new JLabel("Email:"));
				inputPanel1.add(emailField);

				mainPanel1.add(inputPanel1, BorderLayout.CENTER);

				JButton savingButton = new JButton("Guardar");
				savingButton.addActionListener(ev -> {

					String nombre = nameField.getText();
					String password = passwordField.getText();
					String email = emailField.getText();

					if (nombre.isEmpty() || password.isEmpty() || email.isEmpty()) {
						throw new IllegalArgumentException("Todos los campos deben estar llenos");
					}

					Comprador C0 = new Comprador(nombre, password, email);
					C0.setIDUsuario(id);

					try (Connection c = DriverManager.getConnection(url, root, pass)) {
						String insertQuery = "UPDATE Comprador SET Nombre = ?, contraseña = ?, email = ? WHERE idcomprador = ?";
						try (PreparedStatement ps = c.prepareStatement(insertQuery)) {
							ps.setString(1, C0.getNombre());
							ps.setString(2, C0.getPassword());
							ps.setString(3, C0.getEmail());
							ps.setString(4, C0.getIDUsuario());
							ps.executeUpdate();
							JOptionPane.showMessageDialog(dialogo, "Comprador editado exitosamente");
							dialog.dispose();
						} catch (SQLException ex) {
							JOptionPane.showMessageDialog(dialogo,
									"Error a la hora de editar el Comprador: " + ex.getMessage());
						}
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(dialog,
								"Error de conexión a la base de datos: " + ex.getMessage());
					}
				});

				mainPanel1.add(savingButton, BorderLayout.SOUTH);
				dialogo.setVisible(true);

			}

		});

		mainPanel.add(saveButton, BorderLayout.SOUTH);
		dialog.setVisible(true);
	}
///////////////////////////////////////////////////////////////////	
///////////////////////////////////////////////////////////////////			
	static void editVendedor() {
		JDialog dialog = new JDialog();
		dialog.setTitle("Editar Vendedor");
		dialog.setSize(400, 300);
		dialog.setLocation(500, 250);
		JPanel mainPanel = new JPanel(new BorderLayout());
		dialog.add(mainPanel);

		JPanel inputPanel = new JPanel(new GridLayout(6, 2));
		JTextField idField = new JTextField();

		inputPanel.add(new JLabel("ID:"));
		inputPanel.add(idField);

		mainPanel.add(inputPanel, BorderLayout.CENTER);

		JButton saveButton = new JButton("Aceptar");
		saveButton.addActionListener(e -> {

			String id = idField.getText();

			// Verificar la existencia del usuario
			if (!CompradorExiste(id)) {
				JOptionPane.showMessageDialog(dialog, "El Vendedor con el ID proporcionado no existe.", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				JDialog dialogo = new JDialog();
				dialogo.setTitle("Editar Vendedor");
				dialogo.setSize(400, 300);
				dialogo.setLocation(500, 250);

				JPanel mainPanel1 = new JPanel(new BorderLayout());
				dialogo.add(mainPanel1);

				JPanel inputPanel1 = new JPanel(new GridLayout(6, 2));
				JTextField nameField = new JTextField();
				JTextField passwordField = new JTextField();
				JTextField emailField = new JTextField();

				inputPanel1.add(new JLabel("Nombre:"));
				inputPanel1.add(nameField);
				inputPanel1.add(new JLabel("Contraseña:"));
				inputPanel1.add(passwordField);
				inputPanel1.add(new JLabel("Email:"));
				inputPanel1.add(emailField);

				mainPanel1.add(inputPanel1, BorderLayout.CENTER);

				JButton savingButton = new JButton("Guardar");
				savingButton.addActionListener(ev -> {

					String nombre = nameField.getText();
					String password = passwordField.getText();
					String email = emailField.getText();

					if (nombre.isEmpty() || password.isEmpty() || email.isEmpty()) {
						throw new IllegalArgumentException("Todos los campos deben estar llenos");
					}

					Vendedor V0 = new Vendedor(nombre, password, email);
					V0.setIDUsuario(id);

					try (Connection c = DriverManager.getConnection(url, root, pass)) {
						String insertQuery = "UPDATE Vendedor SET Nombre = ?, contraseña = ?, email = ? WHERE idvendedor = ?";
						try (PreparedStatement ps = c.prepareStatement(insertQuery)) {
							ps.setString(1, V0.getNombre());
							ps.setString(2, V0.getPassword());
							ps.setString(3, V0.getEmail());
							ps.setString(4, V0.getIDUsuario());
							ps.executeUpdate();
							JOptionPane.showMessageDialog(dialogo, "Vendedor editado exitosamente");
							dialog.dispose();
						} catch (SQLException ex) {
							JOptionPane.showMessageDialog(dialogo,
									"Error a la hora de editar el Vendedor: " + ex.getMessage());
						}
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(dialog,
								"Error de conexión a la base de datos: " + ex.getMessage());
					}
				});

				mainPanel1.add(savingButton, BorderLayout.SOUTH);
				dialogo.setVisible(true);

			}
		});

		mainPanel.add(saveButton, BorderLayout.SOUTH);
		dialog.setVisible(true);
	}
///////////////////////////////////////////////////////////////////	
///////////////////////////////////////////////////////////////////			
	static void showEditSaldo() {
		JDialog dialog = new JDialog();
		dialog.setTitle("Editar Saldo");
		dialog.setSize(400, 200);
		dialog.setLocation(500, 250);

		JPanel mainPanel = new JPanel(new BorderLayout());
		dialog.add(mainPanel);
		JPanel inputPanel = new JPanel(new GridLayout(4, 2));
		JTextField idField = new JTextField();
		JTextField amountField = new JTextField();
		JComboBox<String> userTypeBox = new JComboBox<>(new String[] { "Comprador", "Vendedor" });
		inputPanel.add(new JLabel("Tipo de Usuario:"));
		inputPanel.add(userTypeBox);
		inputPanel.add(new JLabel("ID Usuario:"));
		inputPanel.add(idField);
		inputPanel.add(new JLabel("Cantidad a Sumar:"));
		inputPanel.add(amountField);
		mainPanel.add(inputPanel, BorderLayout.CENTER);
		JButton saveButton = new JButton("Guardar");
		mainPanel.add(saveButton, BorderLayout.SOUTH);
		saveButton.addActionListener(e -> {
			try {
				String userID = idField.getText().trim();
				String amountText = amountField.getText().trim();
				String userType = (String) userTypeBox.getSelectedItem();

				if (userID.isEmpty() || amountText.isEmpty()) {
					JOptionPane.showMessageDialog(dialog, "Por favor, rellena todos los campos.");
					return;
				}
				double amount = Double.parseDouble(amountText);
				if (amount <= 0) {
					JOptionPane.showMessageDialog(dialog, "Por favor, introduce una cantidad positiva.");
					return;
				}
				String tableName = userType.equals("Comprador") ? "Comprador" : "Vendedor";
				String idColumn = userType.equals("Comprador") ? "IDComprador" : "IDVendedor";
				try (Connection c = DriverManager.getConnection(url, root, pass)) {
					System.out.println("Conexión a la base de datos exitosa."); // Mensaje de depuración
					String updateSaldoQuery = "UPDATE " + tableName + " SET Saldo = Saldo + ? WHERE " + idColumn
							+ " = ?";

					try (PreparedStatement psUpdateSaldo = c.prepareStatement(updateSaldoQuery)) {
						psUpdateSaldo.setDouble(1, amount);
						psUpdateSaldo.setString(2, userID);
						int rowsAffected = psUpdateSaldo.executeUpdate();

						if (rowsAffected > 0) {
							JOptionPane.showMessageDialog(dialog, "Saldo actualizado exitosamente.");
							dialog.dispose();
						} else {
							JOptionPane.showMessageDialog(dialog, userType + " no encontrado.");
						}
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(dialog, "Error de conexión a la base de datos: " + ex.getMessage());
					ex.printStackTrace(); // Mensaje de depuración adicional
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(dialog, "Por favor, introduce una cantidad válida.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		dialog.setVisible(true);
	}
///////////////////////////////////////////////////////////////////	
///////////////////////////////////////////////////////////////////			
	static void showSalesCreate() {
		JDialog dialog = new JDialog();
		dialog.setTitle("Nueva venta");
		dialog.setSize(400, 300);
		dialog.setLocation(500, 250);
		JPanel mainPanel = new JPanel(new BorderLayout());
		dialog.add(mainPanel);
		JPanel inputPanel = new JPanel(new GridLayout(6, 2));
		JTextField idVend = new JTextField();
		JTextField idProd = new JTextField();
		JTextField idComp = new JTextField();
		JTextField idVent = new JTextField();
		inputPanel.add(new JLabel("ID Venta:"));
		inputPanel.add(idVent);
		inputPanel.add(new JLabel("ID Vendedor:"));
		inputPanel.add(idVend);
		inputPanel.add(new JLabel("ID Producto:"));
		inputPanel.add(idProd);
		inputPanel.add(new JLabel("ID Comprador:"));
		inputPanel.add(idComp);
		mainPanel.add(inputPanel, BorderLayout.CENTER);
		JButton saveButton = new JButton("Guardar");
		mainPanel.add(saveButton, BorderLayout.SOUTH);
		saveButton.addActionListener(e -> {
			try {
				String IDVend = idVend.getText().trim();
				String IDProducto = idProd.getText().trim();
				String IDComp = idComp.getText().trim();
				String IDVenta = idVent.getText().trim();
				if (IDVend.isEmpty() || IDProducto.isEmpty() || IDComp.isEmpty() || IDVenta.isEmpty()) {
					JOptionPane.showMessageDialog(dialog, "Por favor, rellena todos los campos.");
					return;
				}
				
				if (!verificarCompra(IDComp, IDProducto, IDVend)) {
					JOptionPane.showMessageDialog(dialog, "No se puede realizar la compra saldo insuficiente.");
					return;
				}
				
	            if (!vendedorEstaVendiendoProducto(IDVend, IDProducto)) {
	                JOptionPane.showMessageDialog(dialog, "El vendedor no está autorizado para vender este producto.");
	                return;
	            }
				
				try (Connection c = DriverManager.getConnection(url, root, pass)) {
					System.out.println("Conexión a la base de datos exitosa."); // Mensaje de depuración
					c.setAutoCommit(false); // Inicia una transacción

					String insertQuery = "INSERT INTO Venta (IDVenta, Comprador, Vendedor, Producto) VALUES (?, ?, ?, ?)";
					try (PreparedStatement ps = c.prepareStatement(insertQuery)) {
						ps.setString(1, IDVenta);
						ps.setString(2, IDComp);
						ps.setString(3, IDVend);
						ps.setString(4, IDProducto);
						ps.executeUpdate();
						c.commit(); // Confirmar la transacción

						JOptionPane.showMessageDialog(dialog, "Venta creada exitosamente");
						dialog.dispose();
					} catch (SQLException ex) {
						c.rollback(); // Deshacer la transacción en caso de error
						JOptionPane.showMessageDialog(dialog, "Error al crear la venta: " + ex.getMessage());
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(dialog, "Error de conexión a la base de datos: " + ex.getMessage());
					ex.printStackTrace(); // Mensaje de depuración adicional
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(dialog, "Por favor, introduce datos válidos.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		dialog.setVisible(true);
	}
///////////////////////////////////////////////////////////////////	    
///////////////////////////////////////////////////////////////////			
	static void RealizarPago(double productValue, String idComprador, String idVend) {
		try (Connection c = DriverManager.getConnection(url, root, pass)) {
			System.out.println("Conexión a la base de datos exitosa."); // Mensaje de depuración
			c.setAutoCommit(false); // Inicia una transacción
			// Actualizar saldo del vendedor
			String updateSaldoVendedorQuery = "UPDATE Vendedor SET Saldo = Saldo + ? WHERE IDVendedor = ?";
			try (PreparedStatement psUpdateSaldoVend = c.prepareStatement(updateSaldoVendedorQuery)) {
				psUpdateSaldoVend.setDouble(1, productValue);
				psUpdateSaldoVend.setString(2, idVend);
				int rowsAffectedVend = psUpdateSaldoVend.executeUpdate();

				if (rowsAffectedVend <= 0) {
					System.out.println("Vendedor no encontrado.");
					c.rollback();
					return;
				}
			}
			// Actualizar saldo del comprador
			String updateSaldoCompradorQuery = "UPDATE Comprador SET Saldo = Saldo - ? WHERE IDComprador = ?";
			try (PreparedStatement psUpdateSaldoComp = c.prepareStatement(updateSaldoCompradorQuery)) {
				psUpdateSaldoComp.setDouble(1, productValue);
				psUpdateSaldoComp.setString(2, idComprador);
				int rowsAffectedComp = psUpdateSaldoComp.executeUpdate();

				if (rowsAffectedComp <= 0) {
					System.out.println("Comprador no encontrado.");
					c.rollback();
					return;
				}
			}
			c.commit(); // Confirmar la transacción
			System.out.println("Saldo actualizado exitosamente.");
		} catch (SQLException ex) {
			System.out.println("Error de conexión a la base de datos: " + ex.getMessage());
			ex.printStackTrace(); // Mensaje de depuración adicional
		}
	}
///////////////////////////////////////////////////////////////////	
///////////////////////////////////////////////////////////////////			
	static boolean verificarCompra(String idComprador, String idProducto, String idVend) {
		try (Connection c = DriverManager.getConnection(url, root, pass)) {
			System.out.println("Conexión a la base de datos exitosa."); // Mensaje de depuración
			// 1. Obtener el valor económico del producto
			String productQuery = "SELECT precio FROM Producto WHERE IDProducto = ?";
			double productValue = 0.0;
			try (PreparedStatement psProduct = c.prepareStatement(productQuery)) {
				psProduct.setString(1, idProducto);
				try (ResultSet rs = psProduct.executeQuery()) {
					if (rs.next()) {
						productValue = rs.getDouble("precio");
					} else {
						System.out.println("Producto no encontrado.");
						return false;
					}
				}
			}
			// 2. Verificar el saldo del comprador
			String saldoQuery = "SELECT Saldo FROM Comprador WHERE IDComprador = ?";
			double compradorSaldo = 0.0;
			try (PreparedStatement psSaldo = c.prepareStatement(saldoQuery)) {
				psSaldo.setString(1, idComprador);
				try (ResultSet rs = psSaldo.executeQuery()) {
					if (rs.next()) {
						compradorSaldo = rs.getDouble("Saldo");
					} else {
						System.out.println("Comprador no encontrado.");
						return false;
					}
				}
			}
			// 3. Comprobar si el saldo del comprador es suficiente
			if (compradorSaldo < productValue) {
				System.out.println("Saldo insuficiente para realizar la compra.");
				return false;
			}
			// 4. Realizar el pago
			RealizarPago(productValue, idComprador, idVend);
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}
///////////////////////////////////////////////////////////////////	
///////////////////////////////////////////////////////////////////			
	static boolean vendedorEstaVendiendoProducto(String idVendedor, String idProducto) {
	    String query = "SELECT COUNT(*) FROM Vendedor WHERE IDVendedor = ? AND Producto = ?";
	    try (Connection c = DriverManager.getConnection(url, root, pass);
	         PreparedStatement ps = c.prepareStatement(query)) {
	        ps.setString(1, idVendedor);
	        ps.setString(2, idProducto);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	    } catch (SQLException ex) {
	        JOptionPane.showMessageDialog(null, "Error al verificar la relación entre vendedor y producto: " + ex.getMessage());
	    }
	    return false;
	}
///////////////////////////////////////////////////////////////////		
}