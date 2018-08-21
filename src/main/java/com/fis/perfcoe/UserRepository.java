package com.fis.perfcoe.Repository;
import org.springframework.data.repository.CrudRepository;

import com.fis.perfcoe.models.UserDTO;
public interface UserRepository extends CrudRepository<User, Integer> {

}