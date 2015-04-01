<?php

  $banco = "DataBase";
  $usuario = "root";
  $senha = "";
  $hostname = "localhost";
  $link2 = mysqli_connect($hostname, $usuario, $senha, $banco) or die ("Erro ao conectar!<br>");

   $nome = $_POST['nome'];


   $funcionario_cpf = $_POST['funcionario_cpf'];


  $sql1 = "INSERT INTO departamento (nome,numero,funcionario_cpf)";
  $sql2 = "VALUES(";
  if($nome!= ''){  $sql2 = $sql2.'"'.$nome.'",'; }else{ $sql2 = $sql2.'NULL,'; }
  if($numero!= ''){  $sql2 = $sql2.'"'.$numero.'",'; }else{ $sql2 = $sql2.'NULL,'; }
  if($funcionario_cpf!= ''){  $sql2 = $sql2.'"'.$funcionario_cpf.'")'; }else{ $sql2 = $sql2.'NULL)'; }    echo $sql1.$sql2; 

  $result = mysqli_query($link2,$sql1.$sql2);

   $localizacoes = $_POST['localizacoes'];
   foreach($departamento_localizacoes as $item){
    $sql = "INSERT INTO departamento_localizacoes(localizacoes,departamento_numero) VALUES ("; 
  if($item != ''){$sql = $sql.'"'.$item.'",';}else{$sql = $sql.'NULL,';}

  if($numero!= ''){  $sql = $sql.'"'.$numero.'"'; }else{ $sql = $sql.'NULL'; }
$sql = $sql.')';

    echo $sql;     $result = mysqli_query($link2,$sql);
    }

?>