package com.saltfish.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class payment implements Serializable {
    private Long id;
    private String numbers;
    private String content;
}
