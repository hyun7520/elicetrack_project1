package com.fisrtproject.forum.repository;

import com.fisrtproject.forum.dto.BoardCreateDto;
import com.fisrtproject.forum.entity.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateBoardRepository {

    private final JdbcTemplate jdbcTemplate;
    private final PostRepository postRepository;

    @Autowired
    public JdbcTemplateBoardRepository(JdbcTemplate jdbcTemplate, PostRepository postRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.postRepository = postRepository;
    }

    private final RowMapper<BoardEntity> boardMapper() {
        return (rs, rowNum) -> {
            BoardEntity board = new BoardEntity();
            board.setId(rs.getLong("board_id"));
            board.setTopic(rs.getString("topic"));
            board.setBoardDescription(rs.getString("board_description"));
            return board;
        };
    }

    public List<BoardEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM board", boardMapper());
    }

    public Optional<BoardEntity> findById(Long id) {
        return jdbcTemplate
                .query("SELECT * FROM board WHERE board_id = ?", boardMapper(), id)
                .stream()
                .findFirst();
    }

    public void save(BoardEntity board) {
        String sql = "INSERT INTO board(topic, board_description) VALUES (?, ?)";
        jdbcTemplate.update(sql, board.getTopic(), board.getBoardDescription());
    }

    public void updateBoard(Long id, BoardCreateDto boardCreateDto) {
        String sql = "UPDATE board SET topic = ?, board_description = ? WHERE board_id = ?";
        jdbcTemplate.update(sql, boardCreateDto.getTopic(), boardCreateDto.getBoardDescription(), id);
    }

    public void deleteBoard(Long id) {
        String sql = "DELETE FROM \n" +
                "\tcomment \n" +
                "WHERE \n" +
                "\tpost_id IN \n" +
                "\t\t(SELECT DISTINCT post_id FROM post WHERE board_id = ?);";
        jdbcTemplate.update(sql, id);
        sql = "DELETE FROM post WHERE board_id = ?";
        jdbcTemplate.update(sql, id);
        sql = "DELETE FROM board WHERE board_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
