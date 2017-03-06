package br.com.prime.webservice.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.prime.commons.entity.Usuario;
import br.com.prime.webservice.security.JwtTokenUtil;

@RestController
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public Usuario buscarUsuarioAutenticado(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String login = jwtTokenUtil.buscarLoginPorToken(token);
        Usuario usuario = (Usuario) userDetailsService.loadUserByUsername(login);
        return usuario;
    }

}
