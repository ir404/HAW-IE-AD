package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DataParser {
    private static final String FILENAME = "/student_grades.txt";

    public static List<Student> loadStudentData() throws IOException {
        List<Student> studentList = new ArrayList<>();
        InputStream inputStream = DataParser.class.getResourceAsStream(FILENAME);

        if (inputStream == null) {
            throw new IOException("Resource file not found: " + FILENAME + ". Make sure it's in the 'src' folder.");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (isHeader) {
                    isHeader = false;
                } else if (!line.isEmpty()) {
                    try {
                        String[] parts = line.split(",");
                        if (parts.length == 2) {
                            String name = parts[0].trim();
                            int grade = Integer.parseInt(parts[1].trim());

                            studentList.add(new Student(name, grade));
                        } else {
                            System.err.println("Skipping malformed line: " + line);
                        }

                    } catch (NumberFormatException e) {
                        System.err.println("Skipping line (bad grade format): " + line);
                    } catch (Exception e) {
                        System.err.println("Skipping line (unknown error): " + line + " | " + e.getMessage());
                    }
                }
            }
        }
        return studentList;
    }
}