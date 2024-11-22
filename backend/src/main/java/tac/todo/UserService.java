package tac.todo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(String id) {
        return userRepository.findByUsername(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Adding a task to a user's task list
    public void addTask(String username, String task) {
        // Grabs the appropriate document from the database
        Query query = new Query(Criteria.where("username").is(username));
        // Adding the task to the proper document
        Update update = new Update().push("tasks", task);
        // Saving our work done
        mongoTemplate.updateFirst(query, update, User.class);
    }

    // Remove a task from a user's task list
    public void removeTask(String username, String task) {
        // Grabs the appropriate document from the database
        Query query = new Query(Criteria.where("username").is(username));
        // Removing the task to the proper document
        Update update = new Update().pull("tasks", task);
        // Saving our work done
        mongoTemplate.updateFirst(query, update, User.class);
    }
}
