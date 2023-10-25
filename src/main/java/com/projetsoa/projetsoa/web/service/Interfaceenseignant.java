package com.projetsoa.projetsoa.web.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.projetsoa.projetsoa.web.modules.Enseignant;

public interface Interfaceenseignant {

    public ResponseEntity<List<Enseignant>> getallEnseignant();

    public ResponseEntity<Enseignant> getbyidallEnseignant(long id);

    public Enseignant addEnseignant(Enseignant enseignant);

    public ResponseEntity<String> updateEnseignant(long id , Enseignant enseignant);

    public ResponseEntity<String> deleteEnseignant(long id);


}
