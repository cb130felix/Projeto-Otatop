
package Geradores;

import ModeloRel.ModeloR;
import ProjetoInfo.ProjetoInfo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe responsável por gerar arquivos de navegação do sistema
 * @author Renan
 */

public class PageNavGen {
    
    ModeloR modelor;
    String caminho_dir;
    String htmlInfo, titulo;

    public PageNavGen(ModeloR modelor, String caminho_dir, ProjetoInfo info) {
        this.modelor = modelor;
        this.caminho_dir = caminho_dir;
        htmlInfo = "echo \"<!DOCTYPE html><html><head>\"; //Inicio de Head\n";
        titulo = "echo \"<title>"+info.nome_projeto+"</title>\"; //Inicio de Head\n";
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
htmlInfo +
titulo                +
"\n" +
"include '../menu/add_menu.php';\n" +
"include '../menu/add_menu_drop_cad.php';\n" +
"echo '<script src=\"../cadastro/forms/formCad.js\" type=\"text/javascript\"></script>';\n"+
"echo '<link rel=\"stylesheet\" href=\"../cadastro/forms/formCadStyle.css\">';"+
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
     
     public int gerarPagHome() throws IOException{
        
        File arquivo = new File (caminho_dir+"PageNav/home.php");
              
        if (!arquivo.exists()) {       
            arquivo.createNewFile();  
        }
        
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("<?php\n" +
"\n" +
htmlInfo +
titulo                +
"\n" +
//"echo   '<link rel=\"stylesheet\" href=\"menu/style_menu.css\">';\n" +
//"echo   '<script src=\"http://code.jquery.com/jquery-latest.min.js\" type=\"text/javascript\"></script>';\n" +
//"echo   '<script src=\"menu/script.js\"></script>';\n" +
//"echo   '<script src=\"menu/menu.js\" type=\"text/javascript\"></script>';\n" +
//"echo '<link rel=\"stylesheet\" href=\"cadastro/forms/formCadStyle.css\">';"+
"include '../menu/add_menu.php';\n" +
"echo '<script src=\"../cadastro/forms/formCad.js\" type=\"text/javascript\"></script>';\n"+
"echo '<link rel=\"stylesheet\" href=\"../cadastro/forms/formCadStyle.css\">';"+
                
                
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
"echo \"<div id='form_cad'><form><h1>Pagina inicial</h1></form></div>\";\n" +
"\n" +
"echo \"</body></html>\"; //Fim do body\n" +
"?>");
     
        bw.close();
        fw.close();
        
        
        
     return 0;
     }
        
     public int gerarPagIndex() throws IOException{
     
          File arquivo = new File (caminho_dir+"/index.php");
              
        if (!arquivo.exists()) {       
            arquivo.createNewFile();  
        }
        
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("<meta http-equiv=\"refresh\" content=\"0;URL=PageNav/home.php\" />");
         
        bw.close();
        fw.close();
         
     return 0;
     }
     
     public int gerarPagNav() throws IOException{
     
         this.gerarPagIndex();
         this.gerarPagCad();
         this.gerarPagHome();
         
         return 0;
     
     }
    
}
