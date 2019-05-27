package com.it_academy.by.database;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import com.it_academy.by.database.entities.Passport;
import com.it_academy.by.database.entities.Role;
import com.it_academy.by.database.entities.User;
import com.it_academy.by.database.entities.UserStatus;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.util.Objects.isNull;

public final class EntityGeneratorUtils {

    private static Faker faker = new Faker();
    private static Random random = new Random();


    private EntityGeneratorUtils() {
    }

    public static User createUser() {
        return User.builder()
                .address(createAdress())
                .birthDate(createZoneDateTime().toLocalDate())
                .name(faker.name().name())
                .login(faker.internet().slug())
                .password(faker.internet().password())
                .role(createRole())
                .status(createStatus())
                .passport(createPassport())
                .build();
    }

    private static Passport createPassport() {
        List<String> strings = Arrays.asList("MP", "PP");
        return Passport.builder()
                .number(faker.number().digit())
                .series(strings.get(random.nextInt(strings.size() - 1)))
                .build();
    }

    private static UserStatus createStatus() {
        return UserStatus.values()[random.nextInt(UserStatus.values().length - 1)];
    }

    private static Role createRole() {
        return Role.values()[random.nextInt(Role.values().length - 1)];
    }

    public static com.it_academy.by.database.entities.Address createAdress() {
        Address address = faker.address();
        return com.it_academy.by.database.entities.Address.builder()
                .city(address.city())
                .street(address.streetAddress())
                .build();
    }

    public static ZonedDateTime createZoneDateTime() {
        String format = String.format("%04d-%02d-%02dT%02d:%02d:%02d+03:00", 2019 - random.nextInt(50) + 10,
                random.nextInt(11) + 1,
                random.nextInt(27) + 1,
                random.nextInt(24),
                random.nextInt(59),
                random.nextInt(59));

        return ZonedDateTime.parse(format, DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(createUser());
        }
    }

    public static String createUri() {
        return createUri(10);
    }

    public static String createUri(Integer length) {
        if (isNull(length)) {
            length = 10;
        }
        StringBuilder uri = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (random.nextBoolean())
                uri.append((char) (random.nextInt(25) + 65));
            else uri.append((char) (random.nextInt(25) + 97));
        }
        return uri.toString();
    }
}
