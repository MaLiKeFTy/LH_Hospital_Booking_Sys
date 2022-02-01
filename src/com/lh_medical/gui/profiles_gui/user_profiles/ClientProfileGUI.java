package com.lh_medical.gui.profiles_gui.user_profiles;

import com.lh_medical.db.DataBaseManager;
import com.lh_medical.gui.alerts.AlertMessage;
import com.lh_medical.gui.profiles_gui.base.ProfileGUI;
import com.lh_medical.users.Client;
import com.lh_medical.users.Practitioner;
import com.lh_medical.users.base.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientProfileGUI extends ProfileGUI {

    JPanel practitionersTitlePanel = new JPanel();
    JLabel practitionersTitle = new JLabel("My Practitioners:");

    JList<String> practitionersListContainer = new JList<>();
    JScrollPane practitionersListScroll = new JScrollPane(practitionersListContainer);


    JPanel treatmentsTitlePanel = new JPanel();
    JLabel treatmentCourseTitle = new JLabel(" My TreatmentCourses:");


    JPanel practitionersListPanel = new JPanel();
    JPanel treatmentsListPanel = new JPanel();


    JList<String> treatmentCourseListContainer = new JList<>();
    JScrollPane treatmentCourseListScroll = new JScrollPane(treatmentCourseListContainer);


    JPanel practitionersButtonPanel = new JPanel();
    JButton addPractitionerButton = new JButton("Add Practitioners");
    JButton addPractitioner1Button = new JButton("Add Practitioner");
    JButton goBackPractitionerButton = new JButton("Go Back");

    JPanel treatmentsButtonPanel = new JPanel();
    JLabel addTreatmentCourseButton = new JLabel();

    Client client;


    Practitioner selectedPractitioner;

    Practitioner[] clientPractitioners;

    DataBaseManager dataBaseManager = new DataBaseManager();

    @Override
    public void displayGUI() {
        super.displayGUI();

        client = (Client) getUser();

        practitionersTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        practitionersTitlePanel.setBounds(100, 260, 295, 100);
        practitionersTitlePanel.setLayout(new GridLayout());
        practitionersTitlePanel.setBackground(Color.BLUE);
        getMainFrame().add(practitionersTitlePanel);

        practitionersTitle.setFont(new Font("Serif", Font.BOLD, 25));
        practitionersTitle.setForeground(Color.WHITE);
        practitionersTitlePanel.add(practitionersTitle);

        practitionersListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        practitionersListPanel.setBounds(100, 360, 295, 200);
        practitionersListPanel.setLayout(new GridLayout());
        practitionersListPanel.setBackground(Color.BLUE);
        getMainFrame().add(practitionersListPanel);
        practitionersListPanel.add(practitionersListScroll);

        practitionersButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        practitionersButtonPanel.setBounds(100, 560, 295, 70);
        practitionersButtonPanel.setLayout(new GridLayout());
        practitionersButtonPanel.setBackground(Color.BLUE);
        getMainFrame().add(practitionersButtonPanel);

        try {
            displayMyPractitioners();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        treatmentsTitlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        treatmentsTitlePanel.setBounds(405, 260, 295, 100);
        treatmentsTitlePanel.setLayout(new GridLayout());
        treatmentsTitlePanel.setBackground(Color.BLUE);
        getMainFrame().add(treatmentsTitlePanel);

        treatmentCourseTitle.setFont(new Font("Serif", Font.BOLD, 25));
        treatmentCourseTitle.setForeground(Color.WHITE);
        treatmentsTitlePanel.add(treatmentCourseTitle);

        treatmentsListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        treatmentsListPanel.setBounds(405, 360, 295, 200);
        treatmentsListPanel.setLayout(new GridLayout());
        treatmentsListPanel.setBackground(Color.BLUE);
        getMainFrame().add(treatmentsListPanel);
        treatmentsListPanel.add(treatmentCourseListScroll);

        treatmentsButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        treatmentsButtonPanel.setBounds(405, 560, 295, 70);
        treatmentsButtonPanel.setLayout(new GridLayout());
        treatmentsButtonPanel.setBackground(Color.BLUE);
        getMainFrame().add(treatmentsButtonPanel);

        addTreatmentCourseButton.setText("<html>" + "Please contact your practitioner to edit or add treatment courses." + "</html>");
        addTreatmentCourseButton.setFont(new Font("Serif", Font.BOLD, 20));
        addTreatmentCourseButton.setForeground(Color.WHITE);
        treatmentsButtonPanel.add(addTreatmentCourseButton);

        getMainFrame();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);

        if (e.getSource() == addPractitionerButton) {


            try {
                displayAvailablePractitioners();


            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == goBackPractitionerButton) {
            try {
                displayMyPractitioners();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


        if (e.getSource() == addPractitioner1Button) {
            client = (Client) getUser();

            DataBaseManager dataBaseManager = new DataBaseManager();
            try {
                dataBaseManager.addPractitionerToClient(selectedPractitioner, client);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            AlertMessage alertMessage = new AlertMessage();
            alertMessage.displayAlert(new String[]{"ok"}, "You have successfully added a practitioner.");
        }

    }


    void displayMyTreatments() throws SQLException {

        System.out.println(dataBaseManager.clientTreatmentNames(client).length);
        treatmentCourseListContainer.setListData(dataBaseManager.clientTreatmentNames(client));
    }


    void displayMyPractitioners() throws SQLException {
        practitionersTitle.setText("My Practitioners:");

        client = (Client) getUser();

        Practitioner[] practitioners = dataBaseManager.getClientPractitioners(client);

        clientPractitioners = practitioners;

        practitionersListContainer.setListData(practitionerNames(practitioners));

        addPractitionerButton.addActionListener(this);
        addPractitionerButton.setFont(new Font("Serif", Font.BOLD, 20));
        addPractitionerButton.setBackground(Color.WHITE);
        addPractitionerButton.setForeground(Color.BLUE);
        practitionersButtonPanel.add(addPractitionerButton);

        practitionersButtonPanel.remove(goBackPractitionerButton);
        practitionersButtonPanel.remove(addPractitioner1Button);

        displayMyTreatments();
    }

    void displayAvailablePractitioners() throws SQLException {

        practitionersTitle.setText("Available Practitioners:");

        User[] practitioners = dataBaseManager.getAllUsers("Practitioner");

        List<Practitioner> tempPractitioners = new ArrayList<>();

        if (clientPractitioners.length >= 1) {
            for (User practitionerDB : practitioners) {
                for (Practitioner thisPractitioner : clientPractitioners) {
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


        practitionersListContainer.setListData(practitionerNames(tempPractitioners.toArray(new Practitioner[0])));

        goBackPractitionerButton.addActionListener(this);
        goBackPractitionerButton.setFont(new Font("Serif", Font.BOLD, 13));
        goBackPractitionerButton.setBackground(Color.WHITE);
        goBackPractitionerButton.setForeground(Color.BLUE);

        addPractitioner1Button.addActionListener(this);
        addPractitioner1Button.setFont(new Font("Serif", Font.BOLD, 13));
        addPractitioner1Button.setBackground(Color.WHITE);
        addPractitioner1Button.setForeground(Color.BLUE);

        practitionersButtonPanel.add(goBackPractitionerButton);

        practitionersListContainer.addListSelectionListener(e -> {

            getSelectedAvailablePractitioner(practitioners);
            practitionersButtonPanel.remove(goBackPractitionerButton);
            practitionersButtonPanel.add(addPractitioner1Button);
            practitionersButtonPanel.add(goBackPractitionerButton);
            getMainFrame();
        });


        practitionersButtonPanel.remove(addPractitionerButton);
    }


    void getSelectedAvailablePractitioner(User[] practitioners) {
        for (User practitioner : practitioners) {
            if (practitionersListContainer.getSelectedValue() != null) {
                if ((practitioner.get_name().getFirstName() + " " + practitioner.get_name().getLastName()).equals(practitionersListContainer.getSelectedValue())) {
                    selectedPractitioner = (Practitioner) practitioner;
                    return;
                }
            }

        }
    }


    String[] practitionerNames(Practitioner[] practitioners) {
        List<String> practitionerNames = new ArrayList<>();
        for (Practitioner practitioner : practitioners)
            practitionerNames.add(practitioner.get_name().getFirstName() + " " + practitioner.get_name().getLastName());
        return practitionerNames.toArray(new String[0]);
    }
}
