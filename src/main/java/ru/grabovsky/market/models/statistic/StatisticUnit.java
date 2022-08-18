package ru.grabovsky.market.models.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticUnit {
    private String unitName;
    private int executionCount;
    private int averageExecutionTime;
    private long allExecutionTime;

    public StatisticUnit(String unitName) {
        this.unitName = unitName;
    }

    public void addDuration(long time){
        this.allExecutionTime += time;
        this.executionCount++;
        averageExecutionTime = (int) (allExecutionTime / executionCount);
    }
}
