package com.fisrtproject.forum.repository;

import com.fisrtproject.forum.entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcTemplateBoardRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateBoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PostEntity> postMapper() {
        return (rs, rowNum) -> {
            PostEntity post = new PostEntity();
            post.setId(rs.getLong("post_id"));
            post.setTitle(rs.getString("title"));
            post.setContent(rs.getString("content"));
            post.setCreatedAt(rs.getTimestamp("created_at"));
            return post;
        };
    }

    public List<PostEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM post", postMapper());
    }
}
