package com.home.project.service;

import com.home.project.entity.Client;
import com.home.project.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll(){ return clientRepository.findAll(); }

    public Client save(Client cliente) { return clientRepository.save(cliente); }

    public Optional<Client> findById(Long id) {
        Optional<Client> cliente = clientRepository.findById(id);
        if(cliente.isPresent()){
            cliente.get();
        }else {
            throw new RuntimeException("Client not found for id: " + id);
        }
        return cliente;
    }

    public void delete(Long id) { clientRepository.deleteById(id); }

    /*
    public Client update(Client client, Long id)  {

        Client clientUpdate = clientRepository.getOne(id);
        clientUpdate.setName(client.getName());
        clientUpdate.setLastName(client.getLastName());
        clientUpdate.setEmail(client.getEmail());
        clientRepository.save(clientUpdate);
        return clientUpdate;
    }
    */

    public Client getOne(Long id) { return clientRepository.getOne(id);}
}
