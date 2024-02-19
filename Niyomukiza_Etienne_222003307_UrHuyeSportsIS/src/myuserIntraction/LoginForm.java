package myuserIntraction;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import home.UsersForm;

public class LoginForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textEmail;
    private JPasswordField passwordField;
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
                    LoginForm frame = new LoginForm();
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
    public LoginForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 518, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblLogin = new JLabel("Login");
        lblLogin.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblLogin.setBounds(200, 51, 96, 33);
        contentPane.add(lblLogin);
        
        JLabel lblEmail = new JLabel("E-mail");
        lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblEmail.setBounds(131, 110, 59, 33);
        contentPane.add(lblEmail);
        
        textEmail = new JTextField();
        textEmail.setBounds(200, 114, 199, 28);
        contentPane.add(textEmail);
        textEmail.setColumns(10);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblPassword.setBounds(106, 149, 84, 33);
        contentPane.add(lblPassword);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(200, 153, 199, 28);
        contentPane.add(passwordField);
        
        JButton btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnExit.setBounds(326, 196, 89, 28);
        contentPane.add(btnExit);
        
        JButton btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnReset.setBounds(207, 193, 89, 28);
        contentPane.add(btnReset);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnLogin.setBounds(89, 193, 89, 28);
        contentPane.add(btnLogin);
        
        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnBack.setBounds(10, 11, 80, 20);
        contentPane.add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UsersForm userInteraction = new UsersForm();
                userInteraction.setVisible(true);
                dispose();
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String enteredEmail = textEmail.getText();
                String enteredPassword = new String(passwordField.getPassword());

                if (isValidLogin(enteredEmail, enteredPassword)) {

                    JOptionPane.showMessageDialog(contentPane, "Login successful!", "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    MainMenuForm mainMenu = new MainMenuForm();
                    mainMenu.setVisible(true);

                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Invalid email or password. Please try again.",
                            "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

     
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textEmail.setText("");
                passwordField.setText("");
                JOptionPane.showMessageDialog(contentPane, "Form reset successful!", "Reset Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(contentPane, "Are you sure you want to exit?", "Exit",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
              
            }
        });
    }

    public static boolean isValidLogin(String enteredEmail, String enteredPassword) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sql = "SELECT * FROM Users WHERE Email = ? AND Password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enteredEmail);
            preparedStatement.setString(2, enteredPassword);
         
            resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {           
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
