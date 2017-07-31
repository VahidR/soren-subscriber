package org.vahid.db;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.vahid.api.FavoriteBook;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vahid (@vahid_r)
 */

public class NewsLetterMapper implements ResultSetMapper<FavoriteBook> {

    @Override
    public FavoriteBook map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new FavoriteBook(resultSet.getString("category_path"), resultSet.getString("book"));
    }
}
