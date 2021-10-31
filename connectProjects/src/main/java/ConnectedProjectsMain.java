import common.PersonalData;

public class ConnectedProjectsMain {
    public static void main(String[] args){
        System.out.println("This project has instructions on connecting the different subprojects together and use common classes.");

        PersonalData personalData = PersonalData.getInstance();
        System.out.println("Importing PersonalData class from core project and printing my data:");
        System.out.println(personalData);
    }
}
