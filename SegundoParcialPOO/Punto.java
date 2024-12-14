package SegundoParcialPOO;
import java.util.Vector;
import java.awt.*;
import java.awt.geom.*;

public class Punto <E>{
    private E x;
    private E y;
    private boolean libre = true;

    public Punto(E x, E y){
        this.x = x;
        this.y = y;
    }



    public synchronized void setX(E x){
        /*while (!libre) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        this.x = x;
        //libre = false;
        //notify();
    }

    public synchronized E getX(){
        //while (libre) {
            /*try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        //}
        //libre = true;
        //notify();
        return x;
    }

    public synchronized void setY(E y){
        /*while (!libre) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        libre = false;*/
        this.y = y;
        //notify();
    }

    public synchronized E getY(){
        //while (libre) {
            /*try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        //}
        //libre = true;
        //notify();
        return y;
    }


}
