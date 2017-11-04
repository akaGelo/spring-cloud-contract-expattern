package ru.vyukov.contract

import groovy.ru.vyukov.contract.TestPatterns
import org.springframework.cloud.contract.spec.internal.DslProperty

import java.util.regex.Matcher

class ExPatternsTest extends GroovyTestCase {
    private TestPatterns patterns  = new TestPatterns();


    void testRepeat() {
        for (int i = 0; i < 10_000; i++) {
            testAnyCronExpression();
        }
    }

    void testAnyCronExpression() {
        patterns = new TestPatterns()
        DslProperty dslProperty = patterns.anyCronExpression();
        String value = dslProperty.getServerValue().toString();
        Matcher matcher = ExRegexPatterns.CRON_EXPRESSION.matcher(value);
        assertTrue(matcher.matches());
    }

    void testAnyPositiveNumber() {
        DslProperty dslProperty = patterns.anyPositiveNumber();
        String number = dslProperty.getServerValue().toString();
        assertTrue(Integer.parseInt(number) > 0);
    }


}