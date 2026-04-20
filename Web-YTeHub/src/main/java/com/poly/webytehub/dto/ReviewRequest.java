package com.poly.webytehub.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequest {
    private Integer productId;
    private Integer rating;
    private String comment;
}
