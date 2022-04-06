package view;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.DrinksModify;
import controller.StatisticBillModify;
import model.Bills;
import model.Drinks;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JToggleButton;
import javax.swing.JTabbedPane;
import java.awt.Cursor;
import javax.swing.JButton;

public class StatisticTable extends JFrame {

	private JPanel contentPane;
	private JTable billsTable;
	private JTable amountTable;
	private List<Bills> listBills = new ArrayList<Bills>();
	private List<Drinks> listPA = new ArrayList<Drinks>();
	private DefaultTableModel modelBill;
	private DefaultTableModel modelPA;

	public StatisticTable() {
		init();
		showBills();
		showPA();
		this.setLocationRelativeTo(null);
	}

	public void init() {
		setTitle("Statistic");
		setBounds(100, 100, 531, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("");
		tabbedPane.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabbedPane.setBounds(0, 10, 517, 436);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Bills", null, panel, null);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 512, 409);
		panel.add(scrollPane);

		billsTable = new JTable();
		scrollPane.setViewportView(billsTable);
		modelBill = new DefaultTableModel();
		billsTable.setModel(modelBill);
		modelBill.addColumn("ID");
		modelBill.addColumn("Time");
		modelBill.addColumn("Total");

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Total Amount", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 512, 409);
		panel_1.add(scrollPane_1);

		amountTable = new JTable();
		modelPA = new DefaultTableModel();
		amountTable.setModel(modelPA);
		modelPA.addColumn("ID");
		modelPA.addColumn("Name");
		modelPA.addColumn("Purchased Amount");
		scrollPane_1.setViewportView(amountTable);
	}

	public void showPA() {
		listPA = DrinksModify.fillDrinks();
		modelPA.setRowCount(0);
		listPA.forEach((dr) -> {
			modelPA.addRow(new Object[] { modelPA.getRowCount() + 1, dr.getName(), dr.getQuantity() });
		});
	}

	public void showBills() {
		listBills = StatisticBillModify.fillAll();
		modelBill.setRowCount(0);
		for (Bills bill : listBills) {
			modelBill.addRow(new Object[] { bill.getId(), bill.getDate(), bill.getTotal() });
		}
	}

}
