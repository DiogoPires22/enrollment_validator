package com.diogo.enrollment_validator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class App {

    //Constants
    private static final String ENROLLMENT_WITHOUT_DV_FILE = "matriculasSemDV.txt";
    private static final String ENROLLMENT_WITH_DV_FILE = "matriculasComDV.txt";
    private static final String ENROLLMENT_TO_VALIDATE_FILE = "matriculasParaVerificar.txt";
    private static final String VALIDATED_ENROLMENTS_FILE = "matriculasVerificadas.txt";


    //Business class
    private static final EnrollmentBusiness _business = new EnrollmentBusiness();

    public static void main(String[] args) throws Exception {

        FileOutputStream outputStream;
        Scanner scanner;

        // check if the path was sent in arguments
        if (args == null || args.length == 0) {
            throw new Exception("You forgot to tell the file path");
        }

        //get the files path
        String filesPath = args[0];


        try {

            //get the file with enrollments without DV, if the file or folder not exists throw exception
            File enrollmentWithDVFile = FileHelper.getFile(filesPath + ENROLLMENT_WITH_DV_FILE, true);
            scanner = new Scanner(new FileInputStream(FileHelper.getFile(filesPath + ENROLLMENT_WITHOUT_DV_FILE)));
            outputStream = new FileOutputStream(enrollmentWithDVFile);

            //open a writer with outputStream
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
               //read each line and generate the DV
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    //if line is empty finish
                    if (line.isEmpty()) {
                        break;
                    }
                    //generate enrollment with DV and write in file
                    writer.write(_business.generateEnrollmentWithValidatorCode(line) + "\n");
                }
            }

            File validatedFile = FileHelper.getFile(filesPath + VALIDATED_ENROLMENTS_FILE, true);
            outputStream = new FileOutputStream(validatedFile);

            try (Writer writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {

                scanner = new Scanner(new FileInputStream(FileHelper.getFile(filesPath + ENROLLMENT_TO_VALIDATE_FILE)));
                //read each line and validate enrollment
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.isEmpty()) {
                        break;
                    }
                    //check if enrollment is valid and write in file
                    writer.write(line + " " + _business.validateEnrollment(line) + "\n");
                }
            }

            System.out.println("Process Finished!");

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
