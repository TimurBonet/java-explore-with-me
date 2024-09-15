package ru.practicum.EndpointHit.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import ru.practicum.EndpointHit.model.EndpointHit;
import ru.practicum.ViewStats.model.ViewStats;

import java.time.LocalDateTime;
import java.util.List;

public interface EndpointHitRepository extends JpaRepository<EndpointHit, Long> {

    @Query("select new ru.practicum.ViewStats.model.ViewStats(eh.app, eh.uri, count(distinct eh.ip)) " +
            "from EndpointHit as eh " +
            "where eh.timestamp > :start and eh.timestamp < :end and eh.uri in (:uri) " +
            "group by eh.app, eh.uri " +
            "order by count(distinct eh.ip) desc")
    List<ViewStats> findViewStatsByStartAndEndAndUriAndUniqueIp(@Validated @NotNull @Param("start") LocalDateTime start,
                                                                @Validated @NotNull @Param("end") LocalDateTime end,
                                                                @Param("uri") List<String> uri);

    @Query("select new ru.practicum.ViewStats.model.ViewStats(eh.app, eh.uri, count(eh.ip)) " +
            "from EndpointHit as eh " +
            "where eh.timestamp > :start and eh.timestamp < :end and eh.uri in (:uri) " +
            "group by eh.app, eh.uri " +
            "order by count(eh.ip) desc")
    List<ViewStats> findViewStatsByStartAndEndAndUri(@Validated @NotNull @Param("start") LocalDateTime start,
                                                     @Validated @NotNull @Param("end") LocalDateTime end,
                                                     @Param("uri") List<String> uri);

    @Query("select distinct eh.uri " +
            "from EndpointHit as eh ")
    List<String> findUniqueUri();
}
