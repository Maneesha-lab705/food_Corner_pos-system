package lk.ijse.mini.api.dao;

import java.sql.SQLException;

public interface CrudDAO<T,ID> extends SuperDAO{

    T sharch(ID id) throws SQLException;

    boolean delete(ID id) throws SQLException;

    boolean update(T customer) throws SQLException;

    boolean save(T customer) throws SQLException;

}
