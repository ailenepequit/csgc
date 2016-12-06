package Graph.Views;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Graph.Models.Building;
import Graph.Models.Room;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
public class RoomPanel extends JPanel {

	private int roomID, bID;
	private String bname;
	private JTable roomTable;
	private JButton btnAddRoom, btnUpdateRoom, btnDeleteRoom;
	DefaultTableModel roomModel;
	private JTextField roomField, capacityField;
	private JComboBox buildingComboBox, typeComboBox;
	Formatter format = new Formatter();
	private JComboBox comboBox;
	Object[][] room_data;

	Room room = new Room();
	ArrayList<Room> roomlist = room.roomList("All");
	private JPanel panel;

	public RoomPanel() {
		setBackground(Color.WHITE);
		final Building b = new Building();
		final DefaultComboBoxModel<String> cm = new DefaultComboBoxModel<String>();
		ArrayList<Building> blist = b.buildingList();
		final String[] room_columns = new String[] { "Id", "Building", "Name", "Type", "Capacity" };

		room_data = new Object[roomlist.size()][];
		for (int i = 0; i < roomlist.size(); i++) {
			room_data[i] = roomlist.get(i).toObjectArray();
		}
		for (int i = 0; i < blist.size(); i++) {
			cm.addElement(blist.get(i).getBname());
		}
		roomModel = new DefaultTableModel(room_data, room_columns);

		String[] type = { "Laboratory", "Lecture" };

		setBounds(253, 73, 1001, 586);
		setLayout(null);
		setVisible(false);

		btnAddRoom = new JButton("Add");
		btnAddRoom.setEnabled(false);
		format.buttonFormat(btnAddRoom, format.addIcon);
		btnAddRoom.setBounds(590, 488, 91, 30);
		btnAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String building = buildingComboBox.getSelectedItem().toString();
				bID = b.getID(building);
				int roomID = room.getID(roomField.getText());
				String roomname = roomField.getText();
				String type = typeComboBox.getSelectedItem().toString();
				String capacity = capacityField.getText();
				room.addRoom(roomname, bID, type, Integer.parseInt(capacity));
				addRoomToTable(roomID, roomname, building, type, capacity);
			}
		});

		btnDeleteRoom = new JButton("Delete");
		btnDeleteRoom.setEnabled(false);
		format.buttonFormat(btnDeleteRoom, format.deleteIcon);
		btnDeleteRoom.setBounds(881, 488, 91, 30);
		btnDeleteRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = roomTable.getSelectedRow();
				roomID = Integer.parseInt(roomTable.getValueAt(i, 0).toString());
				if (i >= 0) {
					room.deleteRoom(roomID);
					roomModel.removeRow(i);
					clearRoomFormFields();
				} else {
					System.out.println("Delete Error");
				}
			}
		});

		btnUpdateRoom = new JButton("Update ");
		btnUpdateRoom.setEnabled(false);
		btnUpdateRoom.setBounds(736, 488, 91, 30);
		format.buttonFormat(btnUpdateRoom, format.updateIcon);
		btnUpdateRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = roomTable.getSelectedRow();
				roomID = Integer.parseInt(roomTable.getValueAt(i, 0).toString());
				roomModel.setValueAt(roomID, i, 0);
				roomModel.setValueAt(cm.getSelectedItem(), i, 1);
				roomModel.setValueAt(roomField.getText(), i, 2);
				roomModel.setValueAt(capacityField.getText(), i, 4);
				roomModel.setValueAt(typeComboBox.getSelectedItem(), i, 3);
				bID = b.getID(cm.getSelectedItem().toString());
				room.editRoom(roomID, roomField.getText(), bID, typeComboBox.getSelectedItem().toString(),
						Integer.parseInt(capacityField.getText()));
			}
		});

		JButton btnNewRoom = new JButton("New");
		format.buttonFormat(btnNewRoom, format.newIcon);
		btnNewRoom.setBounds(883, 19, 89, 30);
		btnNewRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearRoomFormFields();
			}
		});
		;

		String[] r = { "All", "Laboratory", "Lecture" };
		comboBox = new JComboBox(r);
		comboBox.setBounds(20, 19, 111, 30);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String t = comboBox.getSelectedItem().toString();
				roomlist = room.roomList(t);
				room_data = new Object[roomlist.size()][];
				for (int i = 0; i < roomlist.size(); i++) {
					room_data[i] = roomlist.get(i).toObjectArray();
				}
				roomModel = new DefaultTableModel(room_data, room_columns);
				roomTable.setModel(roomModel);
			}
		});

		JPanel roomFormPanel = new JPanel();
		roomFormPanel.setBounds(590, 61, 382, 416);
		roomFormPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Room Information Details",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		roomFormPanel.setLayout(null);

		JLabel lblBuilding = new JLabel("Building");
		lblBuilding.setBounds(25, 68, 55, 14);
		format.labelFormat(lblBuilding);

		JLabel lblRoomName = new JLabel("Room Name");
		lblRoomName.setBounds(25, 124, 70, 14);
		format.labelFormat(lblRoomName);

		JLabel lblType = new JLabel("Type");
		lblType.setBounds(25, 180, 46, 14);
		format.labelFormat(lblType);

		JLabel lblCapacity = new JLabel("Capacity");
		lblCapacity.setBounds(25, 238, 46, 14);
		format.labelFormat(lblCapacity);

		buildingComboBox = new JComboBox(cm);
		buildingComboBox.setBounds(115, 60, 244, 30);
		format.comboBoxFormat(buildingComboBox);

		roomField = new JTextField();
		roomField.setBounds(115, 116, 244, 30);
		roomField.setColumns(10);

		typeComboBox = new JComboBox<Object>(type);
		typeComboBox.setSelectedIndex(-1);
		format.comboBoxFormat(typeComboBox);
		typeComboBox.setBounds(115, 172, 182, 30);

		capacityField = new JTextField();
		capacityField.setHorizontalAlignment(SwingConstants.TRAILING);
		capacityField.setBounds(115, 230, 86, 30);
		capacityField.setColumns(10);
		add(btnAddRoom);
		add(btnNewRoom);
		add(btnDeleteRoom);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Room List", TitledBorder.CENTER,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(14, 60, 528, 465);
		add(panel);
		panel.setLayout(null);

		roomTable = new JTable(roomModel) {
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width + 10;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				tableColumn.setPreferredWidth(
						Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
				return component;
			}
		};
		
		format.tableFormat(roomTable);
		roomTable.setBounds(26, 92, 446, 462);
		roomTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		JScrollPane roomListScrollPane = new JScrollPane();
		roomListScrollPane.setBounds(6, 16, 516, 450);
		panel.add(roomListScrollPane);
		roomListScrollPane.setViewportView(roomTable);
		roomListScrollPane.getViewport().setBackground(Color.WHITE);

		roomTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int i = roomTable.getSelectedRow();
				btnAddRoom.setEnabled(false);
				btnUpdateRoom.setEnabled(true);
				btnDeleteRoom.setEnabled(true);
				roomID = Integer.parseInt(roomModel.getValueAt(i, 0).toString());
				bname = roomModel.getValueAt(i, 1).toString();
				cm.setSelectedItem(bname);
				roomField.setText(roomModel.getValueAt(i, 2).toString());
				typeComboBox.setSelectedItem(roomModel.getValueAt(i, 3).toString());
				capacityField.setText(roomModel.getValueAt(i, 4).toString());
			}
		});
		add(btnUpdateRoom);
		add(comboBox);
		add(roomFormPanel);
		roomFormPanel.add(lblBuilding);
		roomFormPanel.add(lblRoomName);
		roomFormPanel.add(lblType);
		roomFormPanel.add(lblCapacity);
		roomFormPanel.add(buildingComboBox);
		roomFormPanel.add(roomField);
		roomFormPanel.add(typeComboBox);
		roomFormPanel.add(capacityField);
	}

	public void addRoomToTable(int roomID, String room, String building, String type, String capacity) {
		Object[] newRoom = new Object[roomTable.getRowCount()];
		newRoom[0] = roomID;
		newRoom[1] = room;
		newRoom[2] = building;
		newRoom[3] = type;
		newRoom[4] = capacity;
		roomModel.addRow(newRoom);
	}

	public void clearRoomFormFields() {
		buildingComboBox.setSelectedIndex(-1);
		roomField.setText("");
		typeComboBox.setSelectedIndex(-1);
		capacityField.setText("");
		btnAddRoom.setEnabled(true);
		btnUpdateRoom.setEnabled(false);
		btnDeleteRoom.setEnabled(false);
	}
}
