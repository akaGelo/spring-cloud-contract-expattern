package ru.vyukov.contract;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.cloud.contract.spec.internal.DslProperty;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;
import spock.lang.Specification;

import java.util.regex.Matcher;

import static org.junit.Assert.*;


class ExPatternValueDslPropertyTest extends  GroovyTestCase{

    private final ExPatternValueDslProperty patternValueDslProperty = new ExPatternValueDslProperty();

    void testRepeat(){
        for (int  i  = 0; i<10_000;i++){
            testAnyCronExpression();
        }
    }

    void testAnyCronExpression() {
        DslProperty dslProperty = patternValueDslProperty.anyCronExpression();
        String value = dslProperty.getServerValue().toString();
        Matcher matcher = ExRegexPatterns.CRON_EXPRESSION.matcher(value);
        assertTrue(matcher.matches());
    }

    void testAnyPositiveNumber() {
        DslProperty dslProperty = patternValueDslProperty.anyPositiveNumber();
        String number = dslProperty.getServerValue().toString();
        assertTrue(Integer.parseInt(number) > 0);
    }



}