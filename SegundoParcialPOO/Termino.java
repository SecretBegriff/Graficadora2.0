package SegundoParcialPOO;
import java.lang.Math;


public class Termino
{
    private double Coeficiente;
    private int Exponente;
   
    public Termino(double coef, int xxp)
    {
        Coeficiente = coef;
        Exponente = xxp;
    }


    public double evalua(double x)
    {
        return new Double(Coeficiente*Math.pow(x, Exponente));
    }


    public int getExponente()
    {
        return new Integer(Exponente);
    }


    public double getCoeficiente()
    {
        return new Double(Coeficiente);
    }



    public String toString()
    {
        if(Coeficiente != 0){
            if(Coeficiente == 1 && Exponente != 0){
                return "x" + "^" + Exponente;
            }
            if(Coeficiente == 1 && Exponente == 0){
                return "x";
            }
            if(Coeficiente%1 == 0){
                if(Exponente == 0){
                    return String.valueOf((int)Coeficiente);
                }
                if(Exponente == 1){
                    return (int)Coeficiente + "x";
                }
                return (int)Coeficiente + "x" +"^" + Exponente;
                
            }
            else{
                if(Exponente == 0){
                    return String.valueOf(Coeficiente);
                }
                if(Exponente == 1){
                    return Coeficiente + "x";
                }
                return Coeficiente + "x" +"^" + Exponente;
            }
            
        }
        return "";
    }

}
