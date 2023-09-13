package bank.mysuperbank_v1.controllers;

import bank.mysuperbank_v1.models.DTOs.roleDTOs.RoleRequestDto;
import bank.mysuperbank_v1.models.DTOs.roleDTOs.RoleResponseDto;
import bank.mysuperbank_v1.services.RoleService;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleResponseDto>> showAllRoles(){
        return roleService.showRoles();
    }

    @PostMapping("/role")
    public ResponseEntity<?> addNewRole(@RequestBody RoleRequestDto request){
        return roleService.addNewRole(request);
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<?> deleteRoleById(@PathVariable Long id, @NotNull HttpServletRequest request){
        return roleService.deleteRoleById(id, request);
    }

}
