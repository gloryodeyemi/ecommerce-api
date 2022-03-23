package com.example.ecommerce.services;
//
//import com.example.ecommerce.models.Role;
//import com.example.ecommerce.repositories.RoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataLoader implements ApplicationRunner {
//
//    private RoleRepository roleRepository;
//
//    @Autowired
//    public DataLoader(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }
//
//    public void run(ApplicationArguments args) {
//        roleRepository.save(new Role("ROLE_MERCHANT"));
//        roleRepository.save(new Role("ROLE_CUSTOMER"));
//    }
//}
