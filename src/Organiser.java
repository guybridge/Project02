import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.IOException;
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

        // Build the menu items
        mMenu.put("create", "add a new team");
        mMenu.put("add", "Add a player to a team");
        mMenu.put("remove", "Remove players from a team");
        mMenu.put("quit", "exit the program");

    }

    private String promptAction() throws IOException
    {
        System.out.printf("%n%n%n");

        for (Map.Entry<String, String> option: mMenu.entrySet())
        {

            System.out.println(option.getKey() + ": " + option.getValue());

        }

        System.out.printf("%n%n%n");
        System.out.println("What do you want to do?");
        System.out.printf("Choice: ");

        String choice = mReader.readLine();
        return choice.trim().toLowerCase();

    }

    public void run()
    {
        String choice = "";

        try
        {
            choice = promptAction();

            //TODO: Add switch statement for choice selections
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
