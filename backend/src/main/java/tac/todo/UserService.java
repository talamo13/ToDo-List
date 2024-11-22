package tac.todo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(String id){
        return userRepository.findByUsername(id);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    // add task to a users list

    // remove a task from a users list
}
