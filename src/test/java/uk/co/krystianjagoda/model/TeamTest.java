package uk.co.krystianjagoda.model;


import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TeamTest {

    @Test
    public void forAnEmptyStringAsTeamsNameThrowAnIllegalArgumentException() {

        assertThatThrownBy(() -> new Team(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("You cannot provide either empty or %s team's name.", ""));
    }

    @Test
    public void forANullValueProvidedAsTeamThrowIllegalArgumentException() {


        assertThatThrownBy(() -> new Team(null))
                .isInstanceOf(NullPointerException.class);

    }

    @Test
    public void forANonEmptyOrNotNullNameCreateNewTeam() {

        new Team("Manchester United");
    }

    @Test
    public void forTwoDifferentPlayersAddThemToTheTeam() {
        Team team = new Team("Manchester United");
        team.addNewTeamMember(new Player("Berry", "Walt"));
        team.addNewTeamMember(new Player("John", "Doe"));

        assertThat(team.getTeamMembers()).contains(new Player("John", "Doe"), new Player("Berry", "Walt"));

    }

    @Test
    public void forAddingTheSamePlayerTwiceHeIsAssignedOnlyOnce() {
        Team team = new Team("Manchester United");

        team.addNewTeamMember(new Player("John", "Doe"));
        team.addNewTeamMember(new Player("John", "Doe"));

        assertThat(team.getTeamMembers()).containsOnly(new Player("John", "Doe"));
    }
}