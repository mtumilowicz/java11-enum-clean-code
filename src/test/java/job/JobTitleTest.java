package job;

import org.junit.Test;

import static job.JobTitle.isBusiness;

/**
 * Created by mtumilowicz on 2018-11-07.
 */
public class JobTitleTest {
    
    @Test
    public void isBusinessShowcase() {
        JobTitle.stream()
                .filter(isBusiness())
                .forEach(title -> System.out.println("Performed action for: " + title));
    }
}
