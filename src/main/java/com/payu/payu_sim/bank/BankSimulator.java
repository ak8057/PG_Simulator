package com.payu.payu_sim.bank;

import org.springframework.stereotype.Component;

import com.payu.payu_sim.config.YamlRuleLoader;
import com.payu.payu_sim.model.Rule;

@Component
public class BankSimulator {

    private final YamlRuleLoader loader;


    public BankSimulator(YamlRuleLoader loader) {

        this.loader = loader;
    }


    public String authorize(String cardNumber, double amount) {

        for (Rule rule : loader.getRulesConfig().getRules()) {

            if (rule.getCardPrefix() != null &&
                cardNumber.startsWith(rule.getCardPrefix())) {

                return rule.getResponse();
            }

            if (rule.getAmountGreaterThan() != null &&
                amount > rule.getAmountGreaterThan()) {

                return rule.getResponse();
            }
        }

        return "APPROVED";
    }
}
