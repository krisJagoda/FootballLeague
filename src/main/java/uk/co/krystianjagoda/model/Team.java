package uk.co.krystianjagoda.model;

import java.util.*;

public class Team {
    private final String name;
    private final Set<Player> teamMembers = new HashSet<>();

    public Team(String name) throws IllegalArgumentException {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(String.format("You cannot provide either empty or %s team's name.", name));
        }
        this.name = name;

    }

    public void addNewTeamMember(Player player) {

        if (player == null) {
            throw new IllegalArgumentException("New players cannot be null.");
        }
        teamMembers.add(player);
    }

    public String getName() {
        return name;
    }

    public Set<Player> getTeamMembers() {
        return teamMembers;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;

        Team team = (Team) o;

        return (getName() != null ? getName().equals(team.getName()) : team.getName() == null)
                && (getTeamMembers() != null ? getTeamMembers().equals(team.getTeamMembers()) : team.getTeamMembers() == null);
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getTeamMembers() != null ? getTeamMembers().hashCode() : 0);
        return result;
    }
}
