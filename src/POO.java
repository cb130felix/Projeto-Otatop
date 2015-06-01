
import Auxiliares.FixString;
import Geradores.ProjetoGen;
import ProjetoInfo.ProjetoInfo;
import java.io.IOException;

/**
 * Otatop, sistema gerador de CRUD.
 * Banco de dados Mysql
 * Arquivos gerados em php, javascript, sql
 * Código fonte em java (jdk 1.8)
 * @version 1.0
 * @author Arthur, Guto, Renan
 */
public class POO {

    /**
     * Main do sistema. Informa o arquivo txt que será usado e informações do banco de dados.
     * @throws IOException
     * @param args argumentos em linha de comando
     */
    public static void main(String[] args) throws IOException {
        
        FixString fix = new FixString();
        String arquivo = "SaborRegional.txt";
        String arquivoFix = arquivo.substring(0,arquivo.length()-4); //Tratando a String pra ficar bonitinha
        ProjetoInfo info = new ProjetoInfo(fix.Fix(arquivoFix), "database", "localhost", "", "root");
        ProjetoGen gen = new ProjetoGen(info);
        gen.lerArquivo("src/Arquivos/"+arquivo, true);
        
        
        gen.gerarProjeto();
        
    }
}
