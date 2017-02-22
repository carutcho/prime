package br.com.prime.data.persistence;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.dao.DataAccessException;

import br.com.prime.data.exception.PersistenceValidateException;

/**
 * <p>
 * A interface <code>CrudDao</code> define classes que realizam operaÃ§Ãµes de
 * <b>CRUD</b> (Create, Retrieve, Update e Delete), como por exemplo os mÃ©todos de
 * busca {@link #buscarPorId(Serializable)} e {@link #buscarTodosPorRange()}. Toda classe que
 * implementar <code>CrudDao</code> estarÃ¡ realizando operaÃ§Ãµes em uma entidade
 * especÃ­fica que extende {@link Persistent}.
 * </p>
 *
 * @param <T> entidade a ser manipulada
 * @see {@link Persistent}
 *
 * @author <a
 *         href="mailto:gewtonarq@gmail.com">Gewton
 *         Jhames</a>
 * @author <a
 *         href="mailto:misaelbarreto@gmail.com">Misael
 *         Barreto</a>
 * @author <a
 *         href="mailto:rrafaelpinto@gmail.com">Rafael
 *         Pinto</a>
 *
 * @version 0.3.0.Final
 * @since 0.1.0.Final
 */

public interface CrudDao<T extends Persistent> extends Serializable {

    public abstract Class<T> getPersistentClass();

    public abstract void validar(T entity) throws PersistenceValidateException;

    public abstract void inserir(T entity) throws PersistenceValidateException;

    public abstract void atualizar(T entity) throws PersistenceValidateException;

    public abstract T buscarPorId(Serializable id) throws PersistenceValidateException;

    public abstract Collection<T> listarTodos() throws PersistenceValidateException;

    /**
     * Retorna todas os registros encontrados e realiza ordenaÃ§Ã£o pelo campo
     * <tt>sortField</tt>. Se <tt>sortOrder</tt> for <tt>true</tt>, a ordenaÃ§Ã£o
     * serÃ¡ ascendente (ASC), caso contrÃ¡rio serÃ¡ descendente (DESC).
     * 
     * @param sortField
     *            string representando o nome do campo. Note que esse nome nÃ£o
     *            necessariamente terÃ¡ ligaÃ§Ã£o com o mecanismo de persistÃªncia.
     * @param sortOrder
     *            orientaÃ§Ã£o da ordenaÃ§Ã£o. Valor
     *            <tt>true<tt> indica ordenaÃ§Ã£o ASC e <tt>false<tt> DESC.
     * 
     * @return List<T> a lista com os registros encontrados.
     * 
     * @throws DataAccessException
     *             caso ocorram problemas de acesso ao repositÃ³rio de dados.
     */
    public abstract Collection<T> buscarTodosOrdenados(String campo, Boolean ordem);

    /**
     * Retorna todas os registros encontrados, entre <code>firstResult</code> e
     * <code>maxResult</code>.
     *
     * @param posicaoInicial posiÃ§Ã£o do registro inicial. A primeira posiÃ§Ã£o Ã© 0.
     *        ForneÃ§a um valor maior ou igual a 0 (ZERO).
     * @param quantidadeRegistros quantidade a ser retornada a partir do registro inicial.
     *        Caso seja informado 0, serÃ£o retornadas todas as instÃ¢ncias a
     *        partir do especificado em <code>firstResult</code>. ForneÃ§a um
     *        valor maior ou igual a 0(ZERO).
     *
     * @return List<T> a lista com os registros encontrados.
     * @throws PersistenceValidateException 
     *
     * @throws IllegalArgumentException caso os parÃ¢metros
     *         <code>firstResult</code> e <code>maxResult</code> estejam fora do
     *         especificado.
     * @throws DataAccessException caso ocorram problemas de acesso ao
     *         repositÃ³rio de dados.
     */
    public abstract Collection<T> buscarPorRange(final int posicaoInicial, final int quantidadeRegistros) throws PersistenceValidateException;

    /**
     * Retorna todas os registros encontrados, entre <code>firstResult</code> e
     * <code>maxResult</code>. Realiza ordenaÃ§Ã£o pelo campo <tt>sortField</tt>. Se
     * <tt>sortOrder</tt> for <tt>false</tt>, a ordenaÃ§Ã£o serÃ¡ reversa.
     *
     * @param posicaoInicial posiÃ§Ã£o do registro inicial. A primeira posiÃ§Ã£o Ã© 0.
     *        ForneÃ§a um valor maior ou igual a 0 (ZERO).
     * @param quantidadeRegistros quantidade a ser retornada a partir do registro inicial.
     *        Caso seja informado 0, serÃ£o retornadas todas as instÃ¢ncias a
     *        partir do especificado em <code>firstResult</code>. ForneÃ§a um
     *        valor maior ou igual a 0(ZERO).
     * @param campo string representando o nome do campo. Note que esse
     *        nome nÃ£o necessariamente terÃ¡ ligaÃ§Ã£o com o mecanismo de
     *        persistÃªncia.
     * @param ordem orientaÃ§Ã£o da ordenaÃ§Ã£o. caso seja <tt>false<tt>, a
     *        ordenaÃ§Ã£o serÃ¡ reversa.
     *
     *
     * @return List<T> a lista com os registros encontrados.
     *
     * @throws IllegalArgumentException caso os parÃ¢metros
     *         <code>firstResult</code> e <code>maxResult</code> estejam fora do
     *         especificado.
     * @throws DataAccessException caso ocorram problemas de acesso ao
     *         repositÃ³rio de dados.
     */
    public abstract Collection<T> buscarPorRangeOrdenado(int posicaoInicial, int quantidadeRegistros, String campo, Boolean ordem);

    /***
     * Retorna a quantidade total de registros de um tipo de entidade.
     *
     * @param entity a entidade
     * @return a quantidade de registros dessa entidade.
     * @throws PersistenceValidateException 
     *
     * @throws DataAccessException caso ocorram problemas de acesso ao
     *         repositÃ³rio de dados.
     */
    public abstract Integer totalRegistros() throws PersistenceValidateException;

    public abstract void remover(T entity) throws PersistenceValidateException;

    public abstract void remover(Integer id) throws PersistenceValidateException;
}
