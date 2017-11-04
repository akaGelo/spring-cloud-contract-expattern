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

        '* * * * *'            || false //short
        '* * 24 * * *'         || false
        '61 * 24 * * *'        || false
        ''                     || false
        'other string'         || false
    }


}