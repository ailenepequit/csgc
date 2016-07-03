package Graph.Views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Graph.Models.Offering;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class ScheduleView extends JFrame {

	private JPanel contentPane;
	JScrollPane scrollPane;
	private JTable timetable;
	MultiSpanCellTable mtable;
	Offering c = new Offering();
	ArrayList<Offering> courselist;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ScheduleView(int id, String table) {
		setBounds(100, 100, 1090, 740);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		setTitle(table.toUpperCase() + " Schedule");
		contentPane.setLayout(null);

		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		String[] view = {"Tabular", "Calendar"};
		JComboBox comboBox = new JComboBox(view);
		comboBox.setBounds(861, 23, 102, 20);
		comboBox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedItem().equals("Tabular")){
					timetable.setVisible(true);
					mtable.setVisible(false);
				}
				if(comboBox.getSelectedItem().equals("Calendar")){
					timetable.setVisible(false);
//					m.show();
					setMtable();
				}
			}
			
		});
		contentPane.add(comboBox);
		btnPrint.setBackground(new Color(0, 0, 0));
		btnPrint.setForeground(new Color(255, 255, 255));
		btnPrint.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnPrint.setBounds(993, 21, 71, 23);
		contentPane.add(btnPrint);
		
		JLabel label = new JLabel("");
		
		label.setBackground(new Color(46, 139, 87));
		label.setOpaque(true);
		label.setForeground(new Color(255, 255, 255));
		label.setFont(new Font("SansSerif", Font.PLAIN, 15));
		label.setBounds(10, 26, 779, 18);
		contentPane.add(label);
		
		JLabel header = new JLabel("");
		header.setBounds(0, 0, 1074, 63);
		header.setBackground(new Color(46, 139, 87));
		header.setOpaque(true);
		contentPane.add(header);
		

		Object[][] data = new Object[][] { { null, null, null, null, null, null, null, null, null, null } };
		
		if (table.equals("room")) {
			courselist = c.courseListByRoom(id);
			data = new Object[courselist.size()][];
			if (courselist.size() != 0) {
				for (int i = 0; i < courselist.size(); i++) {
					data[i] = courselist.get(i).toTimetableArray();
				}
			} else
				data = new Object[][] { { null, null, null, null, null, null, null, null, null, null } };
				
			//label.setText(courselist.get(id).getRoom() + " Schedule");
		}
		
		if (table.equals("faculty")) {
			courselist = c.courseListByFaculty(id);
			data = new Object[courselist.size()][];
			if (courselist.size() != 0) {
				for (int i = 0; i < courselist.size(); i++) {
					data[i] = courselist.get(i).toTimetableArray();
				}
			} else
				data = new Object[][] { { null, null, null, null, null, null, null, null, null, null } };
				
				label.setText(courselist.get(id).getFaculty() + " Schedule");
		}
		String[] columns = new String[] { "Id", "SY", "Semester", "Start Time", "End Time", "Day", "Description", "Faculty",
				"Class Size", "Room" };
		DefaultTableModel model = new DefaultTableModel(data, columns);
		
		
		timetable = new JTable(model);
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) timetable.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);	
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		timetable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		timetable.setRowSelectionAllowed(false);
		timetable.setGridColor(Color.lightGray);
		timetable.setRowHeight(21);
		timetable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		timetable.setFont(new Font("SansSerif", Font.PLAIN, 12));
		timetable.setBounds(10, 61, 1054, 489);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 74, 1054, 616);
		scrollPane.setViewportView(timetable);
		contentPane.add(scrollPane);
	}
	
	public void setMtable()
	{
		Object[][] data = new Object[][] {
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
		{null, null, null, null, null, null, null},
	};

		
		String[] cols = new String[] { "Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		AttributiveCellTableModel ml = new AttributiveCellTableModel(data, cols) {
			public Object getValueAt(int row, int col) {
				return "" + row + "," + col;
			}
		};
		final CellSpan cellAtt = (CellSpan) ml.getCellAttribute();
		mtable = new MultiSpanCellTable(ml);
		scrollPane = new JScrollPane(mtable);
		
		String[] timeslot = {"07:00AM-07:30AM", "07:30AM-08:00AM", "08:00AM-08:30AM", "08:30AM-09:00AM", "09:00AM-09:30AM", "09:30AM-10:00AM", "10:00AM-10:30AM", "10:30AM-11:00AM", "11:00AM-11:30AM", "11:30AM-12:00PM","12:00PM-12:30PM","12:30PM-01:00PM", "01:00PM-01:30PM","01:30PM-02:00PM", "02:00PM-02:30PM", "02:30PM-03:00PM", "03:00PM-03:30PM", "03:30PM-04:00PM", "04:00PM-04:30PM", "04:30PM-05:00PM", "05:00PM-05:30PM", "05:30PM-06:00PM", "06:00PM-06:30PM", "06:30PM-07:00PM", "07:00PM-07:30PM", "07:30PM-08:00PM", "08:00PM-08:30PM", "08:30PM-09:00PM"};
		for(int i = 0; i < mtable.getRowCount(); i++){
				mtable.setValueAt(timeslot[i], i, 0);
		}
		mtable.setVisible(true);
		int[] col = {1,1};
		int[] rows = {2,3};
		cellAtt.combine(rows, col);
		mtable.clearSelection();
		mtable.revalidate();
		mtable.repaint();
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) timetable.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);	
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		mtable.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		mtable.setRowSelectionAllowed(false);
		mtable.setGridColor(Color.lightGray);
		mtable.setRowHeight(21);
		mtable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		mtable.setFont(new Font("SansSerif", Font.PLAIN, 12));
		mtable.setBounds(10, 61, 1054, 489);
	}
}

//
//@SuppressWarnings({"serial", "rawtypes", "unchecked"})
//class AttributiveCellTableModel extends DefaultTableModel {
//
//	protected CellAttribute cellAtt;
//
//	
//	public AttributiveCellTableModel() {
//		this((Vector) null, 0);
//	}
//
//	public AttributiveCellTableModel(int numRows, int numColumns) {
//		Vector names = new Vector(numColumns);
//		names.setSize(numColumns);
//		setColumnIdentifiers(names);
//		dataVector = new Vector();
//		setNumRows(numRows);
//		cellAtt = new DefaultCellAttribute(numRows, numColumns);
//	}
//
//	public AttributiveCellTableModel(Vector columnNames, int numRows) {
//		setColumnIdentifiers(columnNames);
//		dataVector = new Vector();
//		setNumRows(numRows);
//		cellAtt = new DefaultCellAttribute(numRows, columnNames.size());
//	}
//
//	public AttributiveCellTableModel(Object[] columnNames, int numRows) {
//		this(convertToVector(columnNames), numRows);
//	}
//
//	public AttributiveCellTableModel(Vector data, Vector columnNames) {
//		setDataVector(data, columnNames);
//	}
//
//	public AttributiveCellTableModel(Object[][] data, Object[] columnNames) {
//		setDataVector(data, columnNames);
//	}
//
//	public void setDataVector(Vector newData, Vector columnNames) {
//		if (newData == null)
//			throw new IllegalArgumentException("setDataVector() - Null parameter");
//		dataVector = new Vector(0);
//		this.columnIdentifiers = columnNames;
//		dataVector = newData;
//
//		//
//		cellAtt = new DefaultCellAttribute(dataVector.size(), columnIdentifiers.size());
//
//		newRowsAdded(
//				new TableModelEvent(this, 0, getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
//	}
//
//	public void addColumn(Object columnName, Vector columnData) {
//		if (columnName == null)
//			throw new IllegalArgumentException("addColumn() - null parameter");
//		columnIdentifiers.addElement(columnName);
//		int index = 0;
//		Enumeration eeration = dataVector.elements();
//		while (eeration.hasMoreElements()) {
//			Object value;
//			if ((columnData != null) && (index < columnData.size()))
//				value = columnData.elementAt(index);
//			else
//				value = null;
//			((Vector) eeration.nextElement()).addElement(value);
//			index++;
//		}
//
//		//
//		cellAtt.addColumn();
//
//		fireTableStructureChanged();
//	}
//
//	public void addRow(Vector rowData) {
//		Vector newData = null;
//		if (rowData == null) {
//			newData = new Vector(getColumnCount());
//		} else {
//			rowData.setSize(getColumnCount());
//		}
//		dataVector.addElement(newData);
//
//		cellAtt.addRow();
//
//		newRowsAdded(new TableModelEvent(this, getRowCount() - 1, getRowCount() - 1, TableModelEvent.ALL_COLUMNS,
//				TableModelEvent.INSERT));
//	}
//
//	public void insertRow(int row, Vector rowData) {
//		if (rowData == null) {
//			rowData = new Vector(getColumnCount());
//		} else {
//			rowData.setSize(getColumnCount());
//		}
//
//		dataVector.insertElementAt(rowData, row);
//
//		//
//		cellAtt.insertRow(row);
//
//		newRowsAdded(new TableModelEvent(this, row, row, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
//	}
//
//	public CellAttribute getCellAttribute() {
//		return cellAtt;
//	}
//
//	public void setCellAttribute(CellAttribute newCellAtt) {
//		int numColumns = getColumnCount();
//		int numRows = getRowCount();
//		if ((newCellAtt.getSize().width != numColumns) || (newCellAtt.getSize().height != numRows)) {
//			newCellAtt.setSize(new Dimension(numRows, numColumns));
//		}
//		cellAtt = newCellAtt;
//		fireTableDataChanged();
//	}
//
//	/*
//	 * public void changeCellAttribute(int row, int column, Object command) {
//	 * cellAtt.changeAttribute(row, column, command); }
//	 * 
//	 * public void changeCellAttribute(int[] rows, int[] columns, Object
//	 * command) { cellAtt.changeAttribute(rows, columns, command); }
//	 */
//
//}
//
///**
// * @version 1.0 11/22/98
// */
//
//class DefaultCellAttribute
//		// implements CellAttribute ,CellSpan {
//		implements CellAttribute, CellSpan, ColoredCell, CellFont {
//
//	//
//	// !!!! CAUTION !!!!!
//	// these values must be synchronized to Table data
//	//
//	protected int rowSize;
//	protected int columnSize;
//	protected int[][][] span; // CellSpan
//	protected Color[][] foreground; // ColoredCell
//	protected Color[][] background; //
//	protected Font[][] font; // CellFont
//
//	public DefaultCellAttribute() {
//		this(1, 1);
//	}
//
//	public DefaultCellAttribute(int numRows, int numColumns) {
//		setSize(new Dimension(numColumns, numRows));
//	}
//
//	protected void initValue() {
//		for (int i = 0; i < span.length; i++) {
//			for (int j = 0; j < span[i].length; j++) {
//				span[i][j][CellSpan.COLUMN] = 1;
//				span[i][j][CellSpan.ROW] = 1;
//			}
//		}
//	}
//
//	public int[] getSpan(int row, int column) {
//		if (isOutOfBounds(row, column)) {
//			int[] ret_code = { 1, 1 };
//			return ret_code;
//		}
//		return span[row][column];
//	}
//
//	public void setSpan(int[] span, int row, int column) {
//		if (isOutOfBounds(row, column))
//			return;
//		this.span[row][column] = span;
//	}
//
//	public boolean isVisible(int row, int column) {
//		if (isOutOfBounds(row, column))
//			return false;
//		if ((span[row][column][CellSpan.COLUMN] < 1) || (span[row][column][CellSpan.ROW] < 1))
//			return false;
//		return true;
//	}
//
//	public void combine(int[] rows, int[] columns) {
//		if (isOutOfBounds(rows, columns))
//			return;
//		int rowSpan = rows.length;
//		int columnSpan = columns.length;
//		int startRow = rows[0];
//		int startColumn = columns[0];
//		for (int i = 0; i < rowSpan; i++) {
//			for (int j = 0; j < columnSpan; j++) {
//				if ((span[startRow + i][startColumn + j][CellSpan.COLUMN] != 1)
//						|| (span[startRow + i][startColumn + j][CellSpan.ROW] != 1)) {
//					// System.out.println("can't combine");
//					return;
//				}
//			}
//		}
//		for (int i = 0, ii = 0; i < rowSpan; i++, ii--) {
//			for (int j = 0, jj = 0; j < columnSpan; j++, jj--) {
//				span[startRow + i][startColumn + j][CellSpan.COLUMN] = jj;
//				span[startRow + i][startColumn + j][CellSpan.ROW] = ii;
//				// System.out.println("r " +ii +" c " +jj);
//			}
//		}
//		span[startRow][startColumn][CellSpan.COLUMN] = columnSpan;
//		span[startRow][startColumn][CellSpan.ROW] = rowSpan;
//
//	}
//
//	public void split(int row, int column) {
//		if (isOutOfBounds(row, column))
//			return;
//		int columnSpan = span[row][column][CellSpan.COLUMN];
//		int rowSpan = span[row][column][CellSpan.ROW];
//		for (int i = 0; i < rowSpan; i++) {
//			for (int j = 0; j < columnSpan; j++) {
//				span[row + i][column + j][CellSpan.COLUMN] = 1;
//				span[row + i][column + j][CellSpan.ROW] = 1;
//			}
//		}
//	}
//
//	//
//	// ColoredCell
//	//
//	public Color getForeground(int row, int column) {
//		if (isOutOfBounds(row, column))
//			return null;
//		return foreground[row][column];
//	}
//
//	public void setForeground(Color color, int row, int column) {
//		if (isOutOfBounds(row, column))
//			return;
//		foreground[row][column] = color;
//	}
//
//	public void setForeground(Color color, int[] rows, int[] columns) {
//		if (isOutOfBounds(rows, columns))
//			return;
//		setValues(foreground, color, rows, columns);
//	}
//
//	public Color getBackground(int row, int column) {
//		if (isOutOfBounds(row, column))
//			return null;
//		return background[row][column];
//	}
//
//	public void setBackground(Color color, int row, int column) {
//		if (isOutOfBounds(row, column))
//			return;
//		background[row][column] = color;
//	}
//
//	public void setBackground(Color color, int[] rows, int[] columns) {
//		if (isOutOfBounds(rows, columns))
//			return;
//		setValues(background, color, rows, columns);
//	}
//	//
//
//	//
//	// CellFont
//	//
//	public Font getFont(int row, int column) {
//		if (isOutOfBounds(row, column))
//			return null;
//		return font[row][column];
//	}
//
//	public void setFont(Font font, int row, int column) {
//		if (isOutOfBounds(row, column))
//			return;
//		this.font[row][column] = font;
//	}
//
//	public void setFont(Font font, int[] rows, int[] columns) {
//		if (isOutOfBounds(rows, columns))
//			return;
//		setValues(this.font, font, rows, columns);
//	}
//	//
//
//	//
//	// CellAttribute
//	//
//	public void addColumn() {
//		int[][][] oldSpan = span;
//		int numRows = oldSpan.length;
//		int numColumns = oldSpan[0].length;
//		span = new int[numRows][numColumns + 1][2];
//		System.arraycopy(oldSpan, 0, span, 0, numRows);
//		for (int i = 0; i < numRows; i++) {
//			span[i][numColumns][CellSpan.COLUMN] = 1;
//			span[i][numColumns][CellSpan.ROW] = 1;
//		}
//	}
//
//	public void addRow() {
//		int[][][] oldSpan = span;
//		int numRows = oldSpan.length;
//		int numColumns = oldSpan[0].length;
//		span = new int[numRows + 1][numColumns][2];
//		System.arraycopy(oldSpan, 0, span, 0, numRows);
//		for (int i = 0; i < numColumns; i++) {
//			span[numRows][i][CellSpan.COLUMN] = 1;
//			span[numRows][i][CellSpan.ROW] = 1;
//		}
//	}
//
//	public void insertRow(int row) {
//		int[][][] oldSpan = span;
//		int numRows = oldSpan.length;
//		int numColumns = oldSpan[0].length;
//		span = new int[numRows + 1][numColumns][2];
//		if (0 < row) {
//			System.arraycopy(oldSpan, 0, span, 0, row - 1);
//		}
//		System.arraycopy(oldSpan, 0, span, row, numRows - row);
//		for (int i = 0; i < numColumns; i++) {
//			span[row][i][CellSpan.COLUMN] = 1;
//			span[row][i][CellSpan.ROW] = 1;
//		}
//	}
//
//	public Dimension getSize() {
//		return new Dimension(rowSize, columnSize);
//	}
//
//	public void setSize(Dimension size) {
//		columnSize = size.width;
//		rowSize = size.height;
//		span = new int[rowSize][columnSize][2]; // 2: COLUMN,ROW
//		foreground = new Color[rowSize][columnSize];
//		background = new Color[rowSize][columnSize];
//		font = new Font[rowSize][columnSize];
//		initValue();
//	}
//
//	/*
//	 * public void changeAttribute(int row, int column, Object command) { }
//	 * 
//	 * public void changeAttribute(int[] rows, int[] columns, Object command) {
//	 * }
//	 */
//
//	protected boolean isOutOfBounds(int row, int column) {
//		if ((row < 0) || (rowSize <= row) || (column < 0) || (columnSize <= column)) {
//			return true;
//		}
//		return false;
//	}
//
//	protected boolean isOutOfBounds(int[] rows, int[] columns) {
//		for (int i = 0; i < rows.length; i++) {
//			if ((rows[i] < 0) || (rowSize <= rows[i]))
//				return true;
//		}
//		for (int i = 0; i < columns.length; i++) {
//			if ((columns[i] < 0) || (columnSize <= columns[i]))
//				return true;
//		}
//		return false;
//	}
//
//	protected void setValues(Object[][] target, Object value, int[] rows, int[] columns) {
//		for (int i = 0; i < rows.length; i++) {
//			int row = rows[i];
//			for (int j = 0; j < columns.length; j++) {
//				int column = columns[j];
//				target[row][column] = value;
//			}
//		}
//	}
//}
///*
// * (swing1.1beta3)
// * 
// */
//
///**
// * @version 1.0 11/22/98
// */
//
//interface CellAttribute {
//
//	public void addColumn();
//
//	public void addRow();
//
//	public void insertRow(int row);
//
//	public Dimension getSize();
//
//	public void setSize(Dimension size);
//
//}
///*
// * (swing1.1beta3)
// * 
// */
//
///**
// * @version 1.0 11/22/98
// */
//
//interface CellSpan {
//	public final int ROW = 0;
//	public final int COLUMN = 1;
//
//	public int[] getSpan(int row, int column);
//
//	public void setSpan(int[] span, int row, int column);
//
//	public boolean isVisible(int row, int column);
//
//	public void combine(int[] rows, int[] columns);
//
//	public void split(int row, int column);
//
//}
///*
// * (swing1.1beta3)
// * 
// */
//
///**
// * @version 1.0 11/26/98
// */
//
//@SuppressWarnings({"serial", "rawtypes"})
//class MultiSpanCellTable extends JTable {
//
//	public MultiSpanCellTable(TableModel model) {
//		super(model);
//		//setUI(new MultiSpanCellTableUI());
//		getTableHeader().setReorderingAllowed(false);
//		setCellSelectionEnabled(false);
//		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//	}
//
//	public Rectangle getCellRect(int row, int column, boolean includeSpacing) {
//		Rectangle sRect = super.getCellRect(row, column, includeSpacing);
//		if ((row < 0) || (column < 0) || (getRowCount() <= row) || (getColumnCount() <= column)) {
//			return sRect;
//		}
//		CellSpan cellAtt = (CellSpan) ((AttributiveCellTableModel) getModel()).getCellAttribute();
//		if (!cellAtt.isVisible(row, column)) {
//			int temp_row = row;
//			int temp_column = column;
//			row += cellAtt.getSpan(temp_row, temp_column)[CellSpan.ROW];
//			column += cellAtt.getSpan(temp_row, temp_column)[CellSpan.COLUMN];
//		}
//		int[] n = cellAtt.getSpan(row, column);
//
//		int index = 0;
//		int columnMargin = getColumnModel().getColumnMargin();
//		Rectangle cellFrame = new Rectangle();
//		int aCellHeight = rowHeight + rowMargin;
//		cellFrame.y = row * aCellHeight;
//		cellFrame.height = n[CellSpan.ROW] * aCellHeight;
//
//		Enumeration eeration = getColumnModel().getColumns();
//		while (eeration.hasMoreElements()) {
//			TableColumn aColumn = (TableColumn) eeration.nextElement();
//			cellFrame.width = aColumn.getWidth() + columnMargin;
//			if (index == column)
//				break;
//			cellFrame.x += cellFrame.width;
//			index++;
//		}
//		for (int i = 0; i < n[CellSpan.COLUMN] - 1; i++) {
//			TableColumn aColumn = (TableColumn) eeration.nextElement();
//			cellFrame.width += aColumn.getWidth() + columnMargin;
//		}
//
//		if (!includeSpacing) {
//			Dimension spacing = getIntercellSpacing();
//			cellFrame.setBounds(cellFrame.x + spacing.width / 2, cellFrame.y + spacing.height / 2,
//					cellFrame.width - spacing.width, cellFrame.height - spacing.height);
//		}
//		return cellFrame;
//	}
//
//	private int[] rowColumnAtPoint(Point point) {
//		int[] retValue = { -1, -1 };
//		int row = point.y / (rowHeight + rowMargin);
//		if ((row < 0) || (getRowCount() <= row))
//			return retValue;
//		int column = getColumnModel().getColumnIndexAtX(point.x);
//
//		CellSpan cellAtt = (CellSpan) ((AttributiveCellTableModel) getModel()).getCellAttribute();
//
//		if (cellAtt.isVisible(row, column)) {
//			retValue[CellSpan.COLUMN] = column;
//			retValue[CellSpan.ROW] = row;
//			return retValue;
//		}
//		retValue[CellSpan.COLUMN] = column + cellAtt.getSpan(row, column)[CellSpan.COLUMN];
//		retValue[CellSpan.ROW] = row + cellAtt.getSpan(row, column)[CellSpan.ROW];
//		return retValue;
//	}
//
//	public int rowAtPoint(Point point) {
//		return rowColumnAtPoint(point)[CellSpan.ROW];
//	}
//
//	public int columnAtPoint(Point point) {
//		return rowColumnAtPoint(point)[CellSpan.COLUMN];
//	}
//
//	public void columnSelectionChanged(ListSelectionEvent e) {
//		repaint();
//	}
//
//	public void valueChanged(ListSelectionEvent e) {
//		int firstIndex = e.getFirstIndex();
//		int lastIndex = e.getLastIndex();
//		if (firstIndex == -1 && lastIndex == -1) { // Selection cleared.
//			repaint();
//		}
//		Rectangle dirtyRegion = getCellRect(firstIndex, 0, false);
//		int numCoumns = getColumnCount();
//		int index = firstIndex;
//		for (int i = 0; i < numCoumns; i++) {
//			dirtyRegion.add(getCellRect(index, i, false));
//		}
//		index = lastIndex;
//		for (int i = 0; i < numCoumns; i++) {
//			dirtyRegion.add(getCellRect(index, i, false));
//		}
//		repaint(dirtyRegion.x, dirtyRegion.y, dirtyRegion.width, dirtyRegion.height);
//	}
//
//}
//
///**
// * @version 1.0 11/26/98
// */
//
//class MultiSpanCellTableUI extends BasicTableUI {
//
//	public void paint(Graphics g, JComponent c) {
//		Rectangle oldClipBounds = g.getClipBounds();
//		Rectangle clipBounds = new Rectangle(oldClipBounds);
//		int tableWidth = table.getColumnModel().getTotalColumnWidth();
//		clipBounds.width = Math.min(clipBounds.width, tableWidth);
//		g.setClip(clipBounds);
//
//		int firstIndex = table.rowAtPoint(new Point(0, clipBounds.y));
//		int lastIndex = table.getRowCount() - 1;
//
//		Rectangle rowRect = new Rectangle(0, 0, tableWidth, table.getRowHeight() + table.getRowMargin());
//		rowRect.y = firstIndex * rowRect.height;
//
////		for (int index = firstIndex; index <= lastIndex; index++) {
////			if (rowRect.intersects(clipBounds)) {
////				// System.out.println(); // debug
////				// System.out.print("" + index +": "); // row
////				paintRow(g, index);
////			}
////			rowRect.y += rowRect.height;
////		}
//		g.setClip(oldClipBounds);
//	}
//
//	private void paintRow(Graphics g, int row) {
//		Rectangle rect = g.getClipBounds();
//		boolean drawn = false;
//
//		AttributiveCellTableModel tableModel = (AttributiveCellTableModel) table.getModel();
//		CellSpan cellAtt = (CellSpan) tableModel.getCellAttribute();
//		int numColumns = table.getColumnCount();
//
//		for (int column = 0; column < numColumns; column++) {
//			Rectangle cellRect = table.getCellRect(row, column, true);
//			int cellRow, cellColumn;
//			if (cellAtt.isVisible(row, column)) {
//				cellRow = row;
//				cellColumn = column;
//				// System.out.print(" "+column+" "); // debug
//			} else {
//				cellRow = row + cellAtt.getSpan(row, column)[CellSpan.ROW];
//				cellColumn = column + cellAtt.getSpan(row, column)[CellSpan.COLUMN];
//				// System.out.print(" ("+column+")"); // debug
//			}
//			if (cellRect.intersects(rect)) {
//				drawn = true;
//				paintCell(g, cellRect, cellRow, cellColumn);
//			} else {
//				if (drawn)
//					break;
//			}
//		}
//
//	}
//
//	private void paintCell(Graphics g, Rectangle cellRect, int row, int column) {
//		int spacingHeight = table.getRowMargin();
//		int spacingWidth = table.getColumnModel().getColumnMargin();
//
//		Color c = g.getColor();
//		g.setColor(table.getGridColor());
//		g.drawRect(cellRect.x, cellRect.y, cellRect.width - 1, cellRect.height - 1);
//		g.setColor(c);
//
//		cellRect.setBounds(cellRect.x + spacingWidth / 2, cellRect.y + spacingHeight / 2, cellRect.width - spacingWidth,
//				cellRect.height - spacingHeight);
//
//		if (table.isEditing() && table.getEditingRow() == row && table.getEditingColumn() == column) {
//			Component component = table.getEditorComponent();
//			component.setBounds(cellRect);
//			component.validate();
//		} else {
//			TableCellRenderer renderer = table.getCellRenderer(row, column);
//			Component component = table.prepareRenderer(renderer, row, column);
//
//			if (component.getParent() == null) {
//				rendererPane.add(component);
//			}
//			rendererPane.paintComponent(g, component, table, cellRect.x, cellRect.y, cellRect.width, cellRect.height,
//					true);
//		}
//	}
//}
//
//interface CellFont {
//
//	public Font getFont(int row, int column);
//
//	public void setFont(Font font, int row, int column);
//
//	public void setFont(Font font, int[] rows, int[] columns);
//
//}
//
//interface ColoredCell {
//
//	public Color getForeground(int row, int column);
// 
//	public void setForeground(Color color, int row, int column);
//
//	public void setForeground(Color color, int[] rows, int[] columns);
//
//	public Color getBackground(int row, int column);
//
//	public void setBackground(Color color, int row, int column);
//
//	public void setBackground(Color color, int[] rows, int[] columns);
//
//}
