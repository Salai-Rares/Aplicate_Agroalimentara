package Models;

import com.google.gson.annotations.SerializedName;

import static Exceptions.isPhoneException.isPhone;

public class User {
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("nume")
    private String nume;
    @SerializedName("email")
    private String email;
    @SerializedName("telefon")
    private String telefon;
    @SerializedName("rol")
    private String rol;


    public User(String username,String password,String nume, String email, String telefon, String rol) throws IllegalArgumentException{
        this.username=username;
        this.password=password;
        this.nume = nume;
        this.email = email;
        if(telefon.length()!=10)
            throw new IllegalArgumentException("Introduceti un numar valid");
        if(!isPhone(telefon))
            throw new NumberFormatException("Numarul trebuie sa inceapa cu 0");
        this.telefon = telefon;

        this.rol = rol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;
        return rol.equals(user.rol);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + rol.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }


}
