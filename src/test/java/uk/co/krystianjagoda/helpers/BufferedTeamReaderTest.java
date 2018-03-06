package uk.co.krystianjagoda.helpers;


import uk.co.krystianjagoda.helpers.readers.BufferedTeamReader;

public class BufferedTeamReaderTest extends AbstractTeamReaderTest {

    public BufferedTeamReaderTest() {
        super(new BufferedTeamReader());
    }
}