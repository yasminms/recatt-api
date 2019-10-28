package br.com.recatt.controller.user;

import br.com.recatt.domain.UserDTO;
import br.com.recatt.domain.UserRegisterRequest;
import br.com.recatt.service.SaveFaceImageService;
import br.com.recatt.service.SaveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

import static br.com.recatt.domain.Role.STUDENT;
import static br.com.recatt.domain.Role.TEACHER;

@RestController
@RequestMapping("/users")
public class UserController implements UserContract {

    @Autowired
    private SaveUserService saveUserService;

    @Autowired
    private SaveFaceImageService faceImageService;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO saveStudent(@Valid @RequestBody final UserRegisterRequest request) {
        return saveUserService.save(request, STUDENT);
    }

    @Override
    @PostMapping("/teacher")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO saveTeacher(@Valid @RequestBody final UserRegisterRequest request) {
        return saveUserService.save(request, TEACHER);
    }

    @Override
    @PostMapping("/attachment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveFaceImages(@RequestParam("email") final String email, @RequestParam("faceImages") final List<MultipartFile> faceImages) {
        faceImageService.save(email, faceImages);
    }

}
