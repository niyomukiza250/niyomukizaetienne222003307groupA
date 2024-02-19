package otherForms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import myuserIntraction.MainMenuForm;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CompetitionsForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textCompetitionTitle;
	private JTextField textDate;
	private JTextField textVenue;

	  // JDBC Database URL, User, and Password
    private static final String DB_URL = "jdbc:mysql://localhost/niyomukiza_etienne_222003307_urhuyesportsIS";
    private static final String DB_USER = "niyomukizaetienne222003307";
    private static final String DB_PASSWORD = "222003307";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompetitionsForm frame = new CompetitionsForm();
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
	public CompetitionsForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCompetitions = new JLabel("Competitions");
		lblCompetitions.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblCompetitions.setBounds(197, 38, 179, 29);
		contentPane.add(lblCompetitions);
		
		JLabel lblCompetitionTitle = new JLabel("Competition Title");
		lblCompetitionTitle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblCompetitionTitle.setBounds(84, 116, 144, 29);
		contentPane.add(lblCompetitionTitle);
		
		textCompetitionTitle = new JTextField();
		textCompetitionTitle.setBounds(240, 118, 190, 28);
		contentPane.add(textCompetitionTitle);
		textCompetitionTitle.setColumns(10);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDate.setBounds(185, 156, 43, 25);
		contentPane.add(lblDate);
		
		textDate = new JTextField();
		textDate.setBounds(240, 157, 190, 28);
		contentPane.add(textDate);
		textDate.setColumns(10);
		
		JLabel lblVenue = new JLabel("Venue");
		lblVenue.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblVenue.setBounds(170, 192, 58, 28);
		contentPane.add(lblVenue);
		
		textVenue = new JTextField();
		textVenue.setBounds(240, 196, 190, 28);
		contentPane.add(textVenue);
		textVenue.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnAdd.setBounds(46, 260, 89, 28);
		contentPane.add(btnAdd);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnView.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnView.setBounds(158, 260, 89, 28);
		contentPane.add(btnView);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnUpdate.setBounds(274, 260, 100, 28);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnDelete.setBounds(396, 260, 90, 28);
		contentPane.add(btnDelete);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnBack.setBounds(10, 11, 80, 20);
		contentPane.add(btnBack);

		btnBack.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        MainMenuForm mainMenu = new MainMenuForm();
		        mainMenu.setVisible(true);
		        dispose(); // Close the current frame
		    }
		});

		btnAdd.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        addCompetition();
		    }
		});

		btnUpdate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        updateCompetition();
		    }
		});

		btnDelete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        deleteCompetition();
		    }
		});

		btnView.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        viewCompetitions();
		    }
		});
	}

		
	private void addCompetition() {
	    String competitionTitle = textCompetitionTitle.getText().trim();
	    String date = textDate.getText().trim();
	    String venue = textVenue.getText().trim();
	    if (competitionTitle.isEmpty() || date.isEmpty() || venue.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "All fields are required");
	        return;
	    }

	    try {
	        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
	            String sql = "INSERT INTO Competitions (Competition_title, Date, Venue) VALUES (?, ?, ?)";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.setString(1, competitionTitle);
	                statement.setString(2, date);
	                statement.setString(3, venue);

	                int rowsAffected = statement.executeUpdate();

	                if (rowsAffected > 0) {
	                    JOptionPane.showMessageDialog(null, "Competition added successfully");
	                } else {
	                    JOptionPane.showMessageDialog(null, "Failed to add competition");
	                }
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error adding competition: " + ex.getMessage());
	    }
	}

	private void viewCompetitions() {
	    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
	        String sql = "SELECT * FROM Competitions";
	        try (PreparedStatement statement = connection.prepareStatement(sql);
	                ResultSet resultSet = statement.executeQuery()) {

	            DefaultTableModel model = new DefaultTableModel();
	            model.addColumn("Competition_ID");
	            model.addColumn("Competition_title");
	            model.addColumn("Date");
	            model.addColumn("Venue");

	            while (resultSet.next()) {
	                int competitionID = resultSet.getInt("Competition_ID");
	                String title = resultSet.getString("Competition_title");
	                String date = resultSet.getString("Date");
	                String venue = resultSet.getString("Venue");

	                model.addRow(new Object[] { competitionID, title, date, venue });
	            }

	            JTable dataTable = new JTable();
	            JScrollPane scrollPane = new JScrollPane(dataTable);
	            scrollPane.setBounds(550, 70, 700, 150);
	            contentPane.add(scrollPane);

	            dataTable.setModel(model);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error viewing competitions");
	    }
	}

private void updateCompetition() {
    String competitionIDString = JOptionPane.showInputDialog("Enter Competition ID to update:");
    if (competitionIDString == null || competitionIDString.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Invalid Competition ID");
        return;
    }

    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        String sql = "UPDATE Competitions SET Competition_title=?, Date=?, Venue=? WHERE Competition_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, textCompetitionTitle.getText());
            statement.setString(2, textDate.getText());
            statement.setString(3, textVenue.getText());
            statement.setInt(4, Integer.parseInt(competitionIDString));

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Competition updated successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update competition. Competition ID not found.");
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error updating competition");
    }
}

private void deleteCompetition() {
    String competitionIDString = JOptionPane.showInputDialog("Enter Competition ID to delete:");
    if (competitionIDString == null || competitionIDString.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Invalid Competition ID");
        return;
    }

    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        String sql = "DELETE FROM Competitions WHERE Competition_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, Integer.parseInt(competitionIDString));

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Competition deleted successfully");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete competition. Competition ID not found.");
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error deleting competition");
    }
}
}

