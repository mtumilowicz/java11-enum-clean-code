[![Build Status](https://travis-ci.com/mtumilowicz/java11-enum-clean-code.svg?branch=master)](https://travis-ci.com/mtumilowicz/java11-enum-clean-code)

# java11-enum-clean-code
Overview of how to to construct modern enums using lambda.

# project description
We have enum `JobTitle` 

```
public enum JobTitle {
    PRESIDENT, VICE_PRESIDENT, OFFICER, MANAGER, DEVELOPER,
    CONSULTANT, IMPLEMENTATION_OFFICER, INTERN;
}
```

and groups:
* administration:
    ```
    private static ImmutableSet<JobTitle> ADMINISTRATION =
                ImmutableSet.of(PRESIDENT, VICE_PRESIDENT);
    ```
* operational:
    ```
    private static ImmutableSet<JobTitle> OPERATIONAL =
            ImmutableSet.of(MANAGER, OFFICER);    
    ```

* it is a good practice to provide static methods that return predicates:
    ```
    public static Predicate<JobTitle> administration() {
        return JobTitle::isAdministration;
    }
    
    public static Predicate<JobTitle> operational() {
        return JobTitle::isOperational;
    }
    ```
    where, obviously:
    ```
    public boolean isAdministration() {
        return ADMINISTRATION.contains(this);
    }
    
    public boolean isOperational() {
        return OPERATIONAL.contains(this);
    }
    ```
* and a `stream()` method:
    ```
    public static Stream<JobTitle> stream() {
            return Arrays.stream(values());
    }
    ```

because later in the code it's easy to write very expressive code
(like in `JobTitleTest`):
```
@Test
public void isBusinessShowcase() {
    JobTitle.stream()
            .filter(administration().or(operational()))
            .forEach(title -> System.out.println("Performed action for: " + title));
}
```
