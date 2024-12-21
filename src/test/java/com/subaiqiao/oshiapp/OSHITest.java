package com.subaiqiao.oshiapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import java.util.Arrays;
import java.util.List;

public class OSHITest {

    // 创建si对象
    SystemInfo si = new SystemInfo();

    // 获取CPU信息
    @Test
    void testCPU() {
        // 获取硬件信息
        HardwareAbstractionLayer hardware = si.getHardware();
        // 拿到CPU
        CentralProcessor processor = hardware.getProcessor();
        // 获取CPU最大频率
        long maxFreq = processor.getMaxFreq();
        System.out.println("最大频率：" + maxFreq);
        // 获取CPU当前频率；返回所有逻辑CPU当前频率
        long[] currentFreq = processor.getCurrentFreq();
        System.out.println("当前频率：" + Arrays.toString(currentFreq));

        // 当前频率/最大频率 = 当前CPU占用率

        // 获取CPU1s内的使用清空
        double[] processorCpuLoad = processor.getProcessorCpuLoad(1000);
        System.out.println("获取CPU1s内的使用清空：" + Arrays.toString(processorCpuLoad));
    }

    // 获取内存信息
    @Test
    void testMemory() throws JsonProcessingException, InterruptedException {
        // 获取硬件信息
        HardwareAbstractionLayer hardware = si.getHardware();
        // 获取内存信息
        while (true) {
            Thread.sleep(1000);
            GlobalMemory memory = hardware.getMemory();
//            ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(memory);
//        System.out.println(json);
            // 打印可用内存
            System.out.println("可用内存：" + memory.getAvailable());
        }
    }


    // 获取进程信息
    @Test
    void test01() throws JsonProcessingException {
        /// 获取操作系统对象
        OperatingSystem os = si.getOperatingSystem();

        ObjectMapper mapper = new ObjectMapper();

        System.out.println("进程总数：" + os.getProcessCount());
        List<OSProcess> processes = os.getProcesses();
        for (OSProcess process : processes) {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(process);
            System.out.println(json);
        }
    }
}
