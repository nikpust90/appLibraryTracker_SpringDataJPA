package applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.security;

import applibrarytracker_springdatajpa.applibrarytracker_springdatajpa.Model.PersonSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Collections;

public class PersonDetails implements UserDetails {

    private final PersonSecurity personSecurity;

    public PersonDetails(PersonSecurity personSecurity) {
        this.personSecurity = personSecurity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public String getPassword() {
        return personSecurity.getPassword();
    }

    @Override
    public String getUsername() {
        return personSecurity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
