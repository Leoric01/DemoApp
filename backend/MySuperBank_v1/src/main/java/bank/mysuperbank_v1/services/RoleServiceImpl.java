package bank.mysuperbank_v1.services;

import bank.mysuperbank_v1.models.DTOs.roleDTOs.RoleRequestDto;
import bank.mysuperbank_v1.models.DTOs.roleDTOs.RoleResponseDto;
import bank.mysuperbank_v1.models.ErrorResponse;
import bank.mysuperbank_v1.models.Role;
import bank.mysuperbank_v1.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleResponseDto> getAllRoleDTOs() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(role -> {
                    RoleResponseDto responseDto = new RoleResponseDto();
                    responseDto.setId(role.getId());
                    responseDto.setName(role.getName());
                    return responseDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<List<RoleResponseDto>> showRoles() {
        List<RoleResponseDto> roles = getAllRoleDTOs();
        return ResponseEntity.status(200).body(roles);
    }

    @Override
    public ResponseEntity<?> addNewRole(RoleRequestDto request) {
        if (request.getName() == null || request.getName().isBlank()) {
            return ResponseEntity.status(400).body(new ErrorResponse("Missing name"));
        }
        Role role = new Role(request.getName());
        roleRepository.save(role);
        RoleResponseDto responseDto = new RoleResponseDto(role.getId(), role.getName());
        return ResponseEntity.status(201).body(responseDto);
    }
}
