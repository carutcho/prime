package br.com.prime.services.base;

import java.io.Serializable;
import java.util.Collection;

import br.com.prime.commons.data.persistence.Persistent;
import br.com.prime.commons.exceptions.ServiceBusinessException;

public interface CrudService<T extends Persistent> extends Service {

    public abstract Class<T> getPersistentClass();

    public abstract void validar(T entity) throws ServiceBusinessException;

    public abstract void inserir(T entity) throws ServiceBusinessException;

    public abstract T atualizar(T entity) throws ServiceBusinessException;

    public abstract T buscarPorId(Serializable id) throws ServiceBusinessException;

    public abstract Collection<T> listarTodos() throws ServiceBusinessException;

    public abstract Collection<T> buscarTodosOrdenados(final String campo, final Boolean ordem);

    public abstract Collection<T> buscarPorRange(final int posicaoInicial, final int quantidadeRegistros) throws ServiceBusinessException;

    public abstract Collection<T> buscarPorRangeOrdenado(final int posicaoInicial, final int quantidadeRegistros, final String campo, final Boolean ordem);

    public abstract Integer totalRegistros() throws ServiceBusinessException;

    public abstract void remover(T entity) throws ServiceBusinessException;

    public abstract void remover(Long id) throws ServiceBusinessException;
}