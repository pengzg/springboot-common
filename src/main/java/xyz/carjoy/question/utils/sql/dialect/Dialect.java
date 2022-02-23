package xyz.carjoy.question.utils.sql.dialect;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Dialect
{
    public boolean supportsLimit()
    {
        return false;
    }

    public boolean supportsLimitOffset()
    {
        return false;
    }

    public abstract void setLimitParamters(PreparedStatement paramPreparedStatement, int paramInt1, int paramInt2, int paramInt3)
            throws SQLException;

    public String getLimitString(String sql, boolean hasOffset)
    {
        throw new UnsupportedOperationException("Paged queries not supported by " + getClass().getName());
    }

    public String getLimitString(String sql, int offset, int limit)
    {
        return getLimitString(sql, (offset > 0) || (forceLimitUsage()));
    }

    public boolean forceLimitUsage()
    {
        return false;
    }
}