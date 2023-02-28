package University.domains;

import University.gui.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public final class Student extends Person{

    public final static String university = "UniWA";
    private int am;
    private String email;
    private short semester;
    private ArrayList<Subject> subjects = new ArrayList<>();
    private static short totalStudents;
    private StringBuilder uniqueID = new StringBuilder("Student-");


    private Student() {
        this.uniqueID.append(totalStudents);
    }

    private Student(String firstName, String lastName, int am, String email, short semester, ArrayList<Subject> subjects) throws Exceptions {
        super(firstName, lastName, new Date());
        this.setAm(am);
        this.setEmail(email);
        this.setSemester(semester);
        this.setSubjects(subjects);
        this.uniqueID.append(totalStudents);
    }

    private Student(String firstName, String lastName, int am, String email, short semester) throws Exceptions {
        super(firstName, lastName, new Date());
        this.setAm(am);
        this.setEmail(email);
        this.setSemester(semester);
        this.uniqueID.append(totalStudents);
    }

    @Override
    public String toString()
    {
        String str = this.uniqueID +
                "\nFirst Name : " + this.getFirstName() +
                "\nLast  Name : " + this.getLastName() +
                "\nAM         : " + this.getAm() +
                "\nEmail      : " + this.getEmail() +
                "\nSemester   : " + this.getSemester();

        for(Subject sub : this.subjects)
            str += "\nSubject : " + sub.getName();

        str += "\n";

        return str;
    }

    static class LogStudent extends LogAbstract{

        public LogStudent(String info){
            logAction(info);
        }

        @Override
        public void logAction(String info) {
            if(info.length() > 1)
                Logger.logInit(info,"Student");

            Logger.logInit("Student-" + totalStudents++ + "\t at " + new Date(), "");
        }
    }


    //get and set methods


    public static String getUniversity() {
        return university;
    }

    public int getAm() {
        return am;
    }

    public void setAm(int am) {
        this.am = am;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public short getSemester() {
        return semester;
    }

    public void setSemester(short semester) throws Exceptions {
        if ((0 < semester) && (semester < 15))
            this.semester = semester;
        if (this.semester == (short) 0)
            throw new Exceptions("\nIncorrect Semester");
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public void setUniqueID(String uniqueID){
        var id = new StringBuilder();
        id.append(uniqueID);
        this.uniqueID = id;
    }

    public String getUniqueID(){
        return this.uniqueID.toString();
    }


    //new Student

    public static void newStudent(String firstName, String lastName, short semester, int am, String email) throws Exceptions{

        if(Storage.existsStudent(am) == 0){
            Student stud = new Student(firstName, lastName, am, email, semester);
            var log = new LogStudent(stud.toString());
            Storage.students.add(stud);
        }
        else
        {
            throw new Exceptions("Student with AM \"" + am + "\" already exists.");
        }
    }

    public static void newStudent(String firstName, String lastName, short semester, int am, String email, ArrayList<Subject> subjects) throws Exceptions{

        if(Storage.existsStudent(am) == 0){
            Student stud = new Student(firstName, lastName, am, email, semester, subjects);
            var log = new LogStudent(stud.toString());
            Storage.students.add(stud);

            for(Subject sub : subjects)
                sub.addStudent(stud);
        }
        else
        {
            throw new Exceptions("Student with AM \"" + am + "\" already exists.");
        }
    }


    public static String readStudent(BufferedReader br) throws Exceptions, IOException {

        String readResult = "";
        String existSubs = "";

        String firstName;
        String lastName;
        int am;
        String email;
        int semester;
        ArrayList<String> subjectNames = new ArrayList<>();
        ArrayList<Subject> subjects = new ArrayList<>();


        String line = "";

        line = br.readLine();
        firstName = line.substring(line.indexOf(":") + 2);
        line = br.readLine();
        lastName = line.substring(line.indexOf(":") + 2);

        line = br.readLine();
        am = Integer.parseInt(line.substring(line.indexOf(":") + 2));

        line = br.readLine();
        email = line.substring(line.indexOf(":") + 2);

        line = br.readLine();
        semester = Integer.parseInt(line.substring(line.indexOf(":") + 2));

        while ((line = br.readLine()).substring(0,Math.min(line.length(),10)).equals("Subject : "))
        {
            line = line.substring(line.indexOf(":") + 2);
            if(!checkDuplicates(line, subjectNames))
                subjectNames.add(line);
            else
                GUI.windowln("Error", "Deleted duplicates of " +line+ ".", "error");
        }

        try{
            for(String str : subjectNames)
                subjects.add(Storage.searchSubject(str));
        }
        catch (Exceptions e){
            GUI.windowln("Error", e.getMessage(), "error");
            GUI.windowln("Error", "Adding student without subject...", "");
            subjects.clear();
        }

        if(Storage.existsStudent(am) == 0){

            if(subjects.size() == 0){
                existSubs = "(No subjects)";

                var stud = new Student(firstName, lastName, am, email, (short)semester);
                Storage.students.add(stud);
                totalStudents++;
            }
            else{
                Student stud = new Student(firstName, lastName, am, email, (short) semester, subjects);
                Storage.students.add(stud);
                totalStudents++;

                for(Subject sub : subjects)
                    sub.addStudent(stud);

            }


            readResult = "\u2713   " + lastName + " " + firstName + " has been added." + existSubs + "\n";
        }
        else{
            readResult = "\u2718   " + lastName + " " + firstName + " exists already.\n";
        }

        return readResult;
    }


    public static boolean checkDuplicates(String str, ArrayList<String> strings){
        for(String string : strings)
            if(string.equals(str))
                return true;

        return false;
    }
}