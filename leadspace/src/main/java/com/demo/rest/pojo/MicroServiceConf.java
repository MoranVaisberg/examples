package com.demo.rest.pojo;

import lombok.*;
import java.util.Map;

@AllArgsConstructor
@Data
public class MicroServiceConf {
    String name;
    Map<String, String> configurations;
}
