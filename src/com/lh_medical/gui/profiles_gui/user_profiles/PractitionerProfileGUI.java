package com.lh_medical.gui.profiles_gui.user_profiles;

import com.lh_medical.db.DataBaseManager;
import com.lh_medical.gui.alerts.AlertMessage;
import com.lh_medical.gui.profiles_gui.user_profiles.base.UserProfileGUI;
import com.lh_medical.treatment_course.TreatmentCourse;
import com.lh_medical.users.Client;
import com.lh_medical.users.Practitioner;

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

    JButton goBackTreatmentButton = new JButton("Go Back");

    JPanel treatmentsButtonPanel = new JPanel();
    JButton addTreatmentCourseButton = new JButton("Add Treatment");

    Practitioner _practitioner = (Practitioner) getUser();


    JButton confirmAddTreatmentCourseButton = new JButton("Add Treatment");


    JLabel treatmentNameLabel = new JLabel("Treatment Name");
    JTextField treatmentNameField = new JTextField();

    JLabel clientTreatmentAssignLabel = new JLabel("Client To Assign");
    JComboBox<String> clientTreatmentAssignBox = new JComboBox<>();

    Client[] practitionersClient;

    DataBaseManager dataBaseManager = new DataBaseManager();
    AlertMessage alertMessage = new AlertMessage();

    @Override
    public void displayGUI() {
        super.displayGUI();

        _practitioner = (Practitioner) getUser();

        displayClientPanels();
        displayTreatmentPanels();


        addButtonsActionListeners();

        clientTitle.setFont(new Font("Serif", Font.BOLD, 25));
        clientTitle.setForeground(Color.WHITE);
        clientsTitlePanel.add(clientTitle);

        treatmentCourseTitle.setFont(new Font("Serif", Font.BOLD, 25));
        treatmentCourseTitle.setForeground(Color.WHITE);
        treatmentsTitlePanel.add(treatmentCourseTitle);


        clientsListPanel.add(clientsListScroll);


        try {
            displayMyClients();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        displayMyTreatments();


        getMainFrame();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);

        if (e.getSource() == addTreatmentCourseButton) {
            displayAddTreatmentForm();
        }

        if (e.getSource() == goBackTreatmentButton) {
            displayMyTreatments();
        }

        if (e.getSource() == confirmAddTreatmentCourseButton) {


            if (treatmentNameField.getText().equals("")) {
                alertMessage.displayAlert(new String[]{"ok"}, "Please enter a treatment name.");
            } else {
                TreatmentCourse treatmentCourse = new TreatmentCourse(treatmentNameField.getText());
                try {
                    addTreatmentToDB(treatmentCourse, selectedClient(clientTreatmentAssignBox.getSelectedItem().toString()));
                    alertMessage.displayAlert(new String[]{"ok"}, "Treatment has been added successfully.");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }

    }


    void displayMyClients() throws SQLException {
        clientTitle.setText("My Clients:");

        _practitioner = (Practitioner) getUser();


        Client[] clients = dataBaseManager.getPractitionerClients(_practitioner);

        practitionersClient = clients;

        clientTreatmentAssignBox = new JComboBox<>(clientNames(clients));

        clientsListContainer.setListData(clientNames(clients));

        getMainFrame();
    }


    void displayTreatmentPanels() {
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

        getMainFrame().add(treatmentsTitlePanel);
        getMainFrame().add(treatmentsListPanel);
        getMainFrame().add(treatmentsButtonPanel);
    }

    void displayClientPanels() {
        clientsTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        clientsTitlePanel.setBounds(100, 260, 295, 100);
        clientsTitlePanel.setLayout(new GridLayout());
        clientsTitlePanel.setBackground(Color.BLUE);


        clientsListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        clientsListPanel.setBounds(100, 360, 295, 200);
        clientsListPanel.setLayout(new GridLayout());
        clientsListPanel.setBackground(Color.BLUE);

        getMainFrame().add(clientsTitlePanel);
        getMainFrame().add(clientsListPanel);
    }

    void displayAddTreatmentForm() {

        treatmentsButtonPanel.removeAll();
        treatmentsListPanel.removeAll();

        treatmentCourseTitle.setText("Add treatment:");

        displayTreatmentForm();

        treatmentsButtonPanel.add(confirmAddTreatmentCourseButton);
        treatmentsButtonPanel.add(goBackTreatmentButton);

        getMainFrame();

    }

    void displayTreatmentForm() {
        treatmentNameLabel.setForeground(Color.WHITE);
        treatmentsListPanel.add(treatmentNameLabel);

        treatmentsListPanel.add(treatmentNameField);

        clientTreatmentAssignLabel.setForeground(Color.WHITE);
        treatmentsListPanel.add(clientTreatmentAssignLabel);

        treatmentsListPanel.add(clientTreatmentAssignBox);

        treatmentsListPanel.setBorder(BorderFactory.createEmptyBorder(70, 10, 70, 10));
        treatmentsListPanel.setLayout(new GridLayout(2, 2, 10, 10));

    }


    void displayMyTreatments() {
        try {
            getTreatmentNames();
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
        getMainFrame();
    }


    void addButtonsActionListeners() {
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


    void getTreatmentNames() throws SQLException {
        treatmentCourseListContainer.setListData(dataBaseManager.practitionerTreatmentNames(_practitioner));
    }


    void addTreatmentToDB(TreatmentCourse treatmentCourse, Client client) throws SQLException {

        dataBaseManager.assignClientToTreatment(treatmentCourse, client, _practitioner);
    }

    Client selectedClient(String clientName) {
        for (Client client : practitionersClient) {
            if ((client.get_name().getFirstName() + " " + client.get_name().getLastName()).equals(clientName)) {
                return client;
            }
        }
        return null;
    }


    String[] clientNames(Client[] clients) {
        List<String> practitionerNames = new ArrayList<>();
        for (Client client : clients)
            practitionerNames.add(client.get_name().getFirstName() + " " + client.get_name().getLastName());
        return practitionerNames.toArray(new String[0]);
    }
}
