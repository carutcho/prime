package br.com.prime.webservice.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.prime.commons.entity.Usuario;
import br.com.prime.services.interfaces.JwtAuthenticationResponse;
import br.com.prime.webservice.security.JwtAuthenticationRequest;
import br.com.prime.webservice.security.JwtTokenUtil;

@RestController
public class AuthenticationRestController {

	@Autowired
	private Environment properties;
	
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> criarTokenAutenticado(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsuario(),
                        authenticationRequest.getSenha()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsuario());
        final String token = jwtTokenUtil.gerarToken(userDetails, device);

        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> atualizarEBuscarTokenAutenticado(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String login = jwtTokenUtil.buscarLoginPorToken(token);
        Usuario user = (Usuario) userDetailsService.loadUserByUsername(login);

        if (jwtTokenUtil.isTokenPodeSerAtualizado(token, user.getUltimaAtualizacao())) {
            String tokenAtualizado = jwtTokenUtil.atualizarToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(tokenAtualizado));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
