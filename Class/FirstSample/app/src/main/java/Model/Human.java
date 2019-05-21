package Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Human implements Serializable {
    private String name;
    private String phone;
    private boolean gender;

    public Human(String name, String phone, boolean gender) {
        this.name = name;
        this.phone = phone;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

}
