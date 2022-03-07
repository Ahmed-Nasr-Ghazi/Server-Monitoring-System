package io.getarrays.server.service.implementation;

import io.getarrays.server.model.Server;
import io.getarrays.server.repo.ServerRepo;
import io.getarrays.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.logging.Logger;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j //for logging
public class ServerServiceImpl implements ServerService {

    private final ServerRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("Saving new Server", server.getName());
        server.setImageUrl(setServerImageUrl());
        return  serverRepo.save(server);
    }

    private String setServerImageUrl() {
        return null;
    }

    @Override
    public Server ping(String idAddress) {
        return null;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("fetching all servers  ");
        return serverRepo.findAll(PageRequest.of(0,limit).toList());
    }
    @Override
    public Server get(Long id) {
        return null;
    }

    @Override
    public Server update(Server server) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }
}
