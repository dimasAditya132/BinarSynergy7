package com.example.BinFood.controller;

import com.example.BinFood.model.Merchant;
import com.example.BinFood.payload.MerchantDTO;
import com.example.BinFood.payload.Response;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("merchant")
@Slf4j
public class MerchantController {
    final ModelMapper modelMapper;

    final MerchantUtil merchantUtil;

    public MerchantController(MerchantUtil merchantUtil, ModelMapper modelMapper) {
        this.merchantUtil = merchantUtil;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    public ResponseEntity<Response> add(@RequestBody MerchantDTO merchantDTO) {
        Merchant merchant = modelMapper.map(merchantDTO, Merchant.class);
        merchant = merchantUtil.createMerchant(merchant.getMerchantName(), merchant.getMerchantLocation());
        return ResponseEntity.ok(new Response.Success(merchant));
    }

    @GetMapping()
    public ResponseEntity<Response> getMerchants(@RequestParam("open") @Nullable Boolean isOpen) {
        List<Merchant> merchantList;
        if (isOpen == null) {
            merchantList = merchantUtil.getAllMerchants(isOpen);
        } else {
            merchantList = merchantUtil.getAllMerchants();
        }
        List<MerchantDTO> merchantDTOList = merchantList.stream()
                .map(merchant -> modelMapper.map(merchant, MerchantDTO.class))
                .toList();
        Map<String, List<MerchantDTO>> data = new LinkedHashMap<>();
        data.put("merchants", merchantDTOList);

        return ResponseEntity.ok(new Response.Success(data));
    }

    @GetMapping("{id}")
    public ResponseEntity<Response> getMerchantById(@PathVariable("id") String id) {
        Merchant merchant;
        try {
            merchant = merchantUtil.getMerchantDetail(id);
            return ResponseEntity.ok(new Response.Success(modelMapper.map(merchant, MerchantDTO.class)));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new Response.Error("Merchant not found"), HttpStatus.OK);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Response> updateOpenStatus(@PathVariable("id") String id, @RequestBody MerchantDTO merchantDTO) {
        Merchant merchant = modelMapper.map(merchantDTO, Merchant.class);
        merchant = merchantUtil.editMerchantOpenStatus(id, merchant.isOpen());
        if (merchant != null) {
            return ResponseEntity.ok(new Response.Success(modelMapper.map(merchant, MerchantDTO.class)));
        } else {
            return new ResponseEntity<>(new Response.Error("Merchant not found"), HttpStatus.OK);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") String id) {
        try {
            merchantUtil.deleteMerchant(id);
            return ResponseEntity.ok(new Response.Success("Merchant deleted successfully"));
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new Response.Error(e.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    @GetMapping("{id}/generate")
    public ResponseEntity<Response> getMerchantReport(
            @PathVariable("id") String id,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date startPeriod;
        Date endPeriod;
        try {
            startPeriod = dateFormat.parse(startDate);
            endPeriod = dateFormat.parse(endDate);
        } catch (ParseException e) {
            return new ResponseEntity<>(new Response.Error(e.getLocalizedMessage()), HttpStatus.OK);
        }
        Double total = merchantUtil.getMerchantReport(id, startPeriod, endPeriod);

        return ResponseEntity.ok(new Response.Success(total));
    }
}
