package com.company.GUI.ProfilesGUI.UserProfiles;

import com.company.DataBaseConnector;
import com.company.GUI.Alerts.AlertMessage;
import com.company.GUI.ProfilesGUI.UserProfiles.Base.UserProfileGUI;
import com.company.Users.Base.User;
import com.company.Users.Client;
import com.company.Users.Practitioner;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientProfileGUI extends UserProfileGUI {

    JPanel practitionersTitlePanel = new JPanel();
    JLabel practitionersTitle = new JLabel("My Practitioners:");

    JList<String> practitionersListContainer = new JList<>();
    JScrollPane practitionersListScroll = new JScrollPane(practitionersListContainer);


    JPanel treatmentsTitlePanel = new JPanel();
    JLabel treatmentCourseTitle = new JLabel(" My TreatmentCourses:");


    JPanel practitionersListPanel = new JPanel();
    JPanel treatmentsListPanel = new JPanel();


    JList treatmentCourseListContainer = new JList();
    JScrollPane treatmentCourseListScroll = new JScrollPane(treatmentCourseListContainer);


    JPanel practitionersButtonPanel = new JPanel();
    JButton addPractitionerButton = new JButton("Add Practitioners");
    JButton addPractitioner1Button = new JButton("Add Practitioner");
    JButton goBackPractitionerButton = new JButton("Go Back");

    JPanel treatmentsButtonPanel = new JPanel();
    JLabel addTreatmentCourseButton = new JLabel();

    Client _client = (Client) get_user();

    Practitioner _selectedPractitioner;

    Practitioner[] _clientPractitioners;

    @Override
    public void DisplayGUI() {
        super.DisplayGUI();


        practitionersTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        practitionersTitlePanel.setBounds(100, 260, 295, 100);
        practitionersTitlePanel.setLayout(new GridLayout());
        practitionersTitlePanel.setBackground(Color.BLUE);
        GetMainFrame().add(practitionersTitlePanel);

        practitionersTitle.setFont(new Font("Serif", Font.BOLD, 25));
        practitionersTitle.setForeground(Color.WHITE);
        practitionersTitlePanel.add(practitionersTitle);

        practitionersListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        practitionersListPanel.setBounds(100, 360, 295, 200);
        practitionersListPanel.setLayout(new GridLayout());
        practitionersListPanel.setBackground(Color.BLUE);
        GetMainFrame().add(practitionersListPanel);
        practitionersListPanel.add(practitionersListScroll);

        practitionersButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        practitionersButtonPanel.setBounds(100, 560, 295, 70);
        practitionersButtonPanel.setLayout(new GridLayout());
        practitionersButtonPanel.setBackground(Color.BLUE);
        GetMainFrame().add(practitionersButtonPanel);

        try {
            DisplayMyPractitioners();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        treatmentsTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        treatmentsTitlePanel.setBounds(405, 260, 295, 100);
        treatmentsTitlePanel.setLayout(new GridLayout());
        treatmentsTitlePanel.setBackground(Color.BLUE);
        GetMainFrame().add(treatmentsTitlePanel);

        treatmentCourseTitle.setFont(new Font("Serif", Font.BOLD, 25));
        treatmentCourseTitle.setForeground(Color.WHITE);
        treatmentsTitlePanel.add(treatmentCourseTitle);

        treatmentsListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        treatmentsListPanel.setBounds(405, 360, 295, 200);
        treatmentsListPanel.setLayout(new GridLayout());
        treatmentsListPanel.setBackground(Color.BLUE);
        GetMainFrame().add(treatmentsListPanel);
        treatmentsListPanel.add(treatmentCourseListScroll);

        treatmentsButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        treatmentsButtonPanel.setBounds(405, 560, 295, 70);
        treatmentsButtonPanel.setLayout(new GridLayout());
        treatmentsButtonPanel.setBackground(Color.BLUE);
        GetMainFrame().add(treatmentsButtonPanel);

        addTreatmentCourseButton.setText("<html>" + "Please contact your practitioner to edit or add treatment courses." + "</html>");
        addTreatmentCourseButton.setFont(new Font("Serif", Font.BOLD, 20));
        addTreatmentCourseButton.setForeground(Color.WHITE);
        treatmentsButtonPanel.add(addTreatmentCourseButton);

        GetMainFrame();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);

        if (e.getSource() == addPractitionerButton) {


            try {
                DisplayAvailablePractitioners();


            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == goBackPractitionerButton) {
            try {
                DisplayMyPractitioners();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


        if (e.getSource() == addPractitioner1Button) {
            _client = (Client) get_user();

            DataBaseConnector dataBaseConnector = new DataBaseConnector();
            try {
                dataBaseConnector.AddPractitionerToClient(_selectedPractitioner, _client);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            AlertMessage alertMessage = new AlertMessage();
            alertMessage.DisplayAlert(new String[]{"ok"}, "You have successfully added a practitioner.");
        }

    }


    void DisplayMyPractitioners() throws SQLException {
        practitionersTitle.setText("My Practitioners:");

        _client = (Client) get_user();

        DataBaseConnector dataBaseConnector = new DataBaseConnector();

        Practitioner[] practitioners = dataBaseConnector.GetClientPractitioners(_client);

        _clientPractitioners = practitioners;

        practitionersListContainer.setListData(PractitionerNames(practitioners));

        addPractitionerButton.addActionListener(this);
        addPractitionerButton.setFont(new Font("Serif", Font.BOLD, 20));
        addPractitionerButton.setBackground(Color.WHITE);
        addPractitionerButton.setForeground(Color.BLUE);
        practitionersButtonPanel.add(addPractitionerButton);

        practitionersButtonPanel.remove(goBackPractitionerButton);
        practitionersButtonPanel.remove(addPractitioner1Button);
    }

    void DisplayAvailablePractitioners() throws SQLException {

        practitionersTitle.setText("Available Practitioners:");

        DataBaseConnector dataBaseConnector = new DataBaseConnector();

        User[] practitioners = dataBaseConnector.GetAllUsers("Practitioner");


        List<Practitioner> tempPractitioners = new ArrayList<>();


        if (_clientPractitioners.length >= 1) {
            for (User practitionerDB : practitioners) {
                for (Practitioner thisPractitioner : _clientPractitioners) {
                    if (thisPractitioner.get_id() != practitionerDB.get_id()) {
                        tempPractitioners.add((Practitioner) practitionerDB);
                    }
                }
            }
        } else {
            for (User thisPractitioner : practitioners) {
                tempPractitioners.add((Practitioner) thisPractitioner);
            }
        }


        practitionersListContainer.setListData(PractitionerNames(tempPractitioners.toArray(new Practitioner[0])));

        goBackPractitionerButton.addActionListener(this);
        goBackPractitionerButton.setFont(new Font("Serif", Font.BOLD, 13));
        goBackPractitionerButton.setBackground(Color.WHITE);
        goBackPractitionerButton.setForeground(Color.BLUE);

        addPractitioner1Button.addActionListener(this);
        addPractitioner1Button.setFont(new Font("Serif", Font.BOLD, 13));
        addPractitioner1Button.setBackground(Color.WHITE);
        addPractitioner1Button.setForeground(Color.BLUE);

        practitionersButtonPanel.add(goBackPractitionerButton);

        practitionersListContainer.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                GetSelectedAvailablePractitioner(practitioners);
                practitionersButtonPanel.remove(goBackPractitionerButton);
                practitionersButtonPanel.add(addPractitioner1Button);
                practitionersButtonPanel.add(goBackPractitionerButton);
                GetMainFrame();
            }
        });


        practitionersButtonPanel.remove(addPractitionerButton);
    }


    void GetSelectedAvailablePractitioner(User[] practitioners) {
        for (User practitioner : practitioners) {
            if(practitionersListContainer.getSelectedValue() != null){
                if ((practitioner.get_name().get_firstName() + " " + practitioner.get_name().get_lastName()).equals(practitionersListContainer.getSelectedValue().toString())) {
                    _selectedPractitioner = (Practitioner) practitioner;
                    return;
                }
            }

        }
    }


    String[] PractitionerNames(Practitioner[] practitioners) {
        List<String> practitionerNames = new ArrayList<>();
        for (Practitioner practitioner : practitioners)
            practitionerNames.add(practitioner.get_name().get_firstName() + " " + practitioner.get_name().get_lastName());
        return practitionerNames.toArray(new String[0]);
    }
}
