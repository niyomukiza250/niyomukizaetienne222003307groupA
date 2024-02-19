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

public class OfficialsForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textName;
    private JTextField textEmail;
    private JTextField textPhone;
    private JTextField textOfficialClassification;
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
                    OfficialsForm frame = new OfficialsForm();
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
    public OfficialsForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 593, 397);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSportsOfficials = new JLabel("Sports Officials/Referees");
        lblSportsOfficials.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblSportsOfficials.setBounds(131, 36, 255, 25);
        contentPane.add(lblSportsOfficials);

        JLabel lblNames = new JLabel("Names");
        lblNames.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNames.setBounds(103, 110, 62, 25);
        contentPane.add(lblNames);

        textName = new JTextField();
        textName.setBounds(198, 110, 237, 28);
        contentPane.add(textName);
        textName.setColumns(10);

        JLabel lblEmail = new JLabel("E-mail");
        lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblEmail.setBounds(103, 150, 62, 25);
        contentPane.add(lblEmail);

        textEmail = new JTextField();
        textEmail.setBounds(198, 150, 237, 28);
        contentPane.add(textEmail);
        textEmail.setColumns(10);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblPhone.setBounds(109, 190, 56, 25);
        contentPane.add(lblPhone);

        textPhone = new JTextField();
        textPhone.setBounds(198, 190, 237, 28);
        contentPane.add(textPhone);
        textPhone.setColumns(10);

        JLabel lblOfficialClassification = new JLabel("Official Classification");
        lblOfficialClassification.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblOfficialClassification.setBounds(47, 230, 186, 22);
        contentPane.add(lblOfficialClassification);

        textOfficialClassification = new JTextField();
        textOfficialClassification.setBounds(260, 230, 226, 28);
        contentPane.add(textOfficialClassification);
        textOfficialClassification.setColumns(10);

        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnAdd.setBounds(68, 300, 89, 28);
        contentPane.add(btnAdd);

        JButton btnView = new JButton("View");
        btnView.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnView.setBounds(184, 300, 89, 28);
        contentPane.add(btnView);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnUpdate.setBounds(301, 300, 100, 28);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnDelete.setBounds(423, 300, 90, 28);
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
                addOfficial();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateOfficial();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteOfficial();
            }
        });

        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewOfficials();
            }
        });
    }

    private void addOfficial() {
        String name = textName.getText().trim();
        String email = textEmail.getText().trim();
        String phone = textPhone.getText().trim();
        String classification = textOfficialClassification.getText().trim();

        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || classification.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO Officials (Name, Email, Phone, Certification) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, phone);
                statement.setString(4, classification);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Official added successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add official");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding official");
        }
    }

    private void viewOfficials() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM Officials";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                    ResultSet resultSet = statement.executeQuery()) {

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Referee_ID");
                model.addColumn("Name");
                model.addColumn("Email");
                model.addColumn("Phone");
                model.addColumn("Certification");

                while (resultSet.next()) {
                    int refereeID = resultSet.getInt("Referee_ID");
                    String name = resultSet.getString("Name");
                    String email = resultSet.getString("Email");
                    String phone = resultSet.getString("Phone");
                    String certification = resultSet.getString("Certification");

                    model.addRow(new Object[] { refereeID, name, email, phone, certification });
                }

                JTable dataTable = new JTable();
                JScrollPane scrollPane = new JScrollPane(dataTable);
                scrollPane.setBounds(550, 70, 700, 150);
                contentPane.add(scrollPane);

                dataTable.setModel(model);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error viewing officials");
        }
    }

    private void updateOfficial() {
        String officialIDString = JOptionPane.showInputDialog("Enter Official ID to update:");
        if (officialIDString == null || officialIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid Official ID");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE Officials SET Name=?, Email=?, Phone=?, Certification=? WHERE Referee_ID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, textName.getText());
                statement.setString(2, textEmail.getText());
                statement.setString(3, textPhone.getText());
                statement.setString(4, textOfficialClassification.getText());
                statement.setInt(5, Integer.parseInt(officialIDString));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Official updated successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update official. Official ID not found.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating official");
        }
    }

    private void deleteOfficial() {
        String officialIDString = JOptionPane.showInputDialog("Enter Official ID to delete:");
        if (officialIDString == null || officialIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid Official ID");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM Officials WHERE Referee_ID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(officialIDString));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Official deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete official. Official ID not found.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting official");
        }
    }
}
