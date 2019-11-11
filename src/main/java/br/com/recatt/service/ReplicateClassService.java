package br.com.recatt.service;

import br.com.recatt.domain.ClassInfo;
import br.com.recatt.entity.Class;
import br.com.recatt.entity.Diary;
import br.com.recatt.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.recatt.utils.DateTimeUtils.stringToLocalDate;
import static br.com.recatt.utils.DateTimeUtils.stringToLocalTime;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

@Service
public class ReplicateClassService {

    @Value("${school.classes.start}")
    private String startClasses;

    @Value("${school.classes.end}")
    private String endClasses;

    @Value("${school.period.duration}")
    private Integer periodDuration;

    @Value("${school.break.start}")
    private String breakStart;

    @Value("${school.break.end}")
    private String breakEnd;

    public List<Class> replicate(final ClassInfo classInfo, final Diary diary, final String tolerance) {

        final List<LocalDateTime> classes = new ArrayList<>();

        final LocalDate startSchoolYear = stringToLocalDate(startClasses);
        final LocalDate endSchoolYear = stringToLocalDate(endClasses);

        LocalDateTime classDateTime = startSchoolYear.with(
                nextOrSame(classInfo.getDayOfWeek()))
                .atTime(stringToLocalTime(classInfo.getStartTime()));

        final LocalDateTime end = endSchoolYear.atStartOfDay();

        LocalDateTime tempDateTime = classDateTime;

        while (tempDateTime.plusWeeks(1).isBefore(end)) {

            tempDateTime = tempDateTime.plusWeeks(1);
            classes.add(tempDateTime);
        }

        return classes.stream()
                .map(startTime -> ClassMapper.apply(classInfo, diary, tolerance, startTime, periodDuration, breakStart, breakEnd))
                .collect(Collectors.toList());
    }

}
