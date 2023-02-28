public class Subject
{
    private String name;

    private short semester;
    private float grade;

    public Subject()
    {

    }

    public Subject(String name, short semester)
    {
        setName(name);
        setSemester(semester);
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setSemester(short semester)
    {
        this.semester = semester;
    }

    public void setGrade(float grade)
    {
        this.grade = grade;
    }

    public String getName()
    {
        return this.name;
    }

    public short getSemester()
    {
        return this.semester;
    }
}
