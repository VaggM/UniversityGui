package University.domains;

import java.util.Date;

public class Person {
    private String firstName;
    private String lastName;
    private Date birthDate;


    public Person() {
    }

    public Person(String firstName, String lastName, Date birthDate) throws Exceptions{
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setBirthDate(birthDate);
    }

    public Person(String firstName, String lastName) throws Exceptions{
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }


    //get and set methods


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws Exceptions{
        if (checkName(firstName))
            this.firstName = firstName;
        if (this.firstName == null)
            throw new Exceptions("Incorrect First Name");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws Exceptions {
        if (checkName(lastName))
            this.lastName = lastName;
        if (this.lastName == null)
            throw new Exceptions("Incorrect Last Name");
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    private boolean checkName (String name){

        char x = name.charAt(0);

        if ((x < 65) || (x > 90))
            return false;

        for (int i=1; i<name.length(); i++)
        {
            x = name.charAt(i);
            if ((x < 97) || (x > 122))
                return false;
        }

        return true;
    }

}