package br.com.brasilprev.util;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/pessoas.feature",
        glue = {"br/com/brasilprev/testesfuncionais"},
        monochrome = true,
        strict = true
)
public class Runner {

}
