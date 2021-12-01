package org.jpos.test;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jpos.ee.usertype.JsonbType;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "test_entity")
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonbType.class)
})
public class TestEntity implements Serializable {

    private static final long serialVersionUID = -1798070786993154676L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Type(type = "jsonb")
    @Column(name = "params", columnDefinition = "jsonb")
    private ObjectNode params;

    public Integer getId() {
        return id;
    }

    public TestEntity setId(Integer employeeId) {
        this.id = employeeId;
        return this;
    }

    public ObjectNode getParams() {
        return params;
    }

    public TestEntity setParams(ObjectNode params) {
        this.params = params;
        return this;
    }
}
