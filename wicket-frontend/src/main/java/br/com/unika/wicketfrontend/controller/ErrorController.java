package br.com.unika.wicketfrontend.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // Aqui você pode personalizar a lógica para diferentes tipos de erro
        return "error"; // Nome do template da página de erro
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
