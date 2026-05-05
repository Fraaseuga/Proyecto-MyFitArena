<?php
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

// Incluir el archivo de conexión
include_once 'conexion.php';

// Detectar el método de la solicitud (GET, POST, PUT, DELETE)
$metodo = $_SERVER['REQUEST_METHOD'];

switch($metodo) {
    case 'GET':
        // ============================================
        // READ: OBTENER TODOS LOS TICKETS
        // ============================================
        try {
            $query = "SELECT id, nombre, asunto, mensaje, 
                      DATE_FORMAT(fecha_creacion, '%d/%m/%Y %H:%i') as fecha_creacion,
                      DATE_FORMAT(fecha_actualizacion, '%d/%m/%Y %H:%i') as fecha_actualizacion
                      FROM tickets
                      ORDER BY id ASC";
            $stmt = $conexion->prepare($query);
            $stmt->execute();
            
            $tickets = $stmt->fetchAll();
            
            http_response_code(200); // OK
            echo json_encode($tickets);
        } catch(PDOException $e) {
            http_response_code(500);
            echo json_encode(["mensaje" => "Error al obtener tickets: " . $e->getMessage()]);
        }
        break;

    case 'POST':
        // ============================================
        // CREATE: AÑADIR UN NUEVO TICKET
        // ============================================
        // Leer los datos en formato JSON enviados en el body
        $datos = json_decode(file_get_contents("php://input"));
        
        if(!empty($datos->nombre) && !empty($datos->email) && !empty($datos->asunto) && !empty($datos->mensaje)) {
            try {
                $query = "INSERT INTO tickets (nombre, email, asunto, mensaje) 
                         VALUES (:nombre, :email, :asunto, :mensaje)";
                $stmt = $conexion->prepare($query);
                
                // Limpiar datos
                $nombre = htmlspecialchars(strip_tags($datos->nombre));
                $email = htmlspecialchars(strip_tags($datos->email));
                $asunto = htmlspecialchars(strip_tags($datos->asunto));
                $mensaje = htmlspecialchars(strip_tags($datos->mensaje));
                
                // Bind de los parámetros
                $stmt->bindParam(":nombre", $nombre);
                $stmt->bindParam(":email", $email);
                $stmt->bindParam(":asunto", $asunto);
                $stmt->bindParam(":mensaje", $mensaje);
                
                if($stmt->execute()) {
                    http_response_code(201); // Created
                    echo json_encode([
                        "mensaje" => "Ticket creado correctamente.", 
                        "id" => $conexion->lastInsertId(),
                        "exito" => true
                    ]);
                } else {
                    http_response_code(503); // Service Unavailable
                    echo json_encode(["mensaje" => "No se pudo crear el ticket.", "exito" => false]);
                }
            } catch(PDOException $e) {
                http_response_code(500);
                echo json_encode(["mensaje" => "Error al insertar: " . $e->getMessage(), "exito" => false]);
            }
        } else {
            http_response_code(400); // Bad Request
            echo json_encode(["mensaje" => "Datos incompletos. Se requieren nombre, email, asunto y mensaje.", "exito" => false]);
        }
        break;

    // case 'PUT':
    //     // ============================================
    //     // UPDATE: ACTUALIZAR ESTADO DE UN TICKET
    //     // ============================================
    //     $datos = json_decode(file_get_contents("php://input"));
        
    //     if(!empty($datos->id) && !empty($datos->estado)) {
    //         try {
    //             $query = "UPDATE tickets SET estado = :estado WHERE id = :id";
    //             $stmt = $conexion->prepare($query);
                
    //             $id = htmlspecialchars(strip_tags($datos->id));
    //             $estado = htmlspecialchars(strip_tags($datos->estado));
                
    //             $stmt->bindParam(":id", $id);
    //             $stmt->bindParam(":estado", $estado);
                
    //             if($stmt->execute()){
    //                 if($stmt->rowCount() > 0) {
    //                     http_response_code(200); // OK
    //                     echo json_encode(["mensaje" => "Ticket actualizado correctamente.", "exito" => true]);
    //                 } else {
    //                     http_response_code(404); // Not Found
    //                     echo json_encode(["mensaje" => "No se encontró el ticket o no hubo cambios.", "exito" => false]);
    //                 }
    //             } else {
    //                 http_response_code(503);
    //                 echo json_encode(["mensaje" => "No se pudo actualizar el ticket.", "exito" => false]);
    //             }
    //         } catch(PDOException $e) {
    //             http_response_code(500);
    //             echo json_encode(["mensaje" => "Error al actualizar: " . $e->getMessage(), "exito" => false]);
    //         }
    //     } else {
    //         http_response_code(400);
    //         echo json_encode(["mensaje" => "Datos incompletos, asegúrate de proporcionar el ID y el estado.", "exito" => false]);
    //     }
    //     break;

    case 'DELETE':
        // ============================================
        // DELETE: ELIMINAR UN TICKET POR SU ID
        // ============================================
        $datos = json_decode(file_get_contents("php://input"));
        
        if(!empty($datos->id)) {
            try {
                $query = "DELETE FROM tickets WHERE id = :id";
                $stmt = $conexion->prepare($query);
                
                $id = htmlspecialchars(strip_tags($datos->id));
                $stmt->bindParam(":id", $id);
                
                if($stmt->execute()) {
                    if($stmt->rowCount() > 0) {
                        http_response_code(200);
                        echo json_encode(["mensaje" => "Ticket eliminado correctamente.", "exito" => true]);
                    } else {
                        http_response_code(404);
                        echo json_encode(["mensaje" => "Ticket no encontrado.", "exito" => false]);
                    }
                } else {
                    http_response_code(503);
                    echo json_encode(["mensaje" => "No se pudo eliminar el ticket.", "exito" => false]);
                }
            } catch(PDOException $e) {
                http_response_code(500);
                echo json_encode(["mensaje" => "Error al eliminar: " . $e->getMessage(), "exito" => false]);
            }
        } else {
            http_response_code(400);
            echo json_encode(["mensaje" => "ID no proporcionado para la eliminación.", "exito" => false]);
        }
        break;

    default:
        // Método no soportado
        http_response_code(405); // Method Not Allowed
        echo json_encode(["mensaje" => "Método no permitido."]);
        break;
}
?>