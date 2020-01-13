package com.skrshop.skrshoptkmybatis.handler;

import com.skrshop.common.enums.BaseEnum;
import com.skrshop.common.utils.EnumUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2020/1/13
 * @copyright 本内容仅限于浙江云贸科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class EnumCodeTypeHandler<E extends Enum<E> & BaseEnum> extends BaseTypeHandler<E> {

    private final Class<E> type;

    public EnumCodeTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
    }

    /**
     * 用于定义设置参数时，该如何把 Java 类型的参数转换为对应的数据库类型
     * @param preparedStatement
     * @param i
     * @param e
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, E e, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, e.getCode());
    }

    /**
     * 用于定义通过字段名称获取字段数据时，如何把数据库类型转换为对应的 Java 类型
     * @param resultSet
     * @param columnName
     * @return
     * @throws SQLException
     */
    @Override
    public E getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        int code = resultSet.getInt(columnName);
        return resultSet.wasNull() ? null : EnumUtils.codeOf(this.type, code);
    }

    /**
     * 用于定义通过字段索引获取字段数据时，如何把数据库类型转换为对应的 Java 类型
     * @param rs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return rs.wasNull() ? null : EnumUtils.codeOf(this.type, code);
    }

    /**
     *  用定义调用存储过程后，如何把数据库类型转换为对应的 Java 类型
     * @param cs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return cs.wasNull() ? null : EnumUtils.codeOf(this.type, code);
    }
}
