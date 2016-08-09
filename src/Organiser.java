import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Team;
import com.teamtreehouse.model.Teams;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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
        mMenu.put("roster", "Show players on a team");
        mMenu.put("add", "Add a player to a team");
        mMenu.put("balance", "League Balance Report");
        mMenu.put("remove", "Remove players from a team");
        mMenu.put("quit", "exit the program");
        mMenu.put("report", "Height report for specific team");

    }


    private String promptAction() throws IOException
    {
        System.out.printf("%n%n%n");

        // Print out the menu options
        for (Map.Entry<String, String> option: mMenu.entrySet())
        {

            System.out.println(option.getKey() + "      -   " + option.getValue());

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
                // Prompt the user for the choice
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
                    case "roster":
                        displayTeamRoster();
                        break;
                    case "balance":
                        balanceReport();
                        break;
                    case "report":
                        heightReport();
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

    private void balanceReport()
    {
            // Display each team in the league and show how many experienced players and inexperienced players on each team

            System.out.println("#############################");
            System.out.println("### League Balance Report ###");
            System.out.println("#############################");

            for (Team team : mTeams.getTeams())
            {
                // Display the team name
                System.out.println("");
                System.out.println("Team: " + team.getTeamName());
                System.out.println("Coach: " + team.getCoachName());

                // Check experienced players count and inexperienced players count

                // For each player on the team increment the count
                int inexperiencedCount = 0;
                int experiencedCount = 0;

                for (Player player : team.getPlayers())
                {
                    if (player.isPreviousExperience())
                    {
                        // The player has experience, increment
                        experiencedCount++;
                    }
                    else // Else increment the inexperienced Count
                    {
                        inexperiencedCount++;
                    }

                }

                // Now display for the given team
                System.out.println("Players with previous experience: " + experiencedCount);
                System.out.println("Players without previous " + inexperiencedCount);
                System.out.println("");

            }
    }

    private void displayTeamRoster()
    {
            System.out.println("#############################");
            System.out.println("###     TEAM ROSTERS      ###");
            System.out.println("#############################");
            System.out.println("Select your team");

            showTeams();

            String team = System.console().readLine("# ");
            int index = getTeamIndex(team);
            // Show the players on it

            System.out.println("Showing all players on " + mTeams.getTeams().get(index).getTeamName());
            System.out.println("");

            for (Player player : mTeams.getTeams().get(index).getPlayers())
            {
                System.out.println(player.getFirstName() + " " + player.getLastName());
            }

    }

    // Displays team alphabetically
    private void showTeams()
    {
        // Display a list of teams
        // Get an ordered list of teams
        Set<Team> sortedTeams = sortTeams();

        for (Team team : sortedTeams)
        {
            sortedTeams.add(team);
        }

        for (Team team : sortedTeams)
        {
            System.out.println("Team: " + team.getTeamName());
            System.out.println("Coach: " + team.getCoachName());
            System.out.println("");
        }
    }

    private int getTeamIndex(String teamName)
    {

        // Loop through the teams list
        int teamIndex = 0;
        for (int i = 0; i < mTeams.getTeams().size(); i++)
        {
            if(teamName.equals(mTeams.getTeams().get(i).getTeamName()))
            {
                System.out.println("Team found at position: " + i);
                // Save location
                teamIndex = i;
            }
        }

      return teamIndex;
    }


    // TODO: Report based on height
    private void heightReport()
    {
        System.out.println("#############################");
        System.out.println("###  TEAM HEIGHT REPORT   ###");
        System.out.println("#############################");
        System.out.println("");

        showTeams();

        // Get a team selection
        String teamSelection = System.console().readLine("Select the team you want to do the height report on: ");
        // Loop through the teams list
        int teamIndex = getTeamIndex(teamSelection);



        Map<Integer, List<String>> map = new TreeMap<>();


        for (Player player : mTeams.getTeams().get(teamIndex).getPlayers())
        {

            List<String> players = new ArrayList<>();
            int height = player.getHeightInInches();
            String firstname = player.getFirstName();
            String lastname = player.getLastName();

            players.add(firstname + " " + lastname);

            map.put(height, players);

        }

       for(Map.Entry<Integer, List<String>> entry : map.entrySet())
       {
           System.out.println("Height Group: " + entry.getKey());
           for(String player : entry.getValue())
           {
               System.out.println("Player Name: " + player);
           }
       }



    }

    private int getPlayerIndex(String player)
    {

        // Loop through the players array to check which one we chose
        int playerIndex = 0;
        for (int i = 0; i < mPlayers.length; i++)
        {
            String playerFullName = mPlayers[i].getFirstName() + " " + mPlayers[i].getLastName();
            if (playerFullName.equals(player)) {
                // Now add the found player to the Team
                System.out.println("");
                System.out.println("###### FOUND PLAYER at index: " + i);
                // Record the player index
                playerIndex = i;
            }
        }

        return playerIndex;
    }

    private void removePlayerFromTeam()
    {

        System.out.println("#############################");
        System.out.println("###     REMOVE PLAYER     ###");
        System.out.println("#############################");

        System.out.println("Which player do you want to remove? ");
        System.out.println("");

        showPlayers();

        // Get the player selection
        String playerSelection = System.console().readLine("Player selection: ");

        // Loop through the players array to check which one we chose
        int playerIndex = getPlayerIndex(playerSelection);

        System.out.println("Now choose what team to remove " + playerSelection + " from.");
        System.out.println("#############################");
        System.out.println("###        TEAM LIST      ###");
        System.out.println("#############################");
        System.out.println("");

        showTeams();

        // Get a team selection
        String teamSelection = System.console().readLine("Select the team you want to remove " + playerSelection + " from #");
        // Loop through the teams list
        int teamIndex = getTeamIndex(teamSelection);

        // Now remove the player to the team at that teams index
        mTeams.getTeams().get(teamIndex).removePlayer(mPlayers[playerIndex]);

        System.out.println(
                "Player: " + mPlayers[playerIndex].getFirstName()
                        + " " + mPlayers[playerIndex].getLastName()
                        + " removed " + " from team " + mTeams.getTeams().get(teamIndex).getTeamName());

    }

    private void showPlayers()
    {
        // Get a listed of sorted players
        Set<Player> sortedPlayers = sortPlayers();
        // Loop through the list and display
        for (Player player : sortedPlayers)
        {
            System.out.println(
                    "Name: " + player.getFirstName() + " " + player.getLastName() + "\n"
                            + "Height: " + player.getHeightInInches() + "\" " + "\n"
                            + "Previous experience: " + player.isPreviousExperience() + "\n");
        }
    }

    private void addPlayerToTeam()
    {

        System.out.println("#############################");
        System.out.println("###     ADD PLAYER        ###");
        System.out.println("#############################");
        System.out.println("Which player do you want to add? ");
        System.out.println("");

        showPlayers();

        // Get the player selection
        String playerSelection = System.console().readLine("Player selection: ");

        // Loop through the players array to check which one we chose
        int playerIndex = getPlayerIndex(playerSelection);

        System.out.println("Now choose what team to add " + playerSelection + " to.");
        System.out.println("### TEAM LIST ####");
        System.out.println("");

        showTeams();

        // Get a team selection
        String teamSelection = System.console().readLine("Select the team you want to add " + playerSelection + " to #");
        // Loop through the teams list
        int teamIndex = getTeamIndex(teamSelection);

        // Now at the player to the team at that teams index
        mTeams.getTeams().get(teamIndex).addPlayer(mPlayers[playerIndex]);

        System.out.println(
                "Player: " + mPlayers[playerIndex].getFirstName()
                + " " + mPlayers[playerIndex].getLastName()
                + " added " + " to team " + mTeams.getTeams().get(teamIndex).getTeamName());



    }

    private Set<Team> sortTeams() {
        // Sort the teams
        Set<Team> sortedTeams = new TreeSet<>(new Comparator<Team>() {
            @Override
            public int compare(Team team1, Team team2)
            {
                if(team1.equals(team2))
                {
                    return 0;
                }
                return team1.getTeamName().compareTo(team2.getTeamName());
            }
        });

        for (Team team : mTeams.getTeams())
        {
            sortedTeams.add(team);
        }
        return sortedTeams;
    }


    // Sort the players alphabetically
    private Set<Player> sortPlayers() {

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
        return sortedPlayers;
    }



    private void createTeam()
    {

        System.out.println("#############################");
        System.out.println("###   CREATE NEW TEAM     ###");
        System.out.println("#############################");

        Console console = System.console();
        String teamName = console.readLine("Team name:");
        String coachName = console.readLine("Coach name:");
        Team team = new Team(teamName, coachName);
        mTeams.addTeam(team);

        System.out.println("");
        System.out.println("Team " + teamName + " added with coach " + coachName);
        System.out.println("");

    }


}
