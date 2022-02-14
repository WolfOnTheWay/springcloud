package com.sdt.fsc.entity.camera;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddDeviceInfoParamDTO {
    private String name;
    private String ip;
    private Integer port;
    private String username;
    private String password;
    private String gbId;
}
