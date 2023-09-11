package com.johnpickup.route.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;

@Setter
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@DynamoDBTable(tableName = "User")
public class User {
    @Id
    private String id;

    private String name;

    private String email;

    private UserRole role;

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    @DynamoDBAttribute
    public String getName() {
        return name;
    }

    @DynamoDBAttribute
    public String getEmail() {
        return email;
    }

    @DynamoDBAttribute
    public UserRole getRole() {
        return role;
    }
}