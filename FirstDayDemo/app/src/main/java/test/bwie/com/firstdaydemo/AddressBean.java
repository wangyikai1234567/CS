package test.bwie.com.firstdaydemo;

/**
 * date: 2017/4/7.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class AddressBean {
    private String id;
    private String address;
    private String addressname;

    public AddressBean() {
    }

    public AddressBean(String id, String address, String addressname) {
        this.id = id;
        this.address = address;
        this.addressname = addressname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressname() {
        return addressname;
    }

    public void setAddressname(String addressname) {
        this.addressname = addressname;
    }
}
