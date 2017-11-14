package ru.vyukov.contract

import spock.lang.Specification

import java.util.regex.Pattern

class ExRegexPatternsSpecTest extends Specification {

    ExRegexPatterns patterns = new ExRegexPatterns()


    def "should generate a regex for a positive number [#textToMatch] that is a match [#shouldMatch]"() {
        expect:
        shouldMatch == Pattern.compile(patterns.positiveNumber()).matcher(textToMatch).matches()
        where:
        textToMatch || shouldMatch
        '1'         || true
        '1.0'       || true
        '0.1'       || true
        '.1'        || true

        '1.'        || false
        '-1'        || false
        '-1.0'      || false
        '-0.1'      || false
        '-.1'       || false
    }

    def "should generate a regex for a short positive number [#textToMatch] that is a match [#shouldMatch]"() {
        expect:
        shouldMatch == Pattern.compile(patterns.shortPositiveNaturalNumber()).matcher(textToMatch).matches()
        where:
        textToMatch   || shouldMatch
        '1'           || true
        '1024'        || true
        '32767'       || true

        '12343245345' || false
        '-1'          || false
        '-.1'         || false
    }


    def "should generate a regex for a cron expression [#textToMatch] that is valid"() {
        expect:
        shouldMatch == Pattern.compile(patterns.cronExpression()).matcher(textToMatch).matches()
        where:
        textToMatch            || shouldMatch
        '* * * * * *'          || true
        '* * * * * MON'        || true
        '1 0 * * * MON-FRI'    || true
        '1 */10 * * * MON-FRI' || true
        '24 * * * * *'         || true
        '* 24 * * * *'         || true
        '59 * 0 * * *'         || true
        '*/2 */2 10 5 2 4'     || true

        '* * * * *'            || false //short
        '* * 24 * * *'         || false
        '61 * 24 * * *'        || false
        ''                     || false
        'other string'         || false
    }


    def "should generate a regex for a network port [#textToMatch] that is a match [#shouldMatch]"() {
        expect:
        shouldMatch == Pattern.compile(patterns.networkPort()).matcher(textToMatch).matches()
        where:
        textToMatch || shouldMatch
        '80'        || true
        '8080'      || true
        '1027'      || true
        '65535'     || true
        '15535'     || true

        '05536'     || false
    }


    def "should generate a regex for a mongo object id [#textToMatch] that is a match [#shouldMatch]"() {
        expect:
        shouldMatch == Pattern.compile(patterns.mongoObjectId()).matcher(textToMatch).matches()
        where:
        textToMatch                || shouldMatch
        '507f191e810c19729de860ed' || true

        '123'                      || false
        'z07f191e810c19729de860ed' || false
    }


}