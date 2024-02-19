package com.olegmng.api;

import lombok.Data;

import java.rmi.server.UID;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class Issue {
    private UUID id;
    private LocalDate issuedAt;
    private UUID readerId;
    private UUID bookId;

}
