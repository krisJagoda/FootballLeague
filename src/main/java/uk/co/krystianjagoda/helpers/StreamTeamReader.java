package uk.co.krystianjagoda.helpers;

import uk.co.krystianjagoda.model.Player;
import uk.co.krystianjagoda.model.Team;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class StreamTeamReader extends AbstractTeamReader {

    @Override
    public Team read(String path) throws IOException {

        File file = getFile(path);

        if (!file.exists()) {
            throw new IOException("File does not exist");
        }

        Team team = createNewTeam(file);

        Files.lines(Paths.get(path))
                .map(line -> {
                    String[] data = line.split(";");
                    return new Player(data[0], data[1]);
                }).forEach(team::addNewTeamMember);

        return team;
    }
}
