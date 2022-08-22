package ru.grabovsky.market.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.grabovsky.market.models.statistic.StatisticUnit;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticDto {
    private String serviceName;
    private List<StatisticUnitDto> methods;
    private StatisticUnitDto summary;
}
