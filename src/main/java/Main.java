public class Main {

    public static void main (String[] args){
        System.out.println("This is the main project and is used for quick tests when i can't be bothered to start a new project");

        PersonalData personalData =  PersonalData.getInstance();
        System.out.println(personalData);
    }
}
