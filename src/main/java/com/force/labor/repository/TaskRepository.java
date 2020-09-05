package com.force.labor.repository;

import com.force.labor.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, BigInteger> {

    List<Task> findAllByIdIn(List<BigInteger> ids);



}
