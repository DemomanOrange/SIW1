package model;

import java.util.Objects;

public class Client {
    private String passportId;
    private String fullName;
    private String phoneNumber;

    public Client(String passportId, String fullName, String phoneNumber) {
        this.passportId = passportId;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public String getPassportId() { return passportId; }
    public String getFullName() { return fullName; }
    public String getPhoneNumber() { return phoneNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(passportId, client.passportId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passportId);
    }

    @Override
    public String toString() {
        return String.format("Client{Name='%s', Passport='%s', Phone='%s'}", fullName, passportId, phoneNumber);
    }
}
