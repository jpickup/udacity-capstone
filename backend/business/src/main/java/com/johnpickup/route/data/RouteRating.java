package com.johnpickup.route.data;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Setter
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@DynamoDBTable(tableName = "RouteRating")
public class RouteRating {
    @Id
    private String id;

    private String routeId;

    private String userId;

    private int rating;

    private String comments;

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    @DynamoDBAttribute
    public String getRouteId() {
        return routeId;
    }

    @DynamoDBAttribute
    public String getUserId() {
        return userId;
    }

    @DynamoDBAttribute
    public int getRating() {
        return rating;
    }

    @DynamoDBAttribute
    public String getComments() {
        return comments;
    }
}