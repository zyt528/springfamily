package com.clay.spring.data.mongo;

import com.clay.spring.data.mongo.converter.MoneyReadConverter;
import com.clay.spring.data.mongo.model.Coffee;
import com.clay.spring.data.mongo.repository.CoffeeRepository;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@Slf4j
@EnableMongoRepositories
public class MongoDemoApplication implements ApplicationRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Collections.singletonList(new MoneyReadConverter()));
    }

    public static void main(String[] args) {
        SpringApplication.run(MongoDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 40.0))
                .createTime(new Date())
                .updateTime(new Date())
                .build();

        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 40))
                .createTime(new Date())
                .updateTime(new Date())
                .build();

        coffeeRepository.insert(Arrays.asList(espresso, latte));
        coffeeRepository.findAll(Sort.by("name")).forEach(c -> log.info("Saved Coffee {}", c));

        Thread.sleep(1000);
        latte.setPrice(Money.of(CurrencyUnit.of("CNY"), 35.0));
        latte.setUpdateTime(new Date());
        coffeeRepository.save(latte);
        coffeeRepository.findByName("latte").forEach(c -> log.info("Coffee: {}", c));

        coffeeRepository.deleteAll();

//        List<Coffee> coffeeList = coffeeRepository.findByName("espresso");
//        log.info("CoffeeList: {}", coffeeList);

//        Coffee espresso = Coffee.builder()
//                .name("espresso")
//                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
//                .createTime(new Date())
//                .updateTime(new Date())
//                .build();
//        Coffee saved = mongoTemplate.save(espresso);
//        log.info("Coffee saved: {}", saved);
//
//        List<Coffee> coffeeList = mongoTemplate.find(Query.query(Criteria.where("name").is("espresso")), Coffee.class);
//        log.info("Find {} Coffee", coffeeList.size());
//        coffeeList.forEach(c -> log.info("Coffee {}", c));
//
//        Thread.sleep(1000);
//        UpdateResult result = mongoTemplate.updateFirst(Query.query(Criteria.where("name").is("espresso")),
//                new Update().set("price", Money.ofMajor(CurrencyUnit.of("CNY"), 30)).currentDate("updateTime"), Coffee.class);
//        log.info("Update Result: {}", result.getModifiedCount());
//        Coffee updateOne = mongoTemplate.findById(saved.getId(), Coffee.class);
//        log.info("Update Result: {}", updateOne);
//
//        DeleteResult deleteResult = mongoTemplate.remove(updateOne);
//        log.info("Delete Result: {}", deleteResult);
    }
}
