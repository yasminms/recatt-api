package br.com.recatt.repository;

import br.com.recatt.entity.Diary;
import br.com.recatt.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {

    @Query("FROM Diary d " +
            "WHERE d.teacher = :teacher")
    List<Diary> findAll(@Param("teacher") Teacher teacher);
}
