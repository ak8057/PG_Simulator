package com.payu.payu_sim.config;

import java.io.InputStream;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import com.payu.payu_sim.model.RulesConfig;

@Component
public class YamlRuleLoader {

    private RulesConfig rulesConfig;


    public YamlRuleLoader() {

        loadRules();
    }


    public void loadRules() {

        try {

            Yaml yaml = new Yaml();

            InputStream inputStream =
                    getClass()
                    .getClassLoader()
                    .getResourceAsStream("simulator-rules.yml");

            rulesConfig = yaml.loadAs(inputStream, RulesConfig.class);

            System.out.println("Rules reloaded successfully");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public RulesConfig getRulesConfig() {

        return rulesConfig;
    }
}
