<?php

  $banco = "DataBase";
  $usuario = "root";
  $senha = "";
  $hostname = "localhost";
  $link2 = mysqli_connect($hostname, $usuario, $senha, $banco) or die ("Erro ao conectar!<br>");

   $funcionario_cpf = $_POST['funcionario_cpf'];

   $projeto_numero = $_POST['projeto_numero'];

   $horas = $_POST['horas'];


  $sql1 = "INSERT INTO trabalhaem (funcionario_cpf,projeto_numero,horas)";
  $sql2 = "VALUES(";
  if($funcionario_cpf!= ''){  $sql2 = $sql2.'"'.$funcionario_cpf.'",'; }else{ $sql2 = $sql2.'NULL,'; }
  if($projeto_numero!= ''){  $sql2 = $sql2.'"'.$projeto_numero.'",'; }else{ $sql2 = $sql2.'NULL,'; }
  if($horas!= ''){  $sql2 = $sql2.'"'.$horas.'")'; }else{ $sql2 = $sql2.'NULL)'; }    echo $sql1.$sql2; 

  $result = mysqli_query($link2,$sql1.$sql2);
?>