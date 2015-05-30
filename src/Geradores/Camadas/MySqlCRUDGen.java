
package Geradores.Camadas;

import Auxiliares.FixString;
import Geradores.BancoGen;
import ModeloRel.ModeloR;
import ModeloRel.Tabela;
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
public class MySqlCRUDGen {
    
    ModeloR modelor;
    String caminho_dir;
    ProjetoInfo info;
    //Arquivo pra escrever a fachada
    File arqFachada;
    FileWriter fw;
    BufferedWriter bw;
    FixString fx = new FixString();
    
    public MySqlCRUDGen(ModeloR modelor, String caminho_dir, ProjetoInfo info) {
        this.modelor = modelor;
        this.caminho_dir = caminho_dir;
        this.info = info;
        arqFachada = new File (caminho_dir+"CRUD/camadas/bancodedados.php");
        
    }
    
    
    
    boolean abrirArquivo() throws IOException{
    
        if (!arqFachada.exists()) {
           arqFachada.createNewFile();  
        }
        
        fw = new FileWriter(arqFachada);
        bw = new BufferedWriter(fw);
        
     
        
        bw.write("<?php\n" +
                 "class BancoDeDados {\n"
                + "function teste(){\n"
                + "     echo\"Eu funciono! :D \";\n"
                + "}\n");
    
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
    
    public boolean addCadastrar() throws IOException{
        
        
        for (int x = 0; x < modelor.tabelas.size(); x++) {
                
            if(modelor.tabelas.get(x).campo_multi != 2){
            
                criaScriptCadatrar(modelor.tabelas.get(x));
                
                }    
            
            
            }
            
            
            
        
        return true;}
    
    
    public void criaScriptCadatrar(Tabela tabela) throws IOException{
    
        Tabela tb = tiraAutoIncremento(tabela);
        
        
        iniciarMetodo(tb.nome);
        criaConexao();
        bw.write("      $sql = \"INSERT INTO "+tb.nome+"(");
        
        escreveParenteses("", tb, "");
        
        bw.write("VALUES (");
        
    }
    
    /**
     * Método que remove as colunas autoIncremento de uma tabela
     * @param tb    é a tabela que srá removida as colunas autoIncremento
     * @return  retorna a tabela sem nenhum mautoIncremento
     */
    public Tabela tiraAutoIncremento(Tabela tb){
    
    Tabela temp = new Tabela();
    
    
        temp.campo_multi = tb.campo_multi;
        temp.nome = tb.nome;
    
        for (int x = 0; x < tb.colunas.size(); x++) {
            
            if(tb.colunas.get(x).auto_inc != true){
                
                temp.colunas.add(tb.colunas.get(x));
            
            }
            
            
        }
    
    
    
    
    
    
    return temp;
    }
    
    public void escreveParenteses(String pre,Tabela tb,String pos) throws IOException{
    
            for (int x = 0; x < tb.colunas.size(); x++) {
            
            if(x<tb.colunas.size() - 1){
                
                bw.write(pre+tb.colunas.get(x).nome+",");
            
            }
            
            else{
            
              bw.write(tb.colunas.get(x).nome+")"+pos);
            }
            
            
        }
        
    
    }
    
    
    /**
     * Método que escreve em um arquivo um scrpit em PHP que conecta com o banco de dados
     * @throws IOException 
     */
    public void criaConexao() throws IOException{
    
        bw.write("\n\n      $banco = \""+info.banco_nome+"\";\n");
        bw.write("      $usuario = \""+info.banco_usuario+"\";\n");
        bw.write("      $senha = \""+info.banco_senha+"\";\n");
        bw.write("      $hostname = \""+info.banco_servidor+"\";\n");
        bw.write("      $link2 = mysqli_connect($hostname, $usuario, $senha, $banco) or die (\"Erro ao conectar!<br>\");\n\n");
                
    
    }
    
    /**
     * Método que escreve em um arquivo um scrpit em PHP que fecha a conexão com o banco de dados
     * @throws IOException 
     */
    
    public void fechaConexao() throws IOException{
    
        bw.write("\n      mysqli_close($link2);\n");
    
    }
    
     public void iniciarMetodo(String nome) throws IOException{
    
            String nome_metodo = fx.criarNomeMetodo("cadastrar",nome,'R');// nome é o nome da tabela
            
            bw.write("\n     public function "+nome_metodo+"(");
            bw.write("$"+nome+"){\n");
    
    
    }
    
    
    public void inserTabelasNormais(Tabela tb) throws IOException{
    
        for (int x = 0; x < tb.colunas.size(); x++) {
            
            
            
            
        }
    
        bw.write("VALUES \"\n");
        
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

    
