package University.domains;

import University.gui.*;
import java.io.*;
import java.util.ArrayList;

public class Logger{

    public static void logInit(String classInfo, String logName){

        try (var bw = new BufferedWriter(new FileWriter(logName + "Log.txt", true))){

            bw.write("\n" + classInfo + "\n");
        }
        catch (IOException e)
        {
            GUI.windowln("Error", "File doesn't exist.", "error");
        }
    }

    public static void showLog(){

        var logs = new ArrayList<String>();

        try {

            BufferedReader br = new BufferedReader(new FileReader("Log.txt"));

            String line = "";

            while (((line=br.readLine()) != null))
            {
                logs.add(line);
            }

            new GuiLog(logs);

        }
        catch (FileNotFoundException e) {
            GUI.windowln("Error", e.getMessage(), "error");
        }
        catch (IOException e) {
            GUI.windowln("Error", e.getMessage(), "error");
        }

    }


    public static String readStudents(){

        String readResult = "";

        try {

            BufferedReader br = new BufferedReader(new FileReader("StudentLog.txt"));

            String line = "";

            while ((line=br.readLine()) != null)
            {
                line = line.substring(0,Math.max(0,line.length()-1));
                if(line.equals("Student-"))
                {
                    readResult += Student.readStudent(br);
                }
            }

            Storage.rewriteStudents();

        }
        catch (IOException e)
        {
            GUI.windowln("Error", e.getMessage(), "error");
        }
        catch (Exceptions e)
        {
            GUI.windowln("Error", e.getMessage(), "error");
        }
        catch (RuntimeException e)
        {
            GUI.windowln("Error", e.getMessage(), "error");
        }

        if(readResult.equals(""))
            readResult = "There is nothing to read.";

        return readResult;

    }

    public static String readTeachers(){

        String readResult = "";

        try {

            BufferedReader br = new BufferedReader(new FileReader("TeacherLog.txt"));

            String line = "";

            while ((line=br.readLine()) != null)
            {

                line = line.substring(0,Math.max(0,line.length()-1));

                if(line.equals("Teacher-"))
                {
                    readResult += Teacher.readTeacher(br);
                }
            }

            Storage.rewriteTeachers();

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        catch (Exceptions e)
        {
            e.printStackTrace();
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }

        if(readResult.equals(""))
            readResult = "There is nothing to read.";

        return readResult;

    }

    public static String readSubjects(){

        String readResult = "";

        try {

            BufferedReader br = new BufferedReader(new FileReader("SubjectLog.txt"));

            String line = "";

            while ((line=br.readLine()) != null)
            {

                line = line.substring(0,Math.max(0,line.length()-1));

                if(line.equals("Subject-"))
                {
                    readResult += Subject.readSubject(br);
                }
            }

            Storage.rewriteSubjects();

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        catch (Exceptions e)
        {
            e.printStackTrace();
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
        }

        if(readResult.equals(""))
            readResult = "There is nothing to read.";

        return readResult;

    }

    public static String readDepartments(){

        String readResult = "";

        try {

            BufferedReader br = new BufferedReader(new FileReader("DepartmentLog.txt"));

            String line = "";

            while ((line=br.readLine()) != null)
            {

                line = line.substring(0,Math.max(0,line.length()-1));

                if(line.equals("Department-"))
                {
                    readResult += Department.readDepartment(br);
                }
            }

            Storage.rewriteDepartments();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        catch (Exceptions e)
        {
            e.printStackTrace();
        }

        if(readResult.equals(""))
            readResult = "There is nothing to read.";

        return readResult;
    }


    public static String clearFiles(String ... logFiles){

        String clearResult = "";

        for(String str : logFiles)
        {
            try (var bw = new BufferedWriter(new FileWriter(str))){
                bw.write("");
                clearResult += (str + " is cleared.\n");

                str = str.substring(0, 4);
                Storage.listClear(str);
            }
            catch (IOException e)
            {
                clearResult += ("Error in clearing Data..." + str + "\n");
            }
        }

        return clearResult;
    }

    public static String resetFiles(String ... logFiles){

        String clearResult = "";

        for(String str : logFiles)
        {
            try (var bw = new BufferedWriter(new FileWriter(str))){
                bw.write("");
            }
            catch (IOException e)
            {
                clearResult += ("Error in reseting Data..." + str + "\n");
            }
        }

        return clearResult;
    }

}