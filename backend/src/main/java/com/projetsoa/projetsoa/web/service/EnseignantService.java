package com.projetsoa.projetsoa.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.projetsoa.projetsoa.web.modules.Enseignant;
import com.projetsoa.projetsoa.web.repository.EnseingnatRepository;

@Service
public class EnseignantService implements Interfaceenseignant {

    private final EnseingnatRepository enseingnatRepository;

    public EnseignantService(EnseingnatRepository enseingnatRepository) {
        this.enseingnatRepository = enseingnatRepository;
    }

    @Override
    public ResponseEntity<List<Enseignant>> getallEnseignant() {
        try {
            List<Enseignant> enseignants = enseingnatRepository.findAll().stream().toList();
            return ResponseEntity.ok(enseignants);
        } catch (Exception E) {
            E.printStackTrace();
        }
        return new ResponseEntity<List<Enseignant>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public Enseignant addEnseignant(Enseignant enseignant) {

        return enseingnatRepository.save(enseignant);
    }

    @Override
    public ResponseEntity<Enseignant> getbyidallEnseignant(long id) {

        try {
            Enseignant enseignants = enseingnatRepository.findById(id).orElse(null);

            if (enseignants == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(enseignants, HttpStatus.OK);

        } catch (Exception E) {
            E.printStackTrace();
        }
        return new ResponseEntity<>(new Enseignant(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> updateEnseignant(long id, Enseignant enseignant) {
        try {
            Optional<Enseignant> EOptional = enseingnatRepository.findById(id);
            
            if (EOptional.isPresent()) {
                Enseignant enseignant2 = EOptional.get();
                enseignant2.setNom(enseignant.getNom());
                enseignant2.setPrenom(enseignant.getPrenom());
                enseignant2.setEmail(enseignant.getEmail());
                enseignant2.setCin(enseignant.getCin());
                enseignant2.setPhoneNumber(enseignant.getPhoneNumber());
                enseignant2.setAdresse(enseignant.getAdresse());
                enseignant2.setMatière(enseignant.getMatière());
                enseingnatRepository.save(enseignant2);
                return new ResponseEntity<String>("Update successfuly ",HttpStatus.ACCEPTED);

            }
                            

            return new ResponseEntity<String>(" Enseignant not found with id : "+id,HttpStatus.NOT_FOUND);

        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

    }



    @Override
    public ResponseEntity<String> deleteEnseignant( long id) {

        try{
            Optional<Enseignant> EOptional=enseingnatRepository.findById(id);
            if(EOptional.isPresent()){
                enseingnatRepository.deleteById(id);
                return new ResponseEntity<>("Delete Successfuly",HttpStatus.ACCEPTED);
                }
                else{
                    return new ResponseEntity<>("Enseignant Not Found With Id: "+id ,HttpStatus.NOT_FOUND);}
                    }catch (Exception e){
                        e.printStackTrace();
                        }return new ResponseEntity<>("Something Wrong",HttpStatus.INTERNAL_SERVER_ERROR);
        
       
       

    }

}
