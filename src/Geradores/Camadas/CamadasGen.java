
package Geradores.Camadas;

import ModeloRel.ModeloR;
import ProjetoInfo.ProjetoInfo;
import java.io.IOException;

/**
 * Classe para chamadas dos geradores das camadas
 * @author Arthur, Guto, Renan
 */
public class CamadasGen {
    
    ClassEntGen ceg;
    FachadaGen fg;
    RegraNegGen rng;
    PersistenciaGen pg;
    MySqlCRUDGen mysql;
    
    
    public CamadasGen(ModeloR modelo, String diretorio, ProjetoInfo projeto){
   
        ceg = new ClassEntGen(modelo, diretorio, projeto);
        fg = new FachadaGen(modelo, diretorio, projeto);
        pg = new PersistenciaGen(modelo, diretorio, projeto);
        rng = new RegraNegGen(modelo, diretorio, projeto);
        mysql = new MySqlCRUDGen(modelo, diretorio, projeto);
        
    }
    
    
    /**
     * Gera as classes responsáveis pelo sistema de CRUD do sistema. Esse método
     * as classes relacionadas a entidades do modelor que está sendo trabalhado, além 
     * de divdir as funcionalidades CRUD em camadas: Fachada, Regra de Negócio e Persistência. Por padrão
     * a linguagem do código gerado é PHP.
     * @return true se a opreção for concluída com sucesso
     * @throws IOException 
     */
    public boolean gerar() throws IOException{
    
        ceg.gerar();
        fg.gerar();
        rng.gerar();
        pg.gerar();
        mysql.gerar();
        
        return true;
        
        
    
    }
    
}
