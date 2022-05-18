package shared.objects.customer;

import java.util.ArrayList;

public class Customer {
    private String userName;
    private String phoneNo;
    private String password;

    public Customer(String userName, String phoneNo, String password) {
        this.userName = userName;
        this.phoneNo = phoneNo;
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getPassword() {
        return password;
    }
}
