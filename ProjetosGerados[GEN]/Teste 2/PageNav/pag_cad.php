<?php

echo "<!DOCTYPE html><html><head>"; //Inicio de Head

include '..\menu\add_menu.php';
include '..\menu\add_menu_drop_cad.php';
echo '<script src="..\cadastro\forms\formCad.js" type="text/javascript"></script>';
echo '<link rel="stylesheet" href="..\cadastro\forms\formCadStyle.css">';
echo "</head>"; // Fim do head



//--------------------------------************************************************-----------------------------

echo "<body>"; //Inicio do body

echo "<div id='menu'></div>";
echo "<div id='form_cad'><form><h1>Escolha um item para cadastro</h1></form></div>";

echo "</body></html>"; //Fim do body
?>