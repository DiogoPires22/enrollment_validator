package com.diogo.enrollment_validator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    EnrollmentBusiness enrollmentBusiness = new EnrollmentBusiness();


    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }


    public void testEnrollmentThrowExceptionWithWrongFormat() {
        Boolean thrown = false;
        thrown = false;
        try {
            enrollmentBusiness.generateEnrollmentWithValidatorCode("233");
        } catch (Exception e) {
            thrown = true;
        }
        assertTrue(thrown);
    }


    public void testEnrollmentOkWithCorrectFormat() {

        String enrollmentCorrect = "9876-E";

        try {
            assertEquals(enrollmentCorrect,
                    enrollmentBusiness.generateEnrollmentWithValidatorCode(enrollmentCorrect.split("[-]")[0]));

        } catch (Exception ex) {
            assertTrue(false);
        }
    }


    public void test_throw_exception_if_send_empty_path() {
        Boolean thrown = false;
        try {
            FileHelper.getFile("");
        } catch (Exception e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    public void test_throw_exception_if_send_wrong_path() {
        Boolean thrown = false;
        try {
            FileHelper.getFile("xxxx/xxx");
        } catch (Exception e) {
            thrown = true;
        }

        assertTrue(thrown);
    }


    public void test_throw_exception_if_not_send_args() {
        Boolean thrown = false;
        try {
            App.main(null);
        } catch (Exception e) {
            thrown = true;
        }

        assertTrue(thrown);
    }
}
