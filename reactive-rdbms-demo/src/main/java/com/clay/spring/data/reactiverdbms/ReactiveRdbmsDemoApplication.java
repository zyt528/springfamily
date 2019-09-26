package com.clay.spring.data.reactiverdbms;

import com.clay.spring.data.reactiverdbms.converter.MoneyReadConverter;
import com.clay.spring.data.reactiverdbms.converter.MoneyWriteConverter;
import com.clay.spring.data.reactiverdbms.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.core.DatabaseClient;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * @author yuntzhao
 */
@SpringBootApplication
@Slf4j
public class ReactiveRdbmsDemoApplication implements ApplicationRunner {

    @Autowired
    private DatabaseClient databaseClient;

    @Bean
    public R2dbcCustomConversions r2dbcCustomConversions() {
        return new R2dbcCustomConversions(Arrays.asList(new MoneyReadConverter(), new MoneyWriteConverter()));
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactiveRdbmsDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        databaseClient.execute("select * from t_coffee")
                .as(Coffee.class)
                .fetch()
                .first()
                .doFinally( s -> countDownLatch.countDown())
                .subscribe(c -> log.info("Fetch execute() {}", c));

        databaseClient.select()
                .from("t_coffee")
                .orderBy(Sort.by(Sort.Direction.DESC, "id"))
                .page(PageRequest.of(0, 3))
                .as(Coffee.class)
                .fetch()
                .all()
                .doFinally(s -> countDownLatch.countDown())
                .subscribe(c -> log.info("Fetch select() {}", c));

        log.info("After Starting...");
        countDownLatch.await();
    }
}
