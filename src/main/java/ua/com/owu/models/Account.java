package ua.com.owu.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="accountType",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Account implements UserDetails {
//    private String accountType G S
//    private int id G S
//    private Role role = Role.ROLE_USER; G S
//    private String password G S
//    private String username G S
//    private String email G S
//    private String firstName G S
//    private boolean isAccountNonExpired= true G S
//    private boolean isAccountNonLocked= true G S
//    private boolean isCredentialsNonExpired =true G S
//    private String token = null G S
//    private boolean isEnabled = true G S


    public Account() {
    }

    @Column(name = "accountType",updatable = false,insertable = false)
    private String accountType;
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    @JsonIgnore
    private String password;
    @Override
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Column(unique = true, nullable = false)
    private String username;
    @Override
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

//    @Column(unique = true)
    private String email;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    private String firstName;
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    private String lastName;
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    private boolean isAccountNonExpired= true;
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }
    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }


    private boolean isAccountNonLocked= true;
    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }
    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }



    private boolean isCredentialsNonExpired =true;
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }


//for account activation
    private String token = null;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    //UNCOMMENT when token activation is used

    public Account(Role role, String password, String username, String email) {
        this.role = role;
        this.password = password;
        this.username = username;
        this.email = email;
    }
//    private boolean isEnabled = false;
    //UNCOMMET when token activation is NOT used

    private boolean isEnabled = true;
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                isAccountNonExpired == account.isAccountNonExpired &&
                isAccountNonLocked == account.isAccountNonLocked &&
                isCredentialsNonExpired == account.isCredentialsNonExpired &&
                isEnabled == account.isEnabled &&
                Objects.equals(accountType, account.accountType) &&
                role == account.role &&
                Objects.equals(password, account.password) &&
                Objects.equals(username, account.username) &&
                Objects.equals(email, account.email) &&
                Objects.equals(firstName, account.firstName) &&
                Objects.equals(lastName, account.lastName) &&
                Objects.equals(token, account.token);
    }



    @Override
    public int hashCode() {
        return Objects.hash(accountType, id, role, password, username, email, firstName, lastName, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, token, isEnabled);
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", role=" + role +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isAccountNonExpired=" + isAccountNonExpired +
                ", isAccountNonLocked=" + isAccountNonLocked +
                ", isCredentialsNonExpired=" + isCredentialsNonExpired +
                ", isEnabled=" + isEnabled +
                '}';
    }
}
