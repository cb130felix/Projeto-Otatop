
package ModeloER;

import java.util.ArrayList;

/**
 *
 * @author Guto Leoni
 */
public class Entidade{

    public String nome;
    public String intensidade;
    public ArrayList<AtributoEnt> atributos = new ArrayList<>();
    
    
    public Entidade() {
    }

    public Entidade(String nome, String intensidade) {
        this.nome = nome;
        this.intensidade = intensidade;
    }

    
    
}
