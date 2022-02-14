package com.sdt.fsc.entity.camera;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceLoginInfo {
    private String ip;
    private Integer port;
    private String username;
    private String password;
}
