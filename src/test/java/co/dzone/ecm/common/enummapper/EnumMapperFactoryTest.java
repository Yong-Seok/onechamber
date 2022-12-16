package co.dzone.ecm.common.enummapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
class EnumMapperFactoryTest {

    @Autowired
    EnumMapperFactory enumMapperFactory;

    @Test
    public void test() throws Exception {
        List<List<EnumMapperValue>> lists = Arrays.asList(enumMapperFactory.get("groupStatus"), enumMapperFactory.get("userStatus"));
        for (List<EnumMapperValue> list : lists) {
            for (EnumMapperValue s : list) {
                System.out.println("s = " + s.getClass() + ", s.getCode() = " + s.getCode() + ", s.getTitle() = " + s.getTitle());
            }
        }
    }
}