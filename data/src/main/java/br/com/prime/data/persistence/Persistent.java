package br.com.prime.data.persistence;

import java.io.Serializable;

/**
 * <code>Persistent</code> define uma entidade persistÃ­vel. Uma classe de
 * domÃ­nio.<br />
 * As classes que a implementarem deverÃ£o fornecer um identificador padrÃ£o e um
 * label.
 *
 * @author <a href="mailto:gewtonarq@gmail.com">Gewton Jhames</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:rrafaelpinto@gmail.com">Rafael Pinto</a>
 */

public interface Persistent extends Serializable{

	public Long getId();

    public String getLabel();

    public String getName();
    
}
