/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores.Camadas;

import ModeloRel.ModeloR;
import ProjetoInfo.ProjetoInfo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Renan
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