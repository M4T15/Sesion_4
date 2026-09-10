 package ejerccio2P4;

class DivisionPorCeroException extends ArithmeticException {
  public DivisionPorCeroException(String mensaje) {
      super(mensaje);
  }
}

class Calculadora {
  public double dividir(double a, double b) {
      if (b == 0) {
          throw new DivisionPorCeroException(" no puede dividir entre 0 ");
      }
      return a / b;
  }
}

public class Main {
  public static void main(String[] args) {
      Calculadora calc = new Calculadora();
      
      System.out.println("Prueba de manera correcta");
      try {
          double resultado = calc.dividir(10, 2);
          System.out.println("rpta :" + resultado);
          
      } catch (Exception e) {
          System.out.println("tipo error:  " + e.getMessage());
      }

      System.out.println("\n ejemplo de divicion entre 0 ");
      try {
          System.out.println("rpta : " + calc.dividir(10, 0));
          
      } catch (IllegalArgumentException e) {
          System.out.println("Error : " + e.getMessage());
          
      } catch (ArithmeticException e) {
          System.out.println("Error  " + e.getMessage());
      }
  }
}