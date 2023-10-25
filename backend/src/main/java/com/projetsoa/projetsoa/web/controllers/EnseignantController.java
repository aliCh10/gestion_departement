package com.projetsoa.projetsoa.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projetsoa.projetsoa.web.modules.Enseignant;
import com.projetsoa.projetsoa.web.repository.EnseingnatRepository;
import com.projetsoa.projetsoa.web.service.EnseignanExeption;
import com.projetsoa.projetsoa.web.service.EnseignantService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Enseignant")
public class EnseignantController {

   @Autowired
    private final EnseignantService enseignantService;
    
    


 
     public EnseignantController(EnseignantService enseignantService) {
        this.enseignantService = enseignantService;
    }




    @GetMapping("/AllEnseignant")
    public ResponseEntity<List<Enseignant>>getAllEnseignant(){
        try {
            return enseignantService.getallEnseignant();
        } catch (Exception e) {
            e.printStackTrace();
        }
       return  new ResponseEntity<List<Enseignant>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);

    }




    @PostMapping("/addEnseignant")
    public ResponseEntity<?> addenseignant(@Valid @RequestBody Enseignant enseignant, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<ErrorResponse> errors = new ArrayList<>();

            for (FieldError fieldError : fieldErrors) {
                errors.add(new ErrorResponse("Validation Error",
                        fieldError.getField() + " " + fieldError.getDefaultMessage()));
            }

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errors);
        }

        try {
            Enseignant enseignantregister = enseignantService.addEnseignant(enseignant);
            return ResponseEntity.ok(enseignantregister);
        } catch (EnseignanExeption e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error", e.getMessage()));
        }

    }


    @PatchMapping("/updateEnseignant/{id}")
    public ResponseEntity<String> updateEnseignant(@PathVariable long id ,@RequestBody Enseignant enseignant)
    {

        try {
            
            return enseignantService.updateEnseignant(id,enseignant);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

    }



    @GetMapping("/{id}")
    public ResponseEntity<Enseignant>getEnseignantbyid(@PathVariable long id ){
        try {
            return enseignantService.getbyidallEnseignant(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return  new ResponseEntity<>(new Enseignant(),HttpStatus.INTERNAL_SERVER_ERROR);

    } 



    @DeleteMapping("/deleteEnseignant/{id}")
    public ResponseEntity<String> deleteEnseignant(@PathVariable long id)
    {
       
     return enseignantService.deleteEnseignant(id);
            
    }















    private static class ErrorResponse {
        private final String error;
        private final String message;

        public ErrorResponse(String error, String message) {
            this.error = error;
            this.message = message;
        }

        public String getError() {
            return error;
        }

        public String getMessage() {
            return message;
        }
    }
    
}
