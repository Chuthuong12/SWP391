/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Dimension {
    private int dimId;
    private String typeId;
    private String typeName;
    private String name;
    private String description;

    public Dimension(String typeId, String name, String description) {
        this.typeId = typeId;
        this.name = name;
        this.description = description;
    }

    public Dimension(int dimId, String typeId, String typeName, String name, String description) {
        this.dimId = dimId;
        this.typeId = typeId;
        this.typeName = typeName;
        this.name = name;
        this.description = description;
    }

    
    
}
