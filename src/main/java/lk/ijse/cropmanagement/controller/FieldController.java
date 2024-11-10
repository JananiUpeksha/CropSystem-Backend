package lk.ijse.cropmanagement.controller;

import lk.ijse.cropmanagement.dto.impl.FieldDTO;
import lk.ijse.cropmanagement.service.FieldService;
import lk.ijse.cropmanagement.utill.RegexProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveField(@RequestBody FieldDTO fieldDTO) {
        try {
            // Save the field along with the staff members association
            fieldService.saveField(fieldDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{fieldId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FieldDTO> getFieldById(@PathVariable("fieldId") String fieldId) {
        if (!RegexProcess.fieldIdMatcher(fieldId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            FieldDTO fieldDTO = fieldService.getFieldById(fieldId);
            return new ResponseEntity<>(fieldDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FieldDTO>> getAllFields() {
        return new ResponseEntity<>(fieldService.getAllFields(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{fieldId}")
    public ResponseEntity<Void> deleteField(@PathVariable("fieldId") String fieldId) {
        try {
            if (!RegexProcess.fieldIdMatcher(fieldId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            fieldService.deleteField(fieldId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{fieldId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateField(@PathVariable("fieldId") String fieldId, @RequestBody FieldDTO fieldDTO) {
        try {
            if (!RegexProcess.fieldIdMatcher(fieldId)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            fieldService.updateField(fieldId, fieldDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
