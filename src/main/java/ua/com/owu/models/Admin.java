package ua.com.owu.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Admin extends Account {

    public Admin() {
    }

    public Admin(Role role, String password, String username, String email) {
        super(role, password, username, email);
    }


}


