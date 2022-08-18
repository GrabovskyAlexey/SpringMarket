package ru.grabovsky.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.grabovsky.market.dto.statistic.StatisticDto;
import ru.grabovsky.market.mappers.StatisticMapper;
import ru.grabovsky.market.services.StatisticService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/statistic")
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticMapper mapper;
    private final StatisticService service;

    @GetMapping
    public ResponseEntity<List<StatisticDto>> getStatistic() {
        List<StatisticDto> result = service.getAllStatistic()
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
        return new ResponseEntity<List<StatisticDto>>(result, HttpStatus.OK);
    }
}
