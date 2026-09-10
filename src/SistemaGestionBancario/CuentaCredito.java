package SistemaGestionBancario;

public class CuentaCredito extends CuentaBancaria {
	private double limiteCredito;

	public CuentaCredito(int numeroCuenta, String titular, double saldo, String pin, String dni, double limiteCredito) {
        super(numeroCuenta, titular, saldo, pin, dni);
        this.limiteCredito = limiteCredito;
    }
	@Override
    public void retiro(double monto) {
        
        double disponible = this.saldo + this.limiteCredito;

        if (monto > disponible) {
            throw new LimiteCreditoExcedidoException("Operación denegada. Supera el límite de crédito disponible.");
        }
        
        this.saldo -= monto; 
    }
}
