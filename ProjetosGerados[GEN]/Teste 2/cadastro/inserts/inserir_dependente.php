<?php

  $banco = "DataBase";
  $usuario = "root";
  $senha = "";
  $hostname = "localhost";
  $link2 = mysqli_connect($hostname, $usuario, $senha, $banco) or die ("Erro ao conectar!<br>");

   $nome = $_POST['nome'];

   $sexo = $_POST['sexo'];

   $parentecos = $_POST['parentecos'];

   $datanasc = $_POST['datanasc'];

   $funcionario_cpf = $_POST['funcionario_cpf'];


  $sql1 = "INSERT INTO dependente (nome,sexo,parentecos,datanasc,funcionario_cpf)";
  $sql2 = "VALUES(";
  if($nome!= ''){  $sql2 = $sql2.'"'.$nome.'",'; }else{ $sql2 = $sql2.'NULL,'; }
  if($sexo!= ''){  $sql2 = $sql2.'"'.$sexo.'",'; }else{ $sql2 = $sql2.'NULL,'; }
  if($parentecos!= ''){  $sql2 = $sql2.'"'.$parentecos.'",'; }else{ $sql2 = $sql2.'NULL,'; }
  if($datanasc!= ''){  $sql2 = $sql2.'"'.$datanasc.'",'; }else{ $sql2 = $sql2.'NULL,'; }
  if($funcionario_cpf!= ''){  $sql2 = $sql2.'"'.$funcionario_cpf.'")'; }else{ $sql2 = $sql2.'NULL)'; }    echo $sql1.$sql2; 

  $result = mysqli_query($link2,$sql1.$sql2);
?>