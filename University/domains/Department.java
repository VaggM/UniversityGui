package University.domains;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public final class Department {
    private String name;
    private ArrayList<Subject> subjects;
    private ArrayList<Teacher> teachers;
    private ArrayList<Student> students;
    private static short totalDepartments;
    private StringBuilder uniqueID = new StringBuilder("Department-");

    private Department() {
        this.uniqueID.append(totalDepartments);
    }

    private Department(String name) {
        this.setName(name);
        this.uniqueID.append(totalDepartments);
    }

    private Department(String name, ArrayList<Subject> subjects, ArrayList<Teacher> teachers, ArrayList<Student> students) {
        this.setName(name);
        this.setSubjects(subjects);
        this.setTeachers(teachers);
        this.setStudents(students);
        this.uniqueID.append(totalDepartments);
    }


    @Override
    public String toString()
    {
        String str = this.uniqueID +
                "\nName : " + this.getName();

        return str;
    }

    static class LogDepartment extends LogAbstract{

        public LogDepartment(String info){
            logAction(info);
        }

        @Override
        public void logAction(String info) {
            if(info.length() > 1)
                Logger.logInit(info,"Department");

            Logger.logInit("Department-" + totalDepartments++ + "\t at " + new Date(), "");
        }
    }


    //get and set methods

    public void setUniqueID(String uniqueID){
        var id = new StringBuilder();
        id.append(uniqueID);
        this.uniqueID = id;
    }

    public String getUniqueID(){
        return this.uniqueID.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }


    //new Department


    public static void newDepartment(String name) throws Exceptions{

        if(Storage.existsDepartment(name) == 0){
            Department dep = new Department(name);
            var log = new LogDepartment(dep.toString());
            Storage.departments.add(dep);
        }
        else
        {
            throw new Exceptions("Department " + name + " already exists.");
        }
    }

    public static String readDepartment(BufferedReader br) throws Exceptions, IOException {

        String readResult = "";

        String name;

        String line = "";

        line = br.readLine();
        name = line.substring(line.indexOf(":") + 2);

        if(Storage.existsDepartment(name) == 0){
            var dep = new Department(name);
            Storage.departments.add(dep);
            totalDepartments++;

            readResult = "\u2713   " + name + " has been added.\n";
        }
        else
        {
            readResult = "\u2718   " + name + " exists already.\n";
        }

        return readResult;
    }
}