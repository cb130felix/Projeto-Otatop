
package ProjetoInfo;

/**
 * Classe com informações padrões de um sistema Otatop
 * @author Renan
 */
public class ProjetoInfo {
    
    public String nome_projeto = "Projeto Otatop";
    public String banco_nome = "database";
    public String banco_servidor = "localhost";
    public String banco_senha = "";
    public String banco_usuario = "root";
    
    public ProjetoInfo(String nome_projeto, String banco_nome, String banco_servidor, String banco_senha, String banco_usuario) {
    
        this.banco_nome = banco_nome;
        this.nome_projeto = nome_projeto;
        this.banco_servidor = banco_servidor;
        this.banco_senha = banco_senha;
        this.banco_usuario = banco_usuario;
        
    }


    
    
}
