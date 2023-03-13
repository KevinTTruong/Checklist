package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ResetTimePopUp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public ResetTimePopUp(ChecklistUI checklist) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 125);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JLabel lblNewLabel = new JLabel("Reset Time ("+checklist.getResetTime()+")");
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Set time (00:00:00-23:59:59): ");
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Set");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LocalTime.parse(textField.getText());
					checklist.setResetTime(textField.getText());
					dispose();
				}catch(DateTimeParseException pe) {
					lblNewLabel.setText(lblNewLabel.getText()+" INVLAID FORMAT");
					contentPane.revalidate();
					contentPane.repaint();
				}
			}
		});
		panel.add(btnNewButton);
	}

}
