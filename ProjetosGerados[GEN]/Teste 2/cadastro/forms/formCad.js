
function form_cad_funcionario(){
  document.getElementById('form_cad').innerHTML = "\n\
  <form action='../cadastro/inserts/inserir_funcionario.php' method='POST'>\n\
  <h1> Cadastro de Funcionario </h1>\
     <p> <label for=nome>Nome</label>\n\
         <input id=nome type=text name=nome autofocus /> </p>\n\
     <p> <label for=dataNascimento>Data Nascimento</label>\n\
         <input id=dataNascimento type=text name=dataNascimento autofocus /> </p>\n\
     <p> <label for=cpf>Cpf</label>\n\
         <input id=cpf type=text name=cpf autofocus /> </p>\n\
     <p> <label for=endereco>Endereco</label>\n\
         <input id=endereco type=text name=endereco autofocus /> </p>\n\
     <p> <label for=sexo>Sexo</label>\n\
         <input id=sexo type=text name=sexo autofocus /> </p>\n\
     <p> <label for=salario>Salario</label>\n\
         <input id=salario type=text name=salario autofocus /> </p>\n\
     <p> <label for=datainicio>Datainicio</label>\n\
         <input id=datainicio type=text name=datainicio autofocus /> </p>\n\
     <p> <label for=funcionario_cpf>Funcionario Cpf</label>\n\
         <input id=funcionario_cpf type=text name=funcionario_cpf autofocus /> </p>\n\
     <p> <label for=departamento_numero>Departamento Numero</label>\n\
         <input id=departamento_numero type=text name=departamento_numero autofocus /> </p>\n\
     <p> <input type='submit' class='button' value='Cadastrar' name='enviar' >\n\
         <input type='reset' class='button' value='Limpar' name='limpar' onclick='form_cad_funcionario();zerar()'></p>\n\
  </form> ";
}


function form_cad_departamento(){
  document.getElementById('form_cad').innerHTML = "\n\
  <form action='../cadastro/inserts/inserir_departamento.php' method='POST'>\n\
  <h1> Cadastro de Departamento </h1>\
     <p> <label for=nome>Nome</label>\n\
         <input id=nome type=text name=nome autofocus /> </p>\n\
     <p> <label for=localizacoes>Localizacoes</label>\n\
         <input id=localizacoes type=text name=localizacoes[] autofocus /></p>\n\
         <div id=mais_localizacoes> </div> \n\
     <p> <input type=button class='button' value=+ onclick=adicionaCampo('localizacoes','0') />\n\
         <input type=button class='button' value=- onclick=removeCampo('localizacoes','0') /></p>\n\
     <p> <label for=funcionario_cpf>Funcionario Cpf</label>\n\
         <input id=funcionario_cpf type=text name=funcionario_cpf autofocus /> </p>\n\
     <p> <input type='submit' class='button' value='Cadastrar' name='enviar' >\n\
         <input type='reset' class='button' value='Limpar' name='limpar' onclick='form_cad_departamento();zerar()'></p>\n\
  </form> ";
}


function form_cad_projeto(){
  document.getElementById('form_cad').innerHTML = "\n\
  <form action='../cadastro/inserts/inserir_projeto.php' method='POST'>\n\
  <h1> Cadastro de Projeto </h1>\
     <p> <label for=nome>Nome</label>\n\
         <input id=nome type=text name=nome autofocus /> </p>\n\
     <p> <label for=localizacao>Localizacao</label>\n\
         <input id=localizacao type=text name=localizacao autofocus /> </p>\n\
     <p> <input type='submit' class='button' value='Cadastrar' name='enviar' >\n\
         <input type='reset' class='button' value='Limpar' name='limpar' onclick='form_cad_projeto();zerar()'></p>\n\
  </form> ";
}


function form_cad_dependente(){
  document.getElementById('form_cad').innerHTML = "\n\
  <form action='../cadastro/inserts/inserir_dependente.php' method='POST'>\n\
  <h1> Cadastro de Dependente </h1>\
     <p> <label for=nome>Nome</label>\n\
         <input id=nome type=text name=nome autofocus /> </p>\n\
     <p> <label for=sexo>Sexo</label>\n\
         <input id=sexo type=text name=sexo autofocus /> </p>\n\
     <p> <label for=parentecos>Parentecos</label>\n\
         <input id=parentecos type=text name=parentecos autofocus /> </p>\n\
     <p> <label for=datanasc>Datanasc</label>\n\
         <input id=datanasc type=text name=datanasc autofocus /> </p>\n\
     <p> <label for=funcionario_cpf>Funcionario Cpf</label>\n\
         <input id=funcionario_cpf type=text name=funcionario_cpf autofocus /> </p>\n\
     <p> <input type='submit' class='button' value='Cadastrar' name='enviar' >\n\
         <input type='reset' class='button' value='Limpar' name='limpar' onclick='form_cad_dependente();zerar()'></p>\n\
  </form> ";
}


function form_cad_trabalhaem(){
  document.getElementById('form_cad').innerHTML = "\n\
  <form action='../cadastro/inserts/inserir_trabalhaem.php' method='POST'>\n\
  <h1> Cadastro de Trabalhaem </h1>\
     <p> <label for=funcionario_cpf>Funcionario Cpf</label>\n\
         <input id=funcionario_cpf type=text name=funcionario_cpf autofocus /> </p>\n\
     <p> <label for=projeto_numero>Projeto Numero</label>\n\
         <input id=projeto_numero type=text name=projeto_numero autofocus /> </p>\n\
     <p> <label for=horas>Horas</label>\n\
         <input id=horas type=text name=horas autofocus /> </p>\n\
     <p> <input type='submit' class='button' value='Cadastrar' name='enviar' >\n\
         <input type='reset' class='button' value='Limpar' name='limpar' onclick='form_cad_trabalhaem();zerar()'></p>\n\
  </form> ";
}


var i = new Array();
i[0]=1;

function zerar() {
 for (var w=0;w<i.length;w++){
     i[w]=1;
 }
}

function adicionaCampo(campo, indice) {
     if (i[indice] < 5){
         var div = document.createElement('div');
         var x = document.createElement('input');
         var p = document.createElement('p');

         div.setAttribute('id', campo+i[indice]);
         x.setAttribute('id', campo);
         x.setAttribute('type', 'text');
         x.setAttribute('name', campo+"[]");

         document.getElementById('mais_'+campo).appendChild(div);
         document.getElementById(campo+i[indice]).appendChild(p);
         document.getElementById(campo+i[indice]).appendChild(x).focus();
         i[indice]++;
     }
 }

function removeCampo(campo, indice) {
     if (i[indice]>1) {
         var node = document.getElementById('mais_'+campo);
         node.removeChild(node.childNodes[i[indice]-1]);
         i[indice]--;
     }
 }
