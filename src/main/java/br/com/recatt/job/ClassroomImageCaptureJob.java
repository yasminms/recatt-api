package br.com.recatt.job;

import br.com.recatt.service.ClassroomFaceRecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClassroomImageCaptureJob {

    @Autowired
    private ClassroomFaceRecognitionService classroomService;

    //TODO: 0 */5 7-23 * 2-12 MON-FRI
    @Scheduled(cron = "0 */5 7-23 * 2-12 MON-FRI")
    public void execute() {

        classroomService.get();
    }
}
