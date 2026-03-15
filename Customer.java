package model;
public class Customer {

    private String name;
    private String dob;
    private String email;
    private String phone;

    public Customer(String name,String dob,String email,String phone){
        this.name=name;
        this.dob=dob;
        this.email=email;
        this.phone=phone;
    }

    public String getName(){ return name; }
    public String getDob(){ return dob; }
    public String getEmail(){ return email; }
    public String getPhone(){ return phone; }
}