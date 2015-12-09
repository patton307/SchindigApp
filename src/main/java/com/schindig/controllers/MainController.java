package com.schindig.controllers;
import com.schindig.entities.Wizard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Agronis on 12/9/15.
 */
@Controller
public class MainController {

    @Autowired
    Wizard wizard;

    
}
