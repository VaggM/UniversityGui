

public class Main
{
    public static void main(String[] Args)
    {
        Department dep = new Department("EEE");
        Student stud = new Student(19387165, "Evangelos", "Mitikas", "eee19387165", "Theanous 13", (short) 6, dep);
        Subject syb = new Subject("C#", (short) 1);
        Subject syb1 = new Subject("JAVA", (short) 2);

        stud.getInfo();

        dep.addSubjects((short) 1, "Kiklomata I", "Fisiki", "Mathimatiki Analisi I");

        dep.getSubjects();

        stud.addSubjectsSemester((short) 1);

        stud.getInfo();
    }
}
