package com.gustavo.orderapi.config;

import com.gustavo.orderapi.entities.Order;
import com.gustavo.orderapi.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
@AllArgsConstructor
public class DatabaseSeedConfig {

    private final OrderRepository orderRepository;

    private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, " +
            "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut " +
            "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud " +
            "exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. " +
            "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore " +
            "eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in" +
            " culpa qui officia deserunt mollit anim id est laborum.";

    @Bean
    public void databaseSeed() {
        Order orderForAlex = new Order("4d234bb8-48dc-42f5-8a10-301dec138f41", "iPhone 13", LOREM_IPSUM, "e9ef7e9c-02fc-4ade-a247-147abd44dba6", LocalDateTime.now(), null, null);
        Order secondOrderForAlex = new Order("fc8234e9-3449-4254-b3cc-94ded06d705f", "Notebook Dell", LOREM_IPSUM, "e9ef7e9c-02fc-4ade-a247-147abd44dba6", LocalDateTime.now(), null, null);
        Order thirdOrderForAlex = new Order("ff59a81c-9ce1-4bcc-944e-bb30f2918704", "Headset Astro A40", LOREM_IPSUM, "e9ef7e9c-02fc-4ade-a247-147abd44dba6", LocalDateTime.now(), null, null);
        Order fourthOrderForAlex = new Order("0ad5c5d8-a366-415c-a553-53809bb75a20", "Monitor Samsung", LOREM_IPSUM, "e9ef7e9c-02fc-4ade-a247-147abd44dba6", LocalDateTime.now(), null, null);
        Order orderForBob = new Order("6d77a938-a60a-46d4-bf0f-9ef2cc01fd0c", "Air Fryer", LOREM_IPSUM, "5ef75b89-c390-4541-a694-e4a73f3c1f16", LocalDateTime.now(), null, null);
        Order secondOrderForBob = new Order("470207ec-c030-4ed0-b9b8-0b305ab02271", "Televisão 42''", LOREM_IPSUM, "5ef75b89-c390-4541-a694-e4a73f3c1f16", LocalDateTime.now(), null, null);
        Order thirdOrderForBob = new Order("3348c272-9f24-4153-bc5f-eb2054b2347b", "PS5", LOREM_IPSUM, "5ef75b89-c390-4541-a694-e4a73f3c1f16", LocalDateTime.now(), null, null);
        Order orderForMaria = new Order("0cb8fb67-67d4-4454-98d4-e4fd72021f2a", "Kit Maquiagem", LOREM_IPSUM, "df23740c-4e8b-41c1-a06a-8321764dbe06", LocalDateTime.now(), null, null);
        Order secondOrderForMaria = new Order("f4d8cb55-7313-43e7-bc9d-1c8e7aa2aefc", "Perfume 212", LOREM_IPSUM, "df23740c-4e8b-41c1-a06a-8321764dbe06", LocalDateTime.now(), null, null);
        Order thirdOrderForMaria = new Order("be718502-2db5-4dbb-87cc-ebe24d97c441", "PS5", LOREM_IPSUM, "df23740c-4e8b-41c1-a06a-8321764dbe06", LocalDateTime.now(), null, null);
        Order fourthOrderForMaria = new Order("e2b9b516-0fb7-40dd-8de4-13f9d11c3211", "iPhone 12", LOREM_IPSUM, "df23740c-4e8b-41c1-a06a-8321764dbe06", LocalDateTime.now(), null, null);

        List<Order> orders = Arrays.asList(orderForAlex,
                        secondOrderForAlex,
                        thirdOrderForAlex,
                        fourthOrderForAlex,
                        orderForBob,
                        secondOrderForBob,
                        thirdOrderForBob,
                        orderForMaria,
                        secondOrderForMaria,
                        thirdOrderForMaria,
                        fourthOrderForMaria);

        orderRepository.saveAll(orders);
    }

}
