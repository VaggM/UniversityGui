
import java.util.ArrayList;
import java.util.List;



public class Student
{
    public final static String university = "UniWA";
    private int am;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private short semester;
    private Department department;
    private ArrayList<Subject> subjects = new ArrayList<>();


    public Student ()
    {

    }

    public Student (int am, String firstName, String lastName, String email, String address, short semester, Department department)
    {
        setAM(am);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setAddress(address);
        setSemester(semester);
        setDepartment(department);
    }

    public void addSubjects (Subject sub)
    {
        this.subjects.add(sub);
    }

    public void addSubjects (Subject ... sub)
    {
        for(int i=0; i<sub.length; i++)
        {
            this.subjects.add(sub[i]);
        }
    }


    public void addSubjectsSemester (short semester)
    {
        ArrayList<Subject> tempSubjects = new ArrayList<>();
        tempSubjects = this.department.assignSemester(semester);

        for(int i=0; i<tempSubjects.size(); i++)
        {
            this.subjects.add(tempSubjects.get(i));
        }
    }


    public void getInfo ()
    {
        System.out.println();
        System.out.println("Student of " + university);
        System.out.println("First name : " + this.firstName);
        System.out.println("Last  name : " + this.lastName);
        System.out.println("AM         : " + this.am);
        System.out.println("Semester   : " + this.semester);

        if( this.subjects.size() > 0)
        {
            System.out.print("\nCurrently attends the following Subjects:\n");

            for(int i=0; i<this.subjects.size(); i++)
            {
                System.out.print("sem : " +this.subjects.get(i).getSemester()+ " - ");
                System.out.println(this.subjects.get(i).getName());
            }
        }
    }


    /* all setFunctions */

    public void setAM (int am)
    {
        if (am > 10000000)
            this.am = am;
    }

    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public void setSemester(short sem)
    {
        if ((0 < sem) && (sem < 15))
            this.semester = sem;
    }

    public void setDepartment (Department department)
    {
        this.department = department;
    }

}