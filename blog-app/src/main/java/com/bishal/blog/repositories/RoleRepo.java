package com.bishal.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bishal.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role,Integer>{

}
