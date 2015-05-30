
package Geradores.Camadas;

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
 * @author Guto Leoni
 */
public class ClassEntGen {
    
    ModeloR modelo;
    String diretorio;
    ProjetoInfo projeto;

    public ClassEntGen(ModeloR modelo, String diretorio, ProjetoInfo projeto) {
        this.modelo = modelo;
        this.diretorio = diretorio;
        this.projeto = projeto;
    }
    
    public void gerar() throws IOException{
    
        gerarClassesEntidades();
       // gerarCamadas();
    
    
    }
    
    
    public void gerarClassesEntidades() throws IOException{// Fiquei em dúvida se a essa string como parametro é necessária, na dúvida deixei =]
    
        for (int x = 0; x < modelo.tabelas.size(); x++) {
         
            if(modelo.tabelas.get(x).campo_multi != 2){
            
            File arquivo = new File (diretorio+"CRUD/Entidades/"+modelo.tabelas.get(x).nome+".php");
            
            
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
        criarConstrutor(modelo.tabelas.get(x), bw);
            
        bw.write("\n\n  }\n?>");
        bw.close();
            }// fim do if de checar se é multivalorado
            
            
        }
    
    
    }// fim do método de gerar as classes das entidades
    
    // ---->>  ATENÇÃO!!! <<----- 
    //O código abaixo não será mais usados por enquanto
    public void gerarCamadas() throws IOException{
        
        ArrayList <File> camadas = new ArrayList<File>();
        
        String caminho = diretorio+"CRUD/Camadas/";
        File fachada = new File(caminho+"Fachada.php");
        File Regra = new File(caminho+"RegraNegocio.php");
        File Persistencia = new File (caminho+"Persistencia.php");
        
        camadas.add(fachada);
        camadas.add(Regra);
        camadas.add(Persistencia);
        
            for(int x = 0; x < 3; x++) {
                
                if(!camadas.get(x).exists()){
                
                    camadas.get(x).createNewFile();
                    
                }
            
            }
        
        gerarCodigoFachada(camadas.get(0));
        gerarCodigoRegraNegocio(camadas.get(1));
        gerarCodigoPersistencia(camadas.get(2));
        
    
    }
    
   
    
    public void gerarCodigoFachada(File arquivo) throws IOException{
        
    FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        String camadaPos = new String("regra_negocio");
        
        
        gerarInicioDaClasse(bw, arquivo);
        bw.write("\n   $"+camadaPos+" = new RegraNegocio();\n");
        
        bw.write("\n//MÉTODOS CADASTRAR");
        gerarMetodosCRUD("cadastrar",camadaPos, bw);// nome da função do CRUD, nome da camada que será chamada, BufferedWriter
        bw.write("\n//MÉTODOS LISTAR");
        gerarMetodosCRUD("listar",camadaPos, bw);
        bw.write("\n//MÉTODOS ATUALIZAR");
        gerarMetodosCRUD("atualizar",camadaPos, bw);
        bw.write("\n//MÉTODOS DELETAR");
        gerarMetodosCRUD("deletar",camadaPos, bw);
        
        bw.write("\n\n  }\n?>");
        bw.close();
        
    }
    
    
    public void gerarCodigoRegraNegocio(File arquivo) throws IOException{
    
        
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        String camadaPos = new String("persistencia");
        
        
        gerarInicioDaClasse(bw, arquivo);
        bw.write("\n   $"+camadaPos+" = new Persistencia();\n");
        
        bw.write("\n//MÉTODOS CADASTRAR");
        gerarMetodosCRUD("cadastrar",camadaPos, bw);// nome da função do CRUD, nome da camada que será chamada, BufferedWriter
        bw.write("\n//MÉTODOS LISTAR");
        gerarMetodosCRUD("listar",camadaPos, bw);
        bw.write("\n//MÉTODOS ATUALIZAR");
        gerarMetodosCRUD("atualizar",camadaPos, bw);
        bw.write("\n//MÉTODOS DELETAR");
        gerarMetodosCRUD("deletar",camadaPos, bw);
        
        bw.write("\n\n  }\n?>");
        bw.close();
        
        
    }
    
    
    public void gerarCodigoPersistencia(File arquivo) throws IOException{
    
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        String camadaPos = new String("banco");
        
        
        gerarInicioDaClasse(bw, arquivo);
        bw.write("\n   $"+camadaPos+" = new Banco();\n");
        
        bw.write("\n//MÉTODOS CADASTRAR");
        gerarMetodosCRUD("cadastrar",camadaPos, bw);// nome da função do CRUD, nome da camada que será chamada, BufferedWriter
        bw.write("\n//MÉTODOS LISTAR");
        gerarMetodosCRUD("listar",camadaPos, bw);
        bw.write("\n//MÉTODOS ATUALIZAR");
        gerarMetodosCRUD("atualizar",camadaPos, bw);
        bw.write("\n//MÉTODOS DELETAR");
        gerarMetodosCRUD("deletar",camadaPos, bw);
        
        bw.write("\n\n  }\n?>");
        bw.close();
        
        
        
    }
    
    
    
    
    public void gerarInicioDaClasse(BufferedWriter bw,File arquivo) throws IOException{
    
        
        bw.write("<?php\n ");
        bw.write(" Class "+arquivo.getName().substring(0,arquivo.getName().length()-4)+"{\n");
    
    
    }
    
    
    public void criarConstrutor(Tabela tabela,BufferedWriter bw) throws IOException{
    
        
        bw.write("\n     fuction __construct(");
        
        for (int x = 0; x < tabela.colunas.size(); x++) {
            
            
            if(x < tabela.colunas.size()-1){
                
                bw.write("$"+tabela.colunas.get(x).nome+",");
                
            }
            else{
            
               bw.write("$"+tabela.colunas.get(x).nome+"){\n"); 
            
            }
            
            
        }
    
        
        for (int x = 0; x < tabela.colunas.size(); x++) {
        
            bw.write("\n     $this->"+tabela.colunas.get(x).nome+" = $"+tabela.colunas.get(x).nome+";\n");
        
        
        }
        
        bw.write("\n     }");
        
    
    }//fim do metodo criar construtores
    
    public void gerarMetodosCRUD(String funcao,String camada,BufferedWriter bw) throws IOException{
    
        for (int x = 0; x < modelo.tabelas.size(); x++) {
         
            bw.write("\n   public fuction "+funcao+"_"+modelo.tabelas.get(x).nome+"($"+modelo.tabelas.get(x).nome+"){\n");
            bw.write("\n     $"+camada+"->"+funcao+"_"+modelo.tabelas.get(x).nome+"($"+modelo.tabelas.get(x).nome+");\n\n");
            bw.write("   }\n");
    
        }
    
    }
    
    
    /*
    public void gerarCodigoCamadas(File arquivo) throws IOException{// fiquei muito em dúvida se eu podia fazer esse método, ou se teria um método pra gerar o código de cada camada
    
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        
        
        bw.write("<?php\n ");
        bw.write(" Class "+arquivo.getName().substring(0,arquivo.getName().length()-4)+"{\n");
        
        
        
        for (int x = 0; x < modelo.tabelas.size(); x++) {
            
            //coloquei um "_" pra separar por que as tabelas começam com letra minuscula
            bw.write("\n    public fuction Cadastrar_"+modelo.tabelas.get(x).nome+"($"+modelo.tabelas.get(x).nome+"){\n\n    }");
            bw.write("\n    public fuction Listar_"+modelo.tabelas.get(x).nome+"($"+modelo.tabelas.get(x).nome+"){\n\n    }");
            bw.write("\n    public fuction Atualizar_"+modelo.tabelas.get(x).nome+"($"+modelo.tabelas.get(x).nome+"){\n\n    }");
            bw.write("\n    public fuction Deletar_"+modelo.tabelas.get(x).nome+"($"+modelo.tabelas.get(x).nome+"){\n\n    }\n\n\n");
        }
        
        
        bw.write("\n\n  }\n?>");
        bw.close();
    }
    
    */
    
    
    
}
