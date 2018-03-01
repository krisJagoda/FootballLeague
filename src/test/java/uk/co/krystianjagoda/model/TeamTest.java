package uk.co.krystianjagoda.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TeamTest {

    private Set<Player> players = new HashSet<>();
    private String name;

    @Test
    public void forAnEmptyStringAsTeamsNameThrowAnIllegalArgumentException() throws Exception {

        name = "";
        players.add(new Player("John", "Doe"));

        assertThatThrownBy(() -> new Team(name, players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("You cannot provide either empty or %s team's name.", name));
    }

    @Test
    public void forANullValueProvidedAsTeamThrowIllegalArgumentException() throws Exception {

        name = null;
        players.add(new Player("John", "Doe"));

        assertThatThrownBy(() -> new Team(name, players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("You cannot provide either empty or %s team's name.", name));
    }

    @Test
    public void forAEmptySetProvidedThrowIllegalArgumentException() throws Exception {

        name = "Manchester United";
        players = null;

        assertThatThrownBy(() -> new Team(name, players))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Team cannot be null.");
    }

    @Test
    public void forNonEmptyNameAndNotNullSetOfPlayersCreateANewInstanceOfTheTeamClass() throws Exception {

        name = "Manchester United";
        players.add(new Player("John", "Doe"));

        new Team(name, players);
    }

    @Test
    public void forANullPlayerThrowAnIllegalArgumentException() throws Exception {

        name = "Manchester United";
        players.add(new Player("John", "Doe"));

        assertThatThrownBy(() -> new Team(name, players).addNewTeamMember(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("New players cannot be null.");
    }

    @Test
    public void forTwoDifferentPlayersAddThemToTheTeam() throws Exception {

        name = "Manchester United";
        players.add(new Player("John", "Doe"));
        Team team = new Team(name, players);

        team.addNewTeamMember(new Player("Berry", "Walt"));


        Assertions.assertThat(team.getTeamMembers()).contains(new Player("John", "Doe"), new Player("Berry", "Walt"));

    }

    @Test
    public void forAddingTheSamePlayerTwiceHeIsAssignedOnlyOnce() throws Exception {
        name = "Manchester United";
        players.add(new Player("John", "Doe"));
        Team team = new Team(name, players);

        team.addNewTeamMember(new Player("John", "Doe"));

        Assertions.assertThat(team.getTeamMembers()).containsOnly(new Player("John", "Doe"));
    }
}