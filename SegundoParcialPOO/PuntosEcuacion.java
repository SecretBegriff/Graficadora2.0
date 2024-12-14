package SegundoParcialPOO;
import java.util.Vector;
import java.awt.*;
import java.awt.geom.*;

public class PuntosEcuacion extends Canvas{
    private static Vector<Punto<Double>> puntos;
    private Polinomio poli;
    private static String ecuacion;
    private Thread hilo;

    public PuntosEcuacion(){
        super();
        hilo = new Thread();
        puntos = new Vector<>();
        poli = new Polinomio(0); // Inicializar poli con un polinomio por defecto
    }

    


    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        int x1 =50;
        int y1=50;
        
        //g2d.translate(0, 0);
        g2d.setColor(Color.LIGHT_GRAY);
        for(int i=0;i<30;i++){
            g2d.draw(new Line2D.Float(x1,0,x1,600));
            x1+=50;
        }
        for(int i=0;i<30;i++){
            g2d.draw(new Line2D.Float(0,y1,600,y1));
            y1+=50;
        }
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2.0f));
        g2d.draw(new Line2D.Float(0, 300, 600, 300));
        g2d.draw(new Line2D.Float(300, 0, 300, 600));
        g2d.setColor(Color.GREEN);
        g2d.setColor(Color.RED);
        g2d.translate(300, 300);

        if (!puntos.isEmpty()) {
            for(int i = 1; i < puntos.size(); i++){
                try {
                    hilo.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                g2d.draw(new Line2D.Double(puntos.get(i-1).getX()*20, puntos.get(i-1).getY()*20, puntos.get(i).getX()*20, puntos.get(i).getY()*20));
            }
        }
        
        Font font = new Font("Arial", Font.BOLD + Font.ITALIC, 15);
        g2d.setFont(font);




    }

    public PuntosEcuacion(Polinomio poli, double linf, double lsup, double inc){
        this.poli = poli;
        puntos = new Vector<>();
        for(double i=linf; i < lsup; i+=inc){
            double y = poli.evalua_p(i);
            Punto<Double> nuevoPunto = new Punto<>(i,y);
            puntos.add(nuevoPunto);
        }
    }

    public Vector<Punto<Double>> getPuntosEcuacion(){ 
        return puntos;
    }

    public Punto<Double> getPunto(int num){
        double aux = poli.evalua_p(num);
        Punto<Double> punto = new Punto<Double>(Double.valueOf(num), Double.valueOf(aux));
        return punto;
    } 
    
}
