package ru.vyukov.contract

import groovy.ru.vyukov.contract.TestPatterns
import org.springframework.cloud.contract.spec.internal.DslProperty
import org.springframework.scheduling.support.CronSequenceGenerator

import java.util.regex.Matcher

class ExPatternsTest extends GroovyTestCase {
    private TestPatterns patterns = new TestPatterns();


    void testAnyCronExpression() {
        patterns = new TestPatterns()
        DslProperty dslProperty = patterns.anyCronExpression();
        String value = dslProperty.getServerValue().toString();
        new CronSequenceGenerator(value);
    }

    void testAnyPositiveNumber() {
        DslProperty dslProperty = patterns.anyPositiveNumber();
        String number = dslProperty.getServerValue().toString();
        assertTrue(Integer.parseInt(number) > 0);
    }


    void testAnyShortPositiveNaturalNumber() {
        DslProperty dslProperty = patterns.anyShortPositiveNaturalNumber();
        String number = dslProperty.getServerValue().toString();
        def val = Integer.parseInt(number)
        assertTrue(val > 0);
        assertTrue(val < Short.MAX_VALUE);
    }


    void testAnyNetworkPort() {
        DslProperty dslProperty = patterns.anyNetworkPort();
        String number = dslProperty.getServerValue().toString();
        def val = Integer.parseInt(number)
        assertTrue(val > 0);
        assertTrue(val < 65535);
    }

    void testAnyMongoObjectId() {
        DslProperty dslProperty = patterns.anyMongoObjectId();
        String objectId = dslProperty.getServerValue().toString();
        assertTrue(objectId.length() == 24);

        def integer = new BigInteger(objectId, 16)
        assertTrue(integer > 0);
    }

}