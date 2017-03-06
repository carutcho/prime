package br.com.prime.services.factory;

import br.com.prime.commons.entity.Usuario;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static Usuario create(Usuario usuario) {
        return new Usuario(
        		usuario.getId(),
    			usuario.getNome(),
    			usuario.getLogin(),
    			usuario.getSenha(),
    			usuario.getAtivo(),
    			//mapToGrantedAuthorities(usuario.getAuthorities()),
    			usuario.getPerfis(), 
    			usuario.getDescricao(),
    			usuario.getUltimaAtualizacao()
        );
    }

    /*private static List<GrantedAuthority> mapToGrantedAuthorities(List<Perfil> perfis) {
        return perfis.stream()
                .map(perfis -> new SimpleGrantedAuthority(perfis.getNome().name()))
                .collect(Collectors.toList());
    }*/
}
