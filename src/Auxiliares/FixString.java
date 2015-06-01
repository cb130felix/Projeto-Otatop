
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
    
    /**
     * método que, dadas as palavras passadas como parametro, retorna uma string que será o nome do método
     * @param acao é o nome da ação que será realizada (cadastrar, listar..)
     * @param nomeTabela nome da tabela que será realizada a ação
     * @param LetraFachada primeira letra da fachada que está trabalhando (tem que estar maiuscula)
     * @return 
     */
    public String criarNomeMetodo(String acao, String nomeTabela, char LetraFachada){
    
    String primeiraLetra = nomeTabela.substring(0,1).toUpperCase();
    
    String nomeMetodo = null;
    
    nomeMetodo = acao + primeiraLetra + nomeTabela.substring(1, nomeTabela.length())+LetraFachada;
    
    
    return nomeMetodo;
    }
}