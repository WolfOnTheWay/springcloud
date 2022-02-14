package com.sdt.fsc.hik;

import com.sdt.fsc.entity.camera.DeviceLoginInfo;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HCNetSDKSingleton {
    public static volatile HCNetSDKSingleton instance;
    public static HCNetSDK hcNetSDK;
    public static HCNetSDK.PlayCtrl playCtrl;

    static {
        hcNetSDK = HCNetSDK.INSTANCE;
        playCtrl = HCNetSDK.PlayCtrl.INSTANCE;
        hcNetSDK.NET_DVR_Init();
    }

    //构造方法私有化
    private HCNetSDKSingleton(){}

    //单例模式双端检索 保证HCNetSDKSingleton 独一份
    public static HCNetSDKSingleton getInstance(){
        if (instance == null) {
            synchronized (HCNetSDKSingleton.class){
                if (instance == null){
                    instance = new HCNetSDKSingleton();
                }
            }
        }
        return instance;
    }

    //设备登录login_V30
    public static NativeLong loginV30(DeviceLoginInfo deviceLoginInfo){
        NativeLong lUserID = new NativeLong(-1);
        if (deviceLoginInfo != null){
            //设置超时时间
            hcNetSDK.NET_DVR_SetConnectTime(2000,1);
            hcNetSDK.NET_DVR_SetReconnect(100000,true);
            //设置设备的注册信息
            HCNetSDK.NET_DVR_DEVICEINFO_V30 net_dvr_deviceinfo_v30 = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
            lUserID = hcNetSDK.NET_DVR_Login_V30(deviceLoginInfo.getIp(), Short.valueOf(deviceLoginInfo.getPort().toString()), deviceLoginInfo.getUsername(), deviceLoginInfo.getPassword(), net_dvr_deviceinfo_v30);
            if (lUserID.intValue() == -1) {
                System.out.println("登录失败,NET_DVR_Login_V30 Failed,Error Code=" + hcNetSDK.NET_DVR_GetLastError());
                log.info("登录失败,NET_DVR_Login_V30 Failed,Error Code=" + hcNetSDK.NET_DVR_GetLastError());
            } else {
                System.out.println("登录成功,NET_DVR_Login_V30 Succeed！");
                log.info("登录成功,NET_DVR_Login_V30 Succeed!");
                log.info("用户句柄lUserID ========> {} :" + lUserID.intValue());
            }
        }
        return lUserID;
    }

    //设备登录login_V40
    public static NativeLong loginV40(DeviceLoginInfo deviceLoginInfo) {
        NativeLong lUserID = new NativeLong(-1);
        if (deviceLoginInfo != null){
            //设置超时时间
            hcNetSDK.NET_DVR_SetConnectTime(2000,1);
            hcNetSDK.NET_DVR_SetReconnect(100000,true);
            //设置设备的注册信息
            HCNetSDK.NET_DVR_USER_LOGIN_INFO m_strLoginInfo = new HCNetSDK.NET_DVR_USER_LOGIN_INFO();//设备登录信息
            HCNetSDK.NET_DVR_DEVICEINFO_V40 m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V40();//设备信息
            String m_sDeviceIP = deviceLoginInfo.getIp();//设备ip地址
            m_strLoginInfo.sDeviceAddress = new byte[HCNetSDK.NET_DVR_DEV_ADDRESS_MAX_LEN];
            System.arraycopy(m_sDeviceIP.getBytes(), 0, m_strLoginInfo.sDeviceAddress, 0, m_sDeviceIP.length());
            String m_sUsername = deviceLoginInfo.getUsername();//设备用户名
            m_strLoginInfo.sUserName = new byte[HCNetSDK.NET_DVR_LOGIN_USERNAME_MAX_LEN];
            System.arraycopy(m_sUsername.getBytes(), 0, m_strLoginInfo.sUserName, 0, m_sUsername.length());
            String m_sPassword = deviceLoginInfo.getPassword();//设备密码
            m_strLoginInfo.sPassword = new byte[HCNetSDK.NET_DVR_LOGIN_PASSWD_MAX_LEN];
            System.arraycopy(m_sPassword.getBytes(), 0, m_strLoginInfo.sPassword, 0, m_sPassword.length());
            m_strLoginInfo.wPort = Short.valueOf(deviceLoginInfo.getPort().toString());
            m_strLoginInfo.bUseAsynLogin = false; //是否异步登录：0- 否，1- 是
            m_strLoginInfo.write();
            lUserID = hcNetSDK.NET_DVR_Login_V40(m_strLoginInfo, m_strDeviceInfo);
            if (lUserID.intValue() == -1) {
                System.out.println("登录失败,NET_DVR_Login_V40 Failed,Error Code=" + hcNetSDK.NET_DVR_GetLastError());
                log.info("登录失败,NET_DVR_Login_V40 Failed,Error Code=" + hcNetSDK.NET_DVR_GetLastError());
            } else {
                System.out.println("登录成功,NET_DVR_Login_V40 Succeed！");
                log.info("登录成功,NET_DVR_Login_V40 Succeed!");
            }
        }
        return lUserID;
    }

    /**
     *
     * @description: 获取通道参数
     * @param
     * @return:
     * @author: hegonglei
     * @date: 2021-7-1 20:12
     */
    public static HCNetSDK.NET_DVR_IPPARACFG_V40 getIPPARACFG(NativeLong lUserID) {
        IntByReference ibrBytesReturned = new IntByReference(0);//获取IP接入配置参数
        HCNetSDK.NET_DVR_IPPARACFG_V40 m_strIpparaCfg = new HCNetSDK.NET_DVR_IPPARACFG_V40();
        m_strIpparaCfg.write();
        //lpIpParaConfig 接收数据的缓冲指针
        Pointer lpIpParaConfig = m_strIpparaCfg.getPointer();
        boolean bRet = hcNetSDK.NET_DVR_GetDVRConfig(lUserID, HCNetSDK.NET_DVR_GET_IPPARACFG_V40, new NativeLong(0), lpIpParaConfig, m_strIpparaCfg.size(), ibrBytesReturned);
        if (!bRet) {
            log.info("获取IP通道失败,NET_DVR_GetDVRConfig Failed,Error Code=" + hcNetSDK.NET_DVR_GetLastError());
        }
        return m_strIpparaCfg;
    }

}
