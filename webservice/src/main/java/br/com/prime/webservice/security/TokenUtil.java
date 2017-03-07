package br.com.prime.webservice.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.prime.commons.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil implements Serializable {

    private static final long serialVersionUID = -3301605591108950415L;

    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_AUDIENCE = "audience";
    static final String CLAIM_KEY_CREATED = "created";

    private static final String AUDIENCE_UNKNOWN = "unknown";
    private static final String AUDIENCE_WEB = "web";
    private static final String AUDIENCE_MOBILE = "mobile";
    private static final String AUDIENCE_TABLET = "tablet";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expiracao;

    public String buscarLoginPorToken(String token) {
        String login;
        try {
            final Claims claims = bucarTokenReivindicado(token);
            login = claims.getSubject();
        } catch (Exception e) {
            login = null;
        }
        return login;
    }

    public Date buscarDataCriacaoToken(String token) {
        Date dataCriacao;
        try {
            final Claims claims = bucarTokenReivindicado(token);
            dataCriacao = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            dataCriacao = null;
        }
        return dataCriacao;
    }

    public Date buscarDataExpiracaoToken(String token) {
        Date dataExpiracao;
        try {
            final Claims claims = bucarTokenReivindicado(token);
            dataExpiracao = claims.getExpiration();
        } catch (Exception e) {
            dataExpiracao = null;
        }
        return dataExpiracao;
    }

    public String buscarTokenPublico(String token) {
        String tokenPublico;
        try {
            final Claims claims = bucarTokenReivindicado(token);
            tokenPublico = (String) claims.get(CLAIM_KEY_AUDIENCE);
        } catch (Exception e) {
            tokenPublico = null;
        }
        return tokenPublico;
    }

    private Claims bucarTokenReivindicado(String token) {
        Claims tokenReivindicado;
        try {
            tokenReivindicado = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            tokenReivindicado = null;
        }
        return tokenReivindicado;
    }

    private Date gerarDataExpiracao() {
        return new Date(System.currentTimeMillis() + Long.parseLong(expiracao) * 1000);
    }

    private Boolean isTokenExpirado(String token) {
        final Date expiracao = buscarDataExpiracaoToken(token);
        return expiracao.before(new Date());
    }

    private Boolean isCriadoAntesDaUltimaAtualizacao(Date criado, Date ultimaAtualizacao) {
        return (ultimaAtualizacao != null && criado.before(ultimaAtualizacao));
    }

    private String gerarChavePublica(Device device) {
        String chavePublica = AUDIENCE_UNKNOWN;
        if (device.isNormal()) {
            chavePublica = AUDIENCE_WEB;
        } else if (device.isTablet()) {
            chavePublica = AUDIENCE_TABLET;
        } else if (device.isMobile()) {
            chavePublica = AUDIENCE_MOBILE;
        }
        return chavePublica;
    }

    private Boolean ignorarExpiracaoToken(String token) {
        String tokenPublico = buscarTokenPublico(token);
        return (AUDIENCE_TABLET.equals(tokenPublico) || AUDIENCE_MOBILE.equals(tokenPublico));
    }

    public String gerarToken(UserDetails userDetails, Device device) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_AUDIENCE, gerarChavePublica(device));
        claims.put(CLAIM_KEY_CREATED, new Date());
        return gerarToken(claims);
    }

    String gerarToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(gerarDataExpiracao())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean isTokenPodeSerAtualizado(String token, Date utlimaAtualizacao) {
        final Date created = buscarDataCriacaoToken(token);
        return !isCriadoAntesDaUltimaAtualizacao(created, utlimaAtualizacao)
                && (!isTokenExpirado(token) || ignorarExpiracaoToken(token));
    }

    public String atualizarToken(String token) {
        String tokenAtualizado;
        try {
            final Claims claims = bucarTokenReivindicado(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            tokenAtualizado = gerarToken(claims);
        } catch (Exception e) {
            tokenAtualizado = null;
        }
        return tokenAtualizado;
    }

    public Boolean validarToken(String token, UserDetails usuario) {
        Usuario usuarioValidar = (Usuario) usuario;
        final String login = buscarLoginPorToken(token);
        final Date created = buscarDataCriacaoToken(token);
        //final Date expiration = getExpirationDateFromToken(token);
        return (
                login.equals(usuarioValidar.getUsername())
                        && !isTokenExpirado(token)
                        && !isCriadoAntesDaUltimaAtualizacao(created, usuarioValidar.getUltimaAtualizacao()));
    }
}