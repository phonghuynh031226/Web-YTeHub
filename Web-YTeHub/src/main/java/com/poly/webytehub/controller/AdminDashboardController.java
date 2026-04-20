package com.poly.webytehub.controller;

import com.poly.webytehub.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/dashboard")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AdminDashboardController {

    private final DashboardService dashboardService;

    public AdminDashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/stats")
    public Map<String, Object> stats() {
        return dashboardService.getStats();
    }

    @GetMapping("/latest-orders")
    public List<Map<String, Object>> latestOrders() {
        return dashboardService.getLatestOrders();
    }

    @GetMapping("/weekly-revenue")
    public List<Map<String, Object>> weeklyRevenue() {
        return dashboardService.getWeeklyRevenue();
    }
}
