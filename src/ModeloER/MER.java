package ModeloER;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Guto Leoni
 */
public class MER {
    
    
    public ArrayList<Entidade> entidades = new ArrayList<Entidade>();
    public ArrayList<R2> relacoes2 = new ArrayList<R2>();
    public ArrayList<R3> relacoes3 = new ArrayList<R3>();
    
    
    public int lerArquivo(String nomeArq) throws FileNotFoundException{
    
        Scanner sc = new Scanner(new FileReader(new File(nomeArq)));
        String temp = null;
        
        while(sc.hasNext()){
            
            temp = sc.next();
            
            
            if(temp.equals("e")){
            
                
                Entidade ent = new Entidade();
                
                ent.nome = sc.next();
                ent.intensidade = sc.next();
                
                
                this.entidades.add(ent);
            
                
            }//fim do if de entidade
            
        
            if(temp.equals("a")){
            
                AtributoEnt at = new AtributoEnt();
                String pk,nullable,auto;
                
                at.nome = sc.next();
                at.nome_atb = sc.next();
                at.classe = sc.next();
                
                at.tipo = sc.next();
                if(at.tipo.equals("string")){at.tipo = "varchar";}
                
                
                at.tamanho_str = sc.nextInt();
                
                pk = sc.next();
                if(pk.equals("true")){at.pk = true; }
                else{at.pk = false;}
                
                nullable = sc.next();
                if(nullable.equals("true")){at.nullable = true; }
                else{at.nullable = false;}
                
                auto = sc.next();
                if(auto.equals("true")){at.autoIncremento = true;}
                else{at.autoIncremento = false;}
                
                for (int x = 0; x < this.entidades.size() ; x++) {
                    
                    Entidade e = new Entidade();
                    
                    e = (Entidade) this.entidades.get(x);
                   if(at.nome.equals(e.nome)){
                       
                       e.atributos.add(at);
                       this.entidades.set(x, e);
                   }
                    
                }//fim do for
                
                
            }//fim do if dos atributos das entidades
        
        
            if(temp.equals("ar")){
                
                AtributoRel at = new AtributoRel();
                String nullable;
                boolean check = false;
                
                at.nome = sc.next();
                at.nome_atb = sc.next();
                at.classe = sc.next();
                
                at.tipo = sc.next();
                if(at.tipo.equals("string")){at.tipo = "varchar";}
                
                at.tamanho_str = sc.nextInt();
                
                nullable = sc.next();
                if(nullable.equals("true")){at.nullable = true;}
                else{at.nullable = false;}
                
                for (int x = 0; x < this.relacoes2.size(); x++) {
                    
                    R2 relacao = new R2();
                    
                    relacao = (R2) this.relacoes2.get(x);
                    
                    
                    if(at.nome.equals(relacao.nome_rel)){ 
                    relacao.atributos.add(at);
                    this.relacoes2.set(x, relacao);
                    check = true;
                    
                    }
                    
                }//fim do primeiro for
                
                if(check == false){
                
                for (int x = 0; x < this.relacoes3.size(); x++) {
                    
                    R3 relacao = new R3();
                    relacao = (R3) this.relacoes3.get(x);
                    
                    if(at.nome.equals(relacao.nome_rel)){
                    
                        relacao.atributos.add(at);
                        this.relacoes3.set(x, relacao);
                       
                    }
                    
                   }//fim do segundo for
                
                
                }//fim do if lá que checa se já achou ou não
                
                
                
            
            }//fim do if dos atributos das relações
            
            if(temp.equals("R2")){
            
                R2 relacao = new R2();
                char c1[],c2[];
                String cardinalidade1,cardinalidade2;
                
                relacao.nome_rel = sc.next();
                relacao.nome_ent1 = sc.next();
                cardinalidade1 = sc.next();
                c1 = cardinalidade1.toCharArray();
                relacao.card1 = c1[0];
                
                relacao.nome_ent2 = sc.next();
                
                cardinalidade2 = sc.next();
                c2 = cardinalidade2.toCharArray();
                relacao.card2 = c2[0];
                
                this.relacoes2.add(relacao);
                
                
            }//fim do if das relações binarias
            
            
            if(temp.equals("R3")){
            
            R3 relacao = new R3();
            String cardinalidade1,cardinalidade2,cardinalidade3;
            char c1[],c2[],c3[];
            
            relacao.nome_rel = sc.next();
            relacao.nome_ent1 = sc.next();
            
            cardinalidade1 = sc.next();
            c1 = cardinalidade1.toCharArray();
            relacao.card1 = c1[0];
            
            relacao.nome_ent2 = sc.next();
            
            cardinalidade2 = sc.next();
            c2 = cardinalidade2.toCharArray();
            relacao.card2 = c2[0];
            
            relacao.nome_ent3 = sc.next();
            
            
            cardinalidade3 = sc.next();
            c3 = cardinalidade3.toCharArray();
            relacao.card3 = c3[0];
            
            this.relacoes3.add(relacao);
            
            }//fim do if das relações ternarias
            
        }//fim do while
        
        
        //System.out.println("Olha aí "+ relacoes2.get(0).atributos.size());
        return 0;
    }
    
}
