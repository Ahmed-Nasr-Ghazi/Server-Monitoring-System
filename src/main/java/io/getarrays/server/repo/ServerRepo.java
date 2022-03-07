package io.getarrays.server.repo;

import io.getarrays.server.model.Server;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.function.Function;

public interface ServerRepo extends JpaRepository<ServerRepo, Long> {

    Server findByIpAddress(String ipAddress);
}
