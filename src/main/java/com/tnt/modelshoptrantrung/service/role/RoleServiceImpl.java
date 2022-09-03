package com.tnt.modelshoptrantrung.service.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tnt.modelshoptrantrung.model.Role;
import com.tnt.modelshoptrantrung.repository.RoleRepository;

import java.util.List;
import java.util.Optional;


@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }


    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById (id);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
