package ru.grabovsky.market.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.grabovsky.market.models.statistic.Statistic;

@Repository
public interface StatisticRepository extends CrudRepository<Statistic, String> {
}
