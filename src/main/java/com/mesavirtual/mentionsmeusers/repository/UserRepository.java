package com.mesavirtual.mentionsmeusers.repository;

import com.mesavirtual.mentionsmeusers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
