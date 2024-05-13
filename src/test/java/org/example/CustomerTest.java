package org.example;

import org.example.core.Customer;
import org.example.core.StatusTypes;
import org.example.exeprions.FieldMustBeNotEmpty;
import org.example.exeprions.NumberIsMinus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    @Test
    void if_username_equal_to_null_must_return_false() {
        Assertions.assertThrows(FieldMustBeNotEmpty.class,
                () -> new Customer(null,"0","0",0,1, StatusTypes.ACTIVE));
    }

    @Test
    void if_mobile_number_equal_to_null_must_return_false() {
        Assertions.assertThrows(FieldMustBeNotEmpty.class,
                () -> new Customer("eiad",null,"0",0,1,StatusTypes.ACTIVE));
    }

    @Test
    void if_emil_equal_to_null_must_return_false() {
        Assertions.assertThrows(FieldMustBeNotEmpty.class,
                () -> new Customer("eiad","0",null,0,1,StatusTypes.ACTIVE));
    }
    @Test
    void if_balance_equal_to_null_must_return_false() {
        Assertions.assertThrows(NumberIsMinus.class,
                () -> new Customer("eiad","0","0",-1,1,StatusTypes.ACTIVE));
    }

    @Test
    void if_account_number_equal_to_null_must_return_false() {
        Assertions.assertThrows(NumberIsMinus.class,
                () -> new Customer("eiad","0","0",0,-5,StatusTypes.ACTIVE));
    }
    @Test
    void if_status_equal_to_null_must_return_false() {
        Assertions.assertThrows(FieldMustBeNotEmpty.class,
                () -> new Customer("eiad","0","0",0,1,null));
    }
}
