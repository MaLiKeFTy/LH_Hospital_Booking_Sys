package com.company.Users;

import com.company.Users.Base.User;
import com.company.Users.UserInfo.UserGender;
import com.company.Users.UserInfo.UserName;

import java.util.ArrayList;
import java.util.List;

public class Practitioner extends User {

    List<Client> _clients = new ArrayList<>();

    public Practitioner(UserName name, String emailAddress, String password, int age, UserGender userGender) {
        super(name, emailAddress, password, age, userGender);
    }

    public Practitioner() {
    }

    public void AssignToClient(Client client) {
        if (!_clients.contains(client))
            _clients.add(client);
    }

    public void UnAssignFromClient(Client client) {
        _clients.remove(client);
    }

}
