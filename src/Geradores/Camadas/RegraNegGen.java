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
public class RegraNegGen {
    
    ModeloR modelor;
    String caminho_dir;
    ProjetoInfo info;
    //Arquivo pra escrever a fachada
    File arq;
    FileWriter fw;
    BufferedWriter bw;
    FixString fx = new FixString();
    
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
    
    public boolean addListar() throws IOException{
        
        
        for (int x = 0; x < modelor.tabelas.size(); x++) {
            
            String nome_metodo = fx.criarNomeMetodo("listar", modelor.tabelas.get(x).nome,'R');
            
            bw.write("\n     public function "+nome_metodo+"(");
            bw.write("$"+modelor.tabelas.get(x).nome+"){\n");
            
            nome_metodo = fx.criarNomeMetodo("listar", modelor.tabelas.get(x).nome,'P');
            
            bw.write("\n     $this->persistencia->"+nome_metodo+"(");
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
        int indice_coluna_pk = 0;
        
        for (int x = 0; x < modelor.tabelas.size(); x++) {
            
            
            iniciarMetodo(modelor.tabelas.get(x).nome);
            
            
            
            if(modelor.tabelas.get(x).campo_multi == 1){
            
                for (int i = 0; i < modelor.tabelas.get(x).colunas.size(); i++) {
                    
                    if(modelor.tabelas.get(x).colunas.get(i).pk == true){
                    
                        indice_coluna_pk = i;
                        break;
                    
                    }
                    
                }
                
            criaConexao();
            bw.write("      $sql_consulta = \"SELECT "+modelor.tabelas.get(x).colunas.get(indice_coluna_pk).nome+
                    " FROM "+modelor.tabelas.get(x).nome+" ORDER BY "+modelor.tabelas.get(x).colunas.get(indice_coluna_pk).nome+
                    " DESC LIMIT 1\";\n");
            
            bw.write("\n      $result = mysqli_query($link2, $sql_consulta) or die(mysqli_error($link2));\n");
            
            bw.write("\n      if (mysqli_num_rows($result) > 0) {\n");
            bw.write("         while($row = mysqli_fetch_assoc($result)) {\n\n");
            bw.write("                $"+modelor.tabelas.get(x).nome+"->"+modelor.tabelas.get(x).colunas.get(indice_coluna_pk).nome+
                    " = $row[\""+modelor.tabelas.get(x).colunas.get(indice_coluna_pk).nome+"\"];\n");
            bw.write("          }\n      }\n");
            
            bw.write("\n      $"+modelor.tabelas.get(x).nome+"->"+modelor.tabelas.get(x).colunas.get(indice_coluna_pk).nome+
                    " = "+"$"+modelor.tabelas.get(x).nome+"->"+modelor.tabelas.get(x).colunas.get(indice_coluna_pk).nome+" + 1;\n");
            
            /*bw.write("      $"+modelor.tabelas.get(x).nome+"->"+modelor.tabelas.get(x).colunas.get(indice_coluna_pk).nome+
                    " = $result+1;\n\n");*/
            
            
            fechaConexao();
            
           
            }// fim do if
            
            
            
            String nome_metodo = fx.criarNomeMetodo("cadastrar", modelor.tabelas.get(x).nome,'P');
            
            bw.write("\n      $this->persistencia->"+nome_metodo+"(");
            bw.write("$"+modelor.tabelas.get(x).nome+");\n");
            bw.write("\n     }\n");
            
            
            
            
            
            
        }
        
        
        return true;}
    
    
    public void iniciarMetodo(String nome) throws IOException{
    
            String nome_metodo = fx.criarNomeMetodo("cadastrar",nome,'R');
            
            bw.write("\n     public function "+nome_metodo+"(");
            bw.write("$"+nome+"){\n");
    
    
    }
    
    
    
    public void criaConexao() throws IOException{
    
        bw.write("\n\n      $banco = \""+info.banco_nome+"\";\n");
        bw.write("      $usuario = \""+info.banco_usuario+"\";\n");
        bw.write("      $senha = \""+info.banco_senha+"\";\n");
        bw.write("      $hostname = \""+info.banco_servidor+"\";\n");
        bw.write("      $link2 = mysqli_connect($hostname, $usuario, $senha, $banco) or die (\"Erro ao conectar!<br>\");\n\n");
                
    
    }
    
    public void fechaConexao() throws IOException{
    
        bw.write("\n      mysqli_close($link2);\n");
    
    }
    
    
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
