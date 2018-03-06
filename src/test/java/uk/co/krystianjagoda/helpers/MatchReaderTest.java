package uk.co.krystianjagoda.helpers;

import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;
import uk.co.krystianjagoda.helpers.readers.MatchReader;
import uk.co.krystianjagoda.model.Match;
import uk.co.krystianjagoda.model.Player;
import uk.co.krystianjagoda.model.Result;
import uk.co.krystianjagoda.model.Team;
import java.io.IOException;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MatchReaderTest {

    private MatchReader reader;
    private Map<String, Team> teams;

    @Before
    public void setUp() {
        reader = new MatchReader();
        Team chelsea = new Team("Chelsea F.C.");
        Team liverpool = new Team("Liverpool F.C.");
        chelsea.addNewTeamMember(new Player("Eden", "Hazard"));
        liverpool.addNewTeamMember(new Player("Adam", "Lallana"));
        teams = ImmutableMap.of(chelsea.getName(), chelsea, liverpool.getName(), liverpool);

    }

    @Test
    public void forANullPathToFileThrowNullPointerException() {

        assertThatThrownBy(() -> reader.read(null, teams))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("Path to file cannot be null.");
    }

    @Test
    public void forAnEmptyPathToFileThrowAnIllegalArgumentException() {

        assertThatThrownBy(() -> reader.read("", teams))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Path to file cannot be empty.");
    }

    @Test
    public void ifFileDoesNotExistThrowIOException() {
        String path = "src/test/resources/test.txt";

        assertThatThrownBy(() -> reader.read(path, teams))
                .isInstanceOf(IOException.class)
                .hasMessage("File does not exist");
    }

    @Test
    public void ifBothTeamsScoredOneGoalTheResultIsDraw() throws IOException {
        String path = "src/test/resources/Chelsea F.C. draws with Liverpool F.C.txt";

        Match match = reader.read(path, teams);

        assertThat(match.getResult()).isEqualByComparingTo(Result.DRAW);
    }

    @Test
    public void ifHostWinsTheResultIsHost() throws IOException {
        String path = "src/test/resources/Chelsea F.C. defeats Liverpool F.C.txt";

        Match match = reader.read(path, teams);

        assertThat(match.getResult()).isEqualByComparingTo(Result.HOST);
    }

    @Test
    public void ifGuestWinsTheResultIsGuest() throws IOException {
        String path = "src/test/resources/Liverpool F.C. defeats Chelsea F.C.txt";

        Match match = reader.read(path, teams);

        assertThat(match.getResult()).isEqualByComparingTo(Result.GUEST);
    }
}