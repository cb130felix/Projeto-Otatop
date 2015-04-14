
import Auxiliares.FixString;
import Geradores.ProjetoGen;
import ProjetoInfo.ProjetoInfo;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Guto Leoni
 */
public class POO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        FixString fix = new FixString();
        String arquivo = "SaborRegional.txt";
        String arquivoFix = arquivo.substring(0,arquivo.length()-4); //Tratando a String pra ficar bonitinha
        ProjetoInfo info = new ProjetoInfo(fix.Fix(arquivoFix), "database", "localhost", "", "root");
        ProjetoGen gen = new ProjetoGen(info);
        gen.lerArquivo("src/Arquivos/"+arquivo, true);
        
        
        gen.gerarProjeto();
        
    }
}
