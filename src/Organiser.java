import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by guyb on 4/08/16.
 */
public class Organiser
{

    private BufferedReader mReader;
    private Map<String, String> mMenu;

    public Organiser()
    {
        mReader = new BufferedReader(new InputStreamReader(System.in));
        mMenu = new HashMap<String, String>();

        mMenu.put("create", "add a new team");
        mMenu.put("quit", "exit the program");

    }



    public void run()
    {
        String choice = "";



    }
}
