package com.teamtreehouse.model;

/**
 * Created by guyb on 4/08/16.
 */
public class Team
{

    // Fields
    private String mTeamName;
    private String mCoachName;
    public static final int TEAM_MAX_PLAYERS = 11;

    // Constructor
    public Team(String teamName, String coachName)
    {
        mTeamName = teamName;
        mCoachName = coachName;

    }


}
