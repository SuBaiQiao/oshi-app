package com.subaiqiao.oshiapp.controller;

import com.subaiqiao.oshiapp.common.R;
import com.subaiqiao.oshiapp.service.CpuLoadMetricsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/metrics")
public class CpuLoadRestController {

    @Resource
    private CpuLoadMetricsService cpuLoadMetricsService;

    @GetMapping("/cpu")
    public R getCpuLoad() {
        double[] cpuLoad = cpuLoadMetricsService.getCpuLoad();
        return R.ok(cpuLoad);
    }
}
