package ru.vyukov.contract

import groovy.transform.CompileStatic

@CompileStatic
class RandomUtils {

    private final static Random random = new Random()

    static int nextInt(int bound) {
        return random.nextInt(bound)
    }

    /**
     * [start,stop]
     * @param start
     * @param bound
     * @return
     */
    static int nextInt(int start, int stop) {
        return random.nextInt(stop) + start;
    }


    static String randomString(int length) {
        randomString("ABCDEFGHIJKLMNOPQRSTUVWXYZ", length);
    }


    static String randomString(String characters, int length) {
        char[] characterSet = characters.toCharArray()
        Random random = new Random()
        char[] result = new char[length]
        for (int i = 0; i < result.length; i++) {
            // picks a random index out of character set > random character
            int randomCharIndex = random.nextInt(characterSet.length)
            result[i] = characterSet[randomCharIndex]
        }
        return new String(result)
    }


    static String randomMongoObjectId() {
        return randomString("abcdef0987654321", 24);
    }

    static String randomCronExpression() {
        //0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
        String template = "%s %s %s %s %s %s";
        String[] vals = new Object[6]
        vals[0] = or(nextInt(59), "*", "*/2", "*/10") //seconds
        vals[1] = or(nextInt(59), "*", "*/2", "*/10") //minutes
        vals[2] = or(nextInt(23), "*", "*/10") //hour
        vals[3] = or(nextInt(1, 31), "*") //days of month
        vals[4] = or(nextInt(1, 12), "*") // month
        vals[5] = or("*", "SUN-SAT") // day of week


        return String.format(template, vals);
    }


    private static Object or(int intVal, String... otherVals) {
        if (random.nextBoolean() || 0 == otherVals.length) {
            return intVal;
        }
        return or(otherVals);
    }

    private static Object or(String... otherVals) {
        return otherVals[random.nextInt(otherVals.length)];
    }


}
