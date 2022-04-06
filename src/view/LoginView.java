package view;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.Color;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField txtA;
	private JTextField txtUser;
	private JTextField txtP;
	private JPasswordField txtPassword;
	private JButton btnLogin;

	
	
	
	public LoginView() {
		init();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}




	public void init() {
		setTitle("Green Coffee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtA = new JTextField();
		txtA.setSelectionColor(SystemColor.controlText);
		txtA.setDisabledTextColor(Color.BLACK);
		txtA.setEnabled(false);
		txtA.setForeground(Color.BLACK);
		txtA.setHorizontalAlignment(SwingConstants.LEFT);
		txtA.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtA.setText("User Name");
		txtA.setEditable(false);
		txtA.setBounds(47, 66, 96, 34);
		contentPane.add(txtA);
		txtA.setColumns(10);
		
		Action action = new AbstractAction()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	if(txtUser.getText().equals("admin") && txtPassword.getText().equals("123456")) {
					GreenCoffeeView grv = new GreenCoffeeView();
					grv.addBtnStatistic();
					grv.addBtnItemDelete();
					grv.addBtnItemAdd();
					grv.setVisible(true);
					closeLoginView(e);
				}else if(txtUser.getText().equals("user") && txtPassword.getText().equals("123456")) {
					GreenCoffeeView grv = new GreenCoffeeView();
					grv.setVisible(true);
					closeLoginView(e);
				}else {	
					JOptionPane.showMessageDialog(null,"Invalid User Name or Password");
				}
		    }
		};
		
		txtUser = new JTextField();
		txtUser.addActionListener(action);
		txtUser.setHorizontalAlignment(SwingConstants.CENTER);
		txtUser.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtUser.setBounds(195, 66, 269, 34);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		txtP = new JTextField();
		txtP.setSelectionColor(SystemColor.menu);
		txtP.setDisabledTextColor(Color.BLACK);
		txtP.setEnabled(false);
		txtP.setText("Password");
		txtP.setHorizontalAlignment(SwingConstants.CENTER);
		txtP.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtP.setEditable(false);
		txtP.setColumns(10);
		txtP.setBounds(47, 142, 96, 34);
		contentPane.add(txtP);
		
		txtPassword = new JPasswordField();
		txtPassword.addActionListener(action);
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtPassword.setBounds(195, 142, 269, 34);
		contentPane.add(txtPassword);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(action);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setBounds(195, 231, 273, 28);
		contentPane.add(btnLogin);	
							
	}
	
	protected void closeLoginView(ActionEvent e) {
		this.setVisible(false);	}
}
