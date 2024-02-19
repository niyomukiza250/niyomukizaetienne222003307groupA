package otherForms;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import myuserIntraction.MainMenuForm;

public class AthletesForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textNames;
    private JTextField textAge;
    private JTextField textNationality;
    private JTextField textEmail;
    private JTextField textPhone;
    private JTextField textResidence;
    private JTextField textTeamID;
    private JComboBox<String> comboBox;

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
                    AthletesForm frame = new AthletesForm();
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
    public AthletesForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 481, 504);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblAthletes = new JLabel("Athletes");
        lblAthletes.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblAthletes.setBounds(190, 32, 122, 28);
        contentPane.add(lblAthletes);

        JLabel lblNames = new JLabel("Names");
        lblNames.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNames.setBounds(116, 83, 64, 28);
        contentPane.add(lblNames);

        textNames = new JTextField();
        textNames.setBounds(190, 85, 187, 28);
        contentPane.add(textNames);
        textNames.setColumns(10);

        JLabel lblAge = new JLabel("Age");
        lblAge.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblAge.setBounds(126, 122, 38, 21);
        contentPane.add(lblAge);

        textAge = new JTextField();
        textAge.setBounds(190, 120, 187, 28);
        contentPane.add(textAge);
        textAge.setColumns(10);

        JLabel lblNationality = new JLabel("Nationality");
        lblNationality.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNationality.setBounds(84, 199, 96, 21);
        contentPane.add(lblNationality);

        textNationality = new JTextField();
        textNationality.setBounds(190, 197, 187, 28);
        contentPane.add(textNationality);
        textNationality.setColumns(10);

        comboBox = new JComboBox<>(new String[] {"Male", "Female"});
        comboBox.setSelectedIndex(0);
        comboBox.setBounds(190, 158, 96, 28);
        contentPane.add(comboBox);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblGender.setBounds(116, 159, 64, 23);
        contentPane.add(lblGender);

        JLabel lblEmail = new JLabel("E-mail");
        lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblEmail.setBounds(116, 238, 64, 21);
        contentPane.add(lblEmail);

        textEmail = new JTextField();
        textEmail.setBounds(190, 236, 187, 28);
        contentPane.add(textEmail);
        textEmail.setColumns(10);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblPhone.setBounds(123, 277, 57, 21);
        contentPane.add(lblPhone);

        textPhone = new JTextField();
        textPhone.setBounds(190, 275, 187, 28);
        contentPane.add(textPhone);
        textPhone.setColumns(10);

        JLabel lblResidence = new JLabel("Residence");
        lblResidence.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblResidence.setBounds(91, 316, 89, 21);
        contentPane.add(lblResidence);

        textResidence = new JTextField();
        textResidence.setBounds(190, 314, 187, 28);
        contentPane.add(textResidence);
        textResidence.setColumns(10);

        JLabel lblTeamID = new JLabel("Team ID");
        lblTeamID.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblTeamID.setBounds(103, 355, 77, 21);
        contentPane.add(lblTeamID);

        textTeamID = new JTextField();
        textTeamID.setBounds(190, 353, 187, 28);
        contentPane.add(textTeamID);
        textTeamID.setColumns(10);

        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnAdd.setBounds(10, 419, 89, 28);
        contentPane.add(btnAdd);

        JButton btnView = new JButton("View");
        btnView.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnView.setBounds(121, 419, 89, 28);
        contentPane.add(btnView);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnUpdate.setBounds(235, 419, 100, 28);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnDelete.setBounds(355, 419, 95, 28);
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
                addAthlete();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateAthlete();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteAthlete();
            }
        });

        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewAthletes();
            }
        });
    }

    private void addAthlete() {
        String name = textNames.getText().trim();
        String age = textAge.getText().trim();
        String gender = comboBox.getSelectedItem().toString();
        String nationality = textNationality.getText().trim();
        String email = textEmail.getText().trim();
        String phone = textPhone.getText().trim();
        String residence = textResidence.getText().trim();
        String teamIDString = textTeamID.getText().trim();
        if (name.isEmpty() || age.isEmpty() || nationality.isEmpty() || email.isEmpty() || phone.isEmpty() || residence.isEmpty() || teamIDString.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required");
            return;
        }

        try {
            int teamID = Integer.parseInt(teamIDString);

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String sql = "INSERT INTO Athletes (Name, Age, Gender, Nationality, Email, Phone, Residence, Team_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, name);
                    statement.setString(2, age);
                    statement.setString(3, gender);
                    statement.setString(4, nationality);
                    statement.setString(5, email);
                    statement.setString(6, phone);
                    statement.setString(7, residence);
                    statement.setInt(8, teamID);

                    int rowsAffected = statement.executeUpdate();

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Athlete added successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to add athlete");
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error adding athlete: " + ex.getMessage());
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input for Team ID");
        }
    }


    private void viewAthletes() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM Athletes";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet resultSet = statement.executeQuery()) {

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Athlete_ID");
                model.addColumn("Name");
                model.addColumn("Age");
                model.addColumn("Gender");
                model.addColumn("Nationality");
                model.addColumn("Email");
                model.addColumn("Phone");
                model.addColumn("Residence");
                model.addColumn("Team_ID");

                while (resultSet.next()) {
                    int athleteID = resultSet.getInt("Athlete_ID");
                    String name = resultSet.getString("Name");
                    String age = resultSet.getString("Age");
                    String gender = resultSet.getString("Gender");
                    String nationality = resultSet.getString("Nationality");
                    String email = resultSet.getString("Email");
                    String phone = resultSet.getString("Phone");
                    String residence = resultSet.getString("Residence");
                    int teamID = resultSet.getInt("Team_ID");

                    model.addRow(new Object[] { athleteID, name, age, gender, nationality, email, phone, residence, teamID });
                }

                JTable dataTable = new JTable();
                JScrollPane scrollPane = new JScrollPane(dataTable);
                scrollPane.setBounds(550, 70, 700, 150);
                contentPane.add(scrollPane);

                dataTable.setModel(model);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error viewing athletes: " + ex.getMessage());
        }
    }

    private void updateAthlete() {
        String athleteIDString = JOptionPane.showInputDialog("Enter Athlete ID to update:");
        if (athleteIDString == null || athleteIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid Athlete ID");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE Athletes SET Name=?, Age=?, Gender=?, Nationality=?, Email=?, Phone=?, Residence=?, Team_ID=? WHERE Athlete_ID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, textNames.getText());
                statement.setString(2, textAge.getText());
                statement.setString(3, comboBox.getSelectedItem().toString());
                statement.setString(4, textNationality.getText());
                statement.setString(5, textEmail.getText());
                statement.setString(6, textPhone.getText());
                statement.setString(7, textResidence.getText());
                statement.setInt(8, Integer.parseInt(textTeamID.getText()));
                statement.setInt(9, Integer.parseInt(athleteIDString));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Athlete updated successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update athlete. Athlete ID not found.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error updating athlete: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input for Athlete ID or Team ID");
        }
    }

    private void deleteAthlete() {
        String athleteIDString = JOptionPane.showInputDialog("Enter Athlete ID to delete:");
        if (athleteIDString == null || athleteIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid Athlete ID");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM Athletes WHERE Athlete_ID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(athleteIDString));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Athlete deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete athlete. Athlete ID not found.");
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error deleting athlete: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid input for Athlete ID");
        }
    }
}
