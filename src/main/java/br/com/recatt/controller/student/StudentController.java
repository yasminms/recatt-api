package br.com.recatt.controller.student;

import br.com.recatt.domain.UserDTO;
import br.com.recatt.domain.UserRegisterRequest;
import br.com.recatt.service.SaveFaceImageService;
import br.com.recatt.service.SaveStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController implements StudentContract {

    @Autowired
    private SaveStudentService saveStudentService;

    @Autowired
    private SaveFaceImageService faceImageService;

    @Override
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO save(@Valid @RequestBody final UserRegisterRequest request) {
        return saveStudentService.save(request);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    @PostMapping("/attachment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveFaceImages(@RequestParam("email") final String email, @RequestParam("faceImages") final List<MultipartFile> faceImages) {
        faceImageService.save(email, faceImages);
    }

}
