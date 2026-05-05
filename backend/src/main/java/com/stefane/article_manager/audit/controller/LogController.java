package com.stefane.article_manager.audit.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;



import java.util.List;

import com.stefane.article_manager.audit.repository.*;
import com.stefane.article_manager.audit.model.*;


@RestController
@RequestMapping("/logs")
public class LogController {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @GetMapping
    public List<AuditLog> listar() {
        return auditLogRepository.findAll();
    }
}