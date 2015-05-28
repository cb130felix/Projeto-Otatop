
package Geradores;

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
public class GerarClasses {
    
    ModeloR modelo;
    String diretorio;
    ProjetoInfo projeto;

    public GerarClasses(ModeloR modelo, String diretorio, ProjetoInfo projeto) {
        this.modelo = modelo;
        this.diretorio = diretorio;
        this.projeto = projeto;
    }
    
    public void gerar(String nomeBanco) throws IOException{
    
        gerarClassesEntidades(nomeBanco);
        gerarCamadas();
    
    
    }
    
    
    public void gerarClassesEntidades(String nomeBanco) throws IOException{// Fiquei em dúvida se a essa string como parametro é necessária, na dúvida deixei =]
    
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
        criarConstrutor(modelo.tabelas.get(x), bw);
            
        bw.write("\n\n  }\n?>");
        bw.close();
            }// fim do if de checar se é multivalorado
            
            
        }
    
    
    }// fim do método de gerar as classes das entidades
    
    public void gerarCamadas() throws IOException{
        
        ArrayList <File> camadas = new ArrayList<File>();
        
        String caminho = diretorio+"Cadastro2/Camadas/";
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
        
            for (int i = 0; i < 3; i++) {
                
                this.gerarCodigoCamadas(camadas.get(i));
            
            }
        
    
    }
    
    /*
    
    
    public void gerarCodigoFachada(){
    
    }
    
    
    public void gerarCodigoRegraNegocio(){
    
    }
    
    
    public void gerarCodigoPersistencia(){
    
    }
    
    
    */
    
    
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
    
    
    
}
