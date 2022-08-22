package ru.grabovsky.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grabovsky.market.services.ReportService;

import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping("/web-socket")
public class ReportController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ReportService reportService;

    @MessageMapping("/report")
    @SendTo("/topic/report")
    public String getReport() {
        reportService.createReport();
        return "report.xlsx";
    }
}
