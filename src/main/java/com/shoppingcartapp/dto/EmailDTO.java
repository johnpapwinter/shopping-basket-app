package com.shoppingcartapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {

    private String recipient;
    private String msgBody;
    private String subject;
}
