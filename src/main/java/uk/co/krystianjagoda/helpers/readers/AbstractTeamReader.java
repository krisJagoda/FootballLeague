package uk.co.krystianjagoda.helpers.readers;


import uk.co.krystianjagoda.helpers.interfaces.TeamReader;
import uk.co.krystianjagoda.model.Team;

import java.io.File;

public abstract class AbstractTeamReader implements TeamReader {

    protected File getFile(String path){

        if (path == null || path.isEmpty()){
            throw new IllegalArgumentException("Path to file cannot be null or empty");
        }

        return new File(path);
    }

    protected Team createNewTeam(File file){
        return new Team(file.getName().replace(".txt", ""));
    }

}
