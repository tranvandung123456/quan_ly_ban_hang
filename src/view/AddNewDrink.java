package view;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DrinksModify;
import model.Drinks;

import javax.swing.JToggleButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddNewDrink extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txtPrice;
	private JTextField txt3;
	private JTextField txtCategory;

	public AddNewDrink() {
		init();
		this.setLocationRelativeTo(null);
	}

	private void init() {
		setBounds(100, 100, 503, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtName = new JTextField();
		txtName.setBounds(182, 36, 283, 35);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txt1 = new JTextField();
		txt1.setEditable(false);
		txt1.setText("Name");
		txt1.setHorizontalAlignment(SwingConstants.CENTER);
		txt1.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt1.setBounds(27, 36, 96, 35);
		contentPane.add(txt1);
		txt1.setColumns(10);

		txt2 = new JTextField();
		txt2.setText("Price");
		txt2.setHorizontalAlignment(SwingConstants.CENTER);
		txt2.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt2.setEditable(false);
		txt2.setColumns(10);
		txt2.setBounds(27, 126, 96, 35);
		contentPane.add(txt2);

		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(182, 126, 283, 35);
		contentPane.add(txtPrice);

		txt3 = new JTextField();
		txt3.setEditable(false);
		txt3.setText("Category");
		txt3.setHorizontalAlignment(SwingConstants.CENTER);
		txt3.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt3.setColumns(10);
		txt3.setBounds(27, 227, 96, 35);
		contentPane.add(txt3);

		txtCategory = new JTextField();
		txtCategory.setColumns(10);
		txtCategory.setBounds(182, 227, 283, 35);
		contentPane.add(txtCategory);

		JButton btnAddNew = new JButton("Add New Drink");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					String name = txtName.getText();
					int price = Integer.valueOf(txtPrice.getText());
					String category = txtCategory.getText();
					if (name != null && price != 0 && category != null) {
						closeAddDrinkView();
						Drinks dr = new Drinks(name, price, category);
						DrinksModify.insert(dr);
					}

				}
			}
		});
		btnAddNew.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAddNew.setToolTipText("Add New Drink");
		btnAddNew.setBounds(190, 292, 140, 35);
		contentPane.add(btnAddNew);
	}

	private void closeAddDrinkView() {
		this.setVisible(false);
	}
}
