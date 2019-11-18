package br.com.recatt.mapper;

import br.com.recatt.domain.DiaryDTO;
import br.com.recatt.entity.Diary;

import java.util.stream.Collectors;

public final class DiaryDTOMapper {

    public static DiaryDTO apply(final Diary diary) {

        return DiaryDTO.builder()
                .id(diary.getId())
                .classroom(diary.getClassroom().getName())
                .group(diary.getGroup().getName())
                .subject(diary.getSubject().getName())
                .year(diary.getYear())
                .teacher(UserDTOMapper.apply(diary.getTeacher()))
                .students(diary.getStudents().stream()
                        .map(UserDTOMapper::apply)
                        .collect(Collectors.toList()))
                .build();
    }
}
