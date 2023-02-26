package com.example.belarusattractions.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "BelarusAttractions")
public class City implements Serializable {
    @Id
    String id;
    String name;
    //@DBRef
    List<String> attraction;

}
