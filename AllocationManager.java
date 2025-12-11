AllocationManager.java

import java.io.*;
import java.util.ArrayList;

public class AllocationManager {
    private ArrayList<Student> students;
    private final String FILE_NAME = "data.txt";

    public AllocationManager() {
        students = new ArrayList<>();
        loadData();
    }

    public void addAllocation(String studentName, String projectTitle) {
        Student s = new Student(studentName, projectTitle, "Pending");
        students.add(s);
        saveData();
    }

    public ArrayList<Student> getAllocations() {
        return students;
    }

    public void updateStatus(int index, String newStatus) {
        if (index >= 0 && index < students.size()) {
            students.get(index).setStatus(newStatus);
            saveData();
        }
    }

    private void loadData() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length == 3) {
                    students.add(new Student(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private void saveData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                bw.write(s.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
