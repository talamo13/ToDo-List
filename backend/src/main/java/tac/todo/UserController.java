package tac.todo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Get all users in the database
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
       return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
    }
    // Get a specific user via their username
    @GetMapping("/{name}")
    public ResponseEntity<Optional<User>> getSingleUser(@PathVariable String name){
        return new ResponseEntity<Optional<User>>(userService.getUser(name), HttpStatus.OK);
    }
    // Add a new user to the database
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.OK);
    }
    // Adding a task to a user's task list
    @PutMapping("add/{username}")
    public ResponseEntity<String> addTask(@RequestBody String task, @PathVariable String username ){
        userService.addTask(username, task);
        return ResponseEntity.ok("Task added successfully!");
    }
    // Removing a task from a user's task list
    @PutMapping("remove/{username}")
    public ResponseEntity<String> removeTask(@RequestBody String task, @PathVariable String username ){
        userService.removeTask(username, task);
        return ResponseEntity.ok("Task successfully removed!");
    }
}
