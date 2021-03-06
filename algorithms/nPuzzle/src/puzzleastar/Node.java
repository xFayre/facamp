/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzleastar;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author estevaofay
 */
public class Node {
    private final Puzzle state;
    private Node parent;
    private Integer depth; //g()
    private Set<Node> possibleMoves;
    private Integer f;
    
    
    public Node(Node parent, Puzzle state){
        this.state = state;
        this.parent = parent;
        if(parent != null) {
            this.depth = parent.depth + 1;
        } else {
            this.depth = 0;
        }
        
    }
    
    public int f() {
        return depth + state.h();
    }
    
    @Override
    public boolean equals(Object o) {
        Node node = (Node) o;
        for (int i = 0; i < state.matrix.length; i++) {
                for (int j = 0; j < state.matrix.length; j++) {
                    if (state.matrix[i][j] != node.getState().matrix[i][j]) {
                        return false;
                    }
                }

            }
        return true;
    }
    
    public Set<Node> expand() {
        Set<Node> ret = new HashSet<>();
        Set<Puzzle> aux = new HashSet<>();
        aux.addAll(this.getState().getPossibleMoves());
        
        for (Puzzle puzzle : aux) {
            ret.add(puzzle.getNode());
        }
        return ret;
    }
    
    public String printTree() {
        StringBuilder sb = new StringBuilder();
        LinkedList<Node> printList = new LinkedList<>();
        Node aux = this;
        while (aux.getParent() !=null){
            printList.addFirst(aux);
            if(aux.getParent()!=null){
                aux = aux.getParent();
            }
            
        }
        for (Node node : printList) {
            sb.append(node.toString());
            sb.append("\n");
        }
        
        return sb.toString();
    }
    
 
    public boolean isGoal(){
        return this.state.isGoal();
    }

    public Puzzle getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    
    @Override
    public String toString() {
        return state.toString();
    }

}
