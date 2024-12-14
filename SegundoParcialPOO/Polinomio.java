package SegundoParcialPOO;

public class Polinomio {
    private Termino[] terminos;
    private int maxGrado;


    public Polinomio(int g){
        terminos = new Termino[g];
        maxGrado = g;
    }


    


    public void agregarTermino(int index, Termino t){
        if(t.getExponente() <= maxGrado){
            if(index >= 0 &&  index < terminos.length) 
                terminos[index] = t;
        }
    }


    public double evalua_p(double x){
        double res=0;
        for(int i = 0; i < maxGrado; i++){
            if(terminos[i] == null){
                res +=0;
            }
            else{
                res += -1*terminos[i].evalua(x);
            }
        }
        return res;
    }


    public String toString() {
        StringBuilder polinomio = new StringBuilder();
    
        for (int i = 0; i < terminos.length; i++) {
            if(terminos[i] != null){
                if (terminos[i].getCoeficiente() != 0) {
                    if (i != 0 && terminos[i].getCoeficiente() > 0) {
                        polinomio.append(" + ");
                    }
                    polinomio.append(terminos[i].toString());
                }
            }
        }
    
        // Si el polinomio está vacío, significa que todos los términos tienen coeficiente 0
        if (polinomio.length() == 0) {
            return "0";
        } else {
            return polinomio.toString();
        }
    }


    public void cambiarTermino(Termino t, int index){
        terminos[index] = t;
    }


}
