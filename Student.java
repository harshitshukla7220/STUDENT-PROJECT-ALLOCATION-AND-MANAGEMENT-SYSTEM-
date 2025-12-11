Student.java

public class Student {
    private String name;
    private String project;
    private String status; // "Submitted" or "Pending"

    public Student(String name, String project, String status) {
        this.name = name;
        this.project = project;
        this.status = status;
    }

    public String getName() { return name; }
    public String getProject() { return project; }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return name + "," + project + "," + status;
    }
}
