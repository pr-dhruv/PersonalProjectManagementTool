package com.maso.app.ppmtool.controller;

import com.maso.app.ppmtool.model.Project;
import com.maso.app.ppmtool.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /**
     * For creating the project POST method is going to be used becuase here we are sending the data into the server
     *
     * ResponseEntity allows us to provide more control on the JSON object.
     * ResponseEntity is Generic type. Here we are using the Project type of ResponseEntity
     *
     * Here ResponseEntity is used because project is dealing with ReactJS so we want to control their response statuses and also JSON Object that we are going to pass to the client.
     *
     * Input parameter is Project JSON object which is going to be mapped with Project Model and stored into the database
     *
     * If the object is not valid then server is going to fire the exceptions which are very hard to understand.
     * We need to validate the RequestBody first to for validating the obect the @Valid annotation is used. It gives
     * the better response if the RequestBody is not valid.
     *
     * If we are not using @Valid annotation then Error 500(Internal Server error) is going to be appeared which is not understandable
     * after using @Valid it gives Error 400(Bad Request) with details
     *
     * BindingResult is an interface which invokes the validation on the object.
     * It analyzes the object and determine the object have the error or not.
     *
     * FieldError
     *
     */
    @PostMapping()
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
        if(result.hasErrors())
            return new ResponseEntity<List<FieldError>>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
        /**
         * Returning New ResponseEntity of type Project with and we have HttpResponse status that we want to setup.
         */
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project1,HttpStatus.CREATED);
    }
}