package com.subaiqiao.oshiapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import oshi.SystemInfo;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import java.util.List;

public class OSHITest {

    // 获取进程信息
    @SneakyThrows
    @Test
    void test01() {
        // 创建si对象
        SystemInfo si = new SystemInfo();
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
