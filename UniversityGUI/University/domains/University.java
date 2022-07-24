package University.domains;

import java.util.ArrayList;

public final class University {
    public final String NAME = "University of West Attica";
    public final String ADDRESS = "Egaleo, Greece";
    private ArrayList<Department> departments;
    private static short totalUniversities;
    private StringBuilder uniqueID = new StringBuilder("University-");


    public University(){
        this.uniqueID.append(totalUniversities);
        LogUniversity log = new LogUniversity("future change");
    };

    public University(ArrayList<Department> departments) {
        this.setDepartments(departments);
        this.uniqueID.append(totalUniversities);
        LogUniversity log = new LogUniversity("future change");
    }

    class LogUniversity extends LogAbstract{

        public LogUniversity(String info){
            logAction(info);
        }

        @Override
        public void logAction(String info) {
            Logger.logInit("University\t" + totalUniversities++,"");
        }
    }


    //get and set methods


    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }
}
