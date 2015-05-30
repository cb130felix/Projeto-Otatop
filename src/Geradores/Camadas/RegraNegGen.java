/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores.Camadas;

import Geradores.BancoGen;
import ModeloRel.ModeloR;
import ProjetoInfo.ProjetoInfo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Renan
 */
public class RegraNegGen {
    
    ModeloR modelor;
    String caminho_dir;
    ProjetoInfo info;
    //Arquivo pra escrever a fachada
    File arq;
    FileWriter fw;
    BufferedWriter bw;
    
    public RegraNegGen(ModeloR modelor, String caminho_dir, ProjetoInfo info) {
        this.modelor = modelor;
        this.caminho_dir = caminho_dir;
        this.info = info;
        arq = new File (caminho_dir+"CRUD/camadas/regranegocio.php");
        
    }
    
    
    
    boolean abrirArquivo() throws IOException{
    
        if (!arq.exists()) {
           arq.createNewFile();  
        }
        
        fw = new FileWriter(arq);
        bw = new BufferedWriter(fw);
        
     
        
        bw.write("<?php\n" +
                "include 'persistencia.php';\n" +
                "class RegraDeNegocio {\n" +
                "     var $persistencia;\n" +
                "	 \n" +
                "	 function __construct(){\n" +
                "		$this->persistencia = new Persistencia();\n" +
                "	 }\n" +
                "	 \n");
    
        return true;
        
        
    }
    
    boolean fecharArquivo() throws IOException{
    
        bw.write("}\n"
                + "?>");
        
        bw.close();
        fw.close();
        
        return true;
    
    }
    
  
     //-------------------------------------------------------
     //---------- Parte de Renan (inicio)-------------------
     //------------------------------------------------------
    
    public boolean addListar(){return true;}

    //------------------------------------------------------
    //---------- Parte de Renan (fim)----------------------
    //-------------------------------------------------------
    
    
    
    
    
    //-------------------------------------------------------
     //---------- Parte de Guto (inicio)-------------------
     //-------------------------------------------------------
    
    public boolean addCadastrar(){
        
        
        
        
        
        return true;}
    
    //-------------------------------------------------------
     //---------- Parte de Guto (fim)-------------------
     //-------------------------------------------------------
    


    //------------------------------------------------------
    //---------- Parte de Arthur (fim)----------------------
    //-------------------------------------------------------
       
    public boolean addDeletar(){return true;}
    
    //-------------------------------------------------------
     //---------- Parte de Arthur (inicio)-------------------
     //-------------------------------------------------------
    

    
    public boolean addAtualizar(){return true;}
    
    
    //Essa método gera o arquivo completo
    public void gerar() throws IOException {
      
        abrirArquivo();
        
        addCadastrar();
        addListar();
        addDeletar();
        addAtualizar();
        
        fecharArquivo();
      
    }
}
