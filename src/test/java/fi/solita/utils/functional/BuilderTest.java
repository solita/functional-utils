package fi.solita.utils.functional;

import static fi.solita.utils.functional.Option.None;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fi.solita.utils.functional.Builder.IncompleteException;

public class BuilderTest {
    @Test
    public void noNeedToSetOptional() {
        Employee employee = Builder.of(Employee_.$Fields(), Employee_.$)
            .with(Employee_.name, "John")
            .with(Employee_.department, new Department("Sales"))
            .build();
        assertEquals("John", employee.name);
        assertEquals(None(), employee.salary);
        assertEquals("Sales", employee.department.name);
    }

    @Test(expected = IncompleteException.class)
    public void builderFailsWhenIncomplete() {
        Builder.of(Employee_.$Fields(), Employee_.$)
            .with(Employee_.name, "Jane")
            .build();
    }
}