
package com.healthhub.healthhub;

import com.healthhub.healthhub.dtos.MedicationDto;
import com.healthhub.healthhub.dtos.ProviderDto;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DtoValidationTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    static void tearDown() {
        factory.close();
    }

    @Test
    void medicationWithValidFieldsShouldPass() {
        MedicationDto medication = MedicationDto.builder()
                .name("Aspirin")
                .dosage("100 mg")
                .userId(UUID.randomUUID())
                .build();

        assertTrue(validator.validate(medication).isEmpty());
    }

    @Test
    void medicationWithBlankNameShouldFail() {
        MedicationDto medication = MedicationDto.builder()
                .name("")
                .dosage("100 mg")
                .userId(UUID.randomUUID())
                .build();

        assertFalse(validator.validate(medication).isEmpty());
    }

    @Test
    void medicationWithMissingUserIdShouldFail() {
        MedicationDto medication = MedicationDto.builder()
                .name("Aspirin")
                .dosage("100 mg")
                .build();

        assertFalse(validator.validate(medication).isEmpty());
    }

    @Test
    void medicationWithBlankDosageShouldFail() {
        MedicationDto medication = MedicationDto.builder()
                .name("Aspirin")
                .dosage("")
                .userId(UUID.randomUUID())
                .build();

        assertFalse(validator.validate(medication).isEmpty());
    }

    @Test
    void providerWithValidFieldsShouldPass() {
        ProviderDto provider = ProviderDto.builder()
                .name("Dr. Smith")
                .userId(UUID.randomUUID())
                .build();

        assertTrue(validator.validate(provider).isEmpty());
    }

    @Test
    void providerWithBlankNameShouldFail() {
        ProviderDto provider = ProviderDto.builder()
                .name("")
                .userId(UUID.randomUUID())
                .build();

        assertFalse(validator.validate(provider).isEmpty());
    }

    @Test
    void providerWithMissingUserIdShouldFail() {
        ProviderDto provider = ProviderDto.builder()
                .name("Dr. Smith")
                .build();

        assertFalse(validator.validate(provider).isEmpty());
    }
}
