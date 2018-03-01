package uk.co.krystianjagoda.model;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class PlayerTest {

    @Test
    public void forAnEmptyStringAsPlayersFirstNameThrowAnIllegalArgumentException() throws Exception {

        assertThatThrownBy(() -> new Player("","Doe"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("You must provide both the first and last name of the player.");
    }

    @Test
    public void forAnEmptyStringAsPlayersLastNameThrowAnIllegalArgumentException() throws Exception {

        assertThatThrownBy(() -> new Player("John", ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("You must provide both the first and last name of the player.");
    }

    @Test
    public void forANullValueGivenAsPLayersFirstNameThrowIllegalArgumentException() throws Exception {

        assertThatThrownBy(() -> new Player(null, "Doe"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("You must provide both the first and last name of the player.");
    }
    @Test
    public void forANullValueGivenAsPLayersLastNameThrowIllegalArgumentException() throws Exception {

        assertThatThrownBy(() -> new Player("John", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("You must provide both the first and last name of the player.");
    }

    @Test
    public void forGivenFirstNameAndLastNameCreateANewInstanceOfPlayerClass() throws Exception {

        new Player("John", "Doe");
    }
}