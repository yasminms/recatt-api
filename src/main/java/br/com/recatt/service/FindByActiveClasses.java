package br.com.recatt.service;

import br.com.recatt.entity.Class;
import br.com.recatt.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindByActiveClasses {

    @Autowired
    private ClassRepository classRepository;

    public List<Class> findAll(final LocalDateTime dateTime) {

        return classRepository.findByActiveClasses(dateTime)
                .stream().filter(this::isAfterTolerance)
                .collect(Collectors.toList());
    }

    private boolean isAfterTolerance(Class activeClass) {

        final LocalDateTime toleranceTime = activeClass.getStartTime().plusMinutes(activeClass.getTolerance());
        return LocalDateTime.now().isAfter(toleranceTime);
    }
}
