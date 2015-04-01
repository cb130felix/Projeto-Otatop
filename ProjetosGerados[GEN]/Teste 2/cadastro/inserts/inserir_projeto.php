<?php

  $banco = "DataBase";
  $usuario = "root";
  $senha = "";
  $hostname = "localhost";
  $link2 = mysqli_connect($hostname, $usuario, $senha, $banco) or die ("Erro ao conectar!<br>");

   $nome = $_POST['nome'];


   $localizacao = $_POST['localizacao'];


  $sql1 = "INSERT INTO projeto (nome,numero,localizacao)";
  $sql2 = "VALUES(";
  if($nome!= ''){  $sql2 = $sql2.'"'.$nome.'",'; }else{ $sql2 = $sql2.'NULL,'; }
  if($numero!= ''){  $sql2 = $sql2.'"'.$numero.'",'; }else{ $sql2 = $sql2.'NULL,'; }
  if($localizacao!= ''){  $sql2 = $sql2.'"'.$localizacao.'")'; }else{ $sql2 = $sql2.'NULL)'; }    echo $sql1.$sql2; 

  $result = mysqli_query($link2,$sql1.$sql2);
?>