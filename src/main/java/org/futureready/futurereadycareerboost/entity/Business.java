import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.data.annotation.Id;

import java.util.List;
@Slf4j
@Entity
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String email;
    private String password;
    private String industry;

//    @OneToMany(mappedBy = "business")
//    private List<BatchProperties.Job> jobs;
//
//    @OneToMany(mappedBy = "business")
//    private List<JobApplication> applicationsSentToStudents;
}
