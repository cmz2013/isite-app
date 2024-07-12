package org.isite.bi;

import org.isite.bi.service.ProjectCostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BiApplication.class)
class BiAdminApplicationTest {

    @Autowired
    private ProjectCostService indexCostService;

    @Test
    void testIndexCost() {
        indexCostService.runProjectCost(1, 3, 1);
    }
}
