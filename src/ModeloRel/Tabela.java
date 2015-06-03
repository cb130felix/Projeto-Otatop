
package ModeloRel;

import java.util.ArrayList;

/**
 * Classe para se trabalhar na hora de gerar os arquivos
 * @author Renan
 */

public class Tabela {
    
    public String nome;
    /*
     * 0 = Não tem campo multi;
     * 1 = Tem campo multi;
     * 2 = É campo multi de alguém.
     */
    public byte campo_multi = 0; 
    public ArrayList<Coluna> colunas = new ArrayList<Coluna>();
    
    /**
     * @param tabelas vetor de tabelas a ser procurado as tabelas multivaloradas da tabela atual
     * @return ArrayList uma lista com as tabelas que são valores multivalorados dessa tabela 
     */
    public ArrayList<Tabela> acharTabelasMultivaloradas(ArrayList<Tabela> tabelas){
        
        ArrayList<Tabela> tabelas_multi = new ArrayList<Tabela>();
        
        for(int i = 0; i < tabelas.size(); i++){
            
            if((tabelas.get(i).campo_multi == 2)){
            
                for (int j = 0; j < tabelas.get(i).colunas.size(); j++) {
                    
                    if(this.nome.equals(tabelas.get(i).colunas.get(j).fk_nome_tabela)){
                    
                        tabelas_multi.add(tabelas.get(i));
                        break;
                        
                    }
                    
                }
                
            }
        
        }
    
        return tabelas_multi;
        
    }
}
