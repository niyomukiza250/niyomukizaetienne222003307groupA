package myuserIntraction;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import otherForms.AthletesForm;
import otherForms.CoachesForm;
import otherForms.CompetitionsForm;
import otherForms.Matches;
import otherForms.OfficialsForm;
import otherForms.TeamsForm;
import otherForms.TrainingFacility;

import javax.swing.JLabel;
import java.awt.Font;

public class MainMenuForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainMenuForm frame = new MainMenuForm();
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
    public MainMenuForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 636, 270);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblMainMenu = new JLabel("MainMenu");
        lblMainMenu.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblMainMenu.setBounds(239, 32, 120, 31);
        contentPane.add(lblMainMenu);

        JButton btnTeams = new JButton("Teams");
        btnTeams.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnTeams.setBounds(80, 100, 120, 28);
        contentPane.add(btnTeams);
        btnTeams.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TeamsForm teamsForm = new TeamsForm();
                teamsForm.setVisible(true);
                setVisible(false);
            }
        });

        JButton btnAthletes = new JButton("Athletes");
        btnAthletes.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnAthletes.setBounds(210, 100, 170, 28);
        contentPane.add(btnAthletes);
        btnAthletes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AthletesForm athletesForm = new AthletesForm();
                athletesForm.setVisible(true);
                setVisible(false);
            }
        });

        JButton btnCoaches = new JButton("Coaches");
        btnCoaches.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnCoaches.setBounds(390, 100, 170, 28);
        contentPane.add(btnCoaches);
        btnCoaches.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CoachesForm coachesForm = new CoachesForm();
                coachesForm.setVisible(true);
                setVisible(false);
            }
        });

        JButton btnOfficials = new JButton("Sports Officials");
        btnOfficials.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnOfficials.setBounds(22, 140, 170, 28);
        contentPane.add(btnOfficials);
        btnOfficials.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OfficialsForm officialsForm = new OfficialsForm();
                officialsForm.setVisible(true);
                setVisible(false);
            }
        });

        JButton btnTrainingFacility = new JButton("Training Facility");
        btnTrainingFacility.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnTrainingFacility.setBounds(202, 140, 190, 28);
        contentPane.add(btnTrainingFacility);
        btnTrainingFacility.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TrainingFacility trainingFacility = new TrainingFacility();
                trainingFacility.setVisible(true);
                setVisible(false);
            }
        });

        JButton btnCompetitions = new JButton("Competitions");
        btnCompetitions.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnCompetitions.setBounds(402, 140, 198, 28);
        contentPane.add(btnCompetitions);
        btnCompetitions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CompetitionsForm competitionsForm = new CompetitionsForm();
                competitionsForm.setVisible(true);
                setVisible(false);
            }
        });

        JButton btnMatches = new JButton("Matches");
        btnMatches.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        btnMatches.setBounds(212, 179, 168, 28);
        contentPane.add(btnMatches);
        btnMatches.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Matches matchesForm = new Matches();
                matchesForm.setVisible(true);
                setVisible(false);
            }
        });
    }

}
