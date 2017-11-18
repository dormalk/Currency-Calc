package ac.shenkar.Calc;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;



//Table GUI not for use.. only show data on windows
public class TableGUI {
	private JFrame frame;
	private JPanel panel;
	
	
	public TableGUI(CurrencyTable _currTable){
		frame=new JFrame();
		frame.setSize(400, 700);
		panel=new JPanel();
		panel.add(buildTable(_currTable));
		frame.add(panel);
		panel.setVisible(true);
	}
	
	private JTable buildTable(CurrencyTable _currTable){
		JTable table;
		DefaultTableModel model=new DefaultTableModel(_currTable.getTable(),new Object[]{"Code","Rate","Change"});
		table=new JTable(model);
		table.setFont(new Font("Arial", Font.PLAIN, 22));
		table.setBackground(new Color(222,222,222));
		table.setRowHeight(40);
		table.setShowGrid(false);
		for (int i=0;i<table.getColumnCount();i++)
			table.getColumnModel().getColumn(i).setPreferredWidth(120);
		
		return table;
	}
	
	
	JFrame getFrame(){
		return frame;
	}
	

}
