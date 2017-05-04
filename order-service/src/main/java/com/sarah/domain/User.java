package com.sarah.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by vagrant on 5/4/17.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Embeddable
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String fullName;

    public User() {

    }

    @JsonCreator
    public User(
            @JsonProperty("fullname") String fullName
    ) {
        this.fullName = fullName;

    }
}