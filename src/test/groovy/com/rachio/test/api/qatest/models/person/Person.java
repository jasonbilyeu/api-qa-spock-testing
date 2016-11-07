package com.rachio.test.api.qatest.models.person;

import com.rachio.test.api.qatest.models.device.Device;
import com.rachio.test.api.qatest.models.device.ManagedDevice;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

@Data
public class Person {
    private DateTime createDate;
    private String id;
    private String username;
    private String fullName;
    private String email;
    private List<Device> devices = new ArrayList<Device>();
    private Boolean enabled;
    private List<Object> roles = new ArrayList<Object>();
    private List<ManagedDevice> managedDevices = new ArrayList<ManagedDevice>();
    private String displayUnit;
}
