/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores.Camadas;

import Auxiliares.FixString;
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
public class FachadaGen {
    
    ModeloR modelor;
    String caminho_dir;
    ProjetoInfo info;
    //Arquivo pra escrever a fachada
    File arqFachada;
    FileWriter fw;
    BufferedWriter bw;
    FixString fx = new FixString();
    
    public FachadaGen(ModeloR modelor, String caminho_dir, ProjetoInfo info) {
        this.modelor = modelor;
        this.caminho_dir = caminho_dir;
        this.info = info;
        arqFachada = new File (caminho_dir+"CRUD/camadas/fachada.php");
        
    }
    
    
    
    boolean abrirArquivo() throws IOException{
    
        if (!arqFachada.exists()) {
           arqFachada.createNewFile();  
        }
        
        fw = new FileWriter(arqFachada);
        bw = new BufferedWriter(fw);
        
     
        
        bw.write("<?php\n" +
                "include 'regranegocio.php';\n" +
                "class Fachada {\n" +
                "     var $regra_negocio;\n" +
                "	 \n" +
                "	 function __construct(){\n" +
                "		$this->regra_negocio = new RegraDeNegocio();\n" +
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
    
    public boolean addListar() throws IOException{
    
        bw.write("// MÉTODOS PARA LISTAR\n ");
        
        for (int x = 0; x < modelor.tabelas.size(); x++) {
            
            String nome_metodo = fx.criarNomeMetodo("listar", modelor.tabelas.get(x).nome,'F');
            
            bw.write("\n     public function "+nome_metodo+"(");
            bw.write("$"+modelor.tabelas.get(x).nome+"){\n");
            
            nome_metodo = fx.criarNomeMetodo("listar", modelor.tabelas.get(x).nome,'R');
            
            bw.write("\n     $this->regra_negocio->"+nome_metodo+"(");
            bw.write("$"+modelor.tabelas.get(x).nome+");\n");
            bw.write("\n     }\n");
            
            
        }
        
        
        return true;
    
    }

    //------------------------------------------------------
    //---------- Parte de Renan (fim)----------------------
    //-------------------------------------------------------
    
    
    
    
    
    //-------------------------------------------------------
     //---------- Parte de Guto (inicio)-------------------
     //-------------------------------------------------------
    
    public boolean addCadastrar() throws IOException{
        
        
        bw.write("// MÉTODOS PARA CADASTRAR\n ");
        
        for (int x = 0; x < modelor.tabelas.size(); x++) {
            
            String nome_metodo = fx.criarNomeMetodo("cadastrar", modelor.tabelas.get(x).nome,'F');
            
            bw.write("\n     public function "+nome_metodo+"(");
            bw.write("$"+modelor.tabelas.get(x).nome+"){\n");
            
            nome_metodo = fx.criarNomeMetodo("cadastrar", modelor.tabelas.get(x).nome,'R');
            
            bw.write("\n     $regra_negocio->"+nome_metodo+"(");
            bw.write("$"+modelor.tabelas.get(x).nome+");\n");
            bw.write("\n     }\n");
            
            
        }
        
        
        
        
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
        
        //addCadastrar();
        addListar();
        addDeletar();
        addAtualizar();
        
        fecharArquivo();
      
    }
    
}
