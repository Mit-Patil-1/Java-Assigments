/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ass.pkg1_p02;

/**
 *
 * @author kevil-gandhi
 */
public class Address {

    private String name;
    private String homeAddress;
    private String homePhone;
    private String businessAddress;
    private String businessPhone;
    private String fax;
    private String mobile;
    private String pager;

    public Address(String name, String homeAddress, String homePhone,
                   String businessAddress, String businessPhone,
                   String fax, String mobile, String pager) {
        this.name = name;
        this.homeAddress = homeAddress;
        this.homePhone = homePhone;
        this.businessAddress = businessAddress;
        this.businessPhone = businessPhone;
        this.fax = fax;
        this.mobile = mobile;
        this.pager = pager;
    }

    // Getters
    public String getName() { return name; }
    public String getHomeAddress() { return homeAddress; }
    public String getHomePhone() { return homePhone; }
    public String getBusinessAddress() { return businessAddress; }
    public String getBusinessPhone() { return businessPhone; }
    public String getFax() { return fax; }
    public String getMobile() { return mobile; }
    public String getPager() { return pager; }
}
