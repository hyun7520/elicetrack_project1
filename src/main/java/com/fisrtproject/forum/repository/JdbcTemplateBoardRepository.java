package com.fisrtproject.forum.repository;

import com.fisrtproject.forum.entity.BoardEntity;
import com.fisrtproject.forum.entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateBoardRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateBoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<BoardEntity> boardMapper() {
        return (rs, rowNum) -> {
            BoardEntity board = new BoardEntity();
            board.setId(rs.getLong("board_id"));
            board.setTopic(rs.getString("topic"));
            return board;
        };
    }

    public List<BoardEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM board", boardMapper());
    }

    public Optional<BoardEntity> findById(Long id) {
        return jdbcTemplate
                .query("SELECT * FROM post WHERE board_id = ?", boardMapper(), id)
                .stream()
                .findFirst();
    }
}
