package br.com.recatt.job;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import br.com.recatt.service.ClassroomFaceRecognitionService;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;

@Slf4j
@Component
public class ClassroomImageCaptureJob {

    @Autowired
    private ClassroomFaceRecognitionService classroomService;

    @Scheduled(cron = "${jobs.scholarAttendance.interval}")
    @SchedulerLock(name = "scholarAttendanceManagement")
    public void execute() {

        final StopWatch sw = new StopWatch();
        sw.start();

        log.info("Iniciando rotina de controle de frequência. Timestamp: {}", OffsetDateTime.now());

        classroomService.get();

        sw.stop();

        log.info("Finalizando rotina de controle de frequência e atribuindo presenças. Tempo de execução: {} ms",
            sw.getTotalTimeMillis());
    }
}
