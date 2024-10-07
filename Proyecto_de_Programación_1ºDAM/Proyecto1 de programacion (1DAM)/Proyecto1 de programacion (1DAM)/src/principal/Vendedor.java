package principal;

public class Vendedor {
	protected String Nombre;
	protected String IDUsuario;
	protected String Password;
	protected String Email;
	protected double Saldo;
	int Producto;

	
	//Constructores
	public Vendedor(String nombre, String IDUsuario, String Password, String Email, double Saldo, int Producto) {
		this.Nombre = nombre;
		this.IDUsuario = IDUsuario;
		this.Password = Password;
		this.Email = Email;
		this.Saldo = Saldo;
		this.Producto = Producto;
	}
	public Vendedor(String id) {
		this.IDUsuario = id;
	}
	public Vendedor(String nombre, String password, String email) {
		this.Nombre = nombre;
		this.Password = password;
		this.Email = email;
	}
	
	//Getters y setters
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getIDUsuario() {
		return IDUsuario;
	}

	public void setIDUsuario(String IDUsuario) {
		this.IDUsuario = IDUsuario;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public double getSaldo() {
		return Saldo;
	}

	public void setSaldo(double saldo) {
		Saldo = saldo;
	}

	public int getProducto() {
		return Producto;
	}

	public void setProducto(int Producto) {
		this.Producto = Producto;
	}
	
	//Metodos
	@Override
	public String toString() {
		return "Usuarios [Nombre=" + Nombre + ", \nIDUsuario=" + IDUsuario + ", \nPassword=" + Password + ", \nEmail="
				+ Email + ", \n Saldo=" + Saldo + "]";
	}
	
}
