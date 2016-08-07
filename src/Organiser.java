import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;
import com.teamtreehouse.model.Teams;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by guyb on 4/08/16.
 */
public class Organiser
{

    private BufferedReader mReader;
    private Map<String, String> mMenu;
    private Player[] mPlayers;
    private Teams mTeams;

    public Organiser(Player[] players)
    {
        mReader = new BufferedReader(new InputStreamReader(System.in));
        mMenu = new HashMap<String, String>();
        mPlayers = players;
        mTeams = new Teams();


        // Build the menu items
        mMenu.put("create", "add a new team");
        mMenu.put("add", "Add a player to a team");
        mMenu.put("remove", "Remove players from a team");
        mMenu.put("quit", "exit the program");

    }


    private String promptAction() throws IOException
    {
        System.out.printf("%n%n%n");

        // Print out the menu options
        for (Map.Entry<String, String> option: mMenu.entrySet())
        {

            System.out.println(option.getKey() + "      :   " + option.getValue());

        }

        System.out.printf("%n%n%n");
        System.out.println("What do you want to do?");
        System.out.printf("# ");

        String choice = mReader.readLine();
        return choice.trim().toLowerCase();

    }

    public void run()
    {
        String choice = "";

        do
        {
            try
            {
                choice = promptAction();

                switch (choice)
                {
                    case "create":
                        // Create a new team
                        createTeam();
                        break;
                    case "add":
                        // Add a player to a team
                        addPlayerToTeam();
                        break;
                    case "remove":
                        removePlayerFromTeam();
                        break;
                    case "quit":
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Unknown choice: " + choice + ", please try again");
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        } while (!choice.equals("quit"));


    }

    private void removePlayerFromTeam()
    {

    }

    private void addPlayerToTeam()
    {
        // Show list of players

        // TODO: Create sort method
        Set<Player> sortedPlayers = new TreeSet<>(new Comparator<Player>() {
            @Override
            public int compare(Player player1, Player player2)
            {
                if(player1.equals(player2))
                {
                    return 0;
                }
                return player1.getFirstName().compareTo(player2.getFirstName());

            }
        });
        // Sort the players alphabetically
        for(Player player : mPlayers)
        {
            sortedPlayers.add(player);
        }

        for (Player player : sortedPlayers)
        {
            System.out.println(
                    "Name: " + player.getFirstName() + " " + player.getLastName() + "\n"
                            + "Height: " + player.getHeightInInches() + "\" " + "\n"
                            + "Previous experience: " + player.isPreviousExperience() + "\n");
        }
    }

    private void createTeam()
    {
        Console console = System.console();
        String teamName = console.readLine("Team name:");
        String coachName = console.readLine("Coach name:");
        Team team = new Team(teamName, coachName);
        mTeams.addTeam(team);

        System.out.println("Team " + teamName + " added with coach " + coachName);

        // TODO: add a class called Teams and add this to the teams class

    }
}
