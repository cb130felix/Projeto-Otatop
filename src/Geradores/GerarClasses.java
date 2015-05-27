/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores;

import ModeloRel.ModeloR;
import ProjetoInfo.ProjetoInfo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Guto Leoni
 */
public class GerarClasses {
    
    ModeloR modelo;
    String diretorio;
    ProjetoInfo projeto;

    public GerarClasses(ModeloR modelo, String diretorio, ProjetoInfo projeto) {
        this.modelo = modelo;
        this.diretorio = diretorio;
        this.projeto = projeto;
    }
    
    
    public void gerarClassesEntidades(String nomeBanco) throws IOException{
    
        for (int x = 0; x < modelo.tabelas.size(); x++) {
         
            if(modelo.tabelas.get(x).campo_multi != 2){
            
            File arquivo = new File (diretorio+"Cadastro2/Entidades/"+modelo.tabelas.get(x).nome+".php");
            
            
        if (!arquivo.exists()) {
          
          arquivo.createNewFile();  
        }
        
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
           
        bw.write("<?php\n");
        bw.write("  \nClass "+modelo.tabelas.get(x).nome+"{\n");
        bw.write("\n    //atributes");
        
                for (int i = 0; i < modelo.tabelas.get(x).colunas.size(); i++) {
                    
                    bw.write("\n    public $"+modelo.tabelas.get(x).colunas.get(i).nome+";");
                    
                    
                }
        
        bw.write("\n\n    //methods");
                
            
        bw.write("\n\n  }\n?>");
        bw.close();
            }// fim do if de checar se é multivalorado
            
            
        }
    
    
    }
    
    
}
