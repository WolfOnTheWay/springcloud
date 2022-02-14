package com.sdt.fsc.entity.record;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceInfo {
    private String deviceIP;
    private String devicePort;
    private String deviceUsername;
    private String devicePassword;
}
