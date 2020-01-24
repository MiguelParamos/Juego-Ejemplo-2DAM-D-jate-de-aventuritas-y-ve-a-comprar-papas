package interfazSwing;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import es.cenecmalaga.ddayvacp.MiJuego;

public class PantallaPrincipal extends JPanel {
    private Ventana ventana;
    private JToggleButton tglbtnGenero;
    private JToggleButton tglbtnModoDeMovimiento;

    public PantallaPrincipal(Ventana v){
    	setLayout(null);
        this.ventana=v;
    	
    	JLabel lblDjateDeAventuritas = new JLabel("D\u00E9jate de aventuritas y ve a comprar papas",SwingConstants.CENTER);
        lblDjateDeAventuritas.setFont(new Font("impact", Font.PLAIN, 26));
        lblDjateDeAventuritas.setForeground(Color.decode("#d54f43"));
    	lblDjateDeAventuritas.setBounds(0, 50, 500, 37);
    	add(lblDjateDeAventuritas);
    	
    	JButton btnJugar = new JButton("Jugar");
    	btnJugar.setBounds(100, 325, 300, 55);
    	btnJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.title="D\u00E9jate de aventuritas y ve a comprar papas";
                config.addIcon("parteAndroid/appIconDesktop.jpg", Files.FileType.Internal);
                config.width=500;
                config.height=500;
                config.resizable=false;
                ventana.setVisible(false);
                //Si es masculino no va a estar "selected" el toggle tglbtnGenero
                //Si es femenino, si va a estarlo.
                new LwjglApplication(new MiJuego(
                        tglbtnGenero.isSelected(),
                        tglbtnModoDeMovimiento.isSelected()
                ), config);
            }
        });
    	add(btnJugar);

    	JButton botonOpciones = new JButton("Guardar seleccion G\u00E9nero/Modo de movimiento");
    	botonOpciones.setBounds(50, 407, 400, 37);
    	botonOpciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedWriter bw=new BufferedWriter(new FileWriter("./opciones.ddayvacp"));

                    if(tglbtnGenero.isSelected()){
                        bw.write("femenino");
                    }else{
                        bw.write("masculino");
                    }
                    bw.newLine();
                    if(tglbtnModoDeMovimiento.isSelected()){
                        bw.write("continuo");
                    }else{
                        bw.write("discreto");
                    }
                    bw.flush();
                    bw.close();
                    JOptionPane.showMessageDialog(ventana, "Configuracion guardada!","Configuracion guardada!",JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(ventana, "Error en el guardado","Error en el guardado", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    	add(botonOpciones);

        tglbtnGenero = new JToggleButton("G\u00E9nero Masculino");
        tglbtnGenero.setBounds(100, 241, 300, 23);
        tglbtnGenero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tglbtnGenero.isSelected()){
                    tglbtnGenero.setText("G\u00E9nero Femenino");
                }else{
                    tglbtnGenero.setText("G\u00E9nero Masculino");
                }
            }
        });
    	add(tglbtnGenero);
    	
    	tglbtnModoDeMovimiento = new JToggleButton("Modo de movimiento Discreto");
    	tglbtnModoDeMovimiento.setBounds(100, 276, 300, 23);
        tglbtnModoDeMovimiento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tglbtnModoDeMovimiento.isSelected()){
                    tglbtnModoDeMovimiento.setText("Modo de movimiento Continuo");
                }else{
                    tglbtnModoDeMovimiento.setText("Modo de movimiento Discreto");
                }
            }
        });
    	add(tglbtnModoDeMovimiento);


    	//Si el fichero de opciones existe, lo leo y marco lo que está escrito en él
    	File opciones=new File("./opciones.ddayvacp");
    	if (opciones.exists()){
            try {
                BufferedReader br=new BufferedReader(new FileReader(opciones));
                if(br.readLine().equals("femenino")){
                    tglbtnGenero.doClick();
                }
                if(br.readLine().equals("continuo")){
                    tglbtnModoDeMovimiento.doClick();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("parteAndroid/fondoact1.jpg"));

            g.drawImage(img, 0, 0,500,500, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
