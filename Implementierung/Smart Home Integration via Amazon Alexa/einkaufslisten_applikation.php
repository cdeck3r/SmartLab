<?php

//BEISPIEL
//localhost/einkaufslistengenerator/einkaufslistengenerator.php?user=fabio&command=get
//localhost/einkaufslistengenerator/einkaufslistengenerator.php?user=fabio&command=list
//localhost/einkaufslistengenerator/einkaufslistengenerator.php?user=fabio&command=reset
//localhost/einkaufslistengenerator/einkaufslistengenerator.php?user=fabio&command=add&product=milch
//localhost/einkaufslistengenerator/einkaufslistengenerator.php?user=fabio&command=remove&product=milch
//localhost/einkaufslistengenerator/einkaufslistengenerator.php?user=fabio&command=get

//BEISPIEL
//milch, käse usw.

//Host = "localhost"
//Username = "root"
//Passwort = "Thierry"
//Datenbankname = "einkaufslistengenerator"

$connect = new mysqli('localhost', 'root', 'Thierry', 'einkaufslistengenerator');
$connect->query('Set NAMES "utf8"');

//Bitte keine SQL Injection
$user = $connect->real_escape_string($_GET["user"]);
$command = $connect->real_escape_string($_GET["command"]);
$product = $connect->real_escape_string($_GET["product"]);
$product_id = ""; 

$result = array();
$result["result"] = "";
$string = "";
//$result["log"] = "user=" . $user . ", command=" . $command . ", product=" . $product;

if($command === "add")
{
	$query = "SELECT produkt_id FROM produkt WHERE Bezeichnung = '$product'";
	$sql = $connect->query($query);
	
	if($row = $sql->fetch_assoc())
	{
		$product_id = $row['produkt_id'];
		
		$query = "SELECT * FROM temp_einkaufsliste WHERE kunden_id = 1 AND produkt_id = '$product_id'";
		$sql = $connect->query($query);
		$row = $sql->fetch_assoc();
				
		if(!$row['produkt_id'])
		{
			$query = "INSERT INTO temp_einkaufsliste(kunden_id, produkt_id) VALUES('1', '$product_id')";
			$sql = $connect->query($query);
			
			$result["result"] = "ok";
		}
		else
		{
			$result["result"] = "Produkt ist schon in der Liste";
		}		
	}
	else
	{
		$message = 'Produkt existiert nicht auf der Liste';
		die($message);
	}
}

if($command === "remove")
{
	$query = "SELECT produkt_id FROM produkt WHERE Bezeichnung = '$product'";
	$sql = $connect->query($query);
	
	if($row = $sql->fetch_assoc())
	{
		$product_id = $row['produkt_id'];
		
		$query = "DELETE FROM temp_einkaufsliste WHERE kunden_id = 1 AND produkt_id = '$product_id'";
		$sql = $connect->query($query);
		
		$result["result"] = "ok";
	}
}

if($command === "reset")
{
	$query = "DELETE FROM temp_einkaufsliste WHERE kunden_id = 1";
	$sql = $connect->query($query);
	
	$result["result"] = "ok";
}

if($command === "list")
{
	$query = "SELECT produkt_id FROM temp_einkaufsliste WHERE kunden_id = 1";
	$sql = $connect->query($query);
	
	if($num_rows = $sql->fetch_assoc() > 0)
	{
		$sql = $connect->query($query);
			
		while($row = $sql->fetch_assoc() )
		{
			$product_id = $row['produkt_id'];
			$query = "SELECT Bezeichnung FROM produkt WHERE produkt_id = '$product_id'";
			$result2 = $connect->query($query);
			$row2 = $result2->fetch_assoc();
			
			$string .= $row2['Bezeichnung'].', ';			
		}
		
		$result["result"] = $string;
	}
	else
	{
		$result["result"] = "null";
	}
	
	//echo $string;
	/*$productAsArray = explode(",", $string);
	$result["result"] = $string;*/
	
	//$firstnamesAsArray = explode(",", $string);
	
}

/*$query = "SELECT * FROM weristdran WHERE user = '$user' ";
$sql = $connect->query($query);

if($row = $sql->fetch_assoc())
{
	$products = $row["product"];
	
	if($command === "get")
	{
		//Rückgabe eines zufälligen vornames 
	}
	
	else if($command === "list")
	{
		//Alle Elemenete in einem Array
		if($products === "")
		{
			$result["result"] = "";
		}
		else
		{
			$productAsArray = explode(",", $products);
			$result["result"] = $productAsArray;
		}
	}
	
	else if($command === "add")
	{
		if($products === "")
		{
			// Feld leer neuen Namen eintragen
			$query = "UPDATE weristdran SET product = '$product' WHERE user = '$user'";
			$sql = $connect->query($query);
		}
		else
		{
			// a, b, c, d ["a", "b", "c", "d"]
			$productAsArray = explode(",", $products);
			
			if(!in_array($product, $productAsArray))
			{
				array_push($productAsArray, $product);
				$products = implode(",", $productAsArray);
				
				$query = "UPDATE weristdran SET product = '$products' WHERE user = '$user'";
				$sql = $connect->query($query);
			}
		}
		
		$result["result"] = "ok";
	}
	
	else if($command === "remove")
	{
		$productAsArray = explode(",", $products);
		$pos = array_search($product, $productAsArray);
		
		if($pos !== false)
		{
			unset($productAsArray[$pos]);
			$products = implode(",", $productAsArray);
			
			$query = "UPDATE weristdran SET product = '$products' WHERE user = '$user'";
			$sql = $connect->query($query);
		
			$result["result"] = "ok";
		}
	}
	
	else if($command === "reset")
	{
		$query = "UPDATE weristdran SET product = '' WHERE user = '$user'";
		$sql = $connect->query($query);
		
		$result["result"] = "ok";
	}
}
else
{
	//Wenn beim ersten Mal noch kein Treffer für user gefunden wird
	if($command === "add")
	{
		//$query = "INSERT INTO weristdran(user, product) VALUES('$user', '$product')";
		//$sql = $connect->query($query);
		
		$sql = $connect->prepare("INSERT INTO weristdran(user, product) VALUES(?, ?)") ;
		// ss = String , i = integer
		$sql->bind_param("ss", $user, $product);
		$sql->execute();
		$sql->close();
		
		$result["result"] = "ok";
	}
}*/

print json_encode($result);



$connect->close();

?>