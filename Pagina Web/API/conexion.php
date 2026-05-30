<?php
// =======================================================
// CABECERAS (CORS + JSON)
// =======================================================
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type, Authorization");
header("Content-Type: application/json; charset=UTF-8");

// Preflight request (CORS)
if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    exit(0);
}

// =======================================================
// CONFIGURACIÓN POSTGRESQL (MV)
// =======================================================
$host = "10.192.91.220";
$port = "5432";
$db_name = "MyFitArena";
$username = "postgres";
$password = "1234";

// =======================================================
// CONEXIÓN PDO POSTGRESQL
// =======================================================
try {
    $conexion = new PDO(
        "pgsql:host=$host;port=$port;dbname=$db_name",
        $username,
        $password
    );

    // Modo de errores
    $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // Resultados como arrays asociativos
    $conexion->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);

} catch (PDOException $exception) {
    http_response_code(500);

    echo json_encode([
        "mensaje" => "Error de conexión a la base de datos",
        "error" => $exception->getMessage()
    ]);

    exit();
}
?>