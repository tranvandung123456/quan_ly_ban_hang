package view;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.BillModify;
import controller.BillsModify;
import controller.DrinksModify;
import controller.Search;
import model.Bill;
import model.Bills;
import model.Drinks;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ListSelectionModel;

public class GreenCoffeeView extends JFrame {

	public JPanel contentPane;
	private JTable billTable;
	private JTextField totalText;
	private JTextField moneyC;
	private JTextField searchText;
	private JTable itemTable;
	public JButton btnDelete;
	public JButton btnStatistic;
	private JPanel panel;
	private JButton btnItemDelete;
	private JButton btnItemAdd;
	private DefaultTableModel defaultTableModel2;
	private DefaultTableModel defaultTableModel1;
	private List<Drinks> listDrinks = new ArrayList<Drinks>();
	private int total = 0;
	private List<Bill> listBill = new ArrayList<Bill>();
	private List<Bill> listBill2 = new ArrayList<Bill>();
	private List<Bills> listBills = new ArrayList<Bills>();
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private JTextField moneyText;
	private JTextField returnText;
	private JTextField txtReturn;
	private int count0, count1 = 0;

	public GreenCoffeeView() {
		init();
		defaultTableModel2 = (DefaultTableModel) itemTable.getModel();
		showDrinks();
		this.setLocationRelativeTo(null);
	}

	public void init() {
		setTitle("Green Coffee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 736, 250);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scroll1 = new JScrollPane();
		scroll1.setBounds(0, 0, 436, 250);
		panel.add(scroll1);

		billTable = new JTable();
		scroll1.setViewportView(billTable);
		billTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		defaultTableModel1 = new DefaultTableModel();
		billTable.setModel(defaultTableModel1);
		defaultTableModel1.addColumn("Name");
		defaultTableModel1.addColumn("Quantity");
		defaultTableModel1.addColumn("Price");

		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (billTable.getRowCount() > 0 && count1 == 0) {
					int money = Integer.valueOf(JOptionPane.showInputDialog(null, "Enter money:"));
					if (money >= Integer.valueOf(total)) {
						count0 = 1;
						count1 = 1;
						moneyC.setText(String.valueOf(money));
						returnText.setText(String.valueOf(money - total));
						Date date = new Date();
						String strDate = formatter.format(date);
						BillsModify.insert(strDate, Integer.valueOf(total));
						BillModify.addListBill(listBill, strDate, BillsModify.takeIdBill(strDate));
						addQuantity(listBill);
					} else {
						JOptionPane.showMessageDialog(null, "Lack of money!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "You have to add drink!");
				}
			}
		});
		btnPay.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPay.setBounds(538, 139, 103, 30);
		panel.add(btnPay);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = billTable.getSelectedRow();
				if (selectedIndex >= 0) {
					Bill bill = listBill.get(selectedIndex);
					((DefaultTableModel) billTable.getModel()).removeRow(selectedIndex);
					total -= bill.getPrice();
					listBill.remove(selectedIndex);
					moneyText.setText(String.valueOf(total));
				} else {
					JOptionPane.showMessageDialog(null, "Click on table to select a row you want to delete!");
				}
			}
		});
		btnDelete.setBounds(538, 70, 103, 30);
		panel.add(btnDelete);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "All", "Coffee", "Tea", "Juice" }));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().toString().equals("Coffee")) {
					List<Drinks> ld = Search.searchCaffee();
					showSearchResult(ld);
				} else if (comboBox.getSelectedItem().equals("Tea")) {
					List<Drinks> ld = Search.searchTea();
					showSearchResult(ld);
				} else if (comboBox.getSelectedItem().toString().equals("Juice")) {
					List<Drinks> ld = Search.searchJuice();
					showSearchResult(ld);
				} else {
					showDrinks();
				}
			}
		});
		comboBox.setBounds(10, 357, 81, 34);
		contentPane.add(comboBox);

		totalText = new JTextField();
		totalText.setForeground(Color.BLACK);
		totalText.setEditable(false);
		totalText.setHorizontalAlignment(SwingConstants.CENTER);
		totalText.setFont(new Font("Tahoma", Font.BOLD, 12));
		totalText.setText("Total:");
		totalText.setBounds(236, 274, 70, 26);
		contentPane.add(totalText);
		totalText.setColumns(10);

		moneyC = new JTextField();
		moneyC.setEditable(false);
		moneyC.setFont(new Font("Tahoma", Font.BOLD, 12));
		moneyC.setBounds(305, 249, 131, 26);
		contentPane.add(moneyC);
		moneyC.setColumns(10);

		searchText = new JTextField();
		searchText.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchText.setBounds(138, 357, 366, 34);
		searchText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Search.searchByBtn(searchText.getText()) != null)
					showSearchResult(Search.searchByBtn(searchText.getText()));
			}
		});
		contentPane.add(searchText);
		searchText.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSearchResult(Search.searchByBtn(searchText.getText()));
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSearch.setBounds(564, 357, 100, 34);
		contentPane.add(btnSearch);

		JScrollPane scroll2 = new JScrollPane();
		scroll2.setBounds(0, 412, 563, 291);
		contentPane.add(scroll2);
		itemTable = new JTable();
		itemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemTable.setFont(new Font("Tahoma", Font.BOLD, 14));
		defaultTableModel2 = new DefaultTableModel();
		itemTable.setModel(defaultTableModel2);
		defaultTableModel2.addColumn("ID");
		defaultTableModel2.addColumn("Name");
		defaultTableModel2.addColumn("Price");
		defaultTableModel2.addColumn("Category");

		scroll2.setViewportView(itemTable);

		JButton btnAddItem = new JButton("Add");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = itemTable.getSelectedRow();
				if (selectedIndex >= 0) {
					Drinks dr = listDrinks.get(selectedIndex);
					String s = JOptionPane.showInputDialog(null, "Enter the quantity");
					if (Integer.valueOf(s) != null) {
						int quantity = Integer.valueOf(s);
						if (quantity > 0) {
							if (count0 == 1) {
								defaultTableModel1.setRowCount(0);
								moneyC.setText("");
								returnText.setText("");
								count0 = 0;
								count1 = 0;
								total = 0;
							}
							defaultTableModel1 = (DefaultTableModel) billTable.getModel();
							defaultTableModel1
									.addRow(new Object[] { dr.getName(), quantity, dr.getPrice() * quantity });
							listBill.add(new Bill(dr.getName(), quantity, dr.getPrice() * quantity));
							total += dr.getPrice() * quantity;
							moneyText.setText(String.valueOf(total));
						} else if (quantity <= 0) {
							JOptionPane.showMessageDialog(null, "The quantity should be > 0");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Click on table to select the drink you want to add!");
				}
			}

		});
		btnAddItem.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAddItem.setBounds(597, 431, 85, 41);
		contentPane.add(btnAddItem);

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					LoginView lgv = new LoginView();
					lgv.setVisible(true);
					closeGreenView(e);
				} else {

				}
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogOut.setBounds(6, 283, 100, 34);
		contentPane.add(btnLogOut);

		moneyText = new JTextField();
		moneyText.setEditable(false);
		moneyText.setFont(new Font("Tahoma", Font.BOLD, 12));
		moneyText.setBounds(305, 276, 131, 24);
		contentPane.add(moneyText);
		moneyText.setColumns(10);

		returnText = new JTextField();
		returnText.setEditable(false);
		returnText.setFont(new Font("Tahoma", Font.BOLD, 12));
		returnText.setColumns(10);
		returnText.setBounds(305, 303, 131, 24);
		contentPane.add(returnText);

		txtReturn = new JTextField();
		txtReturn.setText("Return");
		txtReturn.setHorizontalAlignment(SwingConstants.CENTER);
		txtReturn.setForeground(Color.BLACK);
		txtReturn.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtReturn.setEditable(false);
		txtReturn.setColumns(10);
		txtReturn.setBounds(236, 301, 70, 26);
		contentPane.add(txtReturn);

		btnStatistic = new JButton("Statistic");
		btnStatistic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatisticTable st = new StatisticTable();
				st.setVisible(true);
			}
		});
		btnStatistic.setBounds(597, 632, 94, 41);
		btnStatistic.setFont(new Font("Tahoma", Font.BOLD, 12));

		btnItemDelete = new JButton("Delete");
		btnItemDelete.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int selectedIndex = itemTable.getSelectedRow();
				if (selectedIndex >= 0) {
					Drinks dr = listDrinks.get(selectedIndex);
					int option = JOptionPane.showConfirmDialog(null, "Do you want to delete?");
					if (option == 0) {
						DrinksModify.delete(dr.getId());
						showDrinks();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Click on table to select the drink you want to delete!");
				}
			}
		});
		btnItemDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnItemDelete.setBounds(597, 501, 94, 41);

		btnItemAdd = new JButton("Add New");
		btnItemAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddNewDrink ad = new AddNewDrink();
				ad.setVisible(true);
			}
		});
		btnItemAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnItemAdd.setBounds(597, 564, 92, 41);
	}

	public void addQuantity(List<Bill> listBill) {
		for (Bill b : listBill) {
			DrinksModify.addQuantity(b.getName(), b.getQuantity());
		}
	}

	public void closeGreenView(ActionEvent e) {
		this.setVisible(false);
	}

	public void showDrinks() {
		listDrinks = DrinksModify.fillDrinks();
		defaultTableModel2.setRowCount(0);
		listDrinks.forEach((dr) -> {
			defaultTableModel2.addRow(new Object[] { defaultTableModel2.getRowCount() + 1, dr.getName(), dr.getPrice(),
					dr.getCategory() });
		});

	}

	private void showSearchResult(List<Drinks> lds) {
		defaultTableModel2.setRowCount(0);
		lds.forEach((dr) -> {
			defaultTableModel2.addRow(new Object[] { defaultTableModel2.getRowCount() + 1, dr.getName(), dr.getPrice(),
					dr.getCategory() });
		});
	}

	public void addBtnItemAdd() {
		contentPane.add(btnItemAdd);
	}

	public void addBtnStatistic() {
		contentPane.add(btnStatistic);
	}

	public void addBtnItemDelete() {
		contentPane.add(btnItemDelete);
	}
}
