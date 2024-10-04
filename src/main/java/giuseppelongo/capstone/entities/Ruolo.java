package giuseppelongo.capstone.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Ruolo {
    ADMIN, CLIENTE;

    public GrantedAuthority getAuthority() {
        return new SimpleGrantedAuthority(this.name());
    }
}
