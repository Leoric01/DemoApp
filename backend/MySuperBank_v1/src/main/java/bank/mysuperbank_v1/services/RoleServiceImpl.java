package bank.mysuperbank_v1.services;

import bank.mysuperbank_v1.models.DTOs.roleDTOs.RoleRequestDto;
import bank.mysuperbank_v1.models.DTOs.roleDTOs.RoleResponseDto;
import bank.mysuperbank_v1.models.DTOs.statusDTOs.ErrorResponse;
import bank.mysuperbank_v1.models.DTOs.statusDTOs.SuccessResponse;
import bank.mysuperbank_v1.models.Role;
import bank.mysuperbank_v1.models.User;
import bank.mysuperbank_v1.repositories.RoleRepository;
import bank.mysuperbank_v1.repositories.UserRepository;
import bank.mysuperbank_v1.security.config.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;


    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository, JwtService jwtService) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
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
        if (roleRepository.findRoleByName(request.getName()).isPresent()) return ResponseEntity.status(409).body(new ErrorResponse("Role already exists"));
        Role role = new Role(request.getName());
        roleRepository.save(role);
        RoleResponseDto responseDto = new RoleResponseDto(role.getId(), role.getName());
        return ResponseEntity.status(201).body(responseDto);
    }

    @Override
    public ResponseEntity<?> deleteRoleById(Long id, @NotNull HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")){
            return ResponseEntity.status(400).body(new ErrorResponse("Bearer token error"));
        }
        final String jwt = authHeader.substring(7);
        User user = userRepository.findUserByUsername(jwtService.extractUsername(jwt));
        if (!user.getRole().getName().equals("ADMIN")) return ResponseEntity.status(401).body(new ErrorResponse("Must be admin to be able to touch roles"));
        Role role;
        if (roleRepository.findRoleById(id).isPresent()){
            role = roleRepository.findRoleById(id).get();
            roleRepository.delete(role);
            return ResponseEntity.status(200).body(new SuccessResponse(role.getName() + " role with id: " + id + " was successfully deleted"));
        }
        return ResponseEntity.status(404).body(new ErrorResponse("Role with this id probably doesn't exist. Try to check: http://localhost:8080/api/roles"));

    }
}
