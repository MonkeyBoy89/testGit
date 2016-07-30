package home;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.StyleConstants.FontConstants;

/**
 * Gestion de l'affichage graphique de l'application
 * @author MonkeyBoy
 *
 */
public class IHM {

	/** Champs de saisie */
	private static JTextField textBox;
	
	public static void main(String[] args) 
	{
		createWindow();
	}

	/**
	 * Set up the main window
	 */
	private static void createWindow() {
		JFrame fenetre = new JFrame();
		fenetre.setTitle("DNA Decryptor 2000");
		fenetre.setSize(600, 470);
		fenetre.setLocationRelativeTo(null);
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setResizable(false);
		fenetre.setContentPane(createPanel());
		createButton(fenetre);
		createTextBox(fenetre);
		fenetre.setVisible(true);
	}
	
	
	private static Panneau createPanel()
	{
		return new Panneau();
	}
	
	private static void createButton(JFrame window)
	{
		window.getContentPane().setLayout(new BorderLayout());
		JButton button = new JButton("Decrypter...");
		window.getContentPane().add(button, BorderLayout.SOUTH);
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				process();
			}
		});
	}
	
	/**
	 * Panneau personnalisé avec une image de fond
	 * @author MonkeyBoy
	 *
	 */
	private static class Panneau extends JPanel
	{
		private static final long serialVersionUID = 1L;

		@Override
		protected void paintComponent(Graphics g) 
		{
			try 
			{
				Image img = ImageIO.read(new File(getClass().getResource("test.png").toURI()));
				g.drawImage(img, 0, 0, this);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	private static void createTextBox(JFrame window)
	{
		textBox = new JTextField();
		window.getContentPane().add(textBox, BorderLayout.NORTH);
	}
	
	
	/**
	 * Execute the decrypting process of DNA
	 */
	private static void process() 
	{
		if (textBox.getText() == null || textBox.getText().length() == 0) 
		{
			textBox.setText("Veuillez saisir une chaine valide !");
			return;
		}
		
		String res = DNADescryptor.decrypt(textBox.getText());
		
		try 
		{
			DNADescryptor.checkSequence(res);
			textBox.setText(res);
		} 
		catch (Exception e) 
		{
			textBox.setText(e.getMessage());
		}
		
	}

}
