
import java.util.ArrayList;
import java.util.List;



public class Professor
{
    public final static String university = "UniWA";
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Department department;
    private ArrayList<Subject> subjects = new ArrayList<>();


    public Professor ()
    {

    }

    public Professor (String firstName, String lastName, String email, String address, Department department)
    {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setAddress(address);
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


    public void getInfo ()
    {
        System.out.println();
        System.out.println("Professor of " + university);
        System.out.println("First name : " + this.firstName);
        System.out.println("Last  name : " + this.lastName);

        System.out.print("\nCurrently attends the following Subjects:\n");

        for(int i=0; i<this.subjects.size(); i++)
        {
            System.out.println(this.subjects.get(i).getName());
        }
    }


    /* all setFunctions */



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


    public void setDepartment (Department department)
    {
        this.department = department;
    }

}