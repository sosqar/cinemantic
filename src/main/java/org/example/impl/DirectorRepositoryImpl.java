package org.example.impl;


import org.example.service.DirectorRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DirectorRepositoryImplements implements DirectorRepository {
    private static final Logger LOGGER = Logger.getLogger(org.example.service.DirectorRepository.class.getName());
    private static final String SQL_SAVE = "insert into authors (fullname, created_at) VALUES (?, ?)";
    private static final String SQL_FIND_BY_NAME = "select * from authors where fullName = ?";
    private static final String SQL_IS_FULLNAME_UNIQUE = "select count(*) from authors where fullName = ?";


}
