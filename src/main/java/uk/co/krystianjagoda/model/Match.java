package uk.co.krystianjagoda.model;

import java.util.*;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Match {

    private Result result;
    private final Team host;
    private final Team guest;
    private final List<Player> hostScores;
    private final List<Player> guestScores;

    public Match(Team host, Team guest, List<Player> hostScores, List<Player> guestScores) {
        this.host = checkNotNull(host, "Team cannot be null.");
        this.guest = checkNotNull(guest, "Team cannot be null.");
        this.hostScores = checkNotNull(hostScores, "List of players who scored cannot be null.");
        this.guestScores = checkNotNull(guestScores, "List of players who scored cannot be null.");

        checkArgument(host.getTeamMembers().containsAll(hostScores)
                , "Team should have all players who scored during match.");
        checkArgument(guest.getTeamMembers().containsAll(guestScores)
                , "Team should have all players who scored during match.");

        if (hostScores.size() > guestScores.size()){
            this.result = Result.HOST;
        }
        if (hostScores.size() < guestScores.size()){
            this.result = Result.GUEST;
        }
        if (hostScores.size() == guestScores.size()){
            this.result = Result.DRAW;
        }
    }

    public Result getResult() {
        return result;
    }

    public Team getHost() {
        return host;
    }

    public Team getGuest() {
        return guest;
    }

    public List<Player> getHostScores() {
        return hostScores;
    }

    public List<Player> getGuestScores() {
        return guestScores;
    }

    @Override
    public String toString() {
        return "Match{" +
                "result=" + result +
                ", host=" + host +
                ", guest=" + guest +
                ", hostScores=" + hostScores +
                ", guestScores=" + guestScores +
                '}';
    }
}
