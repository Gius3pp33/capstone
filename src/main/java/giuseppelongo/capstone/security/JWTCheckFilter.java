package giuseppelongo.capstone.security;

import giuseppelongo.capstone.entities.Utente;
import giuseppelongo.capstone.exceptions.UnauthorizedException;
import giuseppelongo.capstone.services.UtenteService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class JWTCheckFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UtenteService utenteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Per favore inserisci correttamente il token nell'Authorization Header");
        }

        String accessToken = authHeader.substring(7);
        jwtTools.verifyToken(accessToken);

        Claims claims = jwtTools.extractClaimsFromToken(accessToken);
        String id = claims.getSubject();
        Utente currentUser = utenteService.findById(UUID.fromString(id))
                .orElseThrow(() -> new UnauthorizedException("Utente non trovato!"));

        Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, null, List.of(currentUser.getRuolo().getAuthority()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
