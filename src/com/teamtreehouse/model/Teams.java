package com.teamtreehouse.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guyb on 7/08/16.
 */
public class Teams
{
    private List<Team> teams;

    public Teams()
    {
        teams = new ArrayList<>();
    }

    public void addTeam(Team team)
    {
        teams.add(team);
    }




}
