package uk.co.krystianjagoda.helpers.readers;

import uk.co.krystianjagoda.helpers.readers.AbstractTeamReader;
import uk.co.krystianjagoda.model.Player;
import uk.co.krystianjagoda.model.Team;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BufferedTeamReader extends AbstractTeamReader {

    @Override
    public Team read(String path) throws IOException {

        File file = getFile(path);

        if (!file.exists()) {
            throw new IOException("File does not exist");
        }
        Team team = createNewTeam(file);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line = reader.readLine();

            while (line != null) {
                String[] names = line.split(";");
                team.addNewTeamMember(new Player(names[0], names[1]));
                line = reader.readLine();
            }

        }
        return team;
    }
}
