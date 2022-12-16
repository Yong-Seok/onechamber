package co.dzone.ecm.config.enummapper;

import co.dzone.ecm.common.enummapper.EnumMapperFactory;
import co.dzone.ecm.group.entity.ONEGroup;
import co.dzone.ecm.user.entity.ONEUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class EnumMapperConfiguration {

    @Bean
    public EnumMapperFactory createEnumMapperFactory() {
        EnumMapperFactory enumMapperFactory = new EnumMapperFactory(new LinkedHashMap<>());
        enumMapperFactory.put("groupStatus", ONEGroup.Status.class);
        enumMapperFactory.put("userStatus", ONEUser.Status.class);
        return enumMapperFactory;
    }
}
