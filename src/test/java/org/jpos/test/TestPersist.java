package org.jpos.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

public class TestPersist {

    @Test
    public void test_insert_select() throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();

        TestEntity te = new TestEntity();
        te.setParams(createDummy());

        Transaction tx = session.beginTransaction();
        session.save(te);
        tx.commit();

        TestEntity loaded = session.find(TestEntity.class, te.getId());
        assertNotNull(loaded);

        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(loaded.getParams()));
    }

    public ObjectNode createDummy() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        root.set("integer", mapper.convertValue(1, JsonNode.class));
        root.set("string", mapper.convertValue("string", JsonNode.class));
        root.set("bool", mapper.convertValue(true, JsonNode.class));
        root.set("array", mapper.convertValue(Arrays.asList("a", "b", "c"), JsonNode.class));
        return root;
    }
}
