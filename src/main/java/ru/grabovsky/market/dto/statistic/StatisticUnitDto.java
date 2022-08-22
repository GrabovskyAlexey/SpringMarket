package ru.grabovsky.market.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticUnitDto {
    private String unitName;
    private int executionCount;
    private int averageExecutionTime;
    private long allExecutionTime;
}
