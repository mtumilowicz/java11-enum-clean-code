package job;

import org.junit.Test;

import static job.JobTitle.*;

/**
 * Created by mtumilowicz on 2018-11-07.
 */
public class JobTitleTest {
    
    @Test
    public void isBusinessShowcase() {
        JobTitle.stream()
                .filter(administration().or(operational()))
                .forEach(title -> System.out.println("Performed action for: " + title));
    }
}
