package Ejercicios;

import java.io.IOException;

public class ProcesadorEntrada {

    private LeerEntrada lector;

    public ProcesadorEntrada() {
        this.lector = new LeerEntrada(System.in);
    }

    public void procesar() throws ExcepcionVocal, ExcepcionNumero, ExcepcionBlanco, ExcepcionSalida, IOException {
        char c = lector.getChar();

        if (c == 'q' || c == 'Q') {
            throw new ExcepcionSalida("Caracter de salida detectado.");
        }

        if ("aeiouAEIOU".indexOf(c) != -1) {
            throw new ExcepcionVocal("Es una vocal: " + c);
        }

        if (Character.isDigit(c)) {
            throw new ExcepcionNumero("Es un numero: " + c);
        }

        if (Character.isWhitespace(c)) {
            throw new ExcepcionBlanco("Es un espacio en blanco.");
        }
    }

    public static void main(String[] args) {
        ProcesadorEntrada app = new ProcesadorEntrada();
        boolean ejecutando = true;

        while (ejecutando) {
            try {
                app.procesar();
            } catch (ExcepcionVocal e) {
                System.out.println(e.getMessage());
            } catch (ExcepcionNumero e) {
                System.out.println(e.getMessage());
            } catch (ExcepcionBlanco e) {
                System.out.println(e.getMessage());
            } catch (ExcepcionSalida e) {
                System.out.println(e.getMessage());
                ejecutando = false;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                ejecutando = false;
            }
        }
    }
}