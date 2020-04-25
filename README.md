[![Build Status](https://travis-ci.com/mtumilowicz/java11-enum-clean-code.svg?branch=master)](https://travis-ci.com/mtumilowicz/java11-enum-clean-code)

# java11-enum-clean-code
Overview of how to to construct modern enums using lambda.

# enum compliation
* https://stackoverflow.com/questions/32354107/how-are-enums-internally-represented-in-java
* only syntactic sugar

```
public enum Ordinals {
    FIRST("st"),
    SECOND("nd"),
    THIRD("rd");
    private String notation;
    private Ordinals(String notation) {
        this.notation = notation;
    }
    public String getNotation() {
        return notation;
    }
}
```

```
public final class Ordinals extends java.lang.Enum<Ordinals> {
  public static final Ordinals FIRST;

  public static final Ordinals SECOND;

  public static final Ordinals THIRD;

  private String notation;

  private static final Ordinals[] $VALUES;

  public static Ordinals[] values() {
      return $VALUES.clone();
  }

  public static Ordinals valueOf(String name) {
      return (Ordinals) Enum.valueOf(Ordinals.class, name);
  }

  private Ordinals(String name, int ordinal, String notation) {
      super(name, ordinal);
      this.notation = notation
  }

  static {
      FIRST = new Ordinals("FIRST", 0, "st");
      SECOND = new Ordinals("SECOND", 1, "nd");
      THIRD = new Ordinals("THIRD", 2, "rd");
      Ordinals[] $VALUES = new Ordinals[3];
      $VALUES[0] = FIRST;
      $VALUES[1] = SECOND;
      $VALUES[2] = THIRD;
      Ordinals.$VALUES = $VALUES;
  }
}
```

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
