package com.omarazzam.paymentguard.evaluation.entity.scenario;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnifiedConditionNode {
    private UnifiedCondition data;
    private UnifiedConditionNode prev;
    private UnifiedConditionNode next;

}
