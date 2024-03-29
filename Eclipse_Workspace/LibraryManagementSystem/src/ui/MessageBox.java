package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class MessageBox extends JFrame {

	/**
	 * Launch the application.
	 */
	static void MessageS(String text) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MessageBox frame = new MessageBox(text);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private MessageBox(String text) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(750, 300, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Style.mBlue);
		
		JLabel lblNewLabel = new JLabel("Message");
		lblNewLabel.setText(text);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(12, 13, 408, 200);
		lblNewLabel.setForeground(Style.lBlue);
		contentPane.add(lblNewLabel);
		
		JButton btnDismiss = new JButton("Dismiss");
		btnDismiss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnDismiss.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnDismiss.setBounds(308, 210, 112, 30);
		btnDismiss.setBackground(Style.dBlue);
		btnDismiss.setForeground(Style.lBlue);
		contentPane.add(btnDismiss);
	}
}
