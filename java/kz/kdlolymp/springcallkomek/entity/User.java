package kz.kdlolymp.springcallkomek.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Component
@Table(name="users")
public class User implements UserDetails, Serializable {
    private static final String ROLE_PREFIX = "ROLE_";

    @Id
    @Column(columnDefinition = "serial", name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login")
    private String username;
    @Column(name = "hashcode")
    private String password;
    @Transient
    private String passwordConfirm;
    @Column(name = "role")
    private String role;
    @Column(name = "is_enabled")
    private boolean isEnabled;
    @Column(name = "user_surname")
    private String userSurname;
    @Column(name = "user_name")
    private String userFirstname;
    @Column(name = "user_position")
    private String position;
    @Column(name = "email")
    private String email;
    @Column(name = "editor")
    private boolean editor;
    @Column(name = "is_temporary")
    private boolean isTemporary;

    public User(){}

//    public User(Long id, String username, String role, String userSurname, String userFirstname,
//                int departmentId, List<UserRights> userRightsList, int branchId) {
//        this.id = id;
//        this.username = username;
//        this.role = role;
//        this.userSurname = userSurname;
//        this.userFirstname = userFirstname;
//        this.departmentId = departmentId;
//        this.userRightsList = userRightsList;
//        this.branchId = branchId;
//    }

//    public void addUserRights(UserRights userRights){
//        this.userRightsList.add(userRights);
//    }

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public void setUsername(String username) {this.username = username;}

    public void setPassword(String password) {this.password = password;}

    public String getPasswordConfirm() {return passwordConfirm;}

    public String getRole() {return role;}

    public void setRole(String role) {this.role = role;}

    public void setEnabled(boolean enabled) {isEnabled = enabled;}

    public String getUserSurname() {return userSurname;}

    public void setUserSurname(String userSurname) {this.userSurname = userSurname;}

    public String getUserFirstname() {return userFirstname;}

    public void setUserFirstname(String userFirstname) {this.userFirstname = userFirstname;}

    public String getPosition() {return position;}

    public void setPosition(String position) {this.position = position;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public boolean isEditor() {return editor;}

    public void setEditor(boolean editor) {this.editor = editor;}

    public boolean isTemporary() {return isTemporary;}

    public void setTemporary(boolean temporary) {isTemporary = temporary;}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
        return list;
    }

    @Override
    public String getPassword() {return password;}

    @Override
    public String getUsername() {return username;}

    @Override
    public boolean isEnabled() {return isEnabled;}

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

}
