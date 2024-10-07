package principal;

public class Venta {
	String idVenta;
	Vendedor Vendedores;
	Comprador Compradores;
	Producto Producto;
	
	
	//Constructores
	public Venta (String idVenta, Vendedor Vendedores, Producto Producto, Comprador Compradores) {
		this.idVenta = idVenta;
		this.Vendedores = Vendedores;
		this.Producto = Producto;
		this.Compradores = Compradores;
	}
	public Venta (String idVenta) {
		this.idVenta = idVenta;
	}
	
	//Getters y setters
	public String getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(String idVenta) {
		this.idVenta = idVenta;
	}
	public Vendedor getVendedores() {
        return Vendedores;
    }
	public void setVendedores(Vendedor Vendedores) {
        this.Vendedores = Vendedores;
    }
	public Comprador getCompradores() {
		return Compradores;
	}
	public void setCompradores(Comprador Compradores) {
        this.Compradores = Compradores;
    }
	public Producto getProducto() {
        return Producto;
    }
	public Producto setProductos(Producto Producto) {
		this.Producto = Producto;
        return Producto;
	}
	

}
