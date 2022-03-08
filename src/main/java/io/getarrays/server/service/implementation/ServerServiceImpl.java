package io.getarrays.server.service.implementation;

import io.getarrays.server.enumeration.Status;
import io.getarrays.server.model.Server;
import io.getarrays.server.repo.ServerRepo;
import io.getarrays.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j //for logging
public class ServerServiceImpl implements ServerService {

    private final ServerRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("Saving new Server", server.getName());
        server.setImageURL(setServerImageUrl());    //clean code ?
        return serverRepo.save(server);
    }


    @Override
    public Server ping(String idAddress) throws IOException {
        log.info("Pinging ipAddress of server" + idAddress);
        Server server = serverRepo.findByIpAddress(idAddress);
        InetAddress address = InetAddress.getByName(idAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("fetching all servers ");
        return serverRepo.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching Server " + id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating new Server", server.getName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting Server " + id);
        serverRepo.deleteById(id); //if not deleted it will throw exception
        return Boolean.TRUE;
    }


    private String setServerImageUrl() {
        String[] imageNames = {"server1", "server2","server3", "server4",};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" +
                imageNames[new Random().nextInt(4)]).toUriString();
    }

}
