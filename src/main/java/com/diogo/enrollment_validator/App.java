package com.diogo.enrollment_validator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class App {
    private static final String ENROLLMENT_WITHOUT_DV_FILE = "matriculasSemDV.txt";
    private static final String ENROLLMENT_WITH_DV_FILE = "matriculasComDV.txt";
    private static final String ENROLLMENT_TO_VALIDATE_FILE = "matriculasParaVerificar.txt";
    private static final String VALIDATED_ENROLMENTS_FILE = "matriculasVerificadas.txt";

    private static final EnrollmentBusiness _business = new EnrollmentBusiness();

    public static void main(String[] args) throws Exception {

        FileOutputStream outputStream;
        Scanner scanner;

        if (args == null || args.length == 0) {
            throw new Exception("You forgot to tell the file path");
        }

        String filesPath = args[0];


        try {

            File enrollmentWithDVFile = FileHelper.getFile(filesPath + ENROLLMENT_WITH_DV_FILE, true);
            scanner = new Scanner(new FileInputStream(FileHelper.getFile(filesPath + ENROLLMENT_WITHOUT_DV_FILE)));
            outputStream = new FileOutputStream(enrollmentWithDVFile);

            try (Writer writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.isEmpty()) {
                        break;
                    }
                    System.out.println(_business.generateEnrollmentWithValidatorCode(line));
                    writer.write(_business.generateEnrollmentWithValidatorCode(line) + "\n");
                }
            }

            File validatedFile = FileHelper.getFile(filesPath + VALIDATED_ENROLMENTS_FILE, true);
            outputStream = new FileOutputStream(validatedFile);

            try (Writer writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {

                scanner = new Scanner(new FileInputStream(FileHelper.getFile(filesPath + ENROLLMENT_TO_VALIDATE_FILE)));

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.isEmpty()) {
                        break;
                    }
                    System.out.println(line + " " + _business.validateEnrollment(line));
                    writer.write(line + " " + _business.validateEnrollment(line) + "\n");
                }
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
