
package Auxiliares;

/**
 * 
 * @author arthur
 */
public class FixString {

    /**
     * Modelador de palavras.
     * Ex.: nome_tabela -> Nome Tabela
     * @param palavra palavra que irá ser modelada
     * @return retorna a palavra já modelada.
     */
    
    public String Fix(String palavra){
        
         String temp = "";
         boolean inicial=true;   
            
         for (int i=0; i<palavra.length(); i++){
             if (inicial){
                temp = temp + palavra.substring(i, i+1).toUpperCase();
                inicial=false;
             }
             
             else if(palavra.substring(i, i+1).equals("_")){
                 
                 temp = temp + " ";
                 inicial=true;
             }
             
             else if (palavra.substring(i, i+1).toUpperCase().equals(palavra.substring(i, i+1))){
                 temp = temp + " ";
                 temp = temp + palavra.substring(i, i+1);
             }
             
             else{
                 
                 temp = temp + palavra.substring(i, i+1); 
             }
         }
        
        return temp;
    }
    
    
    public String criarNomeMétodo(String acao, String nomeTabela, char LetraFachada){
    
    String primeiraLetra = nomeTabela.substring(0,1).toUpperCase();
    
    String nomeMetodo = null;
    
    nomeMetodo = acao + primeiraLetra + nomeTabela.substring(1, nomeTabela.length())+LetraFachada;
    
    
    return nomeMetodo;
    }
    
    
    
    
    
}