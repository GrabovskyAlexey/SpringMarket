package ru.grabovsky.market.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.grabovsky.market.dto.statistic.StatisticDto;
import ru.grabovsky.market.models.statistic.Statistic;

@Mapper(injectionStrategy = InjectionStrategy.FIELD)
public interface StatisticMapper {
    @Mappings(value = {
            @Mapping(target = "serviceName", source = "id")
    })
    StatisticDto map(Statistic statistic);
}
