package com.picpaysimplificado.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long sender_Id, Long receiver_Id) {

}
