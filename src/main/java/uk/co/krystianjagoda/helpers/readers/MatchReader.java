package uk.co.krystianjagoda.helpers.readers;

import uk.co.krystianjagoda.model.Match;
import uk.co.krystianjagoda.model.Player;
import uk.co.krystianjagoda.model.Team;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

public class MatchReader {

    public Match read(String path, Map<String, Team> teams) throws IOException{

        checkNotNull(path, "Path to file cannot be null.");
        checkArgument(!path.isEmpty(), "Path to file cannot be empty.");
        checkIfFileExists(path);
        checkArgument(teams.size() == 2, "There have to be two teams to read the match.");

        List<String> lines = Files.readAllLines(Paths.get(path));

        String hostName = getTeamName(lines.stream(), "host:");
        String guestName = getTeamName(lines.stream(), "guest:");

        checkState(teams.containsKey(hostName), "%s doest not contain  host %s", teams, hostName);
        checkState(teams.containsKey(guestName), "%s doest not contain  host %s", teams, guestName);

        List<Player> listOfHostPlayersWhoScored = getPlayersTwoScored(lines.stream(), "host-score:");
        List<Player> listOfGuestPlayersWhoScored = getPlayersTwoScored(lines.stream(), "guest-score:");

        return new Match(teams.get(hostName), teams.get(guestName), listOfHostPlayersWhoScored, listOfGuestPlayersWhoScored);

    }


    private void checkIfFileExists(String path) throws IOException {
        File file = new File(path);

        if (!file.exists()) {
            throw new IOException("File does not exist");
        }
    }
    private String getTeamName(Stream<String> lines, String prefix){

        return lines.filter(line -> line.startsWith(prefix))
                .map(line -> line.replace(prefix, ""))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    private List<Player> getPlayersTwoScored(Stream<String> lines, String prefix){

        return lines.filter(line -> line.startsWith(prefix))
                .map(line -> line.replace(prefix, ""))
                .map(this::readFromLine)
                .collect(Collectors.toList());
    }

    private Player readFromLine(String line){
        String[] data = line.split(";");
        return new Player(data[0], data[1]);
    }

}
