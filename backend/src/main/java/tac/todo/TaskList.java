package tac.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "task-lists")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskList {
    @Id
    private ObjectId id;
    // This is what we will use to connect users to their respective task lists
    private ObjectId user;
    private List<String> tasks;
}
