package interfazSwing;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Ventana extends JFrame {
	

	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Dejate de aventuritas y ve a comprar papas");
		this.setSize(500,500);
		//this.setContentPane(new JLabel(new ImageIcon("parteAndroid/fondoAct1.jpg")));
		this.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setContentPane(new PantallaPrincipal(this));
		setVisible(true);
		try {
			setIconImage(ImageIO.read(new File("parteAndroid/appIconDesktop.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
