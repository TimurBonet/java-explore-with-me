package ru.practicum.EndpointHit.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.practicum.Constants;
import ru.practicum.EndpointHit.model.EndpointHit;
import ru.practicum.EndpointHit.repository.EndpointHitRepository;
import ru.practicum.ViewStats.model.ViewStats;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EndpointHitServiceImpl implements EndpointHitService {
    private final EndpointHitRepository endpointHitRepository;

    @Transactional
    @Override
    public void save(EndpointHit endpointHit) {
        log.info("Начало сохранения статистики");
        endpointHitRepository.save(endpointHit);
        log.info("Статистика сохранена");
    }

    @Transactional(readOnly = true)
    @Override
    public List<ViewStats> findByParams(String start, String end, List<String> uris, boolean unique) {
        log.info("Получение статистики просмотров");
        List<ViewStats> listViewStats;

        if (CollectionUtils.isEmpty(uris)) {
            uris = endpointHitRepository.findUniqueUri();
        }

        if (unique) {
            listViewStats = endpointHitRepository.findViewStatsByStartAndEndAndUriAndUniqueIp(decodeTime(start),
                    decodeTime(end),
                    uris);
        } else {
            listViewStats = endpointHitRepository.findViewStatsByStartAndEndAndUri(decodeTime(start),
                    decodeTime(end),
                    uris);
        }

        log.info("Статистика получена успешно");
        return listViewStats;
    }

    private LocalDateTime decodeTime(String time) {
        String decodeTime = URLDecoder.decode(time, StandardCharsets.UTF_8);
        return LocalDateTime.parse(decodeTime, Constants.FORMATTER);
    }
}
