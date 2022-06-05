package com.powerorg.powerplant.util;

import com.powerorg.powerplant.model.Battery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static com.powerorg.powerplant.util.CustomValidator.validateCollection;

@ExtendWith(MockitoExtension.class)
public class CustomValidatorTest {

    @Test
    public void test_validate_collection_with_errors() {

        Battery battery = new Battery("test", "t12324", 1234);

        List<String> errorStrings = validateCollection(Collections.singletonList(battery));

        Assertions.assertFalse(errorStrings.isEmpty());

        Assertions.assertEquals("Entity 1 : numeric value out of bounds (<4 digits>.<0 digits> expected)", errorStrings.get(0));

    }


    @Test
    public void test_validate_collection_without_errors() {

        Battery battery = new Battery("test", "1232", 1234);

        List<String> errorStrings = validateCollection(Collections.singletonList(battery));

        Assertions.assertTrue(errorStrings.isEmpty());

    }


}
