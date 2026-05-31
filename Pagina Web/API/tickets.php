<?php
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

// Incluir conexión
include_once 'conexion.php';

// Detectar método HTTP
$metodo = $_SERVER['REQUEST_METHOD'];

switch($metodo) {

    // ====================================================
    // GET - OBTENER TODOS LOS TICKETS
    // ====================================================
    case 'GET':
        try {
            $query = "SELECT id, nombre, asunto, mensaje,
                      TO_CHAR(fecha_creacion, 'DD/MM/YYYY HH24:MI') as fecha_creacion,
                      TO_CHAR(fecha_actualizacion, 'DD/MM/YYYY HH24:MI') as fecha_actualizacion
                      FROM tickets
                      ORDER BY id ASC";

            $stmt = $conexion->prepare($query);
            $stmt->execute();

            $tickets = $stmt->fetchAll();

            http_response_code(200);
            echo json_encode($tickets);

        } catch(PDOException $e) {
            http_response_code(500);
            echo json_encode([
                "mensaje" => "Error al obtener tickets: " . $e->getMessage()
            ]);
        }
        break;

    // ====================================================
    // POST - CREAR TICKET
    // ====================================================
    case 'POST':

        $datos = json_decode(file_get_contents("php://input"));

        if(!empty($datos->nombre) && !empty($datos->email) && !empty($datos->asunto) && !empty($datos->mensaje)) {

            try {
                $query = "INSERT INTO tickets (nombre, email, asunto, mensaje)
                          VALUES (:nombre, :email, :asunto, :mensaje)
                          RETURNING id";

                $stmt = $conexion->prepare($query);

                $nombre = htmlspecialchars(strip_tags($datos->nombre));
                $email = htmlspecialchars(strip_tags($datos->email));
                $asunto = htmlspecialchars(strip_tags($datos->asunto));
                $mensaje = htmlspecialchars(strip_tags($datos->mensaje));

                $stmt->bindParam(":nombre", $nombre);
                $stmt->bindParam(":email", $email);
                $stmt->bindParam(":asunto", $asunto);
                $stmt->bindParam(":mensaje", $mensaje);

                $stmt->execute();

                $id = $stmt->fetchColumn();

                http_response_code(201);
                echo json_encode([
                    "mensaje" => "Ticket creado correctamente",
                    "id" => $id,
                    "exito" => true
                ]);

            } catch(PDOException $e) {
                http_response_code(500);
                echo json_encode([
                    "mensaje" => "Error al insertar: " . $e->getMessage(),
                    "exito" => false
                ]);
            }

        } else {
            http_response_code(400);
            echo json_encode([
                "mensaje" => "Datos incompletos",
                "exito" => false
            ]);
        }
        break;

    // ====================================================
    // DELETE - ELIMINAR TICKET
    // ====================================================
    case 'DELETE':

        $datos = json_decode(file_get_contents("php://input"));

        if(!empty($datos->id)) {

            try {
                $query = "DELETE FROM tickets WHERE id = :id";
                $stmt = $conexion->prepare($query);

                $id = htmlspecialchars(strip_tags($datos->id));
                $stmt->bindParam(":id", $id);

                $stmt->execute();

                if($stmt->rowCount() > 0) {
                    http_response_code(200);
                    echo json_encode([
                        "mensaje" => "Ticket eliminado correctamente",
                        "exito" => true
                    ]);
                } else {
                    http_response_code(404);
                    echo json_encode([
                        "mensaje" => "Ticket no encontrado",
                        "exito" => false
                    ]);
                }

            } catch(PDOException $e) {
                http_response_code(500);
                echo json_encode([
                    "mensaje" => "Error al eliminar: " . $e->getMessage(),
                    "exito" => false
                ]);
            }

        } else {
            http_response_code(400);
            echo json_encode([
                "mensaje" => "ID no proporcionado",
                "exito" => false
            ]);
        }
        break;

    // ====================================================
    // MÉTODO NO PERMITIDO
    // ====================================================
    default:
        http_response_code(405);
        echo json_encode([
            "mensaje" => "Método no permitido"
        ]);
        break;
}
?>