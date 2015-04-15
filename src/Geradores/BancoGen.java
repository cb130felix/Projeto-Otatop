
package Geradores;

import ModeloRel.Coluna;
import ModeloRel.ModeloR;
import ModeloRel.Tabela;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Arthur
 */
public class BancoGen {
    
    ModeloR modelor;
    String caminho_dir;

    public BancoGen(ModeloR modelor, String caminho_dir) {
        this.modelor = modelor;
        this.caminho_dir = caminho_dir;
    }
    
    
    
   class Estrangeiro {
    
    public String tabela_pk;
    public String campo_pk;
    public String tabela_fk;
    public String campo_fk;
   }
   
    /**
     * Gera um arquivo com código mysql de acordo com o modeloR carregado.
     * @return
     * @throws IOException 
     */
    
   int gerarSQL() throws IOException{
       
        int i=0, w=0;
        
        File arquivo = new File (caminho_dir+"bd_projeto.sql");
        ArrayList<Estrangeiro> estrangeiro = new ArrayList<Estrangeiro>();
        
        if (!arquivo.exists()) {
           arquivo.createNewFile();  
        }
        
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        
       for (Tabela tabela : modelor.tabelas) {
           ArrayList<String> temppk = new ArrayList<String>();
           
           bw.write("CREATE TABLE " + tabela.nome + " (");
           bw.newLine();
           
            for (Coluna coluna : tabela.colunas) {
                
                bw.write(coluna.nome);
                    
                    if ("VARCHAR".equals(coluna.tipo.toUpperCase())){
                      bw.write(" VARCHAR(" + Integer.toString(coluna.tamanho_str) + ")");
                    }
                    else{
                      bw.write(" " + coluna.tipo.toUpperCase());
                    }
                
                    if (coluna.auto_inc){
                      bw.write(" AUTO_INCREMENT");
                    }

                    if (coluna.pk){
                        temppk.add(coluna.nome);
                    }

                    if (coluna.fk){
                      Estrangeiro tempfk = new Estrangeiro(); 
                      
                      tempfk.tabela_pk = tabela.nome;
                      tempfk.campo_pk = coluna.nome;
                      tempfk.tabela_fk = coluna.fk_nome_tabela;
                      tempfk.campo_fk = coluna.fk_nome_coluna;
                      estrangeiro.add(tempfk);
                    }
                    
                    if (coluna.nullable){
                      bw.write(" NULL");
                    }
                    
                    else {
                      bw.write(" NOT NULL");
                    }
                    
                 if ((i < this.modelor.tabelas.get(w).colunas.size()) && (temppk.size()>=0)){ bw.write(","); }
                
                bw.newLine();
                i++;
            }
            
          i=0;  
          w++;
 
          bw.write("PRIMARY KEY (");
          
           for (int j = 0; j < temppk.size(); j++) {
               bw.write("`"+temppk.get(j)+"`");
               if (j<temppk.size()-1){
                   bw.write(",");
               }
           }
           
          bw.write(")");
          bw.newLine();
          bw.write(");");
          bw.newLine();
          bw.newLine();
       }
            int u=0;
            for (Estrangeiro estrangeiro1 : estrangeiro) {
                bw.newLine();
                bw.write("ALTER TABLE `" + estrangeiro1.tabela_pk + "` ADD CONSTRAINT `fk" + u +"_" + 
                        estrangeiro1.tabela_fk + "` FOREIGN KEY ( `" + estrangeiro1.campo_pk + "` ) REFERENCES `" + 
                        estrangeiro1.tabela_fk + "` ( `" + estrangeiro1.campo_fk + "` );");
                u++;
            }
       
        bw.close();
        fw.close();
        return 0;
   }
    
}
