package br.com.prime.webservice.security;

import java.io.Serializable;

/**
 * Created by stephan on 20.03.16.
 */
public class AutenticadorResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public AutenticadorResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
