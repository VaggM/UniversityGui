package University.domains;

import University.gui.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public final class Teacher extends Person{
    private String email;
    private ArrayList<Subject> subjects = new ArrayList<>();
    private static short totalTeachers;
    private StringBuilder uniqueID = new StringBuilder("Teacher-");


    private Teacher() {
        this.uniqueID.append(totalTeachers);
    }

    private Teacher(String firstName, String lastName, String email, ArrayList<Subject> subjects) throws Exceptions{
        super(firstName, lastName, new Date());
        this.setEmail(email);
        this.setSubjects(subjects);
        this.uniqueID.append(totalTeachers);
    }

    private Teacher(String firstName, String lastName, String email) throws Exceptions{
        super(firstName, lastName, new Date());
        this.setEmail(email);
        this.uniqueID.append(totalTeachers);
    }


    @Override
    public String toString()
    {
        String str = this.uniqueID +
                "\nFirst Name : " + this.getFirstName() +
                "\nLast  Name : " + this.getLastName() +
                "\nEmail      : " + this.getEmail();

        for(Subject sub : this.subjects)
            str += "\nSubject : " + sub.getName();

        str += "\n";

        return str;
    }

    static class LogTeacher extends LogAbstract{

        public LogTeacher(String info){
            logAction(info);
        }

        @Override
        public void logAction(String info) {
            if(info.length() > 1)
                Logger.logInit(info,"Teacher");

            Logger.logInit("Teacher-" + totalTeachers++ + "\t at " + new Date(), "");
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }


    // new Teacher

    public static void newTeacher(String firstName, String lastName, String email) throws Exceptions{

        if(Storage.existsTeacher(firstName + lastName) == 0){
            Teacher teacher = new Teacher(firstName, lastName, email);
            var log = new LogTeacher(teacher.toString());
            Storage.teachers.add(teacher);
        }
        else
        {
            throw new Exceptions("Teacher named " + lastName + " " + firstName + " already exists.");
        }
    }

    public static void newTeacher(String firstName, String lastName, String email, ArrayList<Subject> subjects) throws Exceptions{

        if(Storage.existsTeacher(firstName + lastName) == 0){
            Teacher teacher = new Teacher(firstName, lastName, email, subjects);
            var log = new LogTeacher(teacher.toString());
            Storage.teachers.add(teacher);
        }
        else
        {
            throw new Exceptions("Teacher named " + lastName + " " + firstName + " already exists.");
        }
    }


    public static String readTeacher(BufferedReader br) throws Exceptions, IOException {

        String readResult = "";
        String existSubs = "";

        String firstName;
        String lastName;
        String email;
        ArrayList<String> subjectNames = new ArrayList<>();
        ArrayList<Subject> subjects = new ArrayList<>();

        String line = "";

        line = br.readLine();
        firstName = line.substring(line.indexOf(":") + 2);

        line = br.readLine();
        lastName = line.substring(line.indexOf(":") + 2);;

        line = br.readLine();
        email = line.substring(line.indexOf(":") + 2);


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
            GUI.windowln("Error", "Adding teacher without subject...", "");
            subjects.clear();
        }

        if(Storage.existsTeacher(lastName + firstName) == 0){

            if(subjects.size() == 0){
                existSubs = "(No subjects)";

                var teach = new Teacher(firstName, lastName, email);
                Storage.teachers.add(teach);
                totalTeachers++;
            }
            else{
                var teach = new Teacher(firstName, lastName, email, subjects);
                Storage.teachers.add(teach);
                totalTeachers++;
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
