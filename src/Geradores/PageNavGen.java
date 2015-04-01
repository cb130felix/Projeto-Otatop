
package Geradores;

import ModeloRel.ModeloR;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Renan
 */


public class PageNavGen {
    
    ModeloR modelor;
    String caminho_dir;

    public PageNavGen(ModeloR modelor, String caminho_dir) {
        this.modelor = modelor;
        this.caminho_dir = caminho_dir;
    }

    
    
     public int gerarPagCad() throws IOException{
        
        File arquivo = new File (caminho_dir+"PageNav/pag_cad.php");
              
        if (!arquivo.exists()) {       
            arquivo.createNewFile();  
        }
        
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("<?php\n" +
"\n" +
"echo \"<!DOCTYPE html><html><head>\"; //Inicio de Head\n" +
"\n" +
"include '..\\menu\\add_menu.php';\n" +
"include '..\\menu\\add_menu_drop_cad.php';\n" +
"echo '<script src=\"..\\cadastro\\forms\\formCad.js\" type=\"text/javascript\"></script>';\n"+
"echo '<link rel=\"stylesheet\" href=\"..\\cadastro\\forms\\formCadStyle.css\">';"+
"\n" +
"echo \"</head>\"; // Fim do head\n" +
"\n" +
"\n" +
"\n" +
"//--------------------------------************************************************-----------------------------\n" +
"\n" +
"echo \"<body>\"; //Inicio do body\n" +
"\n" +
"echo \"<div id='menu'></div>\";\n" +
"echo \"<div id='form_cad'><form><h1>Escolha um item para cadastro</h1></form></div>\";\n" +
"\n" +
"echo \"</body></html>\"; //Fim do body\n" +
"?>");
     
        bw.close();
        fw.close();
        
        
        
     return 0;
     }
        
    
}
