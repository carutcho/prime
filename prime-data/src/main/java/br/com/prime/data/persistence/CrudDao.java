package br.com.prime.data.persistence;

import java.io.Serializable;
import java.util.Collection;

import br.com.prime.commons.data.persistence.Persistent;
import br.com.prime.commons.exceptions.ServiceBusinessException;
import br.com.prime.data.exception.PersistenceValidateException;

public interface CrudDao<T extends Persistent> extends Serializable {

    public abstract Class<T> getPersistentClass();

    public abstract T inserir(T entity) throws PersistenceValidateException, ServiceBusinessException;

    public abstract void atualizar(T entity) throws PersistenceValidateException, ServiceBusinessException;

    public abstract T buscarPorId(Long id) throws PersistenceValidateException, ServiceBusinessException;

    public abstract Collection<T> listarTodos() throws PersistenceValidateException, ServiceBusinessException;

    public abstract Collection<T> buscarTodosOrdenados(String campo, Boolean ordem) throws PersistenceValidateException, ServiceBusinessException;

    public abstract Collection<T> buscarPorRange(final int posicaoInicial, final int quantidadeRegistros) throws PersistenceValidateException, ServiceBusinessException;

    public abstract Collection<T> buscarPorRangeOrdenado(int posicaoInicial, int quantidadeRegistros, String campo, Boolean ordem);

    public abstract Integer totalRegistros() throws PersistenceValidateException, ServiceBusinessException;

    public abstract void remover(T entity) throws PersistenceValidateException, ServiceBusinessException;

    public abstract void remover(Long id) throws PersistenceValidateException, ServiceBusinessException;
}
