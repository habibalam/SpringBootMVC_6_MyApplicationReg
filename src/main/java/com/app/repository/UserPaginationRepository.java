package com.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.app.model.User;

public interface UserPaginationRepository extends PagingAndSortingRepository<User, Integer> {

}
