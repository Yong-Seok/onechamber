package co.dzone.ecm.base.repository;

import co.dzone.ecm.base.entity.ONEBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<Entity extends ONEBaseEntity> extends JpaRepository<Entity, String> {

}
