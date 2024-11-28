package lk.ijse.cropmanagement.controller;

import lk.ijse.cropmanagement.dto.impl.StaffDTO;
import lk.ijse.cropmanagement.dto.impl.UserDTO;
import lk.ijse.cropmanagement.security.JWTAuthResponse;
import lk.ijse.cropmanagement.security.SignIn;
import lk.ijse.cropmanagement.service.AuthService;
import lk.ijse.cropmanagement.service.StaffService;
import lk.ijse.cropmanagement.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("api/v1/auth")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final StaffService staffService;
    private final UserService userService;
    private final AuthService authService;

    /*@PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    //@PreAuthorize("hasRole('MANAGER') or hasRole('ADMINISTRATOR') or hasRole('SCIENTIST')")
    public ResponseEntity<JWTAuthResponse> createUser(@RequestBody UserDTO userDto) {
        System.out.println(userDto);
        try {
            // Check if a staff member exists with the given email
            Optional<StaffDTO> existingStaff = staffService.findByEmail(userDto.getEmail());


            if (!existingStaff.isPresent()) {
                // Save new staff member if none exists
                StaffDTO newStaff = new StaffDTO();
                newStaff.setEmail(userDto.getEmail());
                newStaff.setRole(userDto.getRole());

                // Save the staff member and get the saved DTO
                newStaff = staffService.save(newStaff);

                // Set the saved staff object to the user DTO (not just staffId)
                userDto.setStaff(newStaff); // Link the full StaffDTO object
            } else {
                // Link to the existing staff member
                userDto.setStaff(existingStaff.get()); // Set the existing StaffDTO object
            }


            // Save the user
            return ResponseEntity.ok(authService.signUp(userDto));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> createUser(@RequestBody UserDTO userDto) {
        try {
            // Check if a staff member exists with the given email
            Optional<StaffDTO> existingStaff = staffService.findByEmail(userDto.getEmail());

            existingStaff.ifPresentOrElse(staff -> {
                // Link to the existing staff member
                userDto.setStaff(staff);
            }, () -> {
                // Save new staff member if none exists
                StaffDTO newStaff = new StaffDTO();
                newStaff.setEmail(userDto.getEmail());
                newStaff.setRole(userDto.getRole());

                // Save the staff member and get the saved DTO
                newStaff = staffService.save(newStaff);

                // Set the saved staff object to the user DTO (not just staffId)
                userDto.setStaff(newStaff);
            });

            // Save the user
            return ResponseEntity.ok(authService.signUp(userDto));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "signIn",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JWTAuthResponse> signIn(@RequestBody SignIn signIn){
        return ResponseEntity.ok(authService.signIn(signIn));

    }
    @PostMapping("refresh")
    public ResponseEntity<JWTAuthResponse> refreshToken(@RequestParam("existingToken") String existingToken) {


        return ResponseEntity.ok(authService.refreshToken(existingToken));
    }
}
