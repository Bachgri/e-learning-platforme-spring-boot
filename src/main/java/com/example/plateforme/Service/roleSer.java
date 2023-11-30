package com.example.plateforme.Service;

import com.example.plateforme.Entities.Role;
import com.example.plateforme.Repository.RepoRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class roleSer {

    @Autowired
    RepoRole repoRole;

    public Role create(String name){
        Role role = new Role();
        role.setName(name);
        return repoRole.save(role);
    }

    public void updateRole(String name, Long id){
        Role r = repoRole.findById(id).orElseThrow(()-> new RuntimeException(String.format("Role %s not found",id)));
        r.setName(name);
        repoRole.save(r);
    }

    public void deleteRole(Long id){
        repoRole.deleteById(id);
    }

    public Role roleById(Long id){
        Role r = repoRole.findById(id).orElseThrow(()-> new RuntimeException(String.format("Role %s not found",id)));
        return r;
    }

    public Role roleByName(String name){
        Role r = repoRole.findByName(name).orElseThrow(()-> new RuntimeException(String.format("Role %s not found",name)));;
        return r;
    }



}