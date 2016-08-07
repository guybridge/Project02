package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guyb on 7/08/16.
 */
public class Teams
{
    private List<Team> mTeams;

    public Teams()
    {
        mTeams = new ArrayList<>();
    }

    public void addTeam(Team team)
    {
        mTeams.add(team);
    }

    public List<Team> getTeams()
    {
        return mTeams;
    }


}
