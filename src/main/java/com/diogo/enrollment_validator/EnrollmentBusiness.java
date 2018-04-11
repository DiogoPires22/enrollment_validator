
package com.diogo.enrollment_validator;

import java.util.regex.Pattern;
import java.util.stream.IntStream;


/**
 * @author diogosilva
 */


public class
EnrollmentBusiness {

    private final int _maxMult = 5;
    private final int _divisor = 16;

    /**
     * Return a string enrollment complete with validator code, when don't match pattern throws a Exception
     *
     * @param enrollmentWithoutValidator the enrollment that needs to be tested, and complete
     * @return enrollment complete with validator code
     * @see throw exception if text not match to regular expression
     */
    public String generateEnrollmentWithValidatorCode(String enrollmentWithoutValidator) throws Exception {
        if (!Pattern.compile("^[0-9]{4}$").matcher(enrollmentWithoutValidator).find()) {
            throw new Exception("Code not match pattern");
        }

        int auxCount;
//        for (int i = 0; i < enrollmentWithoutValidator.length(); i++) {
//            auxCount += Integer.parseInt(enrollmentWithoutValidator.charAt(i) + "") * (_maxMult - i);
//        }

        String[] stringArr = enrollmentWithoutValidator.split("");

        auxCount = IntStream.range(0, stringArr.length)
                .mapToObj(index -> Integer.parseInt(stringArr[index]) * (_maxMult - index))
                .reduce(0, Integer::sum);

        String hexString = ConvertionHelper.intToHexString(auxCount % _divisor).toUpperCase();

        return String.format("%s-%s", enrollmentWithoutValidator, hexString);

    }


    /**
     * Return a boolean that represents whether or not an enrollment is valid
     *
     * @param enrollmentWithValidator the enrollment that needs to be tested
     * @return true if valid and false not
     * @see throw exception if text not match to regular expression
     */
    boolean validateEnrollment(String enrollmentWithValidator) throws Exception {
        if (!Pattern.compile("^[0-9]{4}-[a-fA-F0-9]$").matcher(enrollmentWithValidator).find()) {
            throw new Exception("Code not match pattern");
        }

        String[] splitString = enrollmentWithValidator.split("-");

        int auxCount = 0;

        String[] stringArr = splitString[0].split("");

        auxCount = IntStream.range(0, stringArr.length)
                .mapToObj(index -> Integer.parseInt(stringArr[index]) * (_maxMult - index))
                .reduce(0, Integer::sum);

        String hexString = ConvertionHelper.intToHexString(auxCount % _divisor).toUpperCase();

        return splitString[1].equals(hexString);
    }
}
