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

public class CoachesForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textNames;
    private JTextField textEmail;
    private JTextField textAddress;
    private JTextField textPhone;
    private JComboBox<String> comboBox; // Added this line

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
                    CoachesForm frame = new CoachesForm();
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
    public CoachesForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 511, 425);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblCoaches = new JLabel("Coaches");
        lblCoaches.setBounds(203, 34, 100, 28);
        lblCoaches.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        contentPane.add(lblCoaches);
        
        JLabel lblNames = new JLabel("Names");
        lblNames.setBounds(128, 87, 60, 28);
        lblNames.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        contentPane.add(lblNames);
        
        textNames = new JTextField();
        textNames.setBounds(203, 87, 203, 28);
        contentPane.add(textNames);
        textNames.setColumns(10);
        
        JLabel lblSpecialization = new JLabel("Specialization");
        lblSpecialization.setBounds(64, 132, 124, 24);
        lblSpecialization.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        contentPane.add(lblSpecialization);

        comboBox = new JComboBox<>(new String[] {"Football", "Rugby", "Athletics", "Tennis", "Basketball", "Martial Arts", "Cycling", "Cricket"}); // Changed this line
        comboBox.setBounds(203, 128, 203, 28);
        comboBox.setSelectedIndex(0);
        contentPane.add(comboBox);

        JLabel lblEmail = new JLabel("E-mail");
        lblEmail.setBounds(121, 167, 67, 25);
        lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        contentPane.add(lblEmail);
        
        textEmail = new JTextField();
        textEmail.setBounds(203, 166, 203, 28);
        contentPane.add(textEmail);
        textEmail.setColumns(10);
        
        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(112, 247, 76, 25);
        lblAddress.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        contentPane.add(lblAddress);
        
        textAddress = new JTextField();
        textAddress.setBounds(203, 247, 203, 28);
        contentPane.add(textAddress);
        textAddress.setColumns(10);
        
        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(52, 299, 89, 28);
        btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        contentPane.add(btnAdd);
        
        JButton btnView = new JButton("View");
        btnView.setBounds(155, 299, 89, 28);
        btnView.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        contentPane.add(btnView);
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(254, 299, 100, 28);
        btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        contentPane.add(btnUpdate);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(367, 299, 95, 28);
        btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        contentPane.add(btnDelete);
        
        
        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(131, 213, 57, 23);
        lblPhone.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        contentPane.add(lblPhone);
        
        textPhone = new JTextField();
        textPhone.setBounds(203, 205, 203, 28);
        contentPane.add(textPhone);
        textPhone.setColumns(10);
        
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(10, 11, 80, 20);
        btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 22));
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
                addCoach();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCoach();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteCoach();
            }
        });

        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewCoaches();
            }
        });
    }

    private void addCoach() {
        String name = textNames.getText().trim();
        String email = textEmail.getText().trim();
        String phone = textPhone.getText().trim();
        String address = textAddress.getText().trim();
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO Coaches (Name, Specialization, Email, Phone, Address) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, (String) comboBox.getSelectedItem());
                statement.setString(3, email);
                statement.setString(4, phone);
                statement.setString(5, address);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Coach added successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add coach");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding coach");
        }
    }

    private void viewCoaches() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM Coaches";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet resultSet = statement.executeQuery()) {

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Coach_ID");
                model.addColumn("Name");
                model.addColumn("Specialization");
                model.addColumn("Email");
                model.addColumn("Phone");
                model.addColumn("Address");

                while (resultSet.next()) {
                    int coachID = resultSet.getInt("Coach_ID");
                    String name = resultSet.getString("Name");
                    String specialization = resultSet.getString("Specialization");
                    String email = resultSet.getString("Email");
                    String phone = resultSet.getString("Phone");
                    String address = resultSet.getString("Address");

                    model.addRow(new Object[] { coachID, name, specialization, email, phone, address });
                }

                JTable dataTable = new JTable();
                JScrollPane scrollPane = new JScrollPane(dataTable);
                scrollPane.setBounds(50, 380, 700, 150);
                contentPane.add(scrollPane);

                dataTable.setModel(model);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error viewing coaches");
        }
    }

    private void updateCoach() {
        String coachIDString = JOptionPane.showInputDialog("Enter Coach ID to update:");
        if (coachIDString == null || coachIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid Coach ID");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE Coaches SET Name=?, Specialization=?, Email=?, Phone=?, Address=? WHERE Coach_ID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, textNames.getText());
                statement.setString(2, (String) comboBox.getSelectedItem());
                statement.setString(3, textEmail.getText());
                statement.setString(4, textPhone.getText());
                statement.setString(5, textAddress.getText());
                statement.setInt(6, Integer.parseInt(coachIDString));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Coach updated successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update coach. Coach ID not found.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating coach");
        }
    }

    private void deleteCoach() {
        String coachIDString = JOptionPane.showInputDialog("Enter Coach ID to delete:");
        if (coachIDString == null || coachIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid Coach ID");
            return;
        }

        int coachID = Integer.parseInt(coachIDString);

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // Check if the coach ID exists in other tables
            boolean coachExistsInOtherTables = checkCoachExistenceInOtherTables(connection, coachID);

            if (coachExistsInOtherTables) {
                JOptionPane.showMessageDialog(null, "Coach cannot be deleted because it is referenced in other tables.");
                return;
            }

            // Delete the coach if it doesn't exist in other tables
            String sql = "DELETE FROM Coaches WHERE Coach_ID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, coachID);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Coach deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete coach. Coach ID not found.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting coach");
        }
    }

    private boolean checkCoachExistenceInOtherTables(Connection connection, int coachID) throws SQLException {
        String[] tables = {"teams", "athletes"}; 
        for (String table : tables) {
            String sql = "SELECT COUNT(*) FROM " + table + " WHERE Coach_ID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, coachID);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) > 0) {
                        return true;
                    }
                }
            }
        }
        return false; 
    }
}