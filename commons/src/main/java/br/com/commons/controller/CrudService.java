package br.com.commons.controller;

import java.io.Serializable;
import java.util.List;


/**
 * <code>CrudService</code> expÃµe as funcionalidades de persistÃªncia de uma
 * entidade do sistema que extenda {@link Persistent} para os clientes. <br />
 * <code>CrudService</code> foi projetada para que a classe que a implemente
 * seja <tt>abstract</tt> e realize essa implementaÃ§Ã£o em cima de uma entidade
 * genÃ©rica, para que um terceiro nÃ­vel de classes concretas possam aplicar essa
 * implementaÃ§Ã£o em uma entidade tambÃ©m concreta, reforÃ§ando a coesÃ£o e a
 * orientaÃ§Ã£o a objetos em geral ao usÃ¡-las.
 * 
 * @author <a href="mailto:gewtonarq@gmail.com">Gewton Jhames</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:rrafaelpinto@gmail.com">Rafael Pinto</a>
 * 
 * @param <T>
 *            tipo que serÃ¡ persistido
 */
public interface CrudService<T extends Persistent> extends Service {

    /**
     * Retorna a classe do tipo de entidade.
     *
     * @return a classe que essa instÃ¢ncia manipula
     */
    public abstract Class<T> getPersistentClass();

    /**
     * Realiza a validaÃ§Ã£o de um bean com base na JSR-303.
     *
     * @param entity entidade a ser validada
     * @throws ServiceBusinessException caso ocorram violaÃ§Ã£o das regras do
     *         negÃ³cio
     */
    public abstract void validate(T entity) throws ServiceBusinessException;

    /**
     * Insere uma entidade no repositÃ³rio de dados.
     *
     * @param entity a entidade a ser inserida.
     *
     * @throws ServiceDataAccessException caso ocorram problemas de acesso ao
     *         repositÃ³rio de dados.
     * @throws ServiceBusinessException caso ocorram violaÃ§Ã£o das regras do
     *         negÃ³cio
     */
    public abstract void insert(T entity) throws ServiceBusinessException;

    /**
     * Atualiza uma entidade no repositÃ³rio de dados.
     *
     * @param entity a entidade a ser atualizada.
     *
     * @throws ServiceDataAccessException caso ocorram problemas de acesso ao
     *         repositÃ³rio de dados.
     * @throws ServiceBusinessException caso ocorram violaÃ§Ã£o das regras do
     *         negÃ³cio
     */
    public abstract void update(T entity) throws ServiceBusinessException;

    /**
     * Localiza um objeto pelo seu identificador Ãºnico no mecanismo de
     * persistÃªncia.
     *
     * @param id o identificador Ãºnico da entidade
     * @return <code>null</code> ou a entidade encontrada
     * @throws ServiceDataAccessException caso ocorram problemas de acesso ao
     *         repositÃ³rio de dados.
     */
    public abstract T findById(Serializable id);

    /**
     * Retorna todas as entidades de um tipo especÃ­fico.
     *
     * @return uma lista contendo todas as entidades encontradas. Caso nÃ£o sejam
     *         encontrados registros, retorna uma lista vazia.
     * @throws ServiceDataAccessException caso ocorram problemas de acesso ao
     *         repositÃ³rio de dados.
     */
    public abstract List<T> findAll();

    /**
     * Retorna todas as entidades salvas realizando ordenaÃ§Ã£o pelo campo <tt>sortField</tt>. Se
     * <tt>sortOrder</tt> for <tt>false</tt>, a ordenaÃ§Ã£o serÃ¡ reversa.
     *
     * @param sortField string representando o nome do campo. Note que esse
     *        nome nÃ£o necessariamente terÃ¡ ligaÃ§Ã£o com o mecanismo de
     *        persistÃªncia.
     * @param sortOrder orientaÃ§Ã£o da ordenaÃ§Ã£o. caso seja <tt>false<tt>, a
     *        ordenaÃ§Ã£o serÃ¡ reversa.
     *
     * @return List<T> a lista de entidades encontradas
     *
     * @throws ServiceDataAccessException caso ocorram problemas de acesso ao
     *         repositÃ³rio de dados.
     */
    public abstract List<T> findAll(final String sortField, final Boolean sortOrder);

    /**
     * Retorna todas as entidades salvas, entre <tt>firstResult</tt> e
     * <tt>maxResult</tt>.
     *
     * @param firstResult posiÃ§Ã£o do registro inicial
     * @param maxResult quantidade a ser buscada a partir do registro inicial
     * @return List<T> a lista de entidades encontradas
     *
     * @throws IllegalArgumentException caso os parÃ¢metros
     *         <code>firstResult</code> e <code>maxResult</code> estejam fora do
     *         especificado.
     * @throws ServiceDataAccessException caso ocorram problemas de acesso ao
     *         repositÃ³rio de dados.
     */
    public abstract List<T> findAll(final int firstResult, final int maxResult);

    /**
     * Retorna todas as entidades salvas, entre <tt>firstResult</tt> e
     * <tt>maxResult</tt>.  Realiza ordenaÃ§Ã£o pelo campo <tt>sortField</tt>. Se
     * <tt>sortOrder</tt> for <tt>false</tt>, a ordenaÃ§Ã£o serÃ¡ reversa.
     *
     * @param firstResult posiÃ§Ã£o do registro inicial
     * @param maxResult quantidade a ser buscada a partir do registro inicial
     * @param sortField string representando o nome do campo. Note que esse
     *        nome nÃ£o necessariamente terÃ¡ ligaÃ§Ã£o com o mecanismo de
     *        persistÃªncia.
     * @param sortOrder orientaÃ§Ã£o da ordenaÃ§Ã£o. caso seja <tt>false<tt>, a
     *        ordenaÃ§Ã£o serÃ¡ reversa.
     *
     * @return List<T> a lista de entidades encontradas
     *
     * @throws IllegalArgumentException caso os parÃ¢metros
     *         <code>firstResult</code> e <code>maxResult</code> estejam fora do
     *         especificado.
     * @throws ServiceDataAccessException caso ocorram problemas de acesso ao
     *         repositÃ³rio de dados.
     */
    public abstract List<T> findAll(final int firstResult, final int maxResult,
            final String sortField, final Boolean sortOrder);

    /**
     * Retorna a quantidade de registros de um tipo de entidade.
     *
     * @param entity a entidade
     * @return a quantidade de registros desse tipo
     *
     * @throws ServiceDataAccessException caso ocorram problemas de acesso ao
     *         repositÃ³rio de dados.
     */
    public abstract Integer countAll();

    /**
     * Realiza uma consulta com base nos atributos da entidade passada. SerÃ£o
     * considerados todos os atributos que possuam algum valor definido
     * (incluindo o id) e que nÃ£o sÃ£o coleÃ§Ã£o. O operador lÃ³gico utilizado serÃ¡
     * o <tt>AND</tt>. No caso dos atributos <code>String</code>, a busca vai
     * ser feita de maneira "case insensitive / accent insensitive" e com a
     * busca feita em qualquer parte do texto.
     * 
     * <p>
     * Exemplo:
     * 
     * <pre>
     * {@code
     * 
     * // Obtendo uma instÃ¢ncia de Estado. Obrigatoriamente ele tem que ter o 
     * // seu identificador Ãºnico (id) preenchido. Obs: Recomenda-se regastar
     * // a entidade a partir da camada de serviÃ§o que tenha acesso ao reposi-
     * // tÃ³rio de dados. 
     * Estado estado = new Estado(11, "SÃ£o Paulo");
     * 
     * //  Obtendo uma instÃ¢ncia do repositÃ³rio de dados. 
     * CidadeService cidadeService = new CidadeService();
     * 
     * // Criando uma instÃ¢ncia de Cidade. Ela serÃ¡ o parÃ¢metro para busca. 
     * Cidade cidadeParaBusca = new Cidade();
     * cidadeParaBusca.setNome("SÃ£o JosÃ©")
     * cidadeParaBusca.setEstado(estado);
     * 
     * // Obtendo todas as instÃ¢ncias de cidade que tem o nome composto
     * // por "SÃ£o JosÃ©" e que pertencem ao estado de "SÃ£o Paulo" (id = 11). 
     * // Ex: SÃ£o JosÃ©, SÃ£o JosÃ© do Rio Preto, Pedreira de SÃ£o Josenildo.
     * // Se fosse uma consulta SQL, seria algo do tipo:
     * // SELECT * 
     * // FROM   Cidade c
     * // INNER  JOIN Estado e ON e.id = c.estado_id
     * // WHERE  c.nome LIKE '%SÃ£o JosÃ©%'
     * //        AND estado.id = 11
     * List<Cidade> cidadeList = cidadeService.findByAttributes(cidade);
     * }
     * </pre>
     * 
     * </p>
     * 
     * @param entity
     *            a entidade modelo
     * @return a lista de entidades encontradas
     * 
     * @throws IllegalArgumentException
     *             caso os parÃ¢metros <code>entity</code> seja nulo.
     * @throws ServiceDataAccessException
     *             caso ocorram problemas de acesso ao repositÃ³rio de dados.
     * 
     */
    public abstract List<T> findByAttributes(T entity);

    
    
    /**
     * Realiza uma consulta nos mesmos moldes de
     * {@link CrudService#findByAttributes(Persistent)}, a partir da posiÃ§Ã£o
     * <tt>firstResult</tt> atÃ© o mÃ¡ximo de <tt>maxResult</tt>.
     * 
     * @param entity
     *            a entidade modelo 
     * @param firstResult
     *            posiÃ§Ã£o do registro inicial. A primeira posiÃ§Ã£o Ã© 0. ForneÃ§a
     *            um valor maior ou igual a 0 (ZERO).
     * @param maxResult
     *            quantidade a ser retornada a partir do registro inicial. Caso
     *            seja informado 0, serÃ£o retornadas todas as instÃ¢ncias a
     *            partir do especificado em <code>firstResult</code>. ForneÃ§a um
     *            valor maior ou igual a 0(ZERO).
     * 
     * @return List<T> a lista de entidades encontradas
     * 
     * @throws IllegalArgumentException
     *             caso os parÃ¢metros <code>entity</code> seja nulo.
     * @throws IllegalArgumentException
     *             caso os parÃ¢metros <code>firstResult</code> e
     *             <code>maxResult</code> estejam fora do especificado.
     * @throws ServiceDataAccessException caso ocorram problemas de acesso ao
     *         repositÃ³rio de dados.    
     */
    public abstract List<T> findByAttributes(T entity, int firstResult,int pageSize);    
    
    
    /**
     * Realiza uma consulta nos mesmos moldes de
     * {@link CrudService#findByAttributes(Persistent, int, int)}, levando em conta
     * a ordenaÃ§Ã£o definida no campo <tt>sortField</tt>. Se <tt>sortOrder</tt>
     * for <tt>true</tt>, a ordenaÃ§Ã£o serÃ¡ ascendente (ASC), caso contrÃ¡rio serÃ¡
     * descendente (DESC).
     * 
     * @param entity
     *            a entidade modelo
     * @param firstResult
     *            posiÃ§Ã£o do registro inicial. A primeira posiÃ§Ã£o Ã© 0. ForneÃ§a
     *            um valor maior ou igual a 0 (ZERO).
     * @param maxResult
     *            quantidade a ser retornada a partir do registro inicial. Caso
     *            seja informado 0, serÃ£o retornadas todas as instÃ¢ncias a
     *            partir do especificado em <code>firstResult</code>. ForneÃ§a um
     *            valor maior ou igual a 0(ZERO).
     * @param sortField
     *            string representando o nome do campo. Note que esse nome nÃ£o
     *            necessariamente terÃ¡ ligaÃ§Ã£o com o mecanismo de persistÃªncia.
     * @param sortOrder
     *            orientaÃ§Ã£o da ordenaÃ§Ã£o. Valor
     *            <tt>true<tt> indica ordenaÃ§Ã£o ASC e <tt>false<tt> DESC.
     * 
     * 
     * @return List<T> a lista de entidades encontradas
     * 
     * @throws IllegalArgumentException
     *             caso os parÃ¢metros <code>firstResult</code> e
     *             <code>maxResult</code> estejam fora do especificado.
     * @throws IllegalArgumentException
     *             caso os parÃ¢metros <code>entity</code> seja nulo.
     * @throws ServiceDataAccessException
     *             caso ocorram problemas de acesso ao repositÃ³rio de dados.
     */    
    public abstract List<T> findByAttributes(T entity, final int firstResult,
            final int maxResult, final String sortField, final Boolean sortOrder);
    
    /**
     * Retorna a quantidade de registros de acordo com o exemplo informado.
     *
     * @param entity a entidade modelo
     * @return a quantidade de registros encontrados que sÃ£o similares ao modelo
     *
     * @throws ServiceDataAccessException caso ocorram problemas de acesso ao
     *         repositÃ³rio de dados.
     */
    public abstract Integer countByAttributes(T entity);

    /**
     * Remover um objeto.
     *
     * @param entity objeto a ser removido
     * @throws ServiceDataAccessException caso ocorram erros no processo
     */
    // FIXME: E nos casos em que o registro nÃ£o puder ser deletado porque possui
    // registros dependentes? A exceÃ§Ã£o apropriada para esse caso em seria seria
    // lanÃ§as ServiceBusinessException
    public abstract void delete(T entity);

    /**
     * Remover um objeto pelo seu identificador Ãºnico no mecanismo de
     * persistÃªncia.
     *
     * @param id o identificador Ãºnico da entidade
     * @throws ServiceDataAccessException caso ocorram erros no processo
     */
    // FIXME: E nos casos em que o registro nÃ£o puder ser deletado porque possui
    // registros dependentes? A exceÃ§Ã£o apropriada para esse caso em seria seria
    // lanÃ§as ServiceBusinessException
    public abstract void delete(Integer id);
}