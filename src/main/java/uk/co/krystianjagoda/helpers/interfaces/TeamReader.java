package uk.co.krystianjagoda.helpers.interfaces;

import uk.co.krystianjagoda.model.Team;
import java.io.IOException;

public interface TeamReader {

    Team read(String path) throws IOException;

}
