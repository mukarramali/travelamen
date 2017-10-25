package prashushi.travelamen.model;

/**
 * Created by Dell User on 4/2/2017.
 */

public class User {
    String id, name, email, dob, phone;

    public User(){

    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        if(dob==null)
            return "";
        return dob;
    }

    public String getEmail() {
        if(email==null)
            return "";
        return email;
    }

    public String getId() {
        if(id==null)
            return "";
        return id;
    }

    public String getName() {
        if(name ==null)
            return "";
        return name;
    }

    public String getPhone() {
        if(phone==null)
            return "";
        return phone;
    }

}
