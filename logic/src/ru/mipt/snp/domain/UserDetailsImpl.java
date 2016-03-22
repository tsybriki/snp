package ru.mipt.snp.domain;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>UserDetails implementation for using within spring security</p>
 *
 * Created by Kirill Tsibriy on 01.03.2009
 */
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = -3314221339130326917L;

    private User user;
    private final Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public GrantedAuthority[] getAuthorities() {
        return authorities.toArray(new GrantedAuthority[authorities.size()]);
    }

    public String getPassword() {
        return user.getPassword();
    }

    public String getUsername() {
        return user.getLogin();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }

    public void addAuthority(String authority) {
        authorities.add(new GrantedAuthorityImpl(authority));
    }
}
