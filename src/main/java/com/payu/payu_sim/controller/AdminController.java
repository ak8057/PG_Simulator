package com.payu.payu_sim.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payu.payu_sim.config.YamlRuleLoader;

@RestController
public class AdminController {

    private final YamlRuleLoader loader;


    public AdminController(YamlRuleLoader loader) {

        this.loader = loader;
    }


    @PostMapping("/admin/reload-rules")
    public String reloadRules() {

        loader.loadRules();

        return "Rules reloaded successfully";
    }
}
