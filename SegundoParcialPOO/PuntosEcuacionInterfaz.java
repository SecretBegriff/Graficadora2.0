package SegundoParcialPOO;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

import javax.management.RuntimeErrorException;
import javax.swing.*;

import org.w3c.dom.events.EventException;

/*
 * Avance Puntos Ecuacion Interfaz Grafica v1.0
 * Pablo Serrano Limón, Jesus Perez Rodriguez, Enrique Alfonso Gracian Castro
 */

public class PuntosEcuacionInterfaz extends JFrame{
    private JButton btnCP, btnAT, btnG;
    private JTextField txtGrado, txtCoeficiente, txtExponente, txtLimiteInferior, txtLimiteSuperior, txtIntervalo;
    private JPanel pnlDatos, pnlGrafica, pnlParametros, pnlCoeficientes, pnlLimites, pnlP1, pnlC1, pnlL1;
    private JLabel lblTituloP, lblGrado, lblCoef, lblExponente, lblLimInf, lblLimSup, lblIntervalo;
    private PuntosEcuacion punE;
    private double coef, limI, limS, inter;
    private int expo, grado = -10, index;

    private Termino t1;
    private Polinomio p1;

    private Thread hilo;

    public PuntosEcuacionInterfaz(){
        super("Geogebra");

        index = 0;

        //BOTONES
        btnCP = new JButton("Crear Polinomio");
        btnAT = new JButton("Añadir Término");
        btnG = new JButton("Graficar");

        //JLabelS
        lblTituloP = new JLabel("Parametros de Gráfica:");
        lblGrado = new JLabel("Grado de polinomio");
        lblCoef = new JLabel("Coeficiente de Término");
        lblExponente = new JLabel("Exponente de Término");
        lblLimInf = new JLabel("Limite Inferior");
        lblLimSup = new JLabel("Limite Superior");
        lblIntervalo = new JLabel("Intervalo");

        //JTextField
        txtGrado = new JTextField();
        txtCoeficiente = new JTextField();
        txtExponente = new JTextField();
        txtLimiteInferior = new JTextField();
        txtLimiteSuperior = new JTextField();
        txtIntervalo = new JTextField();


        //JPanelES
        pnlDatos = new JPanel();
        pnlGrafica = new JPanel();
        pnlParametros = new JPanel();
        pnlCoeficientes = new JPanel();
        pnlLimites = new JPanel();
        pnlP1 = new JPanel();
        pnlC1 = new JPanel();
        pnlL1 = new JPanel();

        
            //agregar los JPaneles al JPanel datos y organizarlo
        pnlDatos.setLayout(new GridLayout(3, 1,10,10));
        pnlDatos.add(pnlParametros);
        pnlDatos.add(pnlCoeficientes);
        pnlDatos.add(pnlLimites);
        
            //Dar diseño a los JPaneles de datos
        pnlParametros.setLayout(new BorderLayout());
        pnlCoeficientes.setLayout(new BorderLayout());
        pnlLimites.setLayout(new BorderLayout());
        
            //Dar el diseño de los JPaneles internos de los datos
        pnlP1.setLayout(new GridLayout(4,2));
        pnlC1.setLayout(new GridLayout(4,2));
        pnlL1.setLayout(new GridLayout(6,2));

            //agregar los botones a cada JPanel
        pnlParametros.add(lblTituloP, "North");
        pnlP1.add(lblGrado);
        pnlP1.add(txtGrado);

        pnlC1.add(lblCoef);
        pnlC1.add(txtCoeficiente);
        pnlC1.add(lblExponente);
        pnlC1.add(txtExponente);

        pnlL1.add(lblLimInf);
        pnlL1.add(txtLimiteInferior);
        pnlL1.add(lblLimSup);
        pnlL1.add(txtLimiteSuperior);
        pnlL1.add(lblIntervalo);
        pnlL1.add(txtIntervalo);

            //organizar los JPaneles de datos
        pnlParametros.add(btnCP, "South");
        pnlParametros.add(pnlP1, "Center");
        pnlCoeficientes.add(pnlC1, "Center");
        pnlLimites.add(pnlL1, "Center");

            //organizar los botones del JPanel de coeficiente/exponente
        pnlCoeficientes.add(pnlC1, "Center");
        pnlCoeficientes.add(btnAT, "South");
        pnlLimites.add(btnG, "South");

            //agregar los JPaneles a datos
        pnlDatos.add(pnlParametros);
        pnlDatos.add(pnlCoeficientes);
        pnlDatos.add(pnlLimites);

        punE = new PuntosEcuacion(p1, limI, limS, inter);

        //grafica
        pnlGrafica.add(punE);

        //Dar valores a los botones
        btnCP.addActionListener(new CrearPolinomio());
        btnAT.addActionListener(new anadirTermino());
        btnG.addActionListener(new graficar());

        //ORGANIZAR JPanelES
        add(pnlDatos, "West");
        add(pnlGrafica, "Center");

        


            // Agregar listener para redimensionar la gráfica cuando el JPanel cambie de tamaño
        pnlGrafica.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                punE.setSize(pnlGrafica.getSize());
                punE.repaint();
            }
        });

        //abrir ventana
        setSize(725,650);
        setVisible(true);
        setResizable(false);
    }

    

    private class CloseWindow extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e){
            setVisible(false);
            dispose();
        }

    }

    private class CrearPolinomio implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e){
            try {
                gradoPoli();
            } catch (Exception pe) {
                // TODO: handle exception
                JOptionPane.showMessageDialog(null, pe.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            
        }


        private void gradoPoli() throws PolinomioException{
            if(!txtGrado.getText().matches("\\d+")){
                throw new NumberException();
            }
            else{
                grado = Integer.parseInt(txtGrado.getText());
            }
            if(grado<=0){
                throw new PolinomioException();
            }
            else{
                grado = Integer.parseInt(txtGrado.getText());
                p1 = new Polinomio(grado);
            }
        }

    }

    private class anadirTermino implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e){

            try {
                getGrado();               
            } catch(Exception ex2) {
                JOptionPane.showMessageDialog(null, ex2.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

            private void getGrado() throws GradeException{
                if(txtGrado.getText().isEmpty()){
                    throw new NoGraphicException();
                }
                if ((!txtCoeficiente.getText().matches("-?\\d*\\.?\\d+")) || 
                (!txtExponente.getText().matches("-?\\d*\\.?\\d+"))){
                    throw new NumberException();
                } 

                coef = Double.parseDouble(txtCoeficiente.getText());
                expo = Integer.parseInt(txtExponente.getText());

                if(expo>grado){
                    throw new GradeException();
                }else{
                    t1 = new Termino(coef, expo);
                    p1.agregarTermino(index,t1);
                    index++;
                    System.out.println(t1.toString());
                }

            }
                       
    }

    private class graficar implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e){
            //pnlGrafica.remove(punE);
            // Obtener los valores ingresados en los campos de texto
            try {
                grafic();
            } catch (Exception g) {
                // TODO: handle exception
                JOptionPane.showMessageDialog(null, g.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

            }

        }
    
        private void grafic() throws LimitEx{
            if(txtGrado.getText().isEmpty()){
                throw new NoGraphicException();
            }

            if(txtCoeficiente.getText().isEmpty() && txtExponente.getText().isEmpty()){
                throw new NoGraphicException();
            }

            if ((!txtLimiteInferior.getText().matches("-?\\d*\\.?\\d+")) || 
            (!txtLimiteSuperior.getText().matches("-?\\d*\\.?\\d+")) || 
            (!txtIntervalo.getText().matches("-?\\d*\\.?\\d+"))){
                throw new NumberException();
            } 

            limI = Double.parseDouble(txtLimiteInferior.getText());
            limS = Double.parseDouble(txtLimiteSuperior.getText());

            if(limS - limI < 1){
                throw new LimitEx();
            }

            inter = Double.parseDouble(txtIntervalo.getText());
            
            if(inter <= 0){
                throw new LimitEx();
            }
            else{

                for(Punto<Double> punto : punE.getPuntosEcuacion()){
                    System.out.println(punto.getX() + ", " + punto.getY())  ;
                }
                punE.getPuntosEcuacion();
                    // Agregar la nueva gráfica al JPanel
                pnlGrafica.removeAll();
    
                // Crear una nueva instancia de PuntosEcuacion con los valores obtenidos
                punE = new PuntosEcuacion(p1, limI, limS, inter);
                
                punE.setBounds(10, 5, 700, 600);
                pnlGrafica.add(punE);
                pnlGrafica.setComponentZOrder(punE, 0);
            }
        }

    }


    public static void main(String[] args) {
        PuntosEcuacionInterfaz p = new PuntosEcuacionInterfaz();
        p.setVisible(true);
        

    }

}
