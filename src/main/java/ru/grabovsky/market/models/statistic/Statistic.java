package ru.grabovsky.market.models.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@RedisHash
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistic {
    @Id
    private String id;
    private List<StatisticUnit> methods;
    private StatisticUnit summary;

    public Statistic(String serviceName) {
        this.id = serviceName;
        this.methods = new ArrayList<>();
        this.summary = new StatisticUnit(serviceName);
    }
}
