package com.fis.perfcoe;
import org.springframework.data.repository.CrudRepository;

import com.fis.perfcoe.models.UserDTO;
public interface UserRepository extends CrudRepository<UserDTO, String> {

}
