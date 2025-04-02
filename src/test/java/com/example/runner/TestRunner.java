package com.example.runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features= {"src/test/resources/features"}
        ,glue = {"com.example.stepdefenictions", "com.example.config"}
        //,dryRun = true
        ,monochrome = true
        ,snippets = CucumberOptions.SnippetType.CAMELCASE
        ,tags = "@User"
        //,publish = true

)

public class TestRunner {
}
