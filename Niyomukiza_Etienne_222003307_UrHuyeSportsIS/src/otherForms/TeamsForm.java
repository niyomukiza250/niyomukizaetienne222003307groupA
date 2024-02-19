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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamsForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textTeamName;
	private JTextField textCoachID;
	private JTextField textTrainingFacilityID;
	
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
					TeamsForm frame = new TeamsForm();
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
	public TeamsForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTeams = new JLabel("Teams");
		lblTeams.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblTeams.setBounds(221, 43, 74, 28);
		contentPane.add(lblTeams);
		
		JLabel lblTeamName = new JLabel("Team Name");
		lblTeamName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTeamName.setBounds(128, 112, 109, 21);
		contentPane.add(lblTeamName);
		
		textTeamName = new JTextField();
		textTeamName.setBounds(269, 105, 202, 28);
		contentPane.add(textTeamName);
		textTeamName.setColumns(10);
		
		JLabel lblCoachID = new JLabel("Coach ID");
		lblCoachID.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblCoachID.setBounds(149, 147, 88, 25);
		contentPane.add(lblCoachID);
		
		textCoachID = new JTextField();
		textCoachID.setBounds(269, 144, 202, 28);
		contentPane.add(textCoachID);
		textCoachID.setColumns(10);
		
		JLabel lblTrainingFacilityID = new JLabel("Training Facility ID");
		lblTrainingFacilityID.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTrainingFacilityID.setBounds(64, 183, 173, 25);
		contentPane.add(lblTrainingFacilityID);
		
		textTrainingFacilityID = new JTextField();
		textTrainingFacilityID.setBounds(269, 180, 202, 28);
		contentPane.add(textTrainingFacilityID);
		textTrainingFacilityID.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnAdd.setBounds(41, 237, 89, 28);
		contentPane.add(btnAdd);
		
		JButton btnView = new JButton("View");
		btnView.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnView.setBounds(162, 237, 89, 28);
		contentPane.add(btnView);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnUpdate.setBounds(283, 237, 100, 28);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnDelete.setBounds(410, 237, 90, 28);
		contentPane.add(btnDelete);
		
		JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnBack.setBounds(10, 11, 80, 20);
        contentPane.add(btnBack);
        

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenuForm mainMenu = new MainMenuForm();
                mainMenu.setVisible(true);
                setVisible(false);
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTeam();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTeam();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteTeam();
            }
        });

        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewTeams();
            }
        });
    }

	private void addTeam() {
	    String teamName = textTeamName.getText().trim();
	    String coachIDText = textCoachID.getText().trim();
	    String trainingFacilityIDText = textTrainingFacilityID.getText().trim();
	    if (teamName.isEmpty() || coachIDText.isEmpty() || trainingFacilityIDText.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "All fields are required");
	        return;
	    }
	    
	    try {
	        int coachID = Integer.parseInt(coachIDText);
	        int trainingFacilityID = Integer.parseInt(trainingFacilityIDText);

	        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
	            String sql = "INSERT INTO Teams (Team_Name, Coach_ID, Training_Facility_ID) VALUES (?, ?, ?)";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.setString(1, teamName);
	                statement.setInt(2, coachID);
	                statement.setInt(3, trainingFacilityID);

	                int rowsAffected = statement.executeUpdate();

	                if (rowsAffected > 0) {
	                    JOptionPane.showMessageDialog(null, "Team added successfully");
	                } else {
	                    JOptionPane.showMessageDialog(null, "Failed to add team");
	                }
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error adding team");
	        }
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(null, "Coach ID and Training Facility ID must be integers");
	    }
	}

    private void viewTeams() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM Teams";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet resultSet = statement.executeQuery()) {

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Team_ID");
                model.addColumn("Team_Name");
                model.addColumn("Coach_ID");
                model.addColumn("Training_Facility_ID");

                while (resultSet.next()) {
                    int teamID = resultSet.getInt("Team_ID");
                    String teamName = resultSet.getString("Team_Name");
                    int coachID = resultSet.getInt("Coach_ID");
                    int facilityID = resultSet.getInt("Training_Facility_ID");

                    model.addRow(new Object[] { teamID, teamName, coachID, facilityID });
                }

                JTable dataTable = new JTable();
                JScrollPane scrollPane = new JScrollPane(dataTable);
                scrollPane.setBounds(50, 380, 700, 150);
                contentPane.add(scrollPane);

                dataTable.setModel(model);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error viewing teams");
        }
    }
    private void updateTeam() {
        String teamIDString = JOptionPane.showInputDialog("Enter Team ID to update:");
        if (teamIDString == null || teamIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid Team ID");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE Teams SET Team_Name=?, Coach_ID=?, Training_Facility_ID=? WHERE Team_ID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, textTeamName.getText());
                statement.setInt(2, Integer.parseInt(textCoachID.getText())); 
                statement.setInt(3, Integer.parseInt(textTrainingFacilityID.getText())); 
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Team updated successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update team. Team ID not found.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating team");
        }
    }

    private void deleteTeam() {
        String teamIDString = JOptionPane.showInputDialog("Enter Team ID to delete:");
        if (teamIDString == null || teamIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid Team ID");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM Teams WHERE Team_ID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(teamIDString));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Team deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete team. Team ID not found.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting team");
        }
    }
}
