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
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Matches extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textCompetitionID;
	private JTextField textTeam1ID;
	private JTextField textTeam2ID;
	private JTextField textMatchDateAndTime;
	private JTextField textVenue;
	private JTextField textRefereeID;
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
					Matches frame = new Matches();
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
	public Matches() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMatches = new JLabel("Matches");
		lblMatches.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblMatches.setBounds(182, 40, 155, 23);
		contentPane.add(lblMatches);
		
		JLabel lblCompetitionID = new JLabel("Competition ID");
		lblCompetitionID.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblCompetitionID.setBounds(60, 105, 133, 25);
		contentPane.add(lblCompetitionID);
		
		textCompetitionID = new JTextField();
		textCompetitionID.setBounds(233, 102, 197, 28);
		contentPane.add(textCompetitionID);
		textCompetitionID.setColumns(10);
		
		JLabel lblTeam1ID = new JLabel("Team1 ID");
		lblTeam1ID.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTeam1ID.setBounds(104, 140, 89, 27);
		contentPane.add(lblTeam1ID);
		
		textTeam1ID = new JTextField();
		textTeam1ID.setBounds(233, 141, 197, 28);
		contentPane.add(textTeam1ID);
		textTeam1ID.setColumns(10);
		
		JLabel lblTeam2ID = new JLabel("Team2 ID");
		lblTeam2ID.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTeam2ID.setBounds(104, 178, 89, 29);
		contentPane.add(lblTeam2ID);
		
		textTeam2ID = new JTextField();
		textTeam2ID.setBounds(233, 180, 197, 28);
		contentPane.add(textTeam2ID);
		textTeam2ID.setColumns(10);
		
		JLabel lblMatchDateAndTime = new JLabel("Match Date and Time");
		lblMatchDateAndTime.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMatchDateAndTime.setBounds(12, 218, 181, 28);
		contentPane.add(lblMatchDateAndTime);
		
		textMatchDateAndTime = new JTextField();
		textMatchDateAndTime.setBounds(233, 219, 197, 28);
		contentPane.add(textMatchDateAndTime);
		textMatchDateAndTime.setColumns(10);
		
		JLabel lblVenue = new JLabel("Venue");
		lblVenue.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblVenue.setBounds(134, 257, 59, 27);
		contentPane.add(lblVenue);
		
		textVenue = new JTextField();
		textVenue.setBounds(233, 257, 197, 28);
		contentPane.add(textVenue);
		textVenue.setColumns(10);
		
		JLabel lblRefereeID = new JLabel("Referee ID");
		lblRefereeID.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblRefereeID.setBounds(93, 299, 100, 21);
		contentPane.add(lblRefereeID);
		
		textRefereeID = new JTextField();
		textRefereeID.setBounds(233, 297, 197, 28);
		contentPane.add(textRefereeID);
		textRefereeID.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnAdd.setBounds(15, 362, 89, 28);
		contentPane.add(btnAdd);
		
		JButton btnView = new JButton("View");
		btnView.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnView.setBounds(132, 362, 89, 28);
		contentPane.add(btnView);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnUpdate.setBounds(245, 362, 100, 28);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnDelete.setBounds(373, 362, 90, 28);
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
	                addMatch();
	            }
	        });

	        btnUpdate.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                updateMatch();
	            }
	        });

	        btnDelete.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                deleteMatch();
	            }
	        });

	        btnView.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                viewMatches();
	            }
	        });
	    }

	private void addMatch() {
	    String competitionIDText = textCompetitionID.getText().trim();
	    String team1IDText = textTeam1ID.getText().trim();
	    String team2IDText = textTeam2ID.getText().trim();
	    String matchDateTime = textMatchDateAndTime.getText().trim();
	    String venue = textVenue.getText().trim();
	    String refereeIDText = textRefereeID.getText().trim();
	    if (competitionIDText.isEmpty() || team1IDText.isEmpty() || team2IDText.isEmpty() ||
	            matchDateTime.isEmpty() || venue.isEmpty() || refereeIDText.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "All fields are required");
	        return;
	    }

	    try {
	        int competitionID = Integer.parseInt(competitionIDText);
	        int team1ID = Integer.parseInt(team1IDText);
	        int team2ID = Integer.parseInt(team2IDText);
	        int refereeID = Integer.parseInt(refereeIDText);

	        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
	            String sql = "INSERT INTO Matches (Competition_ID, Team1_ID, Team2_ID, Match_Date, Venue, Referee_ID) VALUES (?, ?, ?, ?, ?, ?)";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.setInt(1, competitionID);
	                statement.setInt(2, team1ID);
	                statement.setInt(3, team2ID);
	                statement.setString(4, matchDateTime);
	                statement.setString(5, venue);
	                statement.setInt(6, refereeID);

	                int rowsAffected = statement.executeUpdate();

	                if (rowsAffected > 0) {
	                    JOptionPane.showMessageDialog(null, "Match added successfully");
	                } else {
	                    JOptionPane.showMessageDialog(null, "Failed to add match");
	                }
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error adding match");
	        }
	    } catch (NumberFormatException ex) {
	        JOptionPane.showMessageDialog(null, "Competition ID, Team1 ID, Team2 ID, and Referee ID must be integers");
	    }
	}

	    private void viewMatches() {
	        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
	            String sql = "SELECT * FROM Matches";
	            try (PreparedStatement statement = connection.prepareStatement(sql);
	                    ResultSet resultSet = statement.executeQuery()) {

	                DefaultTableModel model = new DefaultTableModel();
	                model.addColumn("Match_ID");
	                model.addColumn("Competition_ID");
	                model.addColumn("Team1_ID");
	                model.addColumn("Team2_ID");
	                model.addColumn("Match_Date");
	                model.addColumn("Venue");
	                model.addColumn("Referee_ID");

	                while (resultSet.next()) {
	                    int matchID = resultSet.getInt("Match_ID");
	                    int competitionID = resultSet.getInt("Competition_ID");
	                    int team1ID = resultSet.getInt("Team1_ID");
	                    int team2ID = resultSet.getInt("Team2_ID");
	                    String matchDate = resultSet.getString("Match_Date");
	                    String venue = resultSet.getString("Venue");
	                    int refereeID = resultSet.getInt("Referee_ID");

	                    model.addRow(new Object[] { matchID, competitionID, team1ID, team2ID, matchDate, venue, refereeID });
	                }

	                JTable dataTable = new JTable();
	                JScrollPane scrollPane = new JScrollPane(dataTable);
	                scrollPane.setBounds(500, 70, 700, 150);
	                contentPane.add(scrollPane);

	                dataTable.setModel(model);
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error viewing matches");
	        }
	    }

	    private void updateMatch() {
	        String matchIDString = JOptionPane.showInputDialog("Enter Match ID to update:");
	        if (matchIDString == null || matchIDString.trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Invalid Match ID");
	            return;
	        }

	        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
	            String sql = "UPDATE Matches SET Competition_ID=?, Team1_ID=?, Team2_ID=?, Match_Date=?, Venue=?, Referee_ID=? WHERE Match_ID=?";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.setInt(1, Integer.parseInt(textCompetitionID.getText()));
	                statement.setInt(2, Integer.parseInt(textTeam1ID.getText()));
	                statement.setInt(3, Integer.parseInt(textTeam2ID.getText()));
	                statement.setString(4,textMatchDateAndTime.getText());
	                statement.setString(5, textVenue.getText());
	                statement.setInt(6, Integer.parseInt(textRefereeID.getText()));
	                statement.setInt(7, Integer.parseInt(matchIDString));

	                int rowsAffected = statement.executeUpdate();

	                if (rowsAffected > 0) {
	                    JOptionPane.showMessageDialog(null, "Match updated successfully");
	                } else {
	                    JOptionPane.showMessageDialog(null, "Failed to update match. Match ID not found.");
	                }
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error updating match");
	        }
	    }

	    private void deleteMatch() {
	        String matchIDString = JOptionPane.showInputDialog("Enter Match ID to delete:");
	        if (matchIDString == null || matchIDString.trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Invalid Match ID");
	            return;
	        }

	        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
	            String sql = "DELETE FROM Matches WHERE Match_ID=?";
	            try (PreparedStatement statement = connection.prepareStatement(sql)) {
	                statement.setInt(1, Integer.parseInt(matchIDString));

	                int rowsAffected = statement.executeUpdate();

	                if (rowsAffected > 0) {
	                    JOptionPane.showMessageDialog(null, "Match deleted successfully");
	                } else {
	                    JOptionPane.showMessageDialog(null, "Failed to delete match. Match ID not found.");
	                }
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error deleting match");
	        }
	    }
	}