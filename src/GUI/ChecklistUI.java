package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BackendObjects.Checklist;
import BackendObjects.Item;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class ChecklistUI extends JFrame {

	private JPanel contentPane;
	private Checklist list;
	private ChecklistUI thisReference = this;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the frame.
	 */
	public ChecklistUI(Checklist checklist) {
		this.list = checklist;
		initializeFrame();
		
	}
	
	public void initializeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Add Header
		JPanel headerPanel = new JPanel();
		contentPane.add(headerPanel, BorderLayout.NORTH);
		
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.save();
	            JFileChooser fileChooser = new JFileChooser();
	            int option = fileChooser.showOpenDialog(thisReference);
	            if(option == JFileChooser.APPROVE_OPTION){
	            	File file = fileChooser.getSelectedFile();
		            EventQueue.invokeLater(new Runnable() {
		    			public void run() {
		    				try {
		    		            
		    					ChecklistUI frame = new ChecklistUI(new Checklist(file.getAbsolutePath()));
		    					frame.setVisible(true);
		    					dispose();
		    				} catch (Exception e) {
		    					e.printStackTrace();
		    				}
		    			}
		    		});
	            }
			}
		});
		headerPanel.add(loadButton);
		
		JButton saveButton = new JButton("Save");
		headerPanel.add(saveButton);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.save();
			}
		});
		
		JButton addItemButton = new JButton("Add item");
		headerPanel.add(addItemButton);
		addItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AddPopUp frame = new AddPopUp(thisReference);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		JButton removeItemButton = new JButton("Remove item");
		headerPanel.add(removeItemButton);
		
		JButton resetButton = new JButton("Reset Time");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ResetTimePopUp frame = new ResetTimePopUp(thisReference);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		headerPanel.add(resetButton);
		
		removeItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeMode();
			}
		});
		
		//List items
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane(listPanel);
		contentPane.add(scrollPane, BorderLayout.CENTER);
///////////////////Debug View
//		JPanel itemPanelx = new JPanel();
//		listPanel.add(itemPanelx);
//		itemPanelx.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
//		
//		JButton btnNewButton = new JButton("X");
//		itemPanelx.add(btnNewButton);
//		btnNewButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
//		
//		JCheckBox chckbxNewCheckBoxx = new JCheckBox();
//		itemPanelx.add(chckbxNewCheckBoxx);
//		
//		JTextPane textPane = new JTextPane();
//		itemPanelx.add(textPane);
////////////////////	
		for(Item item:list.getItems()) {
			JPanel itemPanel = new JPanel();
			listPanel.add(itemPanel);
			itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			JCheckBox chckbxNewCheckBox = new JCheckBox();
			chckbxNewCheckBox.setSelected(item.getCheck());
			itemPanel.add(chckbxNewCheckBox);
			chckbxNewCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					item.toggleCheck();
				}
			});
			
			JTextPane textArea = new JTextPane();
			textArea.setText(item.getDescription());
			itemPanel.add(textArea);
		}
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	private void removeMode() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Add Header
		JPanel headerPanel = new JPanel();
		contentPane.add(headerPanel, BorderLayout.NORTH);
		
		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.save();
	            JFileChooser fileChooser = new JFileChooser();
	            int option = fileChooser.showOpenDialog(thisReference);
	            if(option == JFileChooser.APPROVE_OPTION){
	            	File file = fileChooser.getSelectedFile();
		            EventQueue.invokeLater(new Runnable() {
		    			public void run() {
		    				try {
		    					ChecklistUI frame = new ChecklistUI(new Checklist(file.getAbsolutePath()));
		    					frame.setVisible(true);
		    					dispose();
		    				} catch (Exception e) {
		    					e.printStackTrace();
		    				}
		    			}
		    		});
	            }
			}
		});
		headerPanel.add(loadButton);
		
		JButton saveButton = new JButton("Save");
		headerPanel.add(saveButton);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.save();
			}
		});
		
		JButton addItemButton = new JButton("Add item");
		headerPanel.add(addItemButton);
		addItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initializeFrame();
				//(Proceed as normal)
				//Pop up new window
			}
		});
		
		JButton doneItemButton = new JButton("Done");
		headerPanel.add(doneItemButton);
		doneItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initializeFrame();
			}
		});

		//List items
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane(listPanel);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		ArrayList<Item> items = list.getItems();
		for(int i=0;i<items.size();i++) {
			Item item = items.get(i);
			
			JPanel itemPanel = new JPanel();
			listPanel.add(itemPanel);
			itemPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
			
			int index = i;
			JButton btnRemButton = new JButton("X");
			itemPanel.add(btnRemButton);
			btnRemButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					list.removeItem(index);
					removeMode();
				}
			});
			
			JCheckBox chckbxNewCheckBox = new JCheckBox();
			chckbxNewCheckBox.setSelected(item.getCheck());
			itemPanel.add(chckbxNewCheckBox);
			chckbxNewCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					item.toggleCheck();
				}
			});
			
			JTextPane textArea = new JTextPane();
			textArea.setText(item.getDescription());
			itemPanel.add(textArea);
			

			
		}
		
		contentPane.revalidate();
		contentPane.repaint();
		
	}
	
	public void addItem(String desc) {
		list.addItem(desc);
		initializeFrame();
	}
	
	public String getResetTime() {
		return list.getResetTime();
	}

	public void setResetTime(String time) {
		list.setResetTime(time);
		
	}
}
