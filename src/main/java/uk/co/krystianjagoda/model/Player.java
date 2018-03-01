package uk.co.krystianjagoda.model;

public class Player {
    private final String firstName;
    private final String lastName;

    public Player(String firstName, String lastName) {

        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("You must provide both the first and last name of the player.");
        }

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        return (getFirstName() != null ? getFirstName().equals(player.getFirstName()) : player.getFirstName() == null)
                && (getLastName() != null ? getLastName().equals(player.getLastName()) : player.getLastName() == null);
    }

    @Override
    public int hashCode() {
        int result = getFirstName() != null ? getFirstName().hashCode() : 0;
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        return result;
    }
}
