package org.vahid.db;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.vahid.api.MapEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vahid (@vahid_r)
 */

public class MapMapper implements ResultSetMapper<MapEntity> {

    @Override
    public MapEntity map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new MapEntity (resultSet.getString("email"), resultSet.getString("categoryCodes"));
    }
}
