package co.dzone.ecm.base.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class ONEBaseEntity implements Persistable<String> {

    @Id
    @Column(name = "UID", updatable = false)
    private String UID;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifyDate;

    @PrePersist
    public void prePersists() {
        if(!StringUtils.hasText(this.UID)) {
            this.UID = String.valueOf(UUID.randomUUID()).substring(0, 8);
        }

        this.createDate = LocalDateTime.now().withNano(0);
        this.modifyDate = LocalDateTime.now().withNano(0);
    }

    @PreUpdate
    public void preUpdate() {
        this.modifyDate = LocalDateTime.now().withNano(0);
    }

    public void initUID(String UID) {
        this.UID = UID;
    }

    @Override
    public String getId() {
        return this.UID;
    }

    @Override
    public boolean isNew() {
        return this.createDate == null;
    }
}