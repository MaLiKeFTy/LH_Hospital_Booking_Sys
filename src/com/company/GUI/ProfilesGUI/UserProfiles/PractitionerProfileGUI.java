package com.company.GUI.ProfilesGUI.UserProfiles;

import com.company.DataBaseConnector;
import com.company.GUI.Alerts.AlertMessage;
import com.company.GUI.ProfilesGUI.UserProfiles.Base.UserProfileGUI;
import com.company.TreatmentCourse;
import com.company.Users.Client;
import com.company.Users.Practitioner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PractitionerProfileGUI extends UserProfileGUI {

    JPanel clientsTitlePanel = new JPanel();
    JLabel clientTitle = new JLabel("My Clients:");

    JList<String> clientsListContainer = new JList<>();
    JScrollPane clientsListScroll = new JScrollPane(clientsListContainer);


    JPanel treatmentsTitlePanel = new JPanel();
    JLabel treatmentCourseTitle = new JLabel("My TreatmentCourses:");


    JPanel clientsListPanel = new JPanel();
    JPanel treatmentsListPanel = new JPanel();


    JList<String> treatmentCourseListContainer = new JList<String>();
    JScrollPane treatmentCourseListScroll = new JScrollPane(treatmentCourseListContainer);


    JButton confirmAssignToTreatmentButton = new JButton("Assign");
    JButton goBackTreatmentButton = new JButton("Go Back");

    JPanel treatmentsButtonPanel = new JPanel();
    JButton addTreatmentCourseButton = new JButton("Add Treatment");

    Practitioner _practitioner = (Practitioner) get_user();


    JButton confirmAddTreatmentCourseButton = new JButton("Add Treatment");


    JLabel treatmentNameLabel = new JLabel("Treatment Name");
    JTextField treatmentNameField = new JTextField();

    JLabel treatmentExtraServicesLabel = new JLabel("Extra Services");


    JLabel clientTreatmentAssignLabel = new JLabel("Client To Assign");
    JComboBox<String> clientTreatmentAssignBox = new JComboBox<>();

    //Client _selectedClient;

    Client[] _practitionersClient;

    DataBaseConnector dataBaseConnector = new DataBaseConnector();
    AlertMessage alertMessage = new AlertMessage();

    @Override
    public void DisplayGUI() {
        super.DisplayGUI();


        _practitioner = (Practitioner) get_user();

        DisplayClientPanels();
        DisplayTreatmentPanels();


        AddButtonsActionListeners();

        clientTitle.setFont(new Font("Serif", Font.BOLD, 25));
        clientTitle.setForeground(Color.WHITE);
        clientsTitlePanel.add(clientTitle);

        treatmentCourseTitle.setFont(new Font("Serif", Font.BOLD, 25));
        treatmentCourseTitle.setForeground(Color.WHITE);
        treatmentsTitlePanel.add(treatmentCourseTitle);


        clientsListPanel.add(clientsListScroll);


        try {
            DisplayMyClients();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        DisplayMyTreatments();


        GetMainFrame();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);

        if (e.getSource() == addTreatmentCourseButton) {
            DisplayAddTreatmentForm();
        }

        if (e.getSource() == goBackTreatmentButton) {
            DisplayMyTreatments();
        }

        if (e.getSource() == confirmAddTreatmentCourseButton) {


            if (treatmentNameField.getText().equals("")) {
                alertMessage.DisplayAlert(new String[]{"ok"}, "Please enter a treatment name.");
            } else {
                TreatmentCourse treatmentCourse = new TreatmentCourse(treatmentNameField.getText());
                try {
                    AddTreatmentToDB(treatmentCourse, SelectedClient(clientTreatmentAssignBox.getSelectedItem().toString()));
                    alertMessage.DisplayAlert(new String[]{"ok"}, "Treatment has been added successfully.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

    }


    void DisplayMyClients() throws SQLException {
        clientTitle.setText("My Clients:");

        _practitioner = (Practitioner) get_user();


        Client[] clients = dataBaseConnector.GetPractitionerClients(_practitioner);

        _practitionersClient = clients;

        clientTreatmentAssignBox = new JComboBox<>(ClientNames(clients));

        clientsListContainer.setListData(ClientNames(clients));

        GetMainFrame();
    }


    void DisplayTreatmentPanels() {
        treatmentsTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        treatmentsTitlePanel.setBounds(405, 260, 295, 100);
        treatmentsTitlePanel.setLayout(new GridLayout());
        treatmentsTitlePanel.setBackground(Color.BLUE);

        treatmentsListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        treatmentsListPanel.setBounds(405, 360, 295, 200);
        treatmentsListPanel.setLayout(new GridLayout());
        treatmentsListPanel.setBackground(Color.BLUE);

        treatmentsButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        treatmentsButtonPanel.setBounds(405, 560, 295, 70);
        treatmentsButtonPanel.setLayout(new GridLayout(1, 2, 0, 0));
        treatmentsButtonPanel.setBackground(Color.BLUE);

        GetMainFrame().add(treatmentsTitlePanel);
        GetMainFrame().add(treatmentsListPanel);
        GetMainFrame().add(treatmentsButtonPanel);
    }

    void DisplayClientPanels() {
        clientsTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        clientsTitlePanel.setBounds(100, 260, 295, 100);
        clientsTitlePanel.setLayout(new GridLayout());
        clientsTitlePanel.setBackground(Color.BLUE);


        clientsListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        clientsListPanel.setBounds(100, 360, 295, 200);
        clientsListPanel.setLayout(new GridLayout());
        clientsListPanel.setBackground(Color.BLUE);

        GetMainFrame().add(clientsTitlePanel);
        GetMainFrame().add(clientsListPanel);
    }

    void DisplayAddTreatmentForm() {

        treatmentsButtonPanel.removeAll();
        treatmentsListPanel.removeAll();

        treatmentCourseTitle.setText("Add treatment:");

        DisplayTreatmentForm();

        treatmentsButtonPanel.add(confirmAddTreatmentCourseButton);
        treatmentsButtonPanel.add(goBackTreatmentButton);

        GetMainFrame();

    }

    void DisplayTreatmentForm() {
        treatmentNameLabel.setForeground(Color.WHITE);
        treatmentsListPanel.add(treatmentNameLabel);

        treatmentsListPanel.add(treatmentNameField);

        clientTreatmentAssignLabel.setForeground(Color.WHITE);
        treatmentsListPanel.add(clientTreatmentAssignLabel);

        treatmentsListPanel.add(clientTreatmentAssignBox);

        treatmentsListPanel.setBorder(BorderFactory.createEmptyBorder(70, 10, 70, 10));
        treatmentsListPanel.setLayout(new GridLayout(2, 2, 10, 10));

    }


    void DisplayMyTreatments() {
        try {
            GetTreatmentNames();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        treatmentsButtonPanel.removeAll();
        treatmentsListPanel.removeAll();

        treatmentCourseTitle.setText("My TreatmentCourses:");

        treatmentsListPanel.add(treatmentCourseListScroll);
        treatmentsButtonPanel.add(addTreatmentCourseButton);

        treatmentsListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        treatmentsListPanel.setLayout(new GridLayout());
        GetMainFrame();
    }


    void AddButtonsActionListeners() {
        addTreatmentCourseButton.addActionListener(this);
        goBackTreatmentButton.addActionListener(this);
        confirmAddTreatmentCourseButton.addActionListener(this);

        addTreatmentCourseButton.setFont(new Font("Serif", Font.BOLD, 20));
        addTreatmentCourseButton.setForeground(Color.BLUE);
        addTreatmentCourseButton.setBackground(Color.WHITE);

        confirmAddTreatmentCourseButton.setFont(new Font("Serif", Font.BOLD, 15));
        confirmAddTreatmentCourseButton.setForeground(Color.BLUE);
        confirmAddTreatmentCourseButton.setBackground(Color.WHITE);

        goBackTreatmentButton.setFont(new Font("Serif", Font.BOLD, 15));
        goBackTreatmentButton.setForeground(Color.BLUE);
        goBackTreatmentButton.setBackground(Color.WHITE);

    }


    void GetTreatmentNames() throws SQLException {
        treatmentCourseListContainer.setListData(dataBaseConnector.PractitionerTreatmentNames(_practitioner));
    }


    void AddTreatmentToDB(TreatmentCourse treatmentCourse, Client client) throws SQLException {

        dataBaseConnector.AssignClientToTreatment(treatmentCourse, client, _practitioner);
    }

    Client SelectedClient(String clientName) {
        for (Client client : _practitionersClient) {
            if ((client.get_name().get_firstName() + " " + client.get_name().get_lastName()).equals(clientName)) {
                return client;
            }
        }
        return null;
    }


    String[] ClientNames(Client[] clients) {
        List<String> practitionerNames = new ArrayList<>();
        for (Client client : clients)
            practitionerNames.add(client.get_name().get_firstName() + " " + client.get_name().get_lastName());
        return practitionerNames.toArray(new String[0]);
    }
}
