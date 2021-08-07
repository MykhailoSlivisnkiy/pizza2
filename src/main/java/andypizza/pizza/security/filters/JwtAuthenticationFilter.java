package andypizza.pizza.security.filters;

import andypizza.pizza.config.SecurityConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final SecurityConfig securityConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader == null || authorizationHeader.equals("") || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.replace(securityConfig.getTokenPrefix(), "");

        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(securityConfig.getSecretKey())
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();
            System.out.println("Hui2  " + body);
            String username = body.getSubject();
            var authorities = (List<Map<String, String>>) body.get("authorities");
            System.out.println("Hui2  " + authorities);
            var simpleGrantedAuthorities = authorities.stream()
                    .map(m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {
            throw  new IllegalStateException("Token cannot be trusted " + token);
        }

        filterChain.doFilter(request, response);
    }
}
