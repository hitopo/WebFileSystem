<?php
/*
Uploadify
Copyright (c) 2012 Reactive Apps, Ronnie Garcia
Released under the MIT License <http://www.opensource.org/licenses/mit-license.php> 
*/
//header('Content-Type:text/html;charset=utf-8');
// Define a destination
$targetFolder = 'E:/uploads/'.$_POST['userId'].'/'; // Relative to the root and should match the upload folder in the uploader script
//$_SERVER['DOCUMENT_ROOT'] . 

if (file_exists($targetFolder . '/' . $_POST['filename'])) {
	echo 1;
} else { 
	echo 0;
}
?>