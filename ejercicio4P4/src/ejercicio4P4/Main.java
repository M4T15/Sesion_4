package ejercicio4P4;

class Numero {
    private double valor;

    public Numero(double valor) {
        setValor(valor);
    }

    public double getValor() {
    	return valor; 
    }

    public void setValor(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException(" el valor no puede ser negativo" + valor );
        }
        this.valor = valor;
    }
}

public class Main {
    public static void main(String[] args) {
    	
        try {
            Numero num = new Numero(15);
            System.out.println("numero creado de manera correcta  " + num.getValor());
            
            num.setValor(-1);

            System.out.println("intentamos con un numero negativo"+ num.getValor());
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}