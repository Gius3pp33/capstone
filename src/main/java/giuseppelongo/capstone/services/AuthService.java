package giuseppelongo.capstone.services;

import giuseppelongo.capstone.entities.Utente;
import giuseppelongo.capstone.exceptions.UnauthorizedException;
import giuseppelongo.capstone.payloads.UtenteLoginDTO;
import giuseppelongo.capstone.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UtenteService amministratoreService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(UtenteLoginDTO body) {

        Utente found = amministratoreService.findByEmail(body.email())
                .orElseThrow(() -> new UnauthorizedException("Credenziali errate!"));

        if (bcrypt.matches(body.password(), found.getPassword())) {
            return jwtTools.createToken(found);
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }
    }
}
