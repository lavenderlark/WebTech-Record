<?php
// Database configuration
$host = "localhost";
$username = "root";
$password = "";
$dbname = "jewelry_db";

// Create connection
$conn = new mysqli($host, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Create table if it doesn't exist
$tableQuery = "CREATE TABLE IF NOT EXISTS jewelry (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description TEXT
)";
$conn->query($tableQuery);

// Handle form submission
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $name = $conn->real_escape_string($_POST['name']);
    $type = $conn->real_escape_string($_POST['type']);
    $price = $conn->real_escape_string($_POST['price']);
    $description = $conn->real_escape_string($_POST['description']);

    $insertQuery = "INSERT INTO jewelry (name, type, price, description) VALUES ('$name', '$type', '$price', '$description')";
    if ($conn->query($insertQuery) === TRUE) {
        echo "<p style='color: #8e44ad;'>Jewelry item added successfully!</p>";
    } else {
        echo "<p style='color: red;'>Error: " . $conn->error . "</p>";
    }
}

// Fetch records and display
$result = $conn->query("SELECT * FROM jewelry");
if ($result->num_rows > 0) {
    echo "<table>";
    echo "<tr><th>Name</th><th>Type</th><th>Price (USD)</th><th>Description</th></tr>";
    while ($row = $result->fetch_assoc()) {
        echo "<tr>
                <td>" . htmlspecialchars($row['name']) . "</td>
                <td>" . htmlspecialchars($row['type']) . "</td>
                <td>" . htmlspecialchars($row['price']) . "</td>
                <td>" . htmlspecialchars($row['description']) . "</td>
              </tr>";
    }
    echo "</table>";
} else {
    echo "<p>No jewelry items available.</p>";
}

$conn->close();
?>
