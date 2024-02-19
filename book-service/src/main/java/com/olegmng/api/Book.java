package com.olegmng.api;

import lombok.Data;

import java.rmi.server.UID;
import java.util.UUID;

@Data
public class Book {
    private UUID id;
    private String name;
    private Author author;

}
