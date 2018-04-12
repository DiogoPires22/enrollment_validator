
package com.diogo.enrollment_validator;

import java.util.regex.Pattern;
import java.util.stream.IntStream;


/**
 * @author diogosilva
 */


public class
EnrollmentBusiness {

    private final int MAX_MULT = 5;
    private final int DIVISOR = 16;

    /**
     * Return a string enrollment complete with validator code, when don't match pattern throws a Exception
     *
     * @param enrollmentWithoutValidator the enrollment that needs to be tested, and complete
     * @return enrollment complete with validator code
     * @see throw exception if text not match to regular expression
     */
    public String generateEnrollmentWithValidatorCode(String enrollmentWithoutValidator) throws Exception {

        //if the string not match throw exception (need 4 number 0-9)
        if (!Pattern.compile("^[0-9]{4}$").matcher(enrollmentWithoutValidator).find()) {
            throw new Exception("Code not match pattern");
        }


//        for (int i = 0; i < enrollmentWithoutValidator.length(); i++) {
//            auxCount += Integer.parseInt(enrollmentWithoutValidator.charAt(i) + "") * (_maxMult - i);
//        }

        //get each string character on string
        String[] stringArr = enrollmentWithoutValidator.split("");

        // generate a sum  elements transformed with condition of the problem
        int auxCount = IntStream.range(0, stringArr.length)
                .mapToObj(index -> Integer.parseInt(stringArr[index]) * (MAX_MULT - index))
                .reduce(0, Integer::sum);

        //convert module of division to hexadecimal
        String hexString = ConvertionHelper.intToHexString(auxCount % DIVISOR).toUpperCase();

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
        //if the string not match throw exception (need 4 number 0-9) and the DV (hexadecimal string)
        if (!Pattern.compile("^[0-9]{4}-[a-fA-F0-9]$").matcher(enrollmentWithValidator).find()) {
            throw new Exception("Code not match pattern");
        }
        //split string [enrollment without DV,DV]
        String[] splitString = enrollmentWithValidator.split("-");

        //compare the value generated is equal
        return enrollmentWithValidator.equals(generateEnrollmentWithValidatorCode(splitString[0]));
    }
}
