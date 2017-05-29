package com.excilys.cdb.webapp.dto;

public class UserDTO {
    private String name;
    private String password;

    /**
     */
    public UserDTO() {
    }

    /**
     * @param name name
     * @param password password
     */
    public UserDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User" + name;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }

        UserDTO user2 = (UserDTO) obj;

        return name.equals(user2.name);
    }

    @Override
    public int hashCode() {

        final int prime = 73;
        int result = 1;
        result = prime * result + (name == null ? 0 : name.hashCode());
        return result;
    }

}
