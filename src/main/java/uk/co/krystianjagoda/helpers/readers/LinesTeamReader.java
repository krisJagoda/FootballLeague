package uk.co.krystianjagoda.helpers.readers;

import uk.co.krystianjagoda.helpers.readers.AbstractTeamReader;
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

        for (int i = 0; i < lines.size(); i++) {
            team.addNewTeamMember(new Player(lines.get(0), lines.get(1)));

        }
        return team;
    }
}
