

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

/**
 * A demo application to showcase a dashboard.
 * This file demonstrates how to use Panel, StatCard, and Chart to build
 * a complex dashboard view.
 * It also now includes a form page to add new students.
 */
public class DashboardDemo {

    /**
     * A simple Student class that implements Rowable.
     * This is the "model" for our application.
     */
    public static class Student implements Rowable {
        String id;
        String name;
        String major;
        String year;
        double gpa;

        public Student(String id, String name, String major, String year, double gpa) {
            this.id = id;
            this.name = name;
            this.major = major;
            this.year = year;
            this.gpa = gpa;
        }

        // This is the method required by the Rowable interface
        public Object[] getRowData() {
            return new Object[] { id, name, major, year, gpa };
        }
    }

    /**
     * Handles the form submission.
     * Implements the FormSubmitHandler interface.
     */
    public static class StudentFormHandler implements FormSubmitHandler {
        private Table studentTable;

        public StudentFormHandler(Table table) {
            this.studentTable = table;
        }

        public void onSubmit(String[] values) {
            try {
                // Create a new Student object from the form values
                String id = values[0];
                String name = values[1];
                String major = values[2];
                String year = values[3];
                double gpa = Double.parseDouble(values[4]);

                Student newStudent = new Student(id, name, major, year, gpa);

                // Add the new Student object to the table
                studentTable.addRow(newStudent);

                // Show a success message (using the built-in simple message dialog)
                Form.showMessage("Student Added", "Successfully added " + name + ".");

            } catch (Exception e) {
                // Show an error message
                Form.showMessage("Error", "Could not add student. Check all fields.");
            }
        }
    }

    public static void main(String[] args) {
        // 1. Create the application
        App myApp = new App("Student Dashboard");

        // 2. Create sample data
        String[] studentHeaders = { "ID", "Name", "Major", "Year", "GPA" };
        Student[] students = {
                new Student("1001", "Alice Smith", "Comp. Sci", "Sophomore", 3.8),
                new Student("1002", "Bob Johnson", "Biology", "Junior", 3.5),
                new Student("1003", "Charlie Lee", "Physics", "Sophomore", 3.9),
                new Student("1004", "David Kim", "Comp. Sci", "Senior", 3.2),
                new Student("1005", "Eve Davis", "Biology", "Junior", 3.6),
                new Student("1006", "Frank White", "Math", "Senior", 3.7)
        };

        ArrayList<Student> studentData = new ArrayList<>(Arrays.asList(students));

        // 3. Create the "Students" page with the main table
        Page studentPage = myApp.addPage("Students");
        Table studentTable = new Table(studentHeaders, studentData);
        studentPage.addComponent(studentTable);

        System.out.println("Initial data:");
        for (Student student : studentData)
            System.out.println(Arrays.toString(student.getRowData()));
        System.out.println("===========================");

        System.out.println("Adding new student ...");
        studentTable.addRow(new Student("1007", "John Doe", "Comp. Sci", "Sophomore", 3.9));

        for (Student student : studentData)
            System.out.println(Arrays.toString(student.getRowData()));
        System.out.println("===========================");

        // 4. Create the "Add Student" page with the form
        Page formPage = myApp.addPage("Add Student");
        String[] formFields = { "ID", "Name", "Major", "Year", "GPA" };

        // Create an instance of our handler and pass it the table
        FormSubmitHandler handler = new StudentFormHandler(studentTable);

        // Create the form
        Form studentForm = new Form(formFields, "Add New Student", handler);
        formPage.addComponent(studentForm);


        // 5. Create the "Dashboard" page
        Page dashboardPage = myApp.addPage("Dashboard");

        // Create a 2x2 grid panel for our dashboard items
        Panel dashboardPanel = new Panel(2, 2, 10, 10);
        dashboardPage.addComponent(dashboardPanel);

        // --- Add Dashboard Components ---

        // Stat Card 1: Total Students
        StatCard totalStudentsCard = new StatCard("Total Students", String.valueOf(students.length));
        dashboardPanel.add(totalStudentsCard);

        // Stat Card 2: Average GPA
        double totalGpa = 0;
        for (Student s : students) {
            totalGpa += s.gpa;
        }
        double avgGpa = (students.length > 0) ? totalGpa / students.length : 0;
        // Format to 2 decimal places
        String avgGpaStr = String.format("%.2f", avgGpa);
        StatCard avgGpaCard = new StatCard("Average GPA", avgGpaStr);
        dashboardPanel.add(avgGpaCard);

        // Chart 1: Majors (Pie Chart)
        Hashtable majors = new Hashtable();
        for (Student s : students) {
            String major = s.major;
            int count = majors.containsKey(major) ? (int) majors.get(major) : 0;
            majors.put(major, count + 1);
        }
        String[] majorKeys = new String[majors.size()];
        Number[] majorValues = new Number[majors.size()];
        int i = 0;
        for (Object k : majors.keySet()) {
            majorKeys[i] = (String) k;
            majorValues[i] = (Number) majors.get(k);
            i++;
        }
        Chart majorChart = new Chart("Students by Major", "PIE", majorKeys, majorValues);
        dashboardPanel.add(majorChart);

        // Chart 2: Year Level (Bar Chart)
        Hashtable years = new Hashtable();
        for (Student s : students) {
            String year = s.year;
            int count = years.containsKey(year) ? (int) years.get(year) : 0;
            years.put(year, count + 1);
        }
        String[] yearKeys = new String[years.size()];
        Number[] yearValues = new Number[years.size()];
        i = 0;
        for (Object k : years.keySet()) {
            yearKeys[i] = (String) k;
            yearValues[i] = (Number) years.get(k);
            i++;
        }
        Chart yearChart = new Chart("Students by Year", "BAR", yearKeys, yearValues);
        dashboardPanel.add(yearChart);


        // 6. Show the application
        myApp.show();
    }
}

