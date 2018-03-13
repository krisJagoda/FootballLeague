package uk.co.krystianjagoda.model;

import org.junit.Test;
import com.google.common.testing.NullPointerTester;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MatchTest {

    private NullPointerTester nullTester = new NullPointerTester();
    private Team host = new Team("Team Host");
    private Team guest = new Team("Team guest");
    private Player hostPlayerWhoScored = new Player("John", "Doe");
    private Player guestPlayerWhoScored = new Player("Dave", "Rib");
    private Match match;


    @Test
    public void testPublicConstructorOfTheMarchClassForNullValues() {
        nullTester.setDefault(Team.class, guest).testAllPublicConstructors(Match.class);
    }

    @Test
    public void ifNooneScoredDuringTheMatchTheResultIsDraw() {
        match = new Match(host, guest, Collections.emptyList(), Collections.emptyList());

        assertThat(match.getResult()).isEqualTo(Result.DRAW);
    }

    @Test
    public void ifHostPlayerScoredDuringTheMatchHostTeamWin() {
        host.addNewTeamMember(hostPlayerWhoScored);
        match = new Match(host, guest, Collections.singletonList(hostPlayerWhoScored), Collections.emptyList());

        assertThat(match.getResult()).isEqualTo(Result.HOST);

    }

    @Test
    public void ifGuestPlayerScoredDuringTheMatchGuestTeamWin() {
        guest.addNewTeamMember(guestPlayerWhoScored);
        match = new Match(host, guest, Collections.emptyList(), Collections.singletonList(guestPlayerWhoScored));

        assertThat(match.getResult()).isEqualTo(Result.GUEST);

    }

    @Test
    public void forAPlayerWhoIsNotInGuestTeamAndScoredThrowAnIllegalArgumentException() {
        guest.addNewTeamMember(guestPlayerWhoScored);
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("Adam", "Peter"));

        assertThatThrownBy(() -> new Match(host, guest, Collections.emptyList(), playerList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Team should have all players who scored during match.");

    }

    @Test
    public void forAPlayerWhoIsNotInHostTeamAndScoredThrowAnIllegalArgumentException() {
        host.addNewTeamMember(hostPlayerWhoScored);
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("Adam", "Peter"));

        assertThatThrownBy(() -> new Match(host, guest, playerList, Collections.emptyList()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Team should have all players who scored during match.");

    }
}