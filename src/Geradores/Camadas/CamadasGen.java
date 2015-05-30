/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Geradores.Camadas;

import ModeloRel.ModeloR;
import ProjetoInfo.ProjetoInfo;
import java.io.IOException;

/**
 *
 * @author Renan
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
    
    public boolean gerar() throws IOException{
    
        ceg.gerar();
        fg.gerar();
        rng.gerar();
        pg.gerar();
        mysql.gerar();
        
        return false;
        
        
    
    }
    
}
