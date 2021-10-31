import common.PersonalData;

public class Main {
    public static void main (String[] args){
        System.out.println("StreamTests main method");
        PersonalData personalData = PersonalData.getInstance();
        System.out.println(personalData);
    }
}
