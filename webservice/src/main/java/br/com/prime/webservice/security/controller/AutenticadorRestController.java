package br.com.prime.webservice.security.controller;

import static br.com.prime.commons.utils.Utils.isEmpty;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.mobile.device.LiteDeviceResolver;
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
import br.com.prime.webservice.security.AutenticadorRequest;
import br.com.prime.webservice.security.AutenticadorResponse;
import br.com.prime.webservice.security.TokenUtil;

@RestController
public class AutenticadorRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> criarTokenAutenticado(@RequestBody AutenticadorRequest authenticationRequest, HttpServletRequest request) throws AuthenticationException {
    	
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsuario(),
                        authenticationRequest.getSenha()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsuario());
        final String token = tokenUtil.gerarToken(userDetails, getDevice(request));

        return ResponseEntity.ok(new AutenticadorResponse(token));
    }

    private Device getDevice(HttpServletRequest request) {
    	
    	Device retorno = DeviceUtils.getCurrentDevice(request);
    	if (isEmpty(retorno)){
	    	List<String> keywords = new ArrayList<String>();
	        keywords.add("web");
	        LiteDeviceResolver device = new LiteDeviceResolver(keywords);
	        retorno = device.resolveDevice(request);
    	}
        return retorno;
	}

	@RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> atualizarEBuscarTokenAutenticado(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String login = tokenUtil.buscarLoginPorToken(token);
        Usuario user = (Usuario) userDetailsService.loadUserByUsername(login);

        if (tokenUtil.isTokenPodeSerAtualizado(token, user.getUltimaAtualizacao())) {
            String tokenAtualizado = tokenUtil.atualizarToken(token);
            return ResponseEntity.ok(new AutenticadorResponse(tokenAtualizado));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

}
