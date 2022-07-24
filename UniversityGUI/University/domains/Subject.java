package University.domains;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public final class Subject {
    private String name;
    private String unique_id;
    private short semester;
    private ArrayList<Student> students = new ArrayList<>();
    private static short totalSubjects;
    private StringBuilder uniqueID = new StringBuilder("Subject-");


    private Subject(){
        this.uniqueID.append(totalSubjects);
    }

    private Subject(String name, String unique_id, short semester) throws Exceptions{
        setName(name);
        setUnique_id(unique_id);
        setSemester(semester);
        this.uniqueID.append(totalSubjects);
    }


    @Override
    public String toString()
    {
        String str = this.uniqueID +
                "\nName      : " + this.getName() +
                "\nID        : " + this.getUnique_id() +
                "\nSemester  : " + this.getSemester();

        for(Student stud : this.students)
            str += "\nStudent " + stud.getLastName() + " " + stud.getFirstName();

        str += "\n";

        return str;
    }

    static class LogSubject extends LogAbstract{

        public LogSubject(String info){
            logAction(info);
        }

        @Override
        public void logAction(String info) {
            if(info.length() > 1)
                Logger.logInit(info,"Subject");

            Logger.logInit("Subject-" + totalSubjects++ + "\t at " + new Date(), "");
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

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public short getSemester() {
        return semester;
    }

    public void setSemester(short semester) throws Exceptions{
        if ((0 < semester) && (semester < 15))
            this.semester = semester;
        if (this.semester == (short) 0)
            throw new Exceptions("Incorrect Semester");
    }

    public ArrayList<Student> getStudents(){
        return this.students;
    }

    public void setStudents(ArrayList<Student> students){
        this.students = students;
    }

    public void addStudent(Student ... students){
        for(Student stud : students)
            this.students.add(stud);
    }

    //new Subject

    public static void newSubject(String name, String id, short semester) throws Exceptions{

        if(Storage.existsSubject(id) == 0){
            Subject sub = new Subject(name, id, semester);
            var log = new LogSubject(sub.toString());
            Storage.subjects.add(sub);
        }
        else
        {
            throw new Exceptions("Subject with ID \"" + id + "\" already exists.");
        }
    }


    public static String readSubject(BufferedReader br) throws Exceptions, IOException {

        String readResult = "";

        String name;
        String uniqueID;
        int semester;

        String line = "";

        line = br.readLine();
        name = line.substring(line.indexOf(":") + 2);

        line = br.readLine();
        uniqueID = line.substring(line.indexOf(":") + 2);;

        line = br.readLine();
        semester = Integer.parseInt(line.substring(line.indexOf(":") + 2));

        if(Storage.existsSubject(uniqueID) == 0){
            var sub = new Subject(name, uniqueID, (short)semester);
            Storage.subjects.add(sub);
            totalSubjects++;

            readResult = "\u2713   " + name + " has been added.\n";
        }
        else
        {
            readResult = "\u2718   " + name + " exists already.\n";
        }

        return readResult;
    }


    public static boolean checkDuplicates(Subject subject, ArrayList<Subject> subjects){
        for(Subject sub : subjects)
            if(subject.getUnique_id() == sub.getUnique_id())
                return true;

        return false;
    }
}