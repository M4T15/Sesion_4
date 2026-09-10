package SistemaGestionBancario;

public class CuentaBancaria {
	private int numeroCuenta;
	private String password;
	private String id;
	private String titular;
	protected double saldo;
	
	public CuentaBancaria(int numeroCuenta, String titular, double saldo, String password, String id){
		if(numeroCuenta < 0) {
			throw new IllegalArgumentException("el numero de cuenta no puede tener numeros negativos");
		}
		
		if(titular == null || !titular.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
			throw new IllegalArgumentException("El nombre del usuario no es valido(no puede contener numeros ni simbolos).");
		}
		
		if(saldo < 0) {
			throw new IllegalArgumentException("El saldo no puede ser negativo.");
		}
		
		if(password == null || password.matches(".*[a-zA-ZáéíóúÁÉÍÓÚñÑ].*")) {
			throw new IllegalArgumentException("La clave debe contener numeros.");
		}
		
		if(id == null || id.matches(".*[a-zA-ZáéíóúÁÉÍÓÚñÑ].*")) {
			throw new IllegalArgumentException("Su numero de DNI es invalido");
		}
		
		this.numeroCuenta = numeroCuenta;
		this.setPassword(password);
		this.setId(id);
		this.titular = titular;
		this.setSaldo(saldo);
		
		System.out.println("Cuenta creada exitosamente");
	}
	
	public void deposito(double monto) {
		this.saldo += monto;
	}
	
	public void retiro(double monto) {
		if(this.saldo < monto) {
			throw new SaldoInsuficienteException("Saldo insuficiente");
		}
		this.saldo -= monto;
	}
	
	
	public int getNumroCuenta() {
		return numeroCuenta;
	}
	
	public double getSaldo() {
		return saldo;
	}

	private void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}
	
	public String getTitular() {
		return titular;
	}

	public String getPassword() {
		return password;
	}

	private void setPassword(String password) {
		this.password = password;
	}
}
