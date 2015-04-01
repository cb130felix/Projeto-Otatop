
package ModeloER;

/**
 *
 * @author Guto Leoni
 */
public class DadosAtributo {
    
    public String nome,nome_atb,classe,tipo;
    public int tamanho_str;
    public boolean nullable;

    public DadosAtributo() {
    }

    public DadosAtributo(String nome_entidade, String nome_atb, String classe, String tipo, int tamanho_str, boolean nullable) {
        this.nome = nome_entidade;
        this.nome_atb = nome_atb;
        this.classe = classe;
        this.tipo = tipo;
        this.tamanho_str = tamanho_str;
        this.nullable = nullable;
    }
    
    
    
    
    
}
