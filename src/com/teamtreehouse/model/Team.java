package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guyb on 4/08/16.
 */
public class Team
{

    // Fields
    private String mTeamName;
    private String mCoachName;
    public static final int TEAM_MAX_PLAYERS = 11;
    private List<Player> mPlayer = new ArrayList<>();

    // Constructor
    public Team(String teamName, String coachName)
    {
        mTeamName = teamName;
        mCoachName = coachName;

    }

    public String getTeamName()
    {
        return mTeamName;
    }

    public String getCoachName()
    {
        return mCoachName;
    }

    public void addPlayer(Player player)
    {
        mPlayer.add(player);
    }

    public void removePlayer(Player player)
    {
        mPlayer.remove(player);
    }


}
