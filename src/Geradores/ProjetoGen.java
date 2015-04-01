
package Geradores;

import ModeloER.MER;
import ModeloRel.ModeloR;
import ProjetoInfo.ProjetoInfo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Renan
 */
public class ProjetoGen {
    
    String caminho_dir;
    ProjetoInfo info;
    ModeloR modelor = new ModeloR();
    MER mer = new MER();
    BancoGen bancoGen;
    CadFormGen cadFormGen;
    CadInsertGen cadInsertGen ;
    MenuGen menuGen;
    PageNavGen pagNavGen;
    
   
   
    /**
     *  Lê um arquivo com as especificações do modeloER sugerido e o converte para um modelo Relacional.
     * @param nomeArq nome do Arquivo com as especificações do modelo ER
     * @param imprimir true pra imprimir uma prévia das tabelas geradas pela conversão de modeloER para modelo Relacional
     * @throws FileNotFoundException
     * @throws IOException 
     */
    
    public ProjetoGen(ProjetoInfo info){
        this.info = info;
        this.caminho_dir = "src/../../ProjetosGerados[GEN]/";
        new File(caminho_dir).mkdir();
        
        this.caminho_dir = "src/../../ProjetosGerados[GEN]/"+this.info.nome_projeto+"/";
        new File(caminho_dir).mkdir();
        
        bancoGen = new BancoGen(this.modelor, caminho_dir);
        cadFormGen = new CadFormGen(this.modelor, caminho_dir);
        cadInsertGen = new CadInsertGen(this.modelor, caminho_dir);
        menuGen = new MenuGen(this.modelor, caminho_dir);
        pagNavGen = new PageNavGen(this.modelor, caminho_dir);
    }
    
    public ProjetoGen(){}
    
    public void lerArquivo(String nomeArq, boolean imprimir) throws FileNotFoundException, IOException{
    
       
        mer.lerArquivo(nomeArq);
        modelor.converterModelo(mer);
        if(imprimir) modelor.imprime();
      

    }
    
    /**
     * Gera uma página de internet baseado no modelo Entidade Relacionamento proposto
     * @throws IOException 
     */
    
    public void gerarProjeto() throws IOException{
        
        new File(caminho_dir).mkdir();
        this.bancoGen.gerarSQL();
        
        new File(caminho_dir+"PageNav").mkdir();
        this.pagNavGen.gerarPagCad();
        
        
        new File(caminho_dir+"menu").mkdir();
        this.menuGen.gerarMenu();
        
        new File(caminho_dir+"cadastro").mkdir();
        new File(caminho_dir+"cadastro/inserts").mkdir();
        this.cadInsertGen.gerarCadInserir(this.info.nome_banco);
        
        
        new File(caminho_dir+"cadastro/forms").mkdir();
        this.cadFormGen.gerarFormCad();
        
        
    
    }
    
    
}
