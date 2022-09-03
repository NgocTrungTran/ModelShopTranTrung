package com.tnt.modelshoptrantrung.service.role;

import com.tnt.modelshoptrantrung.model.Role;
import com.tnt.modelshoptrantrung.service.IGeneralService;

public interface RoleService extends IGeneralService<Role> {
    Role findByName(String name);
}
