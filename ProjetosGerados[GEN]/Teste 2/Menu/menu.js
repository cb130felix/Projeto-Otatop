$( document ).ready(function() {

	document.getElementById("menu").innerHTML = "\
			<ul>\
			   <li><a href='#'><span>Home</span></a></li>\
			   <li class='active has-sub'><a href='#'><span>Cadastro</span></a><ul id = 'cad_list'></ul></li>\
			   <li><a href='#'><span>Listar</span></a></li>\
			   <li class='last'><a href='#'><span>Sobre</span></a></li>\
			</ul>\
			";

});