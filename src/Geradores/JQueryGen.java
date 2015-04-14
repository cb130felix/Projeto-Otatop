/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores;

import Auxiliares.CopyFile;
import ModeloRel.ModeloR;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Renan
 */
public class JQueryGen {
    
    ModeloR modelor;
    String caminho_dir;
    CopyFile copyFile = new CopyFile();
    
    
    public JQueryGen(ModeloR modelor, String caminho_dir) {
        this.modelor = modelor;
        this.caminho_dir = caminho_dir;
    }
    
    
    
    int gerarJQuery() throws IOException{
    
        this.copyFile.copiarArquivo(new File("src/Arquivos/jquery-1.11.2.js"), new File(caminho_dir+"utilitarios/jquery-1.11.2.js"));
   
        
        return 0;
    }
    
    
}
