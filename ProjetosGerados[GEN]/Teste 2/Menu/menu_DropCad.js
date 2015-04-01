$( document ).ready(function() {

	document.getElementById("cad_list").innerHTML = "\<li id='menu_funcionario'><a href='#'><span>Funcionario</span></a></li>\
<li id='menu_departamento'><a href='#'><span>Departamento</span></a></li>\
<li id='menu_projeto'><a href='#'><span>Projeto</span></a></li>\
<li id='menu_dependente'><a href='#'><span>Dependente</span></a></li>\
<li id='menu_trabalhaem'><a href='#'><span>Trabalhaem</span></a></li>\
";
$("#menu_funcionario").click(function(){
form_cad_funcionario(); 
    });
$("#menu_departamento").click(function(){
form_cad_departamento(); 
    });
$("#menu_projeto").click(function(){
form_cad_projeto(); 
    });
$("#menu_dependente").click(function(){
form_cad_dependente(); 
    });
$("#menu_trabalhaem").click(function(){
form_cad_trabalhaem(); 
    });
$("#menu_departamento_localizacoes").click(function(){
form_cad_departamento_localizacoes(); 
    });
});