
package Geradores.Camadas;

import ModeloRel.ModeloR;
import ProjetoInfo.ProjetoInfo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe teste de renan e-e
 * @author Arthur, Guto, Renan
 */
public class TesteRenanGen {
    
    ModeloR modelor;
    String caminho_dir;
    ProjetoInfo info;

    public TesteRenanGen(ModeloR modelor, String caminho_dir, ProjetoInfo info) {
        this.modelor = modelor;
        this.caminho_dir = caminho_dir;
        this.info = info;
    }

    void gerar() throws IOException{
       
        int i=0, w=0;
        
        File arquivo = new File (caminho_dir+"testeRenan.php");
        
        if (!arquivo.exists()) {
           arquivo.createNewFile();  
        }
        
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        
      
        fw.close();
        bw.close();
                
        }
    
    


}