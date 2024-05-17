package com.example.BinFood.controller;

import com.example.BinFood.model.Merchant;
import com.example.BinFood.model.response.ErrorResponse;
import com.example.BinFood.model.response.MerchantResponse;
import com.example.BinFood.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("merchant")
public class MerchantController {
    @Autowired
    MerchantService merchantService;

    @PostMapping(value = "add", consumes = "application/json")
    public ResponseEntity<String> requestAddMerchantStatus(@RequestBody Merchant merchant) {
        if (merchantService.addMerchant(merchant))
            return new ResponseEntity<>("Successfully added merchant", HttpStatus.OK);
        else return new ResponseEntity<>("Failed to add " + merchant + "to database", HttpStatus.OK);
    }

    @PutMapping(value = "edit/status/{merchant}")
    public ResponseEntity<ErrorResponse<Objects>> requestEditMerchantStatus(@PathVariable("merchant") String merchantName,
                                                                            @RequestParam("nStatus") boolean newStatus) {
        if (merchantService.changeMerchantStatus(merchantName, newStatus)) {
            return new ResponseEntity<>(ErrorResponse.<Objects>builder()
                    .errorMessage("Successfully edited " + merchantName + " status")
                    .errorCode(HttpStatus.OK.value())
                    .build(), HttpStatus.OK);
        } else return new ResponseEntity<>(ErrorResponse.<Objects>builder()
                .errorMessage("Failed to edit " + merchantName + " status")
                .errorCode(HttpStatus.NOT_FOUND.value())
                .build(), HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "get-active/{pageNumber}", produces = "application/json")
    public ResponseEntity requestActiveMerchantList(@PathVariable("pageNumber") int pageNumber) {
        List<MerchantResponse> activeMerchantOfPage = merchantService.pageOfMerchant(pageNumber);
        if (Objects.nonNull(activeMerchantOfPage)) {
            return new ResponseEntity<>(activeMerchantOfPage, HttpStatus.OK);
        }
        return new ResponseEntity<>(ErrorResponse.builder()
                .errorCode(HttpStatus.NOT_FOUND.value())
                .errorMessage("No Active Merchant")
                .build(), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "delete/{merchantName}")
    public ResponseEntity requestDeleteMerchant(@PathVariable("merchantName") String merchantName) {
        if (merchantService.deleteMerchant(merchantName)) {
            return new ResponseEntity<>("Successfully deleted merchant" + merchantName, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ErrorResponse.builder()
                    .errorCode(HttpStatus.NOT_FOUND.value())
                    .errorMessage("Couldn't delete merchant " + merchantName)
                    .build(), HttpStatus.NOT_FOUND);
        }
    }




}
