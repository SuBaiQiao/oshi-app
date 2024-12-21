package com.subaiqiao.oshiapp.service;

import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;

@Service
public class CpuLoadMetricsService {

    // OSHI提供的获取所有数据的入口（线程安全）
    SystemInfo si = new SystemInfo();

    public double[] getCpuLoad() {
        HardwareAbstractionLayer hardware = si.getHardware();
        CentralProcessor processor = hardware.getProcessor();
        return processor.getProcessorCpuLoad(1000);
    }

}
