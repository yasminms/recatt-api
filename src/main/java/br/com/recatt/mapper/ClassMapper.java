package br.com.recatt.mapper;

import br.com.recatt.domain.ClassInfo;
import br.com.recatt.entity.Class;
import br.com.recatt.entity.Diary;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static br.com.recatt.utils.DateTimeUtils.getMinutesDuration;
import static br.com.recatt.utils.DateTimeUtils.isBetween;
import static br.com.recatt.utils.DateTimeUtils.stringToLocalTime;
import static java.time.temporal.ChronoUnit.MINUTES;

public final class ClassMapper {

    public static Class apply(final ClassInfo classInfo, final Diary diary, final String tolerance,
                              final LocalDateTime startTime, final Integer periodDuration, final String breakStart,
                              final String breakEnd) {

        final Class classEntity = new Class();

        classEntity.setActive(true);
        classEntity.setDiary(diary);
        classEntity.setTolerance(getMinutesDuration(stringToLocalTime(tolerance)));
        classEntity.setPeriod(classInfo.getPeriod());
        classEntity.setStartTime(startTime);
        classEntity.setEndTime(getClassEnd(startTime, classInfo.getPeriod(), periodDuration, breakStart, breakEnd));

        return classEntity;
    }

    private static LocalDateTime getClassEnd(final LocalDateTime startTime, final Integer period, final Integer periodDuration,
                                             final String breakStartString, final String breakEndString) {

        final LocalDateTime classEnd = startTime.plusMinutes(period * periodDuration);
        final LocalTime breakStart = stringToLocalTime(breakStartString);
        final LocalTime breakEnd = stringToLocalTime(breakEndString);

        if (isBetween(breakEnd, startTime.toLocalTime(), classEnd.toLocalTime())) {

            return classEnd.plusMinutes(MINUTES.between(breakStart, breakEnd));
        }

        return classEnd;
    }

}
