
package Geradores.Camadas;

import Auxiliares.FixString;
import ModeloRel.Coluna;
import ModeloRel.ModeloR;
import ModeloRel.Tabela;
import ProjetoInfo.ProjetoInfo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe que gerar os métodos referentes a cama regra de negócio
 * @author Arthur, Guto, Renan
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
        arq = new File (caminho_dir+"CRUD/Camadas/regranegocio.php");
        
    }
    
    
    /**
     * Abre o arquivo de regra de negócio para escrita
     * @return
     * @throws IOException 
     */
    boolean abrirArquivo() throws IOException{
    
        if (!arq.exists()) {
           arq.createNewFile();  
        }
        
        fw = new FileWriter(arq);
        bw = new BufferedWriter(fw);
        
     
        
        bw.write("<?php\n" +
                "include 'persistencia.php';\n"+
                "class RegraDeNegocio {\n" +
                "     var $persistencia;\n" +
                "	 \n" +
                "	 function __construct(){\n" +
                "		$this->persistencia = new Persistencia();\n" +
                "	 }\n" +
                "	 \n");
    
        return true;
        
        
    }
    
    /**
     * Fecha o arquivo criado da camada de regra de negócios
     * @return
     * @throws IOException 
     */
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
    /**
     *  Método que cria o Scrpit PHP da funções de listar da camada regra de negócio
     * @return true se a operação foi concluída com sucesso
     * @throws IOException 
     */
    public boolean addListar() throws IOException{
        
        
        for (int x = 0; x < modelor.tabelas.size(); x++) {
            
            String nome_metodo = fx.criarNomeMetodo("listar", modelor.tabelas.get(x).nome,'R');
            
            bw.write("\n     public function "+nome_metodo+"($str){\n");
            
            nome_metodo = fx.criarNomeMetodo("listar", modelor.tabelas.get(x).nome,'P');
            bw.write("\n     $str_tratada =  str_replace(\",\",\" and \",$str);\n");
            bw.write("\n     $resultado = $this->persistencia->"+nome_metodo+"($str_tratada);\n");
            
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
     * Método que cria o Scrpit PHP da funções de cadastrar da camada regra de negócio
     * @return retorna valor booleano
     * @throws IOException 
     */
    
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
    
    
     /**
     * método que escreve o início do método de cadastro em PHP
     * @param nome é o nome da tabela que será inserida
     * @throws IOException 
    */
    public void iniciarMetodo(String nome) throws IOException{
    
            String nome_metodo = fx.criarNomeMetodo("cadastrar",nome,'R');
            
            bw.write("\n     public function "+nome_metodo+"(");
            bw.write("$"+nome+"){\n");
    
    
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
    
    
    //-------------------------------------------------------
     //---------- Parte de Guto (fim)-------------------
     //-------------------------------------------------------
    


    //-------------------------------------------------------
    //---------- Parte de Arthur (inicio)--------------------
    //-------------------------------------------------------
    
    /**
     * Método que escreve no arquivo regranegocio.php os métodos de deletar referentes a camada regra de negócio
     * @return retorna valor booleano
     * @throws IOException 
     * @author Arthur
     */
    public boolean addDeletar() throws IOException{
        
        bw.write("\n// MÉTODOS PARA DELETAR\n ");
        
        for (int x=0; x<modelor.tabelas.size(); x++) {
            
            String metodoR = fx.criarNomeMetodo("deletar",modelor.tabelas.get(x).nome,'R');
            
            bw.write("\n     public function "+metodoR+"(");
            bw.write("$"+modelor.tabelas.get(x).nome+"){\n");
            
            
            String listar = fx.criarNomeMetodo("listar", modelor.tabelas.get(x).nome,'R');
            
            bw.write("\n     $resultado = $this->"+listar+"(");
            bw.write("$"+modelor.tabelas.get(x).nome+");\n");
            
            bw.write("\n     if($resultado!=null){\n");
            
            bw.write("\n      try{");
            
                
            String nome_metodo = fx.criarNomeMetodo("deletar", modelor.tabelas.get(x).nome,'P');
            
            bw.write("\n      $this->persistencia->"+nome_metodo+"(");
            bw.write("$"+modelor.tabelas.get(x).nome+");\n");
                
                
            bw.write("\n      } catch (PDOException $e){    }\n\n");
            bw.write("      }\n");
            bw.write("    }\n");
        }
        
        return true;
    }

    /**
     * Método que escreve no arquivo regranegocio.php os métodos de atualizar referentes a camada regra de negócio
     * @return retorna valor booleano
     * @throws IOException 
     * @author Arthur
     */
    public boolean addAtualizar() throws IOException{
        
        
        for (int x = 0; x < modelor.tabelas.size(); x++) {
            
            String nome_metodo = fx.criarNomeMetodo("atualizar", modelor.tabelas.get(x).nome,'R');
            
            bw.write("\n     public function "+nome_metodo+"($str, $obj){");
            
            nome_metodo = fx.criarNomeMetodo("atualizar", modelor.tabelas.get(x).nome,'P');
            bw.write("\n     $str_tratada =  str_replace(\",\",\" and \",$str);\n");
            bw.write("\n     $resultado = $this->persistencia->"+nome_metodo+"($str_tratada, $obj);");
            
            bw.write("\n     return $resultado;\n");
            bw.write("\n     }\n");
            
            
        }
        
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
