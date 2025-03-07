package com.barber.shop.models;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "schedules", uniqueConstraints = {
        @UniqueConstraint(name = "uk_schedule_interval", columnNames = { "start_at", "end_at" })
})
@Getter
@Setter
@ToString
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "start_at")
    private OffsetDateTime startAt;
    @Column(nullable = false, name = "end_at")
    private OffsetDateTime endAt;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((startAt == null) ? 0 : startAt.hashCode());
        result = prime * result + ((endAt == null) ? 0 : endAt.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ScheduleEntity other = (ScheduleEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (startAt == null) {
            if (other.startAt != null)
                return false;
        } else if (!startAt.equals(other.startAt))
            return false;
        if (endAt == null) {
            if (other.endAt != null)
                return false;
        } else if (!endAt.equals(other.endAt))
            return false;
        return true;
    }

}
