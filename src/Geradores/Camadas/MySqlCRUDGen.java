
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
import java.util.ArrayList;

/**
 * Classe que gera os métodos CRUD do banco de dados
 * @author Arthur, Guto, Renan
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
        arqFachada = new File (caminho_dir+"CRUD/Camadas/bancodedados.php");
        
    }
    
    
    /**
     * Abre o arquivo de banco de dados para escrita
     * @return
     * @throws IOException 
     */
    boolean abrirArquivo() throws IOException{
    
        if (!arqFachada.exists()) {
           arqFachada.createNewFile();  
        }
        
        fw = new FileWriter(arqFachada);
        bw = new BufferedWriter(fw);
        
     
        
        bw.write("<?php\n" +
                "class BancoDeDados {\n" +
                "    private $link;\n" +
                "    private $banco;\n" +
                "    private $usuario;\n" +
                "    private $senha;\n" +
                "    private $hostname;\n\n"
                + "\n"
                + "	function __construct(){\n" +
                "	  \n" +
                "               $this->banco = '"+this.info.banco_nome+"';\n" +
                "               $this->usuario = '"+this.info.banco_usuario+"';\n" +
                "               $this->senha = '"+this.info.banco_senha+"';\n" +
                "               $this->hostname = '"+this.info.banco_servidor+"';\n" +
               // "               $this->link = @mysqli_connect($this->hostname, $this->usuario, $this->senha, $this->banco);\n" +
               // "               if($this->link == false) throw new Exception('Erro de conexão com o banco de dados...');" +
                "	  \n" +
                "	}\n\n"
                + "     function teste(){\n"
                + "         echo\"Eu funciono! :D \";\n"
                + "     }\n");
    
        return true;
        
        
    }
    
    /**
     * Fecha o arquivo criado da camada de banco de dados
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
    
    public boolean addCodigoListar(Tabela tabela) throws IOException{
    
        ArrayList<Coluna> colunas_pk = new ArrayList<Coluna>();
        
        
            
        String nome_metodo = fx.criarNomeMetodo("listar", tabela.nome,'B');
            
        bw.write("\n     function "+nome_metodo+"(");
        bw.write("$str){\n"
                + "\t $link = @mysqli_connect($this->hostname, $this->usuario, $this->senha, $this->banco);\n" +
                  "\t if($link == false) throw new Exception('Erro de conexão com o banco de dados...');\n\n");
            //Aqui começa o código da função
        
        bw.write("\t $sql1 = 'SELECT * FROM " + tabela.nome + " ';\n");
        bw.write("\t $sql1 = $sql1.' where '.$str;\n");
        bw.write("\t $query = $link->query($sql1);\n\n");
            
            
            //Pegando valores normais da tabela
            bw.write("\t //Pegando valores normais da tabela\n"+
                    "\t if ($query->num_rows > 0) {\n" +
                    "\t\t $indice = 0;\n" +
                    "\t\t while($row = $query->fetch_assoc()) {\n" +
                    "\t\t\t $resultado[$indice] = new "+tabela.nome+"();\n");
                    //pegando os valores das colunas da tabela
                    for (int i = 0; i < tabela.colunas.size(); i++) {
                        
                        if(tabela.colunas.get(i).pk == true){
                            colunas_pk.add(tabela.colunas.get(i));
                        }
                        bw.write("\t\t\t $resultado[$indice]->"+tabela.colunas.get(i).nome+" = $row['"+tabela.colunas.get(i).nome+"'];\n");
                
                    }
                    //Pegando os valores das tabelas multivaloradas
                    //String nome_metodo = fx.criarNomeMetodo("listar", modelor.tabelas.get(x).nome,'B');
                    ArrayList<Tabela> tabelas_multi = tabela.acharTabelasMultivaloradas(modelor.tabelas);
                    
                    for (int i = 0; i < tabelas_multi.size(); i++) {
                        for (int j = 0; j < tabelas_multi.get(i).colunas.size(); j++) {
                            if(tabelas_multi.get(i).colunas.get(j).pk == false && tabelas_multi.get(i).colunas.get(j).fk == false){
                                String metodo_chamado = fx.criarNomeMetodo("listar", tabelas_multi.get(i).nome,'B');
                                bw.write("\t\t\t $resultado[$indice]->"+tabelas_multi.get(i).colunas.get(j).nome+" = $this->"+metodo_chamado+"(\" ");
                                // Parametros da pesquisa de valores multivalorados
                                
                                for (int k = 0; k < colunas_pk.size(); k++) {
                                    
                                    bw.write( tabela.nome+"_"+colunas_pk.get(k).nome + " = {$resultado[$indice]->"+colunas_pk.get(k).nome+"}" ); 
                                    
                                    if(k <= colunas_pk.size() - 2){
                                    
                                        bw.write(        " AND "); 
                                    
                                    }
                                }
                                
                                
                                bw.write("\");\n");
                            }
                        }
                    }
                    
            bw.write("" +
                    "\t\t\t $indice++;\n" +
                    "\t\t }\n" +
                    "\t }else{\n" +
                    "\t\t $resultado = null;\n" +
                    "\t }\n" +

                    "\t return $resultado;\n"
                    + "\t mysqli_close($link);\n");
            
            
            //Aqui termina o código da função
            bw.write("\n     }\n");
            
        return true;
        
    }
    
     /**
     * Método que cria o Scrpit PHP das funções de listar da camada de banco de dados
     * @return true se tudo ocorrer bem
     * @throws IOException caso ocorra algum erro
     */
    
    public boolean addListar() throws IOException{
    
        
        bw.write("// MÉTODOS PARA LISTAR\n ");
        
        for (int x = 0; x < modelor.tabelas.size(); x++) {
  
            if(modelor.tabelas.get(x).campo_multi == 1){
                ArrayList<Tabela> tabelas_multi = modelor.tabelas.get(x).acharTabelasMultivaloradas(modelor.tabelas);
                for (Tabela tbm : tabelas_multi) {
                    addCodigoListar(tbm);
                }
            addCodigoListar(modelor.tabelas.get(x));
            }else if(modelor.tabelas.get(x).campo_multi != 2){
                addCodigoListar(modelor.tabelas.get(x));
            }
            
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
     * Método que cria o Scrpit PHP da funções de cadastrar no banco de dados MySql
     * @return retorna valor booleano
     * @throws IOException 
     */
    public boolean addCadastrar() throws IOException{
        
        
        for (int x = 0; x < modelor.tabelas.size(); x++) {
                
            if(modelor.tabelas.get(x).campo_multi != 2){
            
                criaScriptCadastrar(modelor.tabelas.get(x));
                
                }    
            
            
            }
        
        return true;}
    
    /**
     * Método que escreve todo o Scrpit PHP que insere no banco de dados (nele é criado também a conexão com o banco e o encerramento desta conexão).
     * @param tabela é a tabela do banco de dados que os dados serão inseridos
     * @throws IOException 
     */
    public void criaScriptCadastrar(Tabela tabela) throws IOException{
    
        Tabela tb = tiraAutoIncremento(tabela);
        
        
        iniciarMetodo(tb.nome);
        criaConexao();
        bw.write("      $sql = \"INSERT INTO "+tb.nome+"(");
        
        escreveParenteses("", tb, "");
        
        bw.write(" VALUES (");
        
        escreveParenteses2("'$"+tb.nome+"->", tb, "\";\n");
        
        
        bw.write("      $result = mysqli_query($link2,$sql) or die(mysqli_error($link2));\n");
        
        
        if(tb.campo_multi == 1){
        
            Coluna temp_col = new Coluna();
            Coluna PK = new Coluna();
            ArrayList<Tabela> multivaloradas = tb.acharTabelasMultivaloradas(modelor.tabelas);
            
            for (int i = 0; i < tabela.colunas.size(); i++) {
                
                if(tabela.colunas.get(i).pk == true){
                
                    PK = tabela.colunas.get(i);
                    
                }
                
            }
            
            
            for (int x = 0; x < multivaloradas.size(); x++) {
                
                Tabela temp = tiraAutoIncremento(multivaloradas.get(x));
                
                for (int i = 0; i < temp.colunas.size(); i++) {
                    
                    if((temp.colunas.get(i).fk == false)&&(temp.colunas.get(i).pk == false)){
                        
                        temp_col = temp.colunas.get(i);
                        
                        bw.write("\n     foreach($"+tb.nome+"->"+temp_col.nome+
                                " as $"+temp_col.nome+"){\n\n");
                    
                    }
                    
                    
                    
                }// fim do for
                
                
                
                 bw.write("      $sql = \"INSERT INTO "+temp.nome+"(");
                 escreveParenteses("", temp, "");
                 bw.write(" VALUES (");
                 
                 bw.write("'$"+temp_col.nome+"',$"+tb.nome+"->"+PK.nome+")\";");
                 
                 bw.write("\n      $result = mysqli_query($link2,$sql) or die(mysqli_error($link2));\n");
                 
                
                bw.write("\n      }\n");
            }
            
            
                   
        
        }
        
        fechaConexao();
        bw.write("\n     }\n");
    }
    
    /**
     * Método que remove as colunas autoIncremento de uma tabela
     * @param tb é a tabela que srá removida as colunas autoIncremento
     * @return retorna a tabela sem nenhum mautoIncremento
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
    
    
    /**
     * método que escreve os campos entre parenteses que existem no script de inserção de dados numa tabela em SQL
     * @param pre prefixo que será adicionado antes do nome do atributo
     * @param tb  tabela que contem as colunas que serão os atributos
     * @param pos posfixo que será adicionado depois do nome do atributo
     * @throws IOException 
     */
    public void escreveParenteses(String pre,Tabela tb,String pos) throws IOException{
    
            for (int x = 0; x < tb.colunas.size(); x++) {
            
            if(x<tb.colunas.size() - 1){
                
                bw.write(pre+tb.colunas.get(x).nome+",");
            
            }
            
            else{
            
              bw.write(pre+tb.colunas.get(x).nome+")"+pos);
            }
            
            
        }
        
    
    }
    
    
    /**
     * variação do método que escreve os campos entre parenteses que existem no script de inserção de dados numa tabela em SQL
     * @param pre prefixo que será adicionado antes do nome do atributo
     * @param tb  tabela que contem as colunas que serão os atributos
     * @param pos posfixo que será adicionado depois do nome do atributo
     * @throws IOException 
     */
    public void escreveParenteses2(String pre,Tabela tb,String pos) throws IOException{
    
            for (int x = 0; x < tb.colunas.size(); x++) {
            
            if(x<tb.colunas.size() - 1){
                
                bw.write(pre+tb.colunas.get(x).nome+"',");
            
            }
            
            else{
            
              bw.write(pre+tb.colunas.get(x).nome+"')"+pos);
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
    
    
    /**
     * método que escreve o início do método de cadastro em PHP
     * @param nome é o nome da tabela que será inserida
     * @throws IOException 
     */
    public void iniciarMetodo(String nome) throws IOException{
    
            String nome_metodo = fx.criarNomeMetodo("cadastrar",nome,'B');// nome é o nome da tabela
            
            bw.write("\n     public function "+nome_metodo+"(");
            bw.write("$"+nome+"){\n");
    
    
    }
    
    
    
     
     
    //-------------------------------------------------------
     //---------- Parte de Guto (fim)-------------------
     //-------------------------------------------------------
    


    //-------------------------------------------------------
    //---------- Parte de Arthur (inicio)--------------------
    //-------------------------------------------------------
     
    /**
     * método que escreve os métodos de deletar referentes à classe banco de dados
     * @return retorna valor booleano
     * @throws IOException 
     * @author Arthur
     */
    public boolean addDeletar() throws IOException{
        
        bw.write("\n// MÉTODOS PARA DELETAR\n ");
        
        /*bw.write("\n      function condicoes($tabela, $vetor){\n" +
                    "	\n" +
                    "		if (!empty($vetor)){\n" +
                    "\n" +
                    "			$temp = \"\";\n" +
                    "			$array = array();\n" +
                    "			$cond = \"\";			\n" +
                    "\n" +
                    "			for($i=0; $i<strlen($vetor); $i++){\n" +
                    "\n" +
                    "		   		if ($vetor[$i]!=\"=\" && $vetor[$i]!=\",\" && $vetor[$i]!=\"'\" && $vetor[$i]!=\" \"){\n" +
                    "					$temp = $temp . $vetor[$i];	\n" +
                    "				}\n" +
                    "			\n" +
                    "				else if ($vetor[$i]==\"'\"){\n" +
                    "					$temp = $temp . \"\\\"\";\n" +
                    "					$i++;\n" +
                    "\n" +
                    "					while (true){\n" +
                    "						if ($vetor[$i]!=\"'\"){\n" +
                    "						  $temp = $temp . $vetor[$i];\n" +
                    "						  $i++;\n" +
                    "						}\n" +
                    "					\n" +
                    "						else {\n" +
                    "						  $temp = $temp . \"\\\"\";\n" +
                    "						  break;\n" +
                    "						}\n" +
                    "					\n" +
                    "					}\n" +
                    "				}			\n" +
                    "	\n" +
                    "				if ($vetor[$i]==\"=\" || $vetor[$i]==\",\" || $i==strlen($vetor)-1){\n" +
                    "					array_push($array, $temp);\n" +
                    "					$temp=\"\";\n" +
                    "				\n" +
                    "				}\n" +
                    "				\n" +
                    "	    		}\n" +
                    "\n" +
                    "			foreach($array as $i=>$value){\n" +
                    "				if ($i%2==0){\n" +
                    "					$cond = $cond . \" \" . $tabela . \".\" . $value . \"=\";\n" +
                    "				}\n" +
                    "				else{\n" +
                    "					$cond = $cond . $value;\n" +
                    "\n" +
                    "					if ($i<count($array)-1){\n" +
                    "						$cond = $cond . \" and \";\n" +
                    "					}				\n" +
                    "				}\n" +
                    "	\n" +
                    "			}\n" +
                    "		\n" +
                    "		}	\n" +
                    "		return $cond;      \n" +
                    "	}\n"); */
        
        bw.write(   "\n    function chave_primaria($tabela, $tabela_mult, $vetor){\n"
                  + "        $link = @mysqli_connect($this->hostname, $this->usuario, $this->senha, $this->banco);\n" +
                    "        if($link == false) throw new Exception('Erro de conexão com o banco de dados...');\n" +
                    "\n" +
                    "		$sql_consulta = \"SHOW KEYS FROM \".$tabela.\" WHERE Key_name = 'PRIMARY'\";\n" +
                    "		$result = mysqli_query($link, $sql_consulta);\n" +
                    "\n" +
                    "		if (mysqli_num_rows($result) > 0) {\n" +
                    "		   while($row = mysqli_fetch_assoc($result)) {\n" +
                    "\n" +
                    "		           $aux = $row[\"Column_name\"];\n" +
                    "			     $temp = $tabela_mult.\".\".$tabela.\"_\".$aux; \n" +
                    "\n" +
                    "			     $sql_consulta2 = \"SELECT \".$tabela.\".\".$aux.\" FROM \".$tabela.\" WHERE \".$vetor;\n" +
                    "	\n" +
                    "			     $result2 = mysqli_query($link, $sql_consulta2);\n" +
                    "\n" +
                    "				if (mysqli_num_rows($result2) > 0) {\n" +
                    "		   		  while($row2 = mysqli_fetch_assoc($result2)) {	\n" +
                    "					\n" +
                    "					$key = $row2[$aux];	\n" +
                    "				  }\n" +
                    "				}\n" +
                    "		    }\n" +
                    "		}\n" +
                    "\n" +
                    "		return $key;\n" +
                    "	}\n\n");
        
        
        for (int x=0; x<modelor.tabelas.size(); x++) {
            
            int tab=0;
            String metodoR = fx.criarNomeMetodo("deletar",modelor.tabelas.get(x).nome,'B');
            
            bw.write("\n     public function "+metodoR+"(");
            bw.write("$"+modelor.tabelas.get(x).nome+"){\n\n"
                    + ""
                    + "      $link = @mysqli_connect($this->hostname, $this->usuario, $this->senha, $this->banco);\n" +
                      "      if($link == false) throw new Exception('Erro de conexão com o banco de dados...');\n\n");
                
                if (modelor.tabelas.get(x).campo_multi == 1){
                    
                    //bw.write("      $cond = $this->condicoes(\""+modelor.tabelas.get(x).nome+"\", "+"$"+modelor.tabelas.get(x).nome+");\n\n");
                    bw.write("      $cond =  str_replace(\",\",\" and "+modelor.tabelas.get(x).nome+".\", $"+modelor.tabelas.get(x).nome+");\n"
                           + "      $cond = \""+modelor.tabelas.get(x).nome+".\" . $cond; \n\n");
                    
                    for (Tabela tabelas : modelor.tabelas) {
                        if (tabelas.campo_multi==2){
                            
                            for (Coluna colunas : tabelas.colunas) {
                                if (colunas.fk && colunas.fk_nome_tabela.equals(modelor.tabelas.get(x).nome)){
                                    bw.write("      $key = $this->chave_primaria(\""+modelor.tabelas.get(x).nome+"\", \""+tabelas.nome+"\", "+"$cond);\n");
                                    bw.write("      $sql_deleta"+tab+"=(\"DELETE FROM "+tabelas.nome+" WHERE "+tabelas.nome+"."+colunas.nome+" = $key\");\n\n");
                                    tab++;
                                }
                            }   
                        }
                    }
                }
                
            bw.write("      $sql_deleta"+tab+"=(\"DELETE FROM "+modelor.tabelas.get(x).nome+" WHERE $cond\");\n");         
                
            for (int i=0; i<=tab; i++){
                    
                bw.write("\n      mysqli_query($link, $sql_deleta"+i+");");   
            }    
            
            bw.write("\n      mysqli_close($link);\n");
            bw.write("      }\n");
        }
        
        return true;
    }

     /**
     * Método que escreve no arquivo bancodedados.php os métodos de atualizar referentes a camada banco de dados
     * @return retorna valor booleano
     * @throws IOException 
     */
    
    public boolean addAtualizar() throws IOException{
        
         bw.write("\n// MÉTODOS PARA ATUALIZAR\n ");
        
        for (int x=0; x<modelor.tabelas.size(); x++) {
            
            int tab=0;
            String metodoR = fx.criarNomeMetodo("atualizar",modelor.tabelas.get(x).nome,'B');
            
            bw.write("\n     public function "+metodoR+"($str, $obj){\n\n"
                    + "\n"
                    + "\t $link = @mysqli_connect($this->hostname, $this->usuario, $this->senha, $this->banco);\n" +
                      "\t if($link == false) throw new Exception('Erro de conexão com o banco de dados...');\n");
            
            //criando string dos valores normais
            
            bw.write("          $sql = 'UPDATE "+modelor.tabelas.get(x).nome+" set ';\n" +
                    "		//montando string para atualização dos atributos normais\n" +
                    "		$propriedades = get_object_vars($obj);\n" +
                    "		$index = 0;\n" +
                    "		$index_max = count($propriedades);\n" +
                    "		$check = false;\n" +
                    "		foreach($propriedades  as $key => $val){\n" +
                    "			\n" +
                    "			if(($val != null) && (is_array($val) == false)){\n" +
                    "				\n" +
                    "				if(($check == true) && ($index < $index_max)) $sql = $sql . ', ';\n" +
                    "				$sql = $sql . \"$key = '$val'\";\n" +
                    "				$check = true;\n" +
                    "			}\n" +
                    "			$index++;\n" +
                    "		}\n" +
                    "	 \n" +
                    "		$sql = $sql . \" where \" . $str;\n" +
                    "		if ($link->query($sql) === TRUE) {\n"
                  + "                   mysqli_close($link);" +
                    "			return true;\n" +
                    "		} else {\n"
                  + "                   mysqli_close($link);\n" +
                    "			return false;\n" +
                    "		}\n" +
                    "		;\n"
                    + "");
            
           bw.write("\n\n   }");
            
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

    
