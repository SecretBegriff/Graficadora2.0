package SegundoParcialPOO;

public class GradeException extends IllegalArgumentException{

    public GradeException(){
        super("Grado del polinomio menor al grado del coeficiente integrado");
    }

}
