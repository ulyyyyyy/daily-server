package com.heihei.daily.domains.storage;

import com.heihei.daily.domains.models.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
