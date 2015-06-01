package ModeloRel;

import ModeloER.MER;
import java.util.ArrayList;

/**
 * Classe responsável por normalizar todo o modeloER, passando assim para um modelo relacional normalizado
 * @author Renan
 */
public class ModeloR {
    
   public ArrayList<Tabela> tabelas = new ArrayList<Tabela>();
  
   /**
    * @param nome Nome da tabela a ser procurada
    * @return Indice de uma tabela no vetor "Tabelas" dessa classe.
    */
   public int idTabela(String nome){
       
       for(int i = 0 ; i < this.tabelas.size(); i++){
           
           
           if(this.tabelas.get(i).nome.equals(nome)){
           
                return i;
                
           }
       
       }
       
       return -1;
   
   }
   
   public int idColuna(String nomeCol, String nomeTab){
       
       int index = this.idTabela(nomeTab);
       
       Tabela tabela = this.tabelas.get(index);
       for (int i = 0; i < tabela.colunas.size(); i++) {
           if(tabela.colunas.get(i).nome.equals(nomeCol)) return i;
       }
       
       return -1;
   
   }
   
    /**
    * Cria uma chave estrangeira na tabela 2 para a tabela 1
    * @param nome_tabela1 nome de tabela
    * @param nome_tabela2 nome de tabela
    * @param pk booleano de chave estrangeira
    * @return retorna um inteiro
    */ 
   int gerarFK(String nome_tabela1, String nome_tabela2, boolean pk){
       
       int indice_tabela1=0, indice_tabela2=0;
       for(int i = 0 ; i < this.tabelas.size(); i++){
       
           if(this.tabelas.get(i).nome.equals(nome_tabela1)){
           indice_tabela1 = i;
           }
           if(this.tabelas.get(i).nome.equals(nome_tabela2)){
           indice_tabela2 = i;
           }
       
       }
       
       
       
       for(int i = 0; i < this.tabelas.get(indice_tabela1).colunas.size(); i++){
           
           if(this.tabelas.get(indice_tabela1).colunas.get(i).pk == true){
               
                 this.tabelas.get(indice_tabela2).colunas.add( new Coluna(
                       pk,
                       false,
                       false,
                       this.tabelas.get(indice_tabela1).nome + "_" + this.tabelas.get(indice_tabela1).colunas.get(i).nome,
                       this.tabelas.get(indice_tabela1).colunas.get(i).tipo,
                       this.tabelas.get(indice_tabela1).colunas.get(i).nome,
                       this.tabelas.get(indice_tabela1).nome,
                       this.tabelas.get(indice_tabela1).colunas.get(i).tamanho_str,
                         true
                       
               ));
               
           }
       
       }
   
       return 0;
   
   }
   
   /**
    * Preenche o vetor de tabelas de acordo com os atributos forte do MER passado
    * @param mer
    * @return retorna um inteiro, 0 caso sucesso.
    */
   int passo1(MER mer){      

       for(int a = 0; a < mer.entidades.size(); a++){
            Tabela tabela_temp = new Tabela();
            tabela_temp.nome = mer.entidades.get(a).nome;

             for(int i = 0; i < mer.entidades.get(a).atributos.size(); i++){
                 if(mer.entidades.get(a).atributos.get(i).classe.equals("normal")){

                     Coluna coluna_temp = new Coluna();
                     coluna_temp.nome = mer.entidades.get(a).atributos.get(i).nome_atb;
                     coluna_temp.pk = mer.entidades.get(a).atributos.get(i).pk;
                     coluna_temp.tipo = mer.entidades.get(a).atributos.get(i).tipo;
                     coluna_temp.auto_inc = mer.entidades.get(a).atributos.get(i).autoIncremento;
                     coluna_temp.nullable = mer.entidades.get(a).atributos.get(i).nullable;
                     coluna_temp.tamanho_str = mer.entidades.get(a).atributos.get(i).tamanho_str;
                     tabela_temp.colunas.add(coluna_temp);
                 }

             }
             
             this.tabelas.add(tabela_temp);
             
        }
        
       return 0;
   
   }
   
   /**
    * Cria uma chave estrangeira nas tabelas derivadas de entidades fracas para sua tabela derivada de entidade forte
    * @param mer
    * @return retorna um inteiro
    */
   int passo2(MER mer){
   boolean pk;
       for(int i = 0 ; i < mer.entidades.size(); i++){
           
           if(mer.entidades.get(i).intensidade.equals("fraca")){
           
               for(int j = 0; j < mer.relacoes2.size(); j++){
                   
                   //Entidade fraca com relações binárias PS.: Cuidado aqui, eu alterei umas paradas, qualquer coisa tem o backup de arthur
                   if(mer.relacoes2.get(j).nome_ent1.equals(mer.entidades.get(i).nome)){

                       this.gerarFK(mer.relacoes2.get(j).nome_ent2, mer.entidades.get(i).nome, true);
                       mer.relacoes2.get(j).visitado = true;
                   
                   } else if(mer.relacoes2.get(j).nome_ent2.equals(mer.entidades.get(i).nome)){
                       
                       this.gerarFK(mer.relacoes2.get(j).nome_ent1, mer.entidades.get(i).nome, true);
                        mer.relacoes2.get(j).visitado = true;
                   
                   }
                   
               }
               
                //Entidade fraca com relação ternária
                for(int j = 0; j < mer.relacoes3.size(); j++){
                  
                   if(mer.relacoes3.get(j).nome_ent1.equals(mer.entidades.get(i).nome)){
                       if(mer.relacoes3.get(j).card2 == '1'){pk = false;}else{pk = true;}
                       this.gerarFK(mer.relacoes3.get(j).nome_ent2, mer.entidades.get(i).nome, pk);
                       
                       if(mer.relacoes3.get(j).card3 == '1'){pk = false;}else{pk = true;}
                       this.gerarFK(mer.relacoes3.get(j).nome_ent3, mer.entidades.get(i).nome, pk);
                       mer.relacoes3.get(j).visitado = true;
                   
                   }else if(mer.relacoes3.get(j).nome_ent2.equals(mer.entidades.get(i).nome)){
                       if(mer.relacoes3.get(j).card1 == '1'){pk = false;}else{pk = true;}
                       this.gerarFK(mer.relacoes3.get(j).nome_ent1, mer.entidades.get(i).nome, pk);
                       
                       if(mer.relacoes3.get(j).card3 == '1'){pk = false;}else{pk = true;}
                       this.gerarFK(mer.relacoes3.get(j).nome_ent3, mer.entidades.get(i).nome, pk);
                       mer.relacoes3.get(j).visitado = true;
                   
                   }else if(mer.relacoes3.get(j).nome_ent3.equals(mer.entidades.get(i).nome)){
                       if(mer.relacoes3.get(j).card2 == '1'){pk = false;}else{pk = true;}
                       this.gerarFK(mer.relacoes3.get(j).nome_ent2, mer.entidades.get(i).nome, pk);
                       
                       if(mer.relacoes3.get(j).card1 == '1'){pk = false;}else{pk = true;}
                       this.gerarFK(mer.relacoes3.get(j).nome_ent1, mer.entidades.get(i).nome, pk);
                       mer.relacoes3.get(j).visitado = true;
                   
                   }
                   
                }
               // DEPOIS ADICIONAR A MESMA COISA PRAS RELAÇÕES TERNÁRIAS
               
           }
       
       }
       
       return 0;
       
   
   }

    /**
     * Adiciona uma chave primária nas tabelas ligadas por relações 1:1. A entidade 1 da relação é dada como entidade com participação total.
     * @param mer
     * @return retorna um inteiro
     */
   int passo3(MER mer){
   
       for(int i = 0; i < mer.relacoes2.size(); i++){
           
           if(mer.relacoes2.get(i).card1 == '1' && mer.relacoes2.get(i).card2 == '1' && mer.relacoes2.get(i).visitado == false){
           
               this.gerarFK(mer.relacoes2.get(i).nome_ent1, mer.relacoes2.get(i).nome_ent2, false);
               mer.relacoes2.get(i).visitado = true;
                //Adicionando os atributos da telação em uma tabela
               
               for(int j = 0; j < mer.relacoes2.get(i).atributos.size(); j++){
                    
                   
                    Coluna coluna_temp = new Coluna();
                     coluna_temp.nome = mer.relacoes2.get(i).atributos.get(j).nome_atb;
                     coluna_temp.tipo = mer.relacoes2.get(i).atributos.get(j).tipo;
                     coluna_temp.nullable = mer.relacoes2.get(i).atributos.get(j).nullable;
                     coluna_temp.tamanho_str = mer.relacoes2.get(i).atributos.get(j).tamanho_str;
                     int indexTb = this.idTabela(mer.relacoes2.get(i).nome_ent1);
                     this.tabelas.get(indexTb).colunas.add(coluna_temp);
                     

                }
               
           }
       
       }
      
       
       return 0;   
   
   }
   
   /**
    * Adiciona uma chave primária nas tabelas ligadas por relações 1:N. A entidade 1 da relação é dada como entidade com participação total.
    * @param mer
    * @return retorna um inteiro
    */
   
   int passo4(MER mer){
   
       String entidade_principal = "";
       
        for(int i = 0; i < mer.relacoes2.size(); i++){
           entidade_principal = "";
           
           if(mer.relacoes2.get(i).card1 == '1' && mer.relacoes2.get(i).card2 == 'n'){
                if(mer.relacoes2.get(i).visitado == false){

                    this.gerarFK(mer.relacoes2.get(i).nome_ent1, mer.relacoes2.get(i).nome_ent2, false);
                    mer.relacoes2.get(i).visitado = true;
                }
                 entidade_principal = mer.relacoes2.get(i).nome_ent2;
               
               
           }else if(mer.relacoes2.get(i).card1 == 'n' && mer.relacoes2.get(i).card2 == '1'){
                
               if(mer.relacoes2.get(i).visitado == false){
                    this.gerarFK(mer.relacoes2.get(i).nome_ent2, mer.relacoes2.get(i).nome_ent1, false);
                    mer.relacoes2.get(i).visitado = true;
                }
               entidade_principal = mer.relacoes2.get(i).nome_ent1;
                          
           }
           
           //Adicionando atributos na relação
           if(!entidade_principal.equals("")){
           for(int j = 0; j < mer.relacoes2.get(i).atributos.size(); j++){
                
                     Coluna coluna_temp = new Coluna();
                     coluna_temp.nome = mer.relacoes2.get(i).atributos.get(j).nome_atb;
                     coluna_temp.tipo = mer.relacoes2.get(i).atributos.get(j).tipo;
                     coluna_temp.nullable = mer.relacoes2.get(i).atributos.get(j).nullable;
                     coluna_temp.tamanho_str = mer.relacoes2.get(i).atributos.get(j).tamanho_str;
                     int indexTb = this.idTabela(entidade_principal);
                     this.tabelas.get(indexTb).colunas.add(coluna_temp);
                     

                }
           }
       
       }
   
       return 0;
   }
   
   /**
    * Cria uma tabela nova derivada de relações N:N
    * @param mer
    * @return retorna um inteiro
    */
   
   int passo5(MER mer){

       
       for(int i = 0; i < mer.relacoes2.size(); i++){
           
           if(mer.relacoes2.get(i).card1 != '1' && mer.relacoes2.get(i).card2 != '1'){
           //   A MAGICA ACONTECE
           
               Tabela tabela_temp = new Tabela();
               tabela_temp.nome = mer.relacoes2.get(i).nome_rel;
               this.tabelas.add(tabela_temp);
               this.gerarFK(mer.relacoes2.get(i).nome_ent1, tabela_temp.nome, true);
               this.gerarFK(mer.relacoes2.get(i).nome_ent2, tabela_temp.nome, true);
               int indexTbl = this.idTabela(mer.relacoes2.get(i).nome_rel);
               //Depois de cirar as chaves estrngeiras eu digo que elas são todas primárias
               /*for(int j = 0;j < this.tabelas.get(indexTbl).colunas.size(); j++){
           
                   this.tabelas.get(indexTbl).colunas.get(j).pk = true;
                EU ACHO QUE NÃO PRECISA MAIS DISSO NÃO \ O /   
               }*/
               //Agora vou adicionar os atributos da relação na entidade né
               for(int j = 0; j < mer.relacoes2.get(i).atributos.size(); j++){
               
                   this.tabelas.get(indexTbl).colunas.add(new Coluna(
                   false,
                   false,
                   mer.relacoes2.get(i).atributos.get(j).nullable,
                   mer.relacoes2.get(i).atributos.get(j).nome_atb,
                   mer.relacoes2.get(i).atributos.get(j).tipo,
                   null,
                   null,
                   mer.relacoes2.get(i).atributos.get(j).tamanho_str,
                   false
                   ));
                   
                   
                   
               }
               
           
           }
       
       }
       
       return 0;
       
   }
   
   /**
    * Cria uma tabela com os atributos multivalorados das entidades. Tabelas com valores multivalorados recebem o valor 1 no atributo campo_multi. Tabela que é um campo multivalorado de outra tabela recebe o valor 2 no mesmo atributo.
    * @param mer
    * @return retorna um inteiro
    */
   
   int passo6(MER mer){
       
       for(int i = 0; i < mer.entidades.size(); i++){
       
           for(int j = 0; j < mer.entidades.get(i).atributos.size(); j++){
               
               if(mer.entidades.get(i).atributos.get(j).classe.equals("multivalorado")){
               // A MÁGICA ACONTECE DENOVO
                    Tabela tabela_temp = new Tabela();
                    tabela_temp.campo_multi = 2;
                   
                    tabela_temp.nome = mer.entidades.get(i).atributos.get(j).nome + "_" + mer.entidades.get(i).atributos.get(j).nome_atb;
                    
                    //Acrescentando uma coluna pro Id da tabela
                    tabela_temp.colunas.add(new Coluna(
                            true,      
                            true,
                            false,
                            "id_"+mer.entidades.get(i).atributos.get(j).nome_atb ,
                            "int",
                            null,
                            null,
                            0,
                            false
                    ));
                    
                    //Adicionando coluna do campo multivalorado
                    tabela_temp.colunas.add(new Coluna(
                            false,      
                            false,
                            mer.entidades.get(i).atributos.get(j).nullable,
                            mer.entidades.get(i).atributos.get(j).nome_atb ,
                            mer.entidades.get(i).atributos.get(j).tipo,
                            null,
                            null,
                            mer.entidades.get(i).atributos.get(j).tamanho_str,
                            false
                    ));
                    
                    
                    
                    this.tabelas.add(tabela_temp);
                    
                    this.gerarFK(mer.entidades.get(i).nome, tabela_temp.nome, false);
                    int indexTbl = this.idTabela(mer.entidades.get(i).nome);
                    this.tabelas.get(indexTbl).campo_multi = 1;
                            
                   
                   
               }
           
           }
           
           
       }
       
       return 0;
   
   }
   
   /**
    * Cria uma tabela para as relações ternárias no modelo MER
    * @param mer
    * @return retorna um inteiro
    */
   
   int passo7(MER mer){
   
       
       for(int i = 0; i < mer.relacoes3.size(); i++){
       
           boolean pk;
           Tabela tabela_temp = new Tabela();
               tabela_temp.nome = mer.relacoes3.get(i).nome_rel;
               this.tabelas.add(tabela_temp);
               
               pk=false;
               if(mer.relacoes3.get(i).card1 != '1') pk = true;
               this.gerarFK(mer.relacoes3.get(i).nome_ent1, tabela_temp.nome, pk);
               
               pk = false;
               if(mer.relacoes3.get(i).card2 != '1') pk = true;
               this.gerarFK(mer.relacoes3.get(i).nome_ent2, tabela_temp.nome, pk);
               
               pk = false;
               if(mer.relacoes3.get(i).card3 != '1') pk = true;
               this.gerarFK(mer.relacoes3.get(i).nome_ent3, tabela_temp.nome, pk);
               
               int indexTbl = this.idTabela(mer.relacoes3.get(i).nome_rel);
               
                for(int j = 0; j < mer.relacoes3.get(i).atributos.size(); j++){
               
                   this.tabelas.get(indexTbl).colunas.add(new Coluna(
                   false,
                   false,
                   mer.relacoes3.get(i).atributos.get(j).nullable,
                   mer.relacoes3.get(i).atributos.get(j).nome_atb,
                   mer.relacoes3.get(i).atributos.get(j).tipo,
                   null,
                   null,
                   mer.relacoes3.get(i).atributos.get(j).tamanho_str,
                   false
                   ));                
               }        
       }
       return 0;
   }
   
   
   /**
    * Preenche o vetor de Tabelas da classe modeloR com tabelas na 1ª, 2ª e 3ª formal normal
    * @param mer modelo Entidade Relacionamento utilizado para preencher os dados das tabelas
    * @return 0 caso sucesso
    */
   
   public int converterModelo(MER mer){
   
       int erro = 0;
       
       erro = passo1(mer);
       erro = passo2(mer);
       erro = passo3(mer);
       erro = passo4(mer);
       erro = passo5(mer);
       erro = passo6(mer);
       erro = passo7(mer);
       
       return erro;
   
   }
  
  // método de imprimir informações de todas as tabelas 
  public int imprime(){
              
       for (int i=0;i<this.tabelas.size();i++) {
                
            System.out.println("Nome da tabela: " + this.tabelas.get(i).nome + " | CampoMulti: " + this.tabelas.get(i).campo_multi);
                
            for (int w=0;w<this.tabelas.get(i).colunas.size();w++){
                System.out.print(" | Nome: " + this.tabelas.get(i).colunas.get(w).nome);
                System.out.print(" | Auto-incremento: " + this.tabelas.get(i).colunas.get(w).auto_inc);
                System.out.print(" | Pk: " + this.tabelas.get(i).colunas.get(w).pk);
                System.out.print(" | Fk: " + this.tabelas.get(i).colunas.get(w).fk);
                System.out.print(" | Nullable: " + this.tabelas.get(i).colunas.get(w).nullable);
                System.out.print(" | Tipo: " + this.tabelas.get(i).colunas.get(w).tipo);
                System.out.print(" | Fk_nome_coluna: " + this.tabelas.get(i).colunas.get(w).fk_nome_coluna);
                System.out.print(" | Fk_nome_tabela: " + this.tabelas.get(i).colunas.get(w).fk_nome_tabela);
                System.out.println(" | Tamanho_string: " + this.tabelas.get(i).colunas.get(w).tamanho_str  );
            }
            System.out.println("");
        }
     return 0;
   }
}
