<?php

/*
  Plugin Name: Product Catalog Demo
  Plugin URI: http://www.example.com
  Description: An example plugin for interacting with a product catalog.
  Author: Michael
  Version: 1.0
  Author URI: http://www.example.com
 */

global $jal_db_version;
$jal_db_version = '1.0';

function jal_install() {
    global $wpdb;
    global $jal_db_version;

    $table_name = $wpdb->prefix . 'product';

    $charset_collate = $wpdb->get_charset_collate();

    $sql = "CREATE TABLE $table_name (
		product_id INT NOT NULL AUTO_INCREMENT,
        category_id INT NOT NULL,
        name VARCHAR(20) NOT NULL,
        summary VARCHAR(50) NOT NULL,
        description VARCHAR(500),
        price VARCHAR(45) DEFAULT '0.00' NOT NULL,
        qty INT DEFAULT 0 NOT NULL,
        PRIMARY KEY (product_id)
	) $charset_collate;";

    require_once( ABSPATH . 'wp-admin/includes/upgrade.php' );
    dbDelta($sql);

    add_option('jal_db_version', $jal_db_version);
}

function jal_install_data() {
    global $wpdb;

    $table_name = $wpdb->prefix . 'product';

    for ($x = 1; $x <= 10; $x++) {
        $wpdb->insert(
                $table_name, array(
            'category_id' => $x,
            'name' => 'Product Name ' . $x,
            'summary' => 'Product Summary ' . $x,
            'description' => 'Product Description ' . $x,
            'price' => '0.00',
            'qty' => $x,
                )
        );
    }
}

register_activation_hook(__FILE__, 'jal_install');
register_activation_hook(__FILE__, 'jal_install_data');

add_action('admin_menu', 'productcatplugin_setup_menu');
function productcatplugin_setup_menu() {
 add_menu_page('Product Catalog Plugin Page', // Page Title
            'Product Catalog Plugin', // Menu Title
            'manage_options', // Capabilities
            'productcat-plugin', // Menu Slug
            'productcatplugin_list');  // Function

 	//this is a submenu
    add_submenu_page('productcat-plugin', //parent slug
            'Add New Product', //page title
            'Add New', //menu title
            'manage_options', //capability
            'productcatplugin_add', //menu slug
            'productcatplugin_add');  //function            
	//this submenu is HIDDEN, however, we need to add it anyways
    add_submenu_page('productcat-plugin', //parent slug
            'Update Product', //page title
            'Update Product', //menu title
            'manage_options', //capability
            'productcatplugin_update', //menu slug
            'productcatplugin_update'); //function	            
}

function productcatplugin_list() {
echo "<h1>Product Catalog List!</h1>";

    echo "<div class='wrap'>";
    echo "<h4>Product Catalog Plugin Example</h4>";
    echo "<table class='widefat'>";
    echo "<thead>";
    echo "<tr>";
    echo "<th>Product ID</th>";
    echo "<th>Product Name</th>";
    echo "<th>Product Summary</th>";
    echo "<th>Product Description</th>";
    echo "<th>Product Price</th>";
    echo "<th>Product Qty</th>";
    echo "</tr>";
    echo "</thead>";
    echo "<tfoot>";
    echo "<tr>";
    echo "<th>Product ID</th>";
    echo "<th>Product Name</th>";
    echo "<th>Product Summary</th>";
    echo "<th>Product Description</th>";
    echo "<th>Product Price</th>";
    echo "<th>Product Qty</th>";
    echo "</tr>";
    echo "</tfoot>";
    echo "<tbody>";
    
    // Let's Query the WP database
    global $wpdb;
    $table_name = $wpdb->prefix . "product";
    $myproductlist = $wpdb->get_results("
								SELECT product_id, name, summary, description, price, qty
								FROM $table_name");
								
 	// Let's display our results in our table
    // Loop through results
    foreach ($myproductlist as $myproduct) {
        echo "<tr>";
        echo "<td>" . $myproduct->product_id . 	"</td>";
        echo "<td>" . $myproduct->name . 		"</td>";
        echo "<td>" . $myproduct->summary . 	"</td>";
        echo "<td>" . $myproduct->description . "</td>";
        echo "<td>" . $myproduct->price . 		"</td>";
        echo "<td>" . $myproduct->qty . 		"</td>";
        echo "</tr>";
    }						
    
    echo "</tbody>";
    echo "</table>";
    echo "</div>";		

}

function productcatplugin_add(){
    $name = $_POST["name"];
    $summary = $_POST["summary"];
    $description = $_POST["description"];
    $price = $_POST["price"];
    $qty = $_POST["qty"];
    
	 //insert
    if (isset($_POST['insert'])) {
        global $wpdb;
        $table_name = $wpdb->prefix . "product";
        $wpdb->insert(
                $table_name, //table
                array(
            'category_id' => 0,
            'name' => $name,
            'summary' => $summary,
            'description' => $description,
            'price' => $price,
            'qty' => $qty,
                )
        );

        $message .= "Product inserted";
   	 }

 		echo "<div class='wrap'>";
    	echo "<h2>Add New Product</h2>";
    	
    if ($_POST['insert']) {
        echo "<div class='updated'><p>Product Added</p></div>";
        echo "<a href=";
        echo admin_url('admin.php?page=productcat-plugin');
        echo">&laquo; Back to Product list</a>";
    } else {
 		echo "<form method='post' action=" . $_SERVER['REQUEST_URI'] . ">";
        echo "<table class='wp-list-table widefat fixed'>";
        echo "<tr>";
        echo "<th>Name</th>";
        echo "<td><input type='text' name='name' value='" . $name . "' /></td>";
        echo "</tr>";
        echo "<tr>";
        echo "<th>Summary</th>";
        echo "<td><input type='text' name='summary' value='" . $summary . "' /></td>";
        echo "</tr>";
        echo "<tr>";
        echo "<th>Description</th>";
        echo "<td><input	 type='text' name='description' value='" . $description . "' /></td>";
        echo "</tr>";
        echo "<tr>";
        echo "<th>Price (#.##)</th>";
        echo "<td><input type='text' name='price' value='" . $price . "' /></td>";
        echo "</tr>";
        echo "<tr>";
        echo "<th>Qty</th>";
        echo "<td><input type='text' name='qty' value='" . $qty . "' /></td>";
        echo "</tr>";
        echo "</table>";
        echo "<input type='submit' name='insert' value='Save' class='button'>";
        echo "</form>";
        echo "</div>";
        }
}

function productcatplugin_update() {
    global $wpdb;
    $table_name = $wpdb->prefix . "product";
  
    $id = $_POST["id"];
    $name = $_POST["name"];
    $summary = $_POST["summary"];
    $description = $_POST["description"];
    $price = $_POST["price"];
    $qty = $_POST["qty"];

    if (isset($_POST['update'])) {
        $wpdb->update(
                $table_name, //table
                array('name' => $name),  //values sets
                array('product_id' => $id) //where
        );
        $message .= "Product updated";
    }elseif (isset($_POST['delete'])) {
        $wpdb->query($wpdb->prepare("DELETE FROM $table_name WHERE product_id = %s", $id));
        $message .= "Product deleted";
    }

    echo "<div class='wrap'>";
    echo "<h2>Update Product</h2>";

    if ($_POST['update']) {
        echo "<div class='updated'><p>Product updated</p></div>";
        echo "<a href=";
        echo admin_url('admin.php?page=productcat-plugin');
        echo">&laquo; Back to Product list</a>";
    } elseif ($_POST['delete']) {
        echo "<div class='updated'><p>Product Deleted</p></div>";
        echo "<a href=";
        echo admin_url('admin.php?page=productcat-plugin');
        echo">&laquo; Back to Product list</a>";
    } else {
        echo "<form method='post' action=" . $_SERVER['REQUEST_URI'] . ">";
        echo "<table class='wp-list-table widefat fixed'>";
        echo "<tr>";
        echo "<th>id</th>";
        echo "<td><input type='text' name='id' value='" . $id . "' /></td>";
        echo "</tr>";
        echo "<tr>";
        echo "<th>Name</th>";
        echo "<td><input type='text' name='name' value='" . $name . "' /></td>";
        echo "</tr>";
        echo "<tr>";
        echo "<th>Summary</th>";
        echo "<td><input type='text' name='summary' value='" . $summary . "' /></td>";
        echo "</tr>";
        echo "<tr>";
        echo "<th>Description</th>";
        echo "<td><input type='text' name='description' value='" . $description . "' /></td>";
        echo "</tr>";
        echo "<tr>";
        echo "<th>Price (#.##)</th>";
        echo "<td><input type='text' name='price' value='" . $price . "' /></td>";
        echo "</tr>";
        echo "<tr>";
        echo "<th>Qty</th>";
        echo "<td><input type='text' name='qty' value='" . $qty . "' /></td>";
        echo "</tr>";
        echo "</table>";
        echo "<input type='submit' name='update' value='Save' class='button'> &nbsp;&nbsp;";
        echo "<input type='submit' name='delete' value='Delete' class='button'> &nbsp;&nbsp;";      
        echo "</form>";
    }

    echo "</div>";
}

define('ROOTDIR', plugin_dir_path(__FILE__));
require_once(ROOTDIR . 'prodcat-list.php');
require_once(ROOTDIR . 'prodcat-create.php');
require_once(ROOTDIR . 'prodcat-update.php');

?>
