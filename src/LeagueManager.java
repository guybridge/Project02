/**
 * Created by guyb on 4/08/16.
 */
import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;

public class LeagueManager {

    public static void main(String[] args) {
        Player[] players = Players.load();
        System.out.printf("There are currently %d registered players.%n", players.length);

        System.out.println("");
        System.out.println("##########################################");
        System.out.println("###     L E A G U E  M A N A G E R     ###");
        System.out.println("##########################################");

        Organiser organiser = new Organiser(players);

        organiser.run();



    }

}
