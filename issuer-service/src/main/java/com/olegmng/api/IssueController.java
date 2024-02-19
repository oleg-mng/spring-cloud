package com.olegmng.api;

import com.github.javafaker.Faker;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("/api/issue")
public class IssueController {
    private final List<Issue> issuers;
    private final Faker faker;

    public IssueController(BookProvider bookProvider) {
        List<Issue> issues = new ArrayList<>();
        this.faker = new Faker();
        for (int i = 0; i < 15; i++) {
            Issue issue = new Issue();
            issue.setId(UUID.randomUUID());
            Date between = faker.date().between(startOfYear(), endOfYear());
            LocalDate localDate = between.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            issue.setIssuedAt(localDate);
            issue.setBookId(bookProvider.getRandomBookId());
            issue.setReaderId(UUID.randomUUID());

            issues.add(issue);
        }
        this.issuers = List.copyOf(issues);
    }
    public Date startOfYear(){
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, 2024);
        instance.set(Calendar.MONTH, Calendar.JANUARY);
        instance.set(Calendar.DAY_OF_MONTH, 1);
        return instance.getTime();
    }

    public Date endOfYear(){
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, 2024);
        instance.set(Calendar.MONTH, Calendar.DECEMBER);
        instance.set(Calendar.DAY_OF_MONTH, 31);
        return instance.getTime();
    }

    @GetMapping
    public List<Issue> getAll(){
        return issuers;
    }

    @GetMapping("/random")
    public Issue getRandom(){
        int randomIndex = faker.number().numberBetween(0, issuers.size());
        return issuers.get(randomIndex);
    }
}
