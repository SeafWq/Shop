package com.example.shop.user;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 5, max = 25)
    private String nameAcc;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 5, max = 128)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;
    private Boolean locked = false;
    private Boolean enabled = true;


    public User(String email, String nameAcc, String password, Role role) {
        this.email = email;
        this.nameAcc = nameAcc;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }



    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}