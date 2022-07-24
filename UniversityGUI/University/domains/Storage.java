package University.domains;

import University.gui.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    public static List<Student> students = new ArrayList<>();
    public static List<Teacher> teachers = new ArrayList<>();
    public static List<Subject> subjects = new ArrayList<>();
    public static List<Department> departments = new ArrayList<>();

    public static short existsStudent(int am){

        for (Student student : students)

            if (am == student.getAm())

                return 1;

        return 0;
    }

    public static short existsTeacher(String nameFull){

        for(Teacher teacher : teachers)

            if (nameFull.equals(teacher.getLastName()+teacher.getFirstName()))

                return 1;

        return 0;
    }

    public static short existsSubject(String uniqueID){

        for(Subject subject : subjects)

            if (uniqueID.equals(subject.getUnique_id()))

                return 1;

        return 0;
    }

    public static short existsDepartment(String name){

        for(Department department : departments)

            if (name.equals(department.getName()))

                return 1;

        return 0;
    }

    public static void rewriteStudents(){

        Logger.resetFiles("StudentLog.txt");

        for(Student student : students)
            Logger.logInit(student.toString(), "Student");

    }

    public static void rewriteTeachers(){

        Logger.resetFiles("TeacherLog.txt");

        for(Teacher teacher : teachers)
            Logger.logInit(teacher.toString(), "Teacher");

    }

    public static void rewriteSubjects(){

        Logger.resetFiles("SubjectLog.txt");

        for(Subject subject : subjects)
            Logger.logInit(subject.toString(), "Subject");

    }

    public static void rewriteDepartments(){

        Logger.resetFiles("DepartmentLog.txt");

        for(Department department : departments)
            Logger.logInit(department.toString(), "Department");

    }

    public static Subject searchSubject(String name) throws Exceptions{
        for(Subject sub : subjects)
            if(sub.getName().equals(name))
                return sub;

        throw new Exceptions("No subject named " + name + ".");
    }

    public static void listClear(String listName){

        switch (listName) {

            case "Stud" :

                for (Subject subject : subjects)
                    subject.getStudents().clear();

                students.clear();

                break;

            case "Teac" :

                teachers.clear();

                break;

            case "Subj" :

                for (Student student : students)
                    student.getSubjects().clear();

                for (Teacher teacher : teachers)
                    teacher.getSubjects().clear();

                subjects.clear();

                break;

            case "Depa" :

                departments.clear();

                break;

            case "Log." :

                GUI.windowln("Log Update", "Session is cleared.\n", "");

                break;

            default :

                GUI.windowln("Error", "Cannot clear list named " + listName + ".\n", "error");
        }

    }

    public static void orderStudents(){

        var studentsTemp = new ArrayList<Student>();

        while (students.size() > 0){

            var studentA = students.get(0);

            for(Student studentB : students){

                var lengthA = studentA.getLastName().length();
                var lengthB = studentB.getLastName().length();

                for(int i=0; (i<lengthA && i<lengthB); i++){

                    var nameA = studentA.getLastName().charAt(i);
                    var nameB = studentB.getLastName().charAt(i);

                    if(nameB < nameA){
                        studentA = studentB;
                        break;
                    }
                    if(nameA < nameB)
                        break;
                }
            }

            studentsTemp.add(studentA);
            students.remove(studentA);
        }

        students.addAll(studentsTemp);

        var idCounter = 0;
        for(Student student : students){
            var id = student.getUniqueID();
            id = id.replace(id.charAt(id.length()-1), (char) (idCounter++ + '0'));
            student.setUniqueID(id);
        }

        rewriteStudents();
    }

    public static void orderTeachers(){

        var teachersTemp = new ArrayList<Teacher>();

        while (teachers.size() > 0){

            var teacherA = teachers.get(0);

            for(Teacher teacherB : teachers){

                var lengthA = teacherA.getLastName().length();
                var lengthB = teacherB.getLastName().length();

                for(int i=0; (i<lengthA && i<lengthB); i++){

                    var nameA = teacherA.getLastName().charAt(i);
                    var nameB = teacherB.getLastName().charAt(i);

                    if(nameB < nameA){
                        teacherA = teacherB;
                        break;
                    }
                    if(nameA < nameB)
                        break;
                }
            }

            teachersTemp.add(teacherA);
            teachers.remove(teacherA);
        }

        teachers.addAll(teachersTemp);

        var idCounter = 0;
        for(Teacher teacher : teachers){
            var id = teacher.getUniqueID();
            id = id.replace(id.charAt(id.length()-1), (char) (idCounter++ + '0'));
            teacher.setUniqueID(id);
        }

        rewriteTeachers();
    }

    public static void orderSubjects(){

        var subjectsTemp = new ArrayList<Subject>();

        while (subjects.size() > 0){

            var subjectA = subjects.get(0);

            for(Subject subjectB : subjects){

                var lengthA = subjectA.getName().length();
                var lengthB = subjectB.getName().length();


                for(int i=0; (i<lengthA && i<lengthB); i++){

                    var nameA = subjectA.getName().charAt(i);
                    var nameB = subjectB.getName().charAt(i);


                    if(nameB < nameA){
                        subjectA = subjectB;
                        break;
                    }
                    if(nameA < nameB)
                        break;
                }
            }

            subjectsTemp.add(subjectA);
            subjects.remove(subjectA);
        }

        while (subjectsTemp.size() > 0){

            for(int i=1; i<11; i++){

                var subs = new ArrayList<Subject>();

                for(int j=0; j<subjectsTemp.size(); j++){

                    var subject = subjectsTemp.get(j);

                    if(subject.getSemester() == i){

                        subs.add(subject);
                    }

                }

                subjects.addAll(subs);
                subjectsTemp.removeAll(subs);
            }
        }


        var idCounter = 0;
        for(Subject subject : subjects){
            var id = subject.getUniqueID();
            id = id.replace(id.charAt(id.length()-1), (char) (idCounter++ + '0'));
            subject.setUniqueID(id);
        }

        rewriteSubjects();
    }

    public static void orderDepartments(){

        var departmentsTemp = new ArrayList<Department>();

        while (departments.size() > 0){

            var departmentA = departments.get(0);

            for(Department departmentB : departments){

                var lengthA = departmentA.getName().length();
                var lengthB = departmentB.getName().length();

                for(int i=0; (i<lengthA && i<lengthB); i++){

                    var nameA = departmentA.getName().charAt(i);
                    var nameB = departmentB.getName().charAt(i);

                    if(nameB < nameA){
                        departmentA = departmentB;
                        break;
                    }
                    if(nameA < nameB)
                        break;
                }
            }

            departmentsTemp.add(departmentA);
            departments.remove(departmentA);
        }

        departments.addAll(departmentsTemp);

        var idCounter = 0;
        for(Department department : departments){
            var id = department.getUniqueID();
            id = id.replace(id.charAt(id.length()-1), (char) (idCounter++ + '0'));
            department.setUniqueID(id);
        }

        rewriteDepartments();
    }
}