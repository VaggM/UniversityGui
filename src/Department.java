import java.util.ArrayList;

public class Department
{
    private String name;
    private ArrayList<Subject> subjects = new ArrayList<>();


    public Department(String name)
    {
        setName(name);
    }


    public void addSubjects(Subject ... sub)
    {
        for(int i=0; i<sub.length; i++)
        {
            this.subjects.add(sub[i]);
        }
    }

    public void addSubjects(short semester, String ... subjectName)
    {
        for(int i=0; i<subjectName.length; i++)
        {
            Subject sub = new Subject(subjectName[i], semester);
            this.subjects.add(sub);
        }
    }

    public ArrayList<Subject> assignSemester (short semester)
    {
        ArrayList<Subject> subjectsSemester = new ArrayList<>();

        for(int i=0; i<this.subjects.size(); i++)
        {
            if (this.subjects.get(i).getSemester() == semester)
            {
                subjectsSemester.add(this.subjects.get(i));
            }
        }

        return subjectsSemester;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public void getSubjects ()
    {

        System.out.printf("\nThe " + this.name + " department of consists of the following Subjects :\n");

        for(int i=0; i<this.subjects.size(); i++)
        {
            System.out.print("sem : " +this.subjects.get(i).getSemester()+ " - ");
            System.out.println(this.subjects.get(i).getName());
        }
    }

}