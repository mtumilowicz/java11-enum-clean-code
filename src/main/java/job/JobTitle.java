package job;

import com.google.common.collect.ImmutableSet;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by mtumilowicz on 2018-11-07.
 */
public enum JobTitle {
    PRESIDENT, VICE_PRESIDENT, OFFICER, MANAGER, DEVELOPER,
    CONSULTANT, IMPLEMENTATION_OFFICER, INTERN;
    
    private static ImmutableSet<JobTitle> ADMINISTRATION =
            ImmutableSet.of(PRESIDENT, VICE_PRESIDENT);
    
    private static ImmutableSet<JobTitle> OPERATIONAL =
            ImmutableSet.of(MANAGER, OFFICER);
    
    public boolean isAdministration() {
        return ADMINISTRATION.contains(this);
    }

    public boolean isOperational() {
        return OPERATIONAL.contains(this);
    }
    
    public static Predicate<JobTitle> administration() {
        return JobTitle::isAdministration;
    }

    public static Predicate<JobTitle> operational() {
        return JobTitle::isOperational;
    }
    
    public static Stream<JobTitle> stream() {
        return Arrays.stream(values());
    }
}
