package uk.co.krystianjagoda.helpers.readers;

import uk.co.krystianjagoda.model.Player;
import uk.co.krystianjagoda.model.Team;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class LinesTeamReader extends AbstractTeamReader {

    @Override
    public Team read(String path) throws IOException {

        File file = getFile(path);

        if (!file.exists()) {
            throw new IOException("File does not exist");
        }

        Team team = createNewTeam(file);

        List<String> lines = Files.readAllLines(Paths.get(path));

        for (String line : lines) {
            String[] data = line.split(";");
            team.addNewTeamMember(new Player(data[0], data[1]));
        }

        return team;
    }
}
