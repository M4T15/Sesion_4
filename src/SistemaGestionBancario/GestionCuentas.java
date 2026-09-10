package SistemaGestionBancario;
import java.util.ArrayList;
import java.util.List;

public class GestionCuentas {
	private List<CuentaBancaria> cuentas;
	
	public GestionCuentas() {
		this.cuentas = new ArrayList();
	}
	
	void agregarCuenta(CuentaBancaria cuenta) {
		if(cuenta == null){
			throw new IllegalArgumentException("La cuenta no puede ser nula");
		}
		cuentas.add(cuenta);
	}
	
	public CuentaBancaria buscarPorNumero(int numsearch) {
		for(CuentaBancaria c : cuentas) {
			if(c.getNumroCuenta() == numsearch) {
				return c;
			}
		}
		throw new CuentaNoEncontradaException("La cuenta " + numsearch + " no existe en el sitema.");
	}
	
	public boolean eliminarCuenta(int numeroCuenta) {
		CuentaBancaria cuenta = buscarPorNumero(numeroCuenta);
		if(cuenta.getSaldo() > 0) {
			throw new SaldoNoCeroException("La cuenta " + cuenta.getNumroCuenta() +" aun tiene saldo : " + cuenta.getSaldo());
		}
		return cuentas.remove(cuenta);
	}
}
