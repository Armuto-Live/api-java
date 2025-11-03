package com.lta.apis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
- Controller: Una clase en JAVA que gestiona las solicitudes HTPP(GET, POST, PUT, DELETE)
    que llega a la aplicación web o API, puente entre el cliente y la lógica de negocio o servicios
    -> Es donde defines que hacer cuando alguien visita cierta URL

- Controlador REST: Clase en Java que expone rutas HTTP para
    que otros sistemas puedan enviarle peticiones y recibir respuestas en formato JSON

    - Indica que esta clase es un controlador REST
    - Combina @Controller y @ResponseBody
    - No vistas HTML
*/

// Indicamos que esta clase es un controlador web en APIs REST
@RestController

// configuramos una url base para todos los métodos del controlador
@RequestMapping("/micontroller")
public class SaludoController {

    @GetMapping("saludo")
    public String saludar(){
        return "Hola :V";
    }
}
