package uk.co.krystianjagoda.model;

import java.util.HashSet;
import java.util.Set;

public class Team {
    private final String name;
    private final Set<Player> teamMembers;

    public Team(String name, Set<Player> teamMembers) throws IllegalArgumentException {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(String.format("You cannot provide either empty or %s team's name.", name));
        }

        if (teamMembers == null) {
            throw new IllegalArgumentException("Team cannot be null.");
        }

        this.name = name;
        this.teamMembers = new HashSet<>(teamMembers);
    }

    public void addNewTeamMember(Player player) {

        if (player == null){
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;

        Team team = (Team) o;

        return getName().equals(team.getName())
                && getTeamMembers().equals(team.getTeamMembers());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getTeamMembers().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                '}';
    }
}
