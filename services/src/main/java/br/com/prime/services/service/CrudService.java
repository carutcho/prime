package br.com.prime.services.service;

import java.io.Serializable;
import java.util.Collection;

import br.com.commons.exceptions.ServiceBusinessException;
import br.com.prime.data.persistence.Persistent;

/**
 * @author <a href="mailto:gewtonarq@gmail.com">Gewton Jhames</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:rrafaelpinto@gmail.com">Rafael Pinto</a>
 * 
 * @param <T>
 *            tipo que serÃ¡ persistido
 */
public interface CrudService<T extends Persistent> extends Service {

    public abstract Class<T> getPersistentClass();

    public abstract void validar(T entity) throws ServiceBusinessException;

    public abstract void inserir(T entity) throws ServiceBusinessException;

    public abstract void atualizar(T entity) throws ServiceBusinessException;

    public abstract T buscarPorId(Serializable id) throws ServiceBusinessException;

    public abstract Collection<T> listarTodos() throws ServiceBusinessException;

    public abstract Collection<T> buscarTodosOrdenados(final String campo, final Boolean ordem);

    public abstract Collection<T> buscarPorRange(final int posicaoInicial, final int quantidadeRegistros) throws ServiceBusinessException;

    public abstract Collection<T> buscarPorRangeOrdenado(final int posicaoInicial, final int quantidadeRegistros,
            final String campo, final Boolean ordem);

    public abstract Integer totalRegistros() throws ServiceBusinessException;

    public abstract void remover(T entity) throws ServiceBusinessException;

    public abstract void remover(Integer id) throws ServiceBusinessException;
}