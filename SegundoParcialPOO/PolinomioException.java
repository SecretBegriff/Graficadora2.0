package SegundoParcialPOO;

public class PolinomioException extends IllegalArgumentException{
    public PolinomioException() {
        super("El grado del polinomio debe ser mayor que 0.");
    }
}
