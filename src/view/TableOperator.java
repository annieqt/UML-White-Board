package view;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TableOperator {
	private static TableOperator operator=new TableOperator();
	
	private TableOperator() {}
	
	public static void removeRow(JTable table,int i) {
		((DefaultTableModel) table.getModel()).removeRow(i);
	}
	public static void addRow(JTable table,Object[] o) {
		((DefaultTableModel) table.getModel()).addRow(o);
	}
	public static void initTable(JTable table,Object[][] data,Object[] columnNames) {
		table.getTableHeader().setFont(new java.awt.Font("微软雅黑", 0, 14));
		table.setFont(new java.awt.Font("微软雅黑", 0, 14));
		
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
	    render.setHorizontalAlignment(SwingConstants.CENTER);
	    for(int i=0;i<table.getColumnCount();i++) {
	    	table.getColumn(table.getColumnName(i)).setCellRenderer(render);
	    }		
	    table.setColumnSelectionAllowed(false);
	    table.setCellSelectionEnabled(false);
	    table.setRowSelectionAllowed(true);
	    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    table.setEnabled(true);
	    
	    noEditTableModel tableModel=operator.new noEditTableModel(data, columnNames);
	    table.setModel(tableModel);
	}
	public static void refreshTable(JTable table,ArrayList<Object[]> tableArrayList) {
		while (table.getRowCount()>0) {
			removeRow(table, 0);
		}
		for (Object[] stuffIndexColum : tableArrayList) {
			addRow(table, stuffIndexColum);
		}
	}
	public static String getElementOnTable(JTable table,int row,int column) {
		return ((String) ((DefaultTableModel) table.getModel())
				.getValueAt(row, column));
	}
	
	
	/**一个不可以编辑内容的表格模型。*/
	class noEditTableModel extends DefaultTableModel{
		private static final long serialVersionUID = 1L;
		public noEditTableModel(Object[][] data, Object[] columnNames)	{
	    	super(data, columnNames);
	    }
	    public boolean isCellEditable(int row, int column)	{
	    	return false;//不可以编辑
	    }
	}


}
