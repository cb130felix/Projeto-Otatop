
package ModeloRel;

/**
 *
 * @author Renan
 */
public class Coluna {
    
    public boolean pk, auto_inc, nullable, fk = false;
    public String nome, tipo, fk_nome_coluna, fk_nome_tabela;
    public int tamanho_str;

    public Coluna(boolean pk, boolean auto_inc, boolean nullable, String nome, String tipo, String fk_nome_coluna, String fk_nome_tabela, int tamanho_str, boolean fk) {
        this.pk = pk;
        this.auto_inc = auto_inc;
        this.nullable = nullable;
        this.nome = nome;
        this.tipo = tipo;
        this.fk_nome_coluna = fk_nome_coluna;
        this.fk_nome_tabela = fk_nome_tabela;
        this.tamanho_str = tamanho_str;
        this.fk = fk;
    }   
    
    public Coluna(){}
    
    
}
