import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;



public class Lab1 {

    boolean isAdult(int age) {
        /**
         * Checks if a person is an adult
         * this is the main scenario because the whole program depends on this if/else
         *this is  A function that accepts at least one argument and returns a value is defined and used
         * @param age the person's age
         * @return true if the person is 18 or older, false otherwise
         */
        return age >= 18;
    }

    void main()throws IOException  {
        String name = IO.readln("what's your name: ");
        int age = Integer.parseInt(IO.readln("how old are you: "));
//if adult then initiate this code
        if (isAdult(age)) {
            IO.println("ok "  + name + ", you are an adult");
            String drives = IO.readln("do you drive (yes/no): ");
            if (drives.equals("yes")) {
                // if the person drives then Randeep scenarios otherwise just ask for a survey
                String drinks = IO.readln("should you drink alcohol while driving? (yes/no): ");
                while (drinks.equals("yes")) {
                    IO.println("drinking and driving is not allowed in ontario, it's 0% alcohol tolerance if you are under 21 and after that, it is 0.05");
                }
// unless the rating is in numbers and fulfils the requirements keep on looping the while statement
                while(true){
                    String survey = IO.readln("thanks for your time! please rate our survey (out of 10): ");
                    try{
                        // throw errors if string is inputted or numbers not required or otherwise keep on running
                        Integer surveyreply = Integer.parseInt(survey);
                        if(surveyreply >= 1 && surveyreply <= 10){
                            IO.println("thanks, you rated the survey " + surveyreply + " out of ten");

                            // Write survey reply to file
                            String theWholeFile = surveyreply.toString() + System.lineSeparator();
                            Files.writeString(Paths.get("rating.txt"), theWholeFile, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
// once rating is done properly break the while loop
                            break;

                        } else {
                            IO.println("please give a number between 1 and 10 next time.");
                        }
                    } catch(NumberFormatException e){
                        IO.println("oops, you didn’t enter a number between 1 and 10");
                    }
                }
            } else {
                // last thank you message
                IO.println("thanks " + name + " for your time! since this survey is for drivers, we don’t need to continue. have a nice day!");
            }
        }
        //if age fetched from println less than 18 then initiate this code
        else {
            IO.println("ok " + name + ", you are a minor");
            String g1 = IO.readln("are you learning driving (yes/no): ");
            // similarly also run this while loop until and unless the requirements of minor to know the rule is met
            while (g1.equals("yes")) {
                String learning = IO.readln("should you drink alcohol while driving? (yes/no) ");
                IO.println("new ontario rule: minors must follow strict driving supervision law under which drinking and driving is illegal so make sure you read all the g1 rules properly until you are eligible to drive ");
                break;
            }

            while(true){
                // this is same wall statement which breaks on 10 less proper requirements of numbers is met in the survey rating
                String survey = IO.readln("thanks for your time! please rate our survey (out of 10): ");
                try{
                    Integer surveyreply = Integer.parseInt(survey);
                    if(surveyreply >= 1 && surveyreply <= 10 ){
                        IO.println("thanks, you rated the survey " + surveyreply + " out of ten , we hope you start learning soon and be out on the road!");

                        // Write survey reply to rating.txt file
                        String theWholeFile = surveyreply.toString() + System.lineSeparator();
                        Files.writeString(Paths.get("rating.txt"), theWholeFile, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

                        break;
                    } else {
                        IO.println("please give a number between 1 and 10 next time.");
                    }
                } catch (NumberFormatException e){
                    IO.println("oops, you didn’t enter a number between 1 and 10");
                }
            }
        }

        System.exit(0);
    }
}
