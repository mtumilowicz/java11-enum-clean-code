import com.google.common.collect.ImmutableCollection;
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
    
    private static ImmutableCollection<JobTitle> TECHNICAL =
            ImmutableSet.of(DEVELOPER, IMPLEMENTATION_OFFICER);
    
    private static ImmutableCollection<JobTitle> PART_TIME =
            ImmutableSet.of(DEVELOPER, INTERN);

    private static ImmutableCollection<JobTitle> CLIENT_CONTACTS =
            ImmutableSet.of(CONSULTANT, IMPLEMENTATION_OFFICER);
    
    public static Predicate<JobTitle> isAdministrative() {
        return title -> ADMINISTRATION.contains(title);
    }

    public static Predicate<JobTitle> isOperational() {
        return title -> OPERATIONAL.contains(title);
    }

    public static Predicate<JobTitle> isTechnical() {
        return title -> TECHNICAL.contains(title);
    }

    public static Predicate<JobTitle> isPartTime() {
        return title -> PART_TIME.contains(title);
    }

    public static Predicate<JobTitle> isClientContact() {
        return title -> CLIENT_CONTACTS.contains(title);
    }
    
    public static Stream<JobTitle> stream() {
        return Arrays.stream(values());
    }
}
