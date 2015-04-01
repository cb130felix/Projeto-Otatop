
package Geradores;

import Auxiliares.FixString;
import ModeloRel.Coluna;
import ModeloRel.ModeloR;
import ModeloRel.Tabela;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Arthur Flôr
 */

public class CadFormGen {
    
    ModeloR modelor;
    String caminho_dir;
    
    public CadFormGen(ModeloR modelor, String caminho_dir) {
        this.modelor = modelor;
        this.caminho_dir = caminho_dir;
    
    }
    
    public void gerarFormCadStyle() throws IOException{
    
        File arquivo = new File (caminho_dir+"cadastro/forms/formCadStyle.css");
        
       if (!arquivo.exists()) {
         arquivo.createNewFile();  
       }
        
       FileWriter fw = new FileWriter(arquivo);
       BufferedWriter bw = new BufferedWriter(fw);
       
       fw.write("body {\n" +
"  background: #2c3e50;\n" +
"  font-family: 'Verdana';\n" +
"}\n" +
"h1 {\n" +
"  color: #000; \n" +
"  font-family: 'Verdana';\n" +
"  text-align: center; \n" +
"  font-size: 18px;\n" +
"}\n" +
"\n" +
"/********************* Form Css ******************************/\n" +
".error {\n" +
"  border: 3px solid #e74c3c;\n" +
"}\n" +
".valid {\n" +
"  border: 3px solid #2ecc71\n" +
"}\n" +
".reset {\n" +
"  text-decoration: none; \n" +
"  color: #2c3e50;\n" +
"}\n" +
"form {\n" +
"  width: 300px; \n" +
"  margin: 20px auto;\n" +
"  background: #ecf0f1;\n" +
"  padding: 50px;\n" +
"  border-radius: 5px; \n" +
"  -webkit-border-radius: 15px;\n" +
"  -moz-border-radius: 5px; \n" +
"}\n" +
"form span {\n" +
"  border: 3px solid #e74c3c; \n" +
"  width: 100%; \n" +
"  padding: 10px; \n" +
"  float:left; \n" +
"  text-align: center;\n" +
"}\n" +
"input[type=\"text\"], input[type=\"password\"], input[type=\"date\"], input[type=\"datetime\"], input[type=\"datetime-local\"], input[type=\"month\"], input[type=\"week\"], input[type=\"email\"], input[type=\"number\"], input[type=\"search\"], input[type=\"tel\"], input[type=\"time\"], input[type=\"url\"], textarea {\n" +
"background-color: white;\n" +
"font-family: inherit;\n" +
"-webkit-box-shadow: none;\n" +
"box-shadow: none;\n" +
"color: rgba(0, 0, 0, 0.75);\n" +
"display: block;\n" +
"font-size: 0.875em;\n" +
"margin: 0 0 1em 0;\n" +
"padding: 0.5em;\n" +
"height: 2.3125em;\n" +
"width: 100%;\n" +
"-moz-box-sizing: border-box;\n" +
"-webkit-box-sizing: border-box;\n" +
"box-sizing: border-box;\n" +
"-webkit-transition: all 0.15s linear;\n" +
"-moz-transition: all 0.15s linear;\n" +
"transition: all 0.15s linear;\n" +
"}\n" +
"\n" +
"button, .button {\n" +
"border-style: none;\n" +
"border-width: 0;\n" +
"cursor: pointer;\n" +
"font-family: inherit;\n" +
"font-weight: bold;\n" +
"line-height: 1;\n" +
"margin: 0 0 1.25em;\n" +
"position: relative;\n" +
"text-decoration: none;\n" +
"text-align: center;\n" +
"display: inline-block;\n" +
"padding-top: 0.75em;\n" +
"padding-right: 1.5em;\n" +
"padding-bottom: 0.8125em;\n" +
"padding-left: 1.5em;\n" +
"font-size: 1em;\n" +
"background-color: #bdc3c7;\n" +
"border-color: #34495e;\n" +
"color: white;\n" +
"border-radius: 5px; \n" +
"-webkit-border-radius: 15px;\n" +
"-moz-border-radius: 5px;\n" +
"}");

    bw.close();
    fw.close();  
       
    }
    
    
    public void gerarFormCad() throws IOException{
       
       int indice=0; 
       ArrayList <Tabela> multivalorados = new ArrayList<>();
       FixString label = new FixString();
       
       File arquivo = new File (caminho_dir+"cadastro/forms/formCad.js");
        
       if (!arquivo.exists()) {
         arquivo.createNewFile();  
       }
        
       FileWriter fw = new FileWriter(arquivo);
       BufferedWriter bw = new BufferedWriter(fw);

        for (Tabela tabela : modelor.tabelas) {
            if (tabela.campo_multi==2){
                multivalorados.add(tabela);
            }
        }
       
       for (Tabela tabela : modelor.tabelas) {
          if (tabela.campo_multi!=2){  
             
            bw.write( "\nfunction form_cad_" + tabela.nome + "(){\n"
                    + "  document.getElementById('form_cad').innerHTML = \"\\n\\\n"
                    + "  <form action='../cadastro/inserts/inserir_" + tabela.nome + ".php' method='POST'>\\n\\\n"  
                    + "  <h1> Cadastro de " + label.Fix(tabela.nome) + " </h1>\\\n"); //
           
           if (tabela.campo_multi==0){
                for (Coluna coluna : tabela.colunas) {
                    if (!coluna.auto_inc){
                        bw.write( "     <p> <label for=" + coluna.nome + ">" + label.Fix(coluna.nome) + "</label>\\n\\\n"
                                + "         <input id=" + coluna.nome + " type=text name=" + coluna.nome + " autofocus /> </p>\\n\\\n");
                    }
                }
           }
           
           if (tabela.campo_multi==1){
                for (Coluna coluna : tabela.colunas) {
                    if(!coluna.auto_inc){ 
                        bw.write( "     <p> <label for=" + coluna.nome + ">" + label.Fix(coluna.nome) + "</label>\\n\\\n"
                                + "         <input id=" + coluna.nome + " type=text name=" + coluna.nome + " autofocus /> </p>\\n\\\n");
                    }
                    
                    if (coluna.pk){
                        for (int i=0; i<multivalorados.size(); i++) {
                            for (int y=0; y<multivalorados.get(i).colunas.size(); y++) {
                                if(coluna.nome.equals(multivalorados.get(i).colunas.get(y).fk_nome_coluna)){
                                    
                                    for(int w=0; w<multivalorados.get(i).colunas.size(); w++){
                                        if(!multivalorados.get(i).colunas.get(w).auto_inc && !multivalorados.get(i).colunas.get(w).fk){
                                                bw.write( "     <p> <label for=" + multivalorados.get(i).colunas.get(w).nome + ">" + label.Fix(multivalorados.get(i).colunas.get(w).nome) + "</label>\\n\\\n"
                                                        + "         <input id=" + multivalorados.get(i).colunas.get(w).nome + " type=text name=" + multivalorados.get(i).colunas.get(w).nome + "[] autofocus />\\n\\\n"
                                                        + "         <div id=mais_"+multivalorados.get(i).colunas.get(w).nome+"> </div></p> \\n\\\n"
                                                        + "     <p> <input type=button class='button' value=+ onclick=adicionaCampo('"+multivalorados.get(i).colunas.get(w).nome+"','"+indice+"') />\\n\\\n"
                                                        + "         <input type=button class='button' value=- onclick=removeCampo('"+multivalorados.get(i).colunas.get(w).nome+"','"+indice+"') /></p>\\n\\\n"); 
                                                indice++;
                                        }
                                    }
                                }
                            }    
                            i++;              
                        }             
                    }         
                }
           }
           
           bw.write( "     <p> <input type='submit' class='button' value='Cadastrar' name='enviar' >\\n\\\n"
                   + "         <input type='reset' class='button' value='Limpar' name='limpar' onclick='form_cad_" + tabela.nome + "();zerar()'></p>\\n\\\n"
                   + "  </form> \";\n"
                   + "}\n\n");
         }
       }  
       
       bw.write( "\nvar i = new Array();\n");
        
        for (int i=0; i<indice;i++){
            bw.write("i["+i+"]=1;\n");
        }
        
        bw.write("\n"
                + "function zerar() {\n"
                + " for (var w=0;w<i.length;w++){\n"
                + "     i[w]=1;\n"
                + " }\n"
                + "}\n"
                + "\n"
                + "function adicionaCampo(campo, indice) {\n"
                + "     if (i[indice] < 5){\n"
                + "         var div = document.createElement('div');\n"
                + "         var x = document.createElement('input');\n"
                + "         var p = document.createElement('p');\n"
                + "\n"
                + "         div.setAttribute('id', campo+i[indice]);\n"
                + "         x.setAttribute('id', campo);\n"
                + "         x.setAttribute('type', 'text');\n"
                + "         x.setAttribute('name', campo+\"[]\");\n"
                + "\n"
                + "         document.getElementById('mais_'+campo).appendChild(div);\n"
                + "         document.getElementById(campo+i[indice]).appendChild(p);\n"
                + "         document.getElementById(campo+i[indice]).appendChild(x).focus();\n"
                + "         i[indice]++;\n"
                + "     }\n"
                + " }\n"
                + "\n"
                + "function removeCampo(campo, indice) {\n"
                + "     if (i[indice]>1) {\n"
                + "         var node = document.getElementById('mais_'+campo);\n"
                + "         node.removeChild(node.childNodes[i[indice]-1]);\n"
                + "         i[indice]--;\n"
                + "     }\n"
                + " }\n");
       
       bw.close();
       fw.close();  
    
       this.gerarFormCadStyle();
    
    } 
}
