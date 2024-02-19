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

public class TrainingFacility extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFacilityType;
    private JTextField textLocation;
    private JTextField textAvailableEquipment;
    private JTextField textAvailableTime;
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
                    TrainingFacility frame = new TrainingFacility();
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
    public TrainingFacility() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 513, 494);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTrainingFacilities = new JLabel("Training Facilities");
        lblTrainingFacilities.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblTrainingFacilities.setBounds(126, 37, 188, 34);
        contentPane.add(lblTrainingFacilities);
        
        JLabel lblNewLabel_1 = new JLabel("Facility Type");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(89, 106, 113, 27);
        contentPane.add(lblNewLabel_1);
        
        textFacilityType = new JTextField();
        textFacilityType.setBounds(235, 107, 198, 28);
        contentPane.add(textFacilityType);
        textFacilityType.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Location");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(126, 143, 76, 31);
        contentPane.add(lblNewLabel_2);
        
        textLocation = new JTextField();
        textLocation.setBounds(235, 146, 198, 28);
        contentPane.add(textLocation);
        textLocation.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Available Equipment");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_3.setBounds(21, 186, 181, 27);
        contentPane.add(lblNewLabel_3);
        
        textAvailableEquipment = new JTextField();
        textAvailableEquipment.setBounds(235, 187, 198, 28);
        contentPane.add(textAvailableEquipment);
        textAvailableEquipment.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("Available Time");
        lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel_4.setBounds(75, 224, 127, 28);
        contentPane.add(lblNewLabel_4);
        
        textAvailableTime = new JTextField();
        textAvailableTime.setBounds(235, 226, 198, 28);
        contentPane.add(textAvailableTime);
        textAvailableTime.setColumns(10);
        
        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnAdd.setBounds(21, 283, 89, 28);
        contentPane.add(btnAdd);
        
        JButton btnView = new JButton("View");
        btnView.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnView.setBounds(138, 283, 89, 28);
        contentPane.add(btnView);
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnUpdate.setBounds(252, 283, 100, 28);
        contentPane.add(btnUpdate);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnDelete.setBounds(378, 286, 90, 28);
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
                addFacility();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateFacility();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteFacility();
            }
        });

        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewFacilities();
            }
        });
    }

    private void addFacility() {
        String facilityType = textFacilityType.getText().trim();
        String location = textLocation.getText().trim();
        String availableEquipment = textAvailableEquipment.getText().trim();
        String availableTime = textAvailableTime.getText().trim();

        if (facilityType.isEmpty() || location.isEmpty() || availableEquipment.isEmpty() || availableTime.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO TrainingFacilities (Facility_Type, Location, Available_Equipment, Available_Time) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, facilityType);
                statement.setString(2, location);
                statement.setString(3, availableEquipment);
                statement.setString(4, availableTime);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Facility added successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add facility");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding facility");
        }
    }

    private void viewFacilities() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM TrainingFacilities";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet resultSet = statement.executeQuery()) {

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Facility_ID");
                model.addColumn("Facility_Type");
                model.addColumn("Location");
                model.addColumn("Available_Equipment");
                model.addColumn("Available_Time");

                while (resultSet.next()) {
                    int facilityID = resultSet.getInt("Facility_ID");
                    String facilityType = resultSet.getString("Facility_Type");
                    String location = resultSet.getString("Location");
                    String equipment = resultSet.getString("Available_Equipment");
                    String availableTime = resultSet.getString("Available_Time");

                    model.addRow(new Object[] { facilityID, facilityType, location, equipment, availableTime });
                }

                JTable dataTable = new JTable();
                JScrollPane scrollPane = new JScrollPane(dataTable);
                scrollPane.setBounds(50, 380, 700, 150);
                contentPane.add(scrollPane);

                dataTable.setModel(model);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error viewing facilities");
        }
    }

    private void updateFacility() {
        String facilityIDString = JOptionPane.showInputDialog("Enter Facility ID to update:");
        if (facilityIDString == null || facilityIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid Facility ID");
            return;
        }

        String facilityType = textFacilityType.getText().trim();
        String location = textLocation.getText().trim();
        String availableEquipment = textAvailableEquipment.getText().trim();
        String availableTime = textAvailableTime.getText().trim();

        if (facilityType.isEmpty() || location.isEmpty() || availableEquipment.isEmpty() || availableTime.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE TrainingFacilities SET Facility_Type=?, Location=?, Available_Equipment=?, Available_Time=? WHERE Facility_ID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, facilityType);
                statement.setString(2, location);
                statement.setString(3, availableEquipment);
                statement.setString(4, availableTime);
                statement.setInt(5, Integer.parseInt(facilityIDString));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Facility updated successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update facility. Facility ID not found.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating facility");
        }
    }
    private void deleteFacility() {
        String facilityIDString = JOptionPane.showInputDialog("Enter Facility ID to delete:");
        if (facilityIDString == null || facilityIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid Facility ID");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Check if there are any referencing records in other tables
            String checkSql = "SELECT * FROM teams WHERE Training_Facility_ID = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
                checkStatement.setInt(1, Integer.parseInt(facilityIDString));
                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "Cannot delete facility. It is referenced by records in other tables.");
                        return; 
                        }
                }
            }

         
            String deleteSql = "DELETE FROM TrainingFacilities WHERE Facility_ID=?";
            try (PreparedStatement statement = connection.prepareStatement(deleteSql)) {
                statement.setInt(1, Integer.parseInt(facilityIDString));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Facility deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete facility. Facility ID not found.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting facility");
        }
    }
}