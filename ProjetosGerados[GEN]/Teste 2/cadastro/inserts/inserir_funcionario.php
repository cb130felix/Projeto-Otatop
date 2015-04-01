<?php

  $banco = "DataBase";
  $usuario = "root";
  $senha = "";
  $hostname = "localhost";
  $link2 = mysqli_connect($hostname, $usuario, $senha, $banco) or die ("Erro ao conectar!<br>");

   $nome = $_POST['nome'];

   $dataNascimento = $_POST['dataNascimento'];

   $cpf = $_POST['cpf'];

   $endereco = $_POST['endereco'];

   $sexo = $_POST['sexo'];

   $salario = $_POST['salario'];

   $datainicio = $_POST['datainicio'];

   $funcionario_cpf = $_POST['funcionario_cpf'];

   $departamento_numero = $_POST['departamento_numero'];


  $sql1 = "INSERT INTO funcionario (nome,dataNascimento,cpf,endereco,sexo,salario,datainicio,funcionario_cpf,departamento_numero)";
  $sql2 = "VALUES(";
  if($nome!= ''){  $sql2 = $sql2.'"'.$nome.'",'; }else{ $sql2 = $sql2.'NULL,'; }
  if($dataNascimento!= ''){  $sql2 = $sql2.'"'.$dataNascimento.'",'; }else{ $sql2 = $sql2.'NULL,'; }
  if($cpf!= ''){  $sql2 = $sql2.'"'.$cpf.'",'; }else{ $sql2 = $sql2.'NULL,'; }
  if($endereco!= ''){  $sql2 = $sql2.'"'.$endereco.'",'; }else{ $sql2 = $sql2.'NULL,'; }
  if($sexo!= ''){  $sql2 = $sql2.'"'.$sexo.'",'; }else{ $sql2 = $sql2.'NULL,'; }
  if($salario!= ''){  $sql2 = $sql2.'"'.$salario.'",'; }else{ $sql2 = $sql2.'NULL,'; }
  if($datainicio!= ''){  $sql2 = $sql2.'"'.$datainicio.'",'; }else{ $sql2 = $sql2.'NULL,'; }
  if($funcionario_cpf!= ''){  $sql2 = $sql2.'"'.$funcionario_cpf.'",'; }else{ $sql2 = $sql2.'NULL,'; }
  if($departamento_numero!= ''){  $sql2 = $sql2.'"'.$departamento_numero.'")'; }else{ $sql2 = $sql2.'NULL)'; }    echo $sql1.$sql2; 

  $result = mysqli_query($link2,$sql1.$sql2);
?>