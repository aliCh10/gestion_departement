package com.projetsoa.projetsoa.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetsoa.projetsoa.web.modules.Etudiant;

public interface EtudiantRepository  extends JpaRepository<Etudiant,Long>{

    
}