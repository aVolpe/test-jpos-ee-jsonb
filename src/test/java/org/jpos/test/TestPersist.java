package org.jpos.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

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

        TestPojo testPojo = new ObjectMapper().treeToValue(loaded.getParams(), TestPojo.class);
        System.out.println(testPojo.getName());
    }

    public ObjectNode createDummy() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode root = mapper.createObjectNode();
        root.set("name", mapper.convertValue("name", JsonNode.class));
        return root;
    }

    public static class TestPojo {
        private String name;

        public String getName() {
            return name;
        }

        public TestPojo setName(String name) {
            this.name = name;
            return this;
        }
    }
}
