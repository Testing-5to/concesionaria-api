package com.autos.concesionaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Redirecciona la página principal a la documentación de la API REST
 */
@Controller
public class OASController {

    /**
     * Setea el mapping de la raiz del proyecto a la documentacion de la API.
     *
     * @return String con la direccion de la documentacion de la API.
     */
    @RequestMapping("/")
    public String index() {
        return "redirect:swagger-ui/index.html";
    }

}
