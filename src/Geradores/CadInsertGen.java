
package Geradores;

import ModeloRel.Coluna;
import ModeloRel.ModeloR;
import ModeloRel.Tabela;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Renan
 */
public class CadInsertGen {
    
    ModeloR modelor;
    String caminho_dir;

    public CadInsertGen(ModeloR modelor, String caminho_dir) {
        this.modelor = modelor;
        this.caminho_dir = caminho_dir;
    }
    
    /**
     * Gerador do código PHP que irá inserir dados no banco de dados.
     * @param O nome do banco de dados com qual o programa irá trabalhar
     * @throws IOException 
     */
    
     public void gerarCadInserir(String nome_banco) throws IOException{
    
        ArrayList<Integer> indices = new ArrayList<Integer>();
        
        for (int x = 0; x < modelor.tabelas.size(); x++) {
           if(modelor.tabelas.get(x).campo_multi != 2){
           Tabela tab = modelor.tabelas.get(x);
           
        File arquivo = new File (caminho_dir+"cadastro/inserts/inserir_"+tab.nome+".php");
        
        if (!arquivo.exists()) {
          
          arquivo.createNewFile();  
        }
        
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
           
           
           
            if(tab.campo_multi == 0 || tab.campo_multi == 1 ){
           
	        
                gerarConexao(bw, nome_banco);
                
                
                for (int i = 0; i < tab.colunas.size(); i++) {
                    
                    Coluna col = tab.colunas.get(i);
                    
                    bw.newLine();
                    
                    if(col.auto_inc == false){
                    bw.write("   $"+col.nome+" = $_POST['"+col.nome+"'];");
                    bw.newLine();}
                }//fim do for que cria o script php pra inserir no banco
                
                bw.newLine();
                bw.newLine();
                bw.write("  $sql1 = \"INSERT INTO "+tab.nome+" (");// esse \" é pra poder imprimir as aspas duplas
                
                for (int i = 0; i < tab.colunas.size(); i++) {
                    
                    Coluna col = tab.colunas.get(i);
                    
                    if(i < tab.colunas.size()-1){
                        bw.write(col.nome+",");
                    }
                    else{bw.write(col.nome);}
                    
                }
                
                bw.write(")\";");
                
                bw.newLine();
                bw.write("  $sql2 = \"VALUES(\";");// esse \" é pra poder imprimir as aspas duplas //Editado por Renan... PERIGO
                
                for (int i = 0; i < tab.colunas.size(); i++) {
                    
                    Coluna col = tab.colunas.get(i);
                    char separador;
                    if(i < tab.colunas.size()-1){separador = ',';}else{separador = ')'; }
                    
                    
                    if(col.auto_inc == false){
                            bw.write("\n  if($"+col.nome+"!= ''){  $sql2 = $sql2.'\"'.$"+col.nome+".'\""+separador+"'; }else{ $sql2 = $sql2.'NULL"+separador+"'; }");
                    }else{
                        bw.write("\n  $sql2 = $sql2.'NULL"+separador+"';");
                    }
                       
                    
                    
                }
             
                bw.newLine();
                bw.newLine();
                bw.write("  $result = mysqli_query($link2,$sql1.$sql2) or die(mysqli_error($link2)); ");
                
                
                
            }//fim do primeiro if que verifica se é multivalorado ou não
           
            if(tab.campo_multi == 1){ 
                
                for (int i = 0; i < modelor.tabelas.size(); i++) {
                    
                    if(modelor.tabelas.get(i).campo_multi == 2){
                        
                        for (int j = 0; j < this.modelor.tabelas.get(i).colunas.size(); j++) {
                            
                            if(this.modelor.tabelas.get(i).colunas.get(j).fk == true){
                            
                                if(tab.nome.equals(modelor.tabelas.get(i).colunas.get(j).fk_nome_tabela)){
                                        
                                    indices.add(i);// pegando o indice daquela tabela
                                    
                                }
                                
                            }
                            
                            
                        }
                    
                    
                    }
                    
                    //php
                
                    for (int z = 0; z < indices.size(); z++) {
                        
                        bw.newLine();
                        bw.newLine();
                        for (int j = 0; j < modelor.tabelas.get(indices.get(z)).colunas.size(); j++) {
                           
                            if(modelor.tabelas.get(indices.get(z)).colunas.get(j).fk == true){
                            
                                String fk_nome_tabela = modelor.tabelas.get(indices.get(z)).colunas.get(j).fk_nome_tabela;
                            String fk_nome_coluna = modelor.tabelas.get(indices.get(z)).colunas.get(j).fk_nome_coluna;
                               
                                if(modelor.tabelas.get(modelor.idTabela(fk_nome_tabela)).colunas.get(modelor.idColuna(fk_nome_coluna, fk_nome_tabela)).auto_inc == true){ //Ok, isso foi uma obra de arte. Tô só checando se o campo dessa tabela multivalorada é gerada de um campo com auto_incremento
                                    String nomeCol = modelor.tabelas.get(indices.get(z)).colunas.get(j).nome;
                                    String nomeTab = modelor.tabelas.get(indices.get(z)).nome;
                                    bw.write("$sqlConsulta = \"SELECT "+ fk_nome_coluna +" from "+ fk_nome_tabela +" ORDER BY "+ fk_nome_coluna +" DESC LIMIT 1\";\n" +
                                    "	   $result = mysqli_query($link2, $sqlConsulta) or die(mysqli_error($link2)); ; //consulta\n" +
                                    "	   if (mysqli_num_rows($result) > 0) {\n" +
                                    "		while($row = mysqli_fetch_assoc($result)) { $"+fk_nome_coluna+" = $row[\""+fk_nome_coluna+"\"]; }\n" +
                                    "	   }");

                                }
                            }
                            if(modelor.tabelas.get(indices.get(z)).colunas.get(j).fk == false){
                             bw.write("   $"+modelor.tabelas.get(indices.get(z)).colunas.get(j).nome+" = $_POST['"+modelor.tabelas.get(indices.get(z)).colunas.get(j).nome+"'];\n");
                           
                             bw.write("   foreach($"+modelor.tabelas.get(indices.get(z)).colunas.get(j).nome+" as $item){");// TÁ MEIO BICHADO ESSE ITEM AÍ
                             bw.newLine();
                             bw.write("    $sql = \"INSERT INTO "+modelor.tabelas.get(indices.get(z)).nome+"(");


                            for (int o = 0; o < modelor.tabelas.get(indices.get(z)).colunas.size(); o++) {

                                if(modelor.tabelas.get(indices.get(z)).colunas.get(o).auto_inc == false){


                                if(o < modelor.tabelas.get(indices.get(z)).colunas.size() -1){
                                    //bw.write(modelor.tabelas.get(indices.get(z)).colunas.get(o).nome+",");
                                    escreverColuna(bw,modelor.tabelas.get(indices.get(z)).colunas.get(o).nome+",");
                                }

                                else{escreverColuna(bw,modelor.tabelas.get(indices.get(z)).colunas.get(o).nome+") VALUES (\"; \n");}


                                }


                            }
                           
                             
                           } 
                        }
                        
                        
                        
                        
                        //bw.write("VALUES (");
                       
                                
                                for (int j = 0; j < modelor.tabelas.get(indices.get(z)).colunas.size(); j++) {
                                   
                                    if(modelor.tabelas.get(indices.get(z)).colunas.get(j).fk == false){
                                     
                                       // if(j < modelor.tabelas.get(indices.get(z)).colunas.size() - 2){
                                    
                                        
                                            //ANTIGO:  bw.write("'$"+modelor.tabelas.get(indices.get(z)).colunas.get(j).nome+"[]',");
                                          //MENOS ANTIGO, MAS AINDA ASSIM ANTIGO bw.write("'$item',");
                                            bw.write("  if($item != ''){$sql = $sql.'\"'.$item.'\",';}else{$sql = $sql.'NULL,';}\n");
                   
                                        //}
                                        
                                        //else{
                                        
                                          //  bw.write("'$"+modelor.tabelas.get(indices.get(z)).colunas.get(j).nome+"[]')\";");
                                        
                                        //}
                                     
                                    }
                                    
                                    
                                }
                       int numPk=0;        
                        //Esse for é só pra saber o numero de PK        
                       for (int y = 0; y < tab.colunas.size(); y++) { 
                            
                                if(tab.colunas.get(y).pk == true){
                                    numPk++;
                                }
                                
                       }         
                       int countPk = 0;
                       for (int y = 0; y < tab.colunas.size(); y++) { 
                            
                                if(tab.colunas.get(y).pk == true){
                                
                                    //if(y < tab.colunas.size() - 1){
                                        bw.write("\n  if($"+tab.colunas.get(y).nome+"!= ''){  $sql = $sql.'\"'.$"+tab.colunas.get(y).nome+".'\"'; }else{ $sql = $sql.'NULL'; }\n");
                                        if(countPk < numPk-1){
                                            bw.write("$sql = $sql.',';\n");
                                            
                                        }else if(countPk == numPk-1){
                                            bw.write("$sql = $sql.')';\n");
                                      
                                        }
                                        countPk++;
                                    //IMPRIMINDO A CHAVE PRIMÁRIA DA TABELA A QUAL ESSE ATRIBUTO MULTIVALORADO PERTENCE
                                
                                }
                                
                       }
                           
                            ////// coloca aqui
                        
                       bw.newLine();
                       bw.newLine();
                      
                       bw.write("    $result = mysqli_query($link2,$sql) or die(mysqli_error($link2)); ;");
                       bw.newLine();
                       bw.write("    }");
                       bw.newLine();
                       
                    }
                
                
                }
                
                
            }// achando uma tabela que tem multivalorado
                       bw.newLine();
                       bw.write("?>");
                       
            
            if(tab.campo_multi != 2){
                
                bw.close();
                fw.close();
            }
         
                
        }// fim do for que criar os arquivos das tabelas
        
        }
    }//fim do método de gerar o código em PHP
    
	
	/**
         * Método que escreve no arquivo o script que conect com o banco de dados.
         * @param construtor que recebe como argumento o objeto do tipo FileWriter
         * @param string com o nome do banco de dados.
         * @throws IOException 
         */
	public void gerarConexao(BufferedWriter bw, String nome_banco) throws IOException{
    
        bw.write("<?php");
                bw.newLine();
                bw.newLine();
                
                bw.write("  $banco = \""+nome_banco+"\";");
                bw.newLine();
                bw.write("  $usuario = \"root\";");
                bw.newLine();
                bw.write("  $senha = \"\";");
                bw.newLine();
                bw.write("  $hostname = \"localhost\";");
                bw.newLine();
                
                bw.write("  $link2 = mysqli_connect($hostname, $usuario, $senha, $banco) or die (\"Erro ao conectar!<br>\");");
                bw.newLine();
                
    
    }
        
        /**
         * Médoto para escrever o nome de uma coluna em um arquivo. 
         * @param construtor que recebe como argumento o objeto do tipo FileWriter
         * @param string que será escrita (no caso, o nome da coluna) 
         */
        public void escreverColuna(BufferedWriter bw,String s){
            
        try {
            bw.write(s);
        } catch (IOException ex) {
            Logger.getLogger(CadInsertGen.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    
}
