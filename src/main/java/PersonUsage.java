import com.google.gson.Gson;
import pojo.Person;

public class PersonUsage {

    public static void displayPerson(Person person) {

        Gson gson = new Gson();
        String jsonInString = gson.toJson(person);
        System.out.println("Person: " + person);

    }
}
