package aplication.upn.BodyHealthy.Security.Dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtDto {
    @Getter @Setter
    private String token;
    @Getter @Setter
    private String bearer = "Bearer";
    @Getter @Setter
    private String nombreUsuario;
    @Getter @Setter
    private Collection<? extends GrantedAuthority> authorities;

    public JwtDto(String token, String nombreUsuario, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.nombreUsuario = nombreUsuario;
        this.authorities = authorities;
    }
}
