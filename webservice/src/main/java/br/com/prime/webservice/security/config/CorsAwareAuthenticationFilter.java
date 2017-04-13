package br.com.prime.webservice.security.config;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class CorsAwareAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

//
//	public Authentication attemptAuthentication(HttpServletRequest request,
//			HttpServletResponse response) throws AuthenticationException {
////    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
//
//
//        if (request.getHeader(ORIGIN)) {            
///*            response.addHeader('Access-Control-Allow-Origin', "*");
//            response.addHeader('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE');
//            response.addHeader('Access-Control-Allow-Credentials', 'true');
//            response.addHeader('Access-Control-Allow-Headers', request.getHeader('Access-Control-Request-Headers'));*/
//       	}
//
//        if (request.method == 'OPTIONS') {
///*            response.writer.print('OK');
//            response.writer.flush();
//            return;*/
//        }
//        return super.attemptAuthentication(request, response);
//    }

}
