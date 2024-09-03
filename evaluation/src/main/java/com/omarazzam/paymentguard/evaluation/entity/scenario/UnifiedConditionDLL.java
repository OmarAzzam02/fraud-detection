package com.omarazzam.paymentguard.evaluation.entity.scenario;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnifiedConditionDLL {


    private UnifiedConditionNode head;
    private UnifiedConditionNode tail;

    public void add(UnifiedCondition unifiedCondition) {
        UnifiedConditionNode newNode =  UnifiedConditionNode.builder().data(unifiedCondition).build();
        if (head == null) {
            head = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
        }
        tail = newNode;
    }

    public void remove(UnifiedConditionNode node) {
        if (node == null) return;

        if (node == head) {
            head = node.getNext();
            if (head != null) {
                head.setPrev(null);
            }
        } else if (node == tail) {
            tail = node.getPrev();
            if (tail != null) {
                tail.setNext(null);
            }
        } else {
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
        }
    }





}
