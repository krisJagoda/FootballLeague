package uk.co.krystianjagoda.helpers;

import org.junit.Test;
import uk.co.krystianjagoda.helpers.interfaces.TeamReader;
import uk.co.krystianjagoda.model.Player;
import uk.co.krystianjagoda.model.Team;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public abstract class AbstractTeamReaderTest {

    private final TeamReader teamReader;

    public AbstractTeamReaderTest(TeamReader teamReader) {
        this.teamReader = teamReader;
    }

    @Test
    public void forNullFileThrowIllegalArgumentException() {

        assertThatThrownBy(() -> teamReader.read(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Path to file cannot be null or empty");
    }

    @Test
    public void forAnEmptyPathToFileThrowIllegalArgumentException() {

        assertThatThrownBy(() -> teamReader.read(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Path to file cannot be null or empty");
    }

    @Test
    public void forNonExistingFileThrowIOException() {

        String path = "src/test/resources/test.txt";

        assertThatThrownBy(() -> teamReader.read(path))
                .isInstanceOf(IOException.class)
                .hasMessage("File does not exist");
    }

    @Test
    public void forAnExistingFileCreateTeamWithName() throws Exception {

        String path = "src/test/resources/Manchester United F.C..txt";

        Team team = teamReader.read(path);

        assertThat(team.getName()).isEqualToIgnoringCase("Manchester United F.C.");

    }

    @Test
    public void forAnExistingFileCreateListOfPlayers() throws Exception {

        String path = "src/test/resources/Manchester United F.C..txt";

        Team team = teamReader.read(path);

        assertThat(team.getTeamMembers())
                .contains(new Player("Zlatan", "Ibrahimović")
                        , new Player("Paul", "Pogba")
                        , new Player("Henrich", "Mychitarian")
                        , new Player("Luke", "Shaw")
                        , new Player("Juan", "Mata"));
    }
}