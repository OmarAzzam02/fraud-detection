package com.omarazzam.paymentguard.evaluation.entity.scenario;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ScenariosUnifiedConditionNode {
    private UnifiedConditionDLL data;
    private ScenariosUnifiedConditionNode next;
    private ScenariosUnifiedConditionNode prev;
}
