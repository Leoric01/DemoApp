package bank.mysuperbank_v1.services;

import bank.mysuperbank_v1.models.DTOs.roleDTOs.RoleRequestDto;
import bank.mysuperbank_v1.models.DTOs.roleDTOs.RoleResponseDto;
import bank.mysuperbank_v1.models.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService  {
    List<RoleResponseDto> getAllRoleDTOs();
    ResponseEntity<List<RoleResponseDto>> showRoles();

    ResponseEntity<?> addNewRole(RoleRequestDto request);
}
