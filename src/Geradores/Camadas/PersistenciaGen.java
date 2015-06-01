
package Geradores.Camadas;

import Auxiliares.FixString;
import ModeloRel.ModeloR;
import ProjetoInfo.ProjetoInfo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Arthur, Guto, Renan
 */
public class PersistenciaGen {
    
    ModeloR modelor;
    String caminho_dir;
    ProjetoInfo info;
    //Arquivo pra escrever a fachada
    File arqFachada;
    FileWriter fw;
    BufferedWriter bw;
    FixString fx = new FixString();
    
    public PersistenciaGen(ModeloR modelor, String caminho_dir, ProjetoInfo info) {
        this.modelor = modelor;
        this.caminho_dir = caminho_dir;
        this.info = info;
        arqFachada = new File (caminho_dir+"CRUD/Camadas/persistencia.php");
        
    }
    
    
    
    boolean abrirArquivo() throws IOException{
    
        if (!arqFachada.exists()) {
           arqFachada.createNewFile();  
        }
        
        fw = new FileWriter(arqFachada);
        bw = new BufferedWriter(fw);
        
     
        
        bw.write("<?php\n"
                + "include 'bancodedados.php';\n"
                + "class Persistencia {\n"
                        + "     var $bancodedados;\n"
                + "	 function __construct(){\n" 
                + "		$this->bancodedados = new BancoDeDados();\n" 
                + "	 }\n"); 

    
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
        
        bw.write("// MÉTODOS PARA CADASTRAR\n ");
        
        for (int x = 0; x < modelor.tabelas.size(); x++) {
            
            String nome_metodo = fx.criarNomeMetodo("listar", modelor.tabelas.get(x).nome,'P');
            
            bw.write("\n     public function "+nome_metodo+"(");
            bw.write("$"+modelor.tabelas.get(x).nome+"){\n");
            
            nome_metodo = fx.criarNomeMetodo("listar", modelor.tabelas.get(x).nome,'B');
            
            bw.write("\n     $resultado = $this->bancodedados->"+nome_metodo+"(");
            bw.write("$"+modelor.tabelas.get(x).nome+");\n");
            
            bw.write("\n     return $resultado;\n");
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
    
    
    /**
     * Método que cria o Scrpit PHP da funções de cadastrar da camada persistência
     * @return
     * @throws IOException 
     */
    public boolean addCadastrar() throws IOException{
        
         bw.write("// MÉTODOS PARA CADASTRAR\n ");
        
        for (int x = 0; x < modelor.tabelas.size(); x++) {
            
            String nome_metodo = fx.criarNomeMetodo("cadastrar", modelor.tabelas.get(x).nome,'P');
            
            bw.write("\n     public function "+nome_metodo+"(");
            bw.write("$"+modelor.tabelas.get(x).nome+"){\n");
            
            nome_metodo = fx.criarNomeMetodo("cadastrar", modelor.tabelas.get(x).nome,'B');
            
            bw.write("\n     $this->bancodedados->"+nome_metodo+"(");
            bw.write("$"+modelor.tabelas.get(x).nome+");\n");
            bw.write("\n     }\n");
            
            
        }
        
        
        
        
        return true;}
    
    //-------------------------------------------------------
     //---------- Parte de Guto (fim)-------------------
     //-------------------------------------------------------
    


    //-------------------------------------------------------
    //---------- Parte de Arthur (inicio)--------------------
    //-------------------------------------------------------
    
    /**
     * Método que cria o Scrpit PHP da funções de deletar da camada persistência
     * @return Valor booleano
     * @throws IOException
     * @author Arthur
     */
    public boolean addDeletar() throws IOException{
        
        bw.write("\n// MÉTODOS PARA DELETAR\n ");
        
        for (int x=0; x<modelor.tabelas.size(); x++) {
            
            String nome_metodo = fx.criarNomeMetodo("deletar", modelor.tabelas.get(x).nome,'P');
            
            bw.write("\n     public function "+nome_metodo+"(");
            bw.write("$"+modelor.tabelas.get(x).nome+"){\n");
            
            nome_metodo = fx.criarNomeMetodo("deletar", modelor.tabelas.get(x).nome,'B');
            
            bw.write("\n     $this->bancodedados->"+nome_metodo+"(");
            bw.write("$"+modelor.tabelas.get(x).nome+");\n");
            bw.write("\n     }\n");   
            
        } 
        
        return true;
    }
    
    
    public boolean addAtualizar(){
        
        return true;
    }
    
    
    //-------------------------------------------------------
    //---------- Parte de Arthur (fim)-----------------------
    //-------------------------------------------------------
    

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
