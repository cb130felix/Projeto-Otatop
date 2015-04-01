
package ModeloRel;

import java.util.ArrayList;

/**
 * Classe para se trabalhar na hora de gerar os arquivos
 * @author Renan
 */

public class Tabela {
    
    public String nome;
    /**
     * 0 = Não tem campo multi;
     * 1 = Tem campo multi;
     * 2 = É campo multi de alguém.
     */
    public char campo_multi = 0; 
    public ArrayList<Coluna> colunas = new ArrayList<Coluna>();
    
    
    
}
