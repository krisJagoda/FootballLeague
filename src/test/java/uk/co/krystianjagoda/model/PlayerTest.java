package uk.co.krystianjagoda.model;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class PlayerTest {

    @Test
    public void forAnEmptyStringAsPlayersFirstNameThrowAnIllegalArgumentException() {

        assertThatThrownBy(() -> new Player("","Doe"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("You must provide both the first and last name of the player.");
    }

    @Test
    public void forAnEmptyStringAsPlayersLastNameThrowAnIllegalArgumentException() {

        assertThatThrownBy(() -> new Player("John", ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("You must provide both the first and last name of the player.");
    }

    @Test
    public void forANullValueGivenAsPLayersFirstNameThrowIllegalArgumentException() {

        assertThatThrownBy(() -> new Player(null, "Doe"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("You must provide both the first and last name of the player.");
    }
    @Test
    public void forANullValueGivenAsPLayersLastNameThrowIllegalArgumentException() {

        assertThatThrownBy(() -> new Player("John", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("You must provide both the first and last name of the player.");
    }

    @Test
    public void forGivenFirstNameAndLastNameCreateANewInstanceOfPlayerClass() {

        new Player("John", "Doe");
    }
}