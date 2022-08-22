package ru.grabovsky.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.grabovsky.market.models.statistic.Statistic;
import ru.grabovsky.market.models.statistic.StatisticUnit;
import ru.grabovsky.market.repositories.StatisticRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final StatisticRepository statisticRepository;

    public Statistic getStatistic(String serviceName){
        Optional<Statistic> serviceStatistic = statisticRepository.findById(serviceName);
        return serviceStatistic.orElse(new Statistic(serviceName));
    }

    public List<Statistic> getAllStatistic(){
        return StreamSupport.stream(statisticRepository
                        .findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
    }

    public void updateStatistic(String serviceName, String methodName, long duration){
        Statistic statistic = getStatistic(serviceName);
        StatisticUnit summary = statistic.getSummary();
        summary.addDuration(duration);
        Optional<StatisticUnit> method = statistic.getMethods()
                .stream()
                .filter(m -> m.getUnitName().equals(methodName))
                .findFirst();
        if(method.isPresent()){
            StatisticUnit methodUnit = method.get();
            methodUnit.addDuration(duration);
        } else {
            StatisticUnit methodUnit = method.orElse(new StatisticUnit(methodName));
            methodUnit.addDuration(duration);
            statistic.getMethods().add(methodUnit);
        }
        statisticRepository.save(statistic);
    }
}
