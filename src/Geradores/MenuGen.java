
package Geradores;

import Auxiliares.FixString;
import ModeloRel.ModeloR;
import ModeloRel.Tabela;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Renan
 */
public class MenuGen {
    
    ModeloR modelor;
    String caminho_dir;

    public MenuGen(ModeloR modelor, String caminho_dir) {
        this.modelor = modelor;
        this.caminho_dir = caminho_dir;
    }
    
    
    
    
    int gerarCss() throws IOException{
        
        File arquivo = new File (caminho_dir+"menu/style_menu.css");
        
        if (!arquivo.exists()) {
          arquivo.createNewFile();  
        }
        
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("@import url(http://fonts.googleapis.com/css?family=Open+Sans:400,700);\n" +
"#menu,\n" +
"#menu ul,\n" +
"#menu ul li,\n" +
"#menu ul li a,\n" +
"#menu #menu-button {\n" +
"  margin: 0;\n" +
"  padding: 0;\n" +
"  border: 0;\n" +
"  list-style: none;\n" +
"  line-height: 1;\n" +
"  display: block;\n" +
"  position: relative;\n" +
"  -webkit-box-sizing: border-box;\n" +
"  -moz-box-sizing: border-box;\n" +
"  box-sizing: border-box;\n" +
"}\n" +
"#menu:after,\n" +
"#menu > ul:after {\n" +
"  content: \".\";\n" +
"  display: block;\n" +
"  clear: both;\n" +
"  visibility: hidden;\n" +
"  line-height: 0;\n" +
"  height: 0;\n" +
"}\n" +
"#menu #menu-button {\n" +
"  display: none;\n" +
"}\n" +
"#menu {\n" +
"  width: auto;\n" +
"  font-family: 'Open Sans', Helvetica, sans-serif;\n" +
"  background: #39b1cc;\n" +
"  background: -moz-linear-gradient(top, #51bbd2 0%, #2d97af 100%);\n" +
"  background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #51bbd2), color-stop(100%, #2d97af));\n" +
"  background: -webkit-linear-gradient(top, #51bbd2 0%, #2d97af 100%);\n" +
"  background: -o-linear-gradient(top, #51bbd2 0%, #2d97af 100%);\n" +
"  background: -ms-linear-gradient(top, #51bbd2 0%, #2d97af 100%);\n" +
"  background: linear-gradient(to bottom, #51bbd2 0%, #2d97af 100%);\n" +
"}\n" +
"#menu > ul {\n" +
"  background: url('images/bg.png');\n" +
"  box-shadow: inset 0 -3px 0 rgba(0, 0, 0, 0.05);\n" +
"}\n" +
"#menu.align-right > ul > li {\n" +
"  float: right;\n" +
"}\n" +
"#menu > ul > li {\n" +
"  float: left;\n" +
"  display: inline-block;\n" +
"}\n" +
"#menu.align-center > ul {\n" +
"  float: none;\n" +
"  text-align: center;\n" +
"}\n" +
"#menu.align-center > ul > li {\n" +
"  float: none;\n" +
"}\n" +
"#menu.align-center ul ul {\n" +
"  text-align: left;\n" +
"}\n" +
"#menu > ul > li > a {\n" +
"  padding: 18px 25px 21px 25px;\n" +
"  border-right: 1px solid rgba(80, 80, 80, 0.12);\n" +
"  text-decoration: none;\n" +
"  font-size: 13px;\n" +
"  font-weight: 700;\n" +
"  color: #d3eced;\n" +
"  text-transform: uppercase;\n" +
"  letter-spacing: 1px;\n" +
"}\n" +
"#menu > ul > li:hover > a,\n" +
"#menu > ul > li > a:hover,\n" +
"#menu > ul > li.active > a {\n" +
"  color: #ffffff;\n" +
"  background: #32a9c3;\n" +
"  background: rgba(0, 0, 0, 0.1);\n" +
"}\n" +
"#menu > ul > li.has-sub > a {\n" +
"  padding-right: 45px;\n" +
"}\n" +
"#menu > ul > li.has-sub > a::after {\n" +
"  content: \"\";\n" +
"  position: absolute;\n" +
"  width: 0;\n" +
"  height: 0;\n" +
"  border: 6px solid transparent;\n" +
"  border-top-color: #d3eced;\n" +
"  right: 17px;\n" +
"  top: 22px;\n" +
"}\n" +
"#menu > ul > li.has-sub.active > a::after,\n" +
"#menu > ul > li.has-sub:hover > a {\n" +
"  border-top-color: #ffffff;\n" +
"}\n" +
"#menu ul ul {\n" +
"  position: absolute;\n" +
"  left: -9999px;\n" +
"  top: 60px;\n" +
"  padding-top: 6px;\n" +
"  font-size: 13px;\n" +
"  opacity: 0;\n" +
"  -webkit-transition: top 0.2s ease, opacity 0.2s ease-in;\n" +
"  -moz-transition: top 0.2s ease, opacity 0.2s ease-in;\n" +
"  -ms-transition: top 0.2s ease, opacity 0.2s ease-in;\n" +
"  -o-transition: top 0.2s ease, opacity 0.2s ease-in;\n" +
"  transition: top 0.2s ease, opacity 0.2s ease-in;\n" +
"}\n" +
"#menu.align-right ul ul {\n" +
"  text-align: right;\n" +
"}\n" +
"#menu > ul > li > ul::after {\n" +
"  content: \"\";\n" +
"  position: absolute;\n" +
"  width: 0;\n" +
"  height: 0;\n" +
"  border: 5px solid transparent;\n" +
"  border-bottom-color: #ffffff;\n" +
"  top: -4px;\n" +
"  left: 20px;\n" +
"}\n" +
"#menu.align-right > ul > li > ul::after {\n" +
"  left: auto;\n" +
"  right: 20px;\n" +
"}\n" +
"#menu ul ul ul::after {\n" +
"  content: \"\";\n" +
"  position: absolute;\n" +
"  width: 0;\n" +
"  height: 0;\n" +
"  border: 5px solid transparent;\n" +
"  border-right-color: #ffffff;\n" +
"  top: 11px;\n" +
"  left: -4px;\n" +
"}\n" +
"#menu.align-right ul ul ul::after {\n" +
"  border-right-color: transparent;\n" +
"  border-left-color: #ffffff;\n" +
"  left: auto;\n" +
"  right: -4px;\n" +
"}\n" +
"#menu > ul > li > ul {\n" +
"  top: 120px;\n" +
"}\n" +
"#menu > ul > li:hover > ul {\n" +
"  top: 52px;\n" +
"  left: 0;\n" +
"  opacity: 1;\n" +
"}\n" +
"#menu.align-right > ul > li:hover > ul {\n" +
"  left: auto;\n" +
"  right: 0;\n" +
"}\n" +
"#menu ul ul ul {\n" +
"  padding-top: 0;\n" +
"  padding-left: 6px;\n" +
"}\n" +
"#menu.align-right ul ul ul {\n" +
"  padding-right: 6px;\n" +
"}\n" +
"#menu ul ul > li:hover > ul {\n" +
"  left: 180px;\n" +
"  top: 0;\n" +
"  opacity: 1;\n" +
"}\n" +
"#menu.align-right ul ul > li:hover > ul {\n" +
"  left: auto;\n" +
"  right: 100%;\n" +
"  opacity: 1;\n" +
"}\n" +
"#menu ul ul li a {\n" +
"  text-decoration: none;\n" +
"  font-weight: 400;\n" +
"  padding: 11px 25px;\n" +
"  width: 180px;\n" +
"  color: #777777;\n" +
"  background: #ffffff;\n" +
"  box-shadow: 0 2px 2px rgba(0, 0, 0, 0.1), 1px 1px 1px rgba(0, 0, 0, 0.1), -1px 1px 1px rgba(0, 0, 0, 0.1);\n" +
"}\n" +
"#menu ul ul li:hover > a,\n" +
"#menu ul ul li.active > a {\n" +
"  color: #333333;\n" +
"}\n" +
"#menu ul ul li:first-child > a {\n" +
"  border-top-left-radius: 3px;\n" +
"  border-top-right-radius: 3px;\n" +
"}\n" +
"#menu ul ul li:last-child > a {\n" +
"  border-bottom-left-radius: 3px;\n" +
"  border-bottom-right-radius: 3px;\n" +
"}\n" +
"#menu > ul > li > ul::after {\n" +
"  position: absolute;\n" +
"  display: block;\n" +
"}\n" +
"#menu ul ul li.has-sub > a::after {\n" +
"  content: \"\";\n" +
"  position: absolute;\n" +
"  width: 0;\n" +
"  height: 0;\n" +
"  border: 4px solid transparent;\n" +
"  border-left-color: #777777;\n" +
"  right: 17px;\n" +
"  top: 14px;\n" +
"}\n" +
"#menu.align-right ul ul li.has-sub > a::after {\n" +
"  border-left-color: transparent;\n" +
"  border-right-color: #777777;\n" +
"  right: auto;\n" +
"  left: 17px;\n" +
"}\n" +
"#menu ul ul li.has-sub.active > a::after,\n" +
"#menu ul ul li.has-sub:hover > a::after {\n" +
"  border-left-color: #333333;\n" +
"}\n" +
"#menu.align-right ul ul li.has-sub.active > a::after,\n" +
"#menu.align-right ul ul li.has-sub:hover > a::after {\n" +
"  border-right-color: #333333;\n" +
"  border-left-color: transparent;\n" +
"}\n" +
"@media all and (max-width: 800px), only screen and (-webkit-min-device-pixel-ratio: 2) and (max-width: 1024px), only screen and (min--moz-device-pixel-ratio: 2) and (max-width: 1024px), only screen and (-o-min-device-pixel-ratio: 2/1) and (max-width: 1024px), only screen and (min-device-pixel-ratio: 2) and (max-width: 1024px), only screen and (min-resolution: 192dpi) and (max-width: 1024px), only screen and (min-resolution: 2dppx) and (max-width: 1024px) {\n" +
"  #menu {\n" +
"    background: #39b1cc;\n" +
"  }\n" +
"  #menu > ul {\n" +
"    display: none;\n" +
"  }\n" +
"  #menu > ul.open {\n" +
"    display: block;\n" +
"    border-top: 1px solid rgba(0, 0, 0, 0.1);\n" +
"  }\n" +
"  #menu.align-right > ul {\n" +
"    float: none;\n" +
"  }\n" +
"  #menu.align-center > ul {\n" +
"    text-align: left;\n" +
"  }\n" +
"  #menu > ul > li,\n" +
"  #menu.align-right > ul > li {\n" +
"    float: none;\n" +
"    display: block;\n" +
"  }\n" +
"  #menu > ul > li > a {\n" +
"    padding: 18px 25px 18px 25px;\n" +
"    border-right: 0;\n" +
"  }\n" +
"  #menu > ul > li:hover > a,\n" +
"  #menu > ul > li.active > a {\n" +
"    background: rgba(0, 0, 0, 0.1);\n" +
"  }\n" +
"  #menu #menu-button {\n" +
"    display: block;\n" +
"    text-decoration: none;\n" +
"    font-size: 13px;\n" +
"    font-weight: 700;\n" +
"    color: #d3eced;\n" +
"    padding: 18px 25px 18px 25px;\n" +
"    text-transform: uppercase;\n" +
"    letter-spacing: 1px;\n" +
"    background: url('images/bg.png');\n" +
"    cursor: pointer;\n" +
"  }\n" +
"  #menu ul ul,\n" +
"  #menu ul li:hover > ul,\n" +
"  #menu > ul > li > ul,\n" +
"  #menu ul ul ul,\n" +
"  #menu ul ul li:hover > ul,\n" +
"  #menu.align-right ul ul,\n" +
"  #menu.align-right ul li:hover > ul,\n" +
"  #menu.align-right > ul > li > ul,\n" +
"  #menu.align-right ul ul ul,\n" +
"  #menu.align-right ul ul li:hover > ul {\n" +
"    left: 0;\n" +
"    right: auto;\n" +
"    top: auto;\n" +
"    opacity: 1;\n" +
"    width: 100%;\n" +
"    padding: 0;\n" +
"    position: relative;\n" +
"    text-align: left;\n" +
"  }\n" +
"  #menu ul ul li {\n" +
"    width: 100%;\n" +
"  }\n" +
"  #menu ul ul li a {\n" +
"    width: 100%;\n" +
"    box-shadow: none;\n" +
"    padding-left: 35px;\n" +
"  }\n" +
"  #menu ul ul ul li a {\n" +
"    padding-left: 45px;\n" +
"  }\n" +
"  #menu ul ul li:first-child > a,\n" +
"  #menu ul ul li:last-child > a {\n" +
"    border-radius: 0;\n" +
"  }\n" +
"  #menu #menu-button::after {\n" +
"    display: block;\n" +
"    content: '';\n" +
"    position: absolute;\n" +
"    height: 3px;\n" +
"    width: 22px;\n" +
"    border-top: 2px solid #d3eced;\n" +
"    border-bottom: 2px solid #d3eced;\n" +
"    right: 25px;\n" +
"    top: 18px;\n" +
"  }\n" +
"  #menu #menu-button::before {\n" +
"    display: block;\n" +
"    content: '';\n" +
"    position: absolute;\n" +
"    height: 3px;\n" +
"    width: 22px;\n" +
"    border-top: 2px solid #d3eced;\n" +
"    right: 25px;\n" +
"    top: 28px;\n" +
"  }\n" +
"  #menu > ul > li.has-sub > a::after,\n" +
"  #menu ul ul li.has-sub > a::after {\n" +
"    display: none;\n" +
"  }\n" +
"}");
        bw.close();
        fw.close();
    return 0;
    }
    int gerarMenuJs() throws IOException{
    
        File arquivo = new File (caminho_dir+"menu/script.js");
        
        if (!arquivo.exists()) {
          arquivo.createNewFile();  
        }
        
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("( function( $ ) {\n" +
"$( document ).ready(function() {\n" +
"$('#cssmenu').prepend('<div id=\"menu-button\">Menu</div>');\n" +
"	$('#cssmenu #menu-button').on('click', function(){\n" +
"		var menu = $(this).next('ul');\n" +
"		if (menu.hasClass('open')) {\n" +
"			menu.removeClass('open');\n" +
"		}\n" +
"		else {\n" +
"			menu.addClass('open');\n" +
"		}\n" +
"	});\n" +
"});\n" +
"} )( jQuery );");
        bw.close();
        fw.close();
        
    return 0;
    }
    int gerarMenuBar() throws IOException{
    File arquivo = new File (caminho_dir+"menu/menu.js");
        
        if (!arquivo.exists()) {
          arquivo.createNewFile();  
        }
        
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("$( document ).ready(function() {\n" +
"\n" +
"	document.getElementById(\"menu\").innerHTML = \"\\\n" +
"			<ul>\\\n" +
"			   <li><a href='#'><span>Home</span></a></li>\\\n" +
"			   <li class='active has-sub'><a href='#'><span>Cadastro</span></a><ul id = 'cad_list'></ul></li>\\\n" +
"			   <li><a href='#'><span>Listar</span></a></li>\\\n" +
"			   <li class='last'><a href='#'><span>Sobre</span></a></li>\\\n" +
"			</ul>\\\n" +
"			\";\n" +
"\n" +
"});");
        bw.close();
        fw.close();
        
    return 0;
    }
    
    int gerarMenuDropCad() throws IOException{
    
        File arquivo = new File (caminho_dir+"menu/menu_DropCad.js");
        FixString label = new FixString();
        
        if (!arquivo.exists()) {
          arquivo.createNewFile();  
        }
        
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("$( document ).ready(function() {\n" +
"\n" +
"	document.getElementById(\"cad_list\").innerHTML = \"\\");
       
        
        //--------------------------------Aqui é customizado meu irmão
        
        for(Tabela tabela : modelor.tabelas){
            if(tabela.campo_multi != 2){
            bw.write("<li id='menu_"+ tabela.nome +"'><a href='#'><span>"+label.Fix(tabela.nome)+"</span></a></li>\\");
            bw.newLine();
            }
        }
        
        
         bw.write("\";");// Fechei a primeira parte
         bw.newLine();
        //------------------------------------------------------------
        for(Tabela tabela : modelor.tabelas){
        if(tabela.campo_multi != 3){
            
            bw.write("$(\"#menu_"+tabela.nome+"\").click(function(){\n" +
	    "form_cad_"+ tabela.nome +"(); \n" +
"    });");
            bw.newLine();
        }
            
        }

        //----------- Aqui pe a parte 2, pra adicionar as funções js de gerar formulário
        //-------------------------------------------------------------------------------
        
        bw.write("});");// Fechei a segunda parte
        
        
        bw.close();
        fw.close();
        
    return 0;
    
    }
    
    int gerarAddMenu() throws IOException{
    File arquivo = new File (caminho_dir+"menu/add_menu.php");
        
        if (!arquivo.exists()) {
          arquivo.createNewFile();  
        }
        
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("<?php\n" +
"   \n" +
"   echo \"\n" +
"   \n" +
"   <link rel=\\\"stylesheet\\\" href=\\\"../menu/style_menu.css\\\">\n" +
"   <script src=\\\"http://code.jquery.com/jquery-latest.min.js\\\" type=\\\"text/javascript\\\"></script>\n" +
"   <script src=\\\"../menu/script.js\\\"></script>\n" +
"   <script src=\\\"../menu/menu.js\\\" type=\\\"text/javascript\\\"></script>\n" +
"\n" +
"   \";\n" +
"?>");
        bw.close();
        fw.close();
        return 0;
    }
    int gerarAddDropCad() throws IOException{
         File arquivo = new File (caminho_dir+"menu/add_menu_drop_cad.php");
        
        if (!arquivo.exists()) {
          arquivo.createNewFile();  
        }
        
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write("<?php\n" +
"   \n" +
"   echo \"\n" +
"   <script src=\\\"../menu/menu_DropCad.js\\\" type=\\\"text/javascript\\\"></script>\n" +
"   \";\n" +
"?>");
        bw.close();
        fw.close();
        return 0;
    };
    
    public int gerarMenu() throws IOException{
    
    this.gerarCss();
    this.gerarMenuJs();
    this.gerarAddDropCad();
    this.gerarAddMenu();
    this.gerarMenuBar();
    this.gerarMenuDropCad();
    return 0;
    }
    
    
}
