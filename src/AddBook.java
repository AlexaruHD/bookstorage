import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddBook {

	private JFrame frmAdaugaOCarte;
	private JTextField textEditura;
	private JTextField textStoc;
	private JTextField textPret;
	private JTextField textTitlu;
	private JTextField textAutor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBook window = new AddBook();
					window.frmAdaugaOCarte.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddBook() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAdaugaOCarte = new JFrame();
		frmAdaugaOCarte.setBackground(new Color(255, 255, 255));
		frmAdaugaOCarte.setTitle("BOOKSTORAGE ADD A BOOK ");
		frmAdaugaOCarte.getContentPane().setMaximumSize(new Dimension(500, 500));
		frmAdaugaOCarte.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frmAdaugaOCarte.setBounds(100, 100, 378, 165);
		frmAdaugaOCarte.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAdaugaOCarte.getContentPane().setLayout(null);
		frmAdaugaOCarte.setLocationRelativeTo(null);
		frmAdaugaOCarte.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(("h9GsKGxOUVubT0D0apVg--1--nzr9q.jpg"))));
		
		JLabel lbAutor = new JLabel("Autor");
		lbAutor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbAutor.setPreferredSize(new Dimension(30, 13));
		lbAutor.setToolTipText("");
		lbAutor.setBounds(20, 40, 55, 16);
		frmAdaugaOCarte.getContentPane().add(lbAutor);
		
		JLabel lbTitlu = new JLabel("Titlu");
		lbTitlu.setToolTipText("");
		lbTitlu.setPreferredSize(new Dimension(30, 13));
		lbTitlu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbTitlu.setBounds(20, 20, 55, 16);
		frmAdaugaOCarte.getContentPane().add(lbTitlu);
		
		JLabel lblEditura = new JLabel("Editura");
		lblEditura.setToolTipText("");
		lblEditura.setPreferredSize(new Dimension(30, 13));
		lblEditura.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEditura.setBounds(20, 60, 55, 16);
		frmAdaugaOCarte.getContentPane().add(lblEditura);
		
		JLabel lblPret = new JLabel("Pret");
		lblPret.setToolTipText("");
		lblPret.setPreferredSize(new Dimension(30, 13));
		lblPret.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPret.setBounds(20, 100, 55, 16);
		frmAdaugaOCarte.getContentPane().add(lblPret);
		
		JLabel lblStoc = new JLabel("Stoc");
		lblStoc.setToolTipText("");
		lblStoc.setPreferredSize(new Dimension(30, 13));
		lblStoc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStoc.setBounds(20, 80, 55, 16);
		frmAdaugaOCarte.getContentPane().add(lblStoc);
		
		textEditura = new JTextField();
		textEditura.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textEditura.setBounds(75, 60, 100, 16);
		frmAdaugaOCarte.getContentPane().add(textEditura);
		textEditura.setColumns(10);
		
		textStoc = new JTextField();
		textStoc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textStoc.setColumns(10);
		textStoc.setBounds(74, 80, 100, 16);
		frmAdaugaOCarte.getContentPane().add(textStoc);
		
		textPret = new JTextField();
		textPret.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPret.setColumns(10);
		textPret.setBounds(75, 100, 100, 16);
		frmAdaugaOCarte.getContentPane().add(textPret);
		
		textTitlu = new JTextField();
		textTitlu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textTitlu.setColumns(10);
		textTitlu.setBounds(75, 20, 100, 16);
		frmAdaugaOCarte.getContentPane().add(textTitlu);
		
		textAutor = new JTextField();
		textAutor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textAutor.setColumns(10);
		textAutor.setBounds(75, 40, 100, 16);
		frmAdaugaOCarte.getContentPane().add(textAutor);
		
		JButton btnAdaugaCartea = new JButton("Adauga cartea");
		btnAdaugaCartea.setFocusable(false);
		btnAdaugaCartea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				DataBase.insertindb(textTitlu.getText(), textAutor.getText(), textEditura.getText(), Integer.valueOf(textStoc.getText()), Float.parseFloat(textPret.getText()) );
				JOptionPane.showMessageDialog(null,"Cartea a fost adaugata cu succes.");
				
			} catch (Exception e2) {
			
				JOptionPane.showMessageDialog(null,"Verifica campurile introduse.");
			}	
			MainPage.resettable();
			MainPage.insertdata();
		}
		});
		btnAdaugaCartea.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdaugaCartea.setBounds(200, 20, 150, 30);
		frmAdaugaOCarte.getContentPane().add(btnAdaugaCartea);
		
		JButton btnReset = new JButton("Goleste campuri");
		btnReset.setFocusable(false);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAutor.setText(null);			
				textTitlu.setText(null);	
				textEditura.setText(null);	
				textPret.setText(null);	
				textStoc.setText(null);	
				
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReset.setBounds(200, 54, 150, 30);
		frmAdaugaOCarte.getContentPane().add(btnReset);
		
		JButton btnInapoi = new JButton("Inapoi");
		btnInapoi.setFocusable(false);
		btnInapoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAdaugaOCarte.dispose();
				MainPage.resettable();
				MainPage.insertdata();
				}
		});
		btnInapoi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInapoi.setBounds(200, 88, 150, 30);
		frmAdaugaOCarte.getContentPane().add(btnInapoi);
	}
}
