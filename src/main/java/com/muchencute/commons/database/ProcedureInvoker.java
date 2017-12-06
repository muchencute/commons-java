package com.muchencute.commons.database;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

@SuppressWarnings("ALL")
public class ProcedureInvoker {

    private boolean mErrorOccured = false;

    private String mErrorMessage = "";

    private DataSource mDataSource;

    private Connection mConnection = null;

    private CallableStatement mStatement = null;

    private ResultSet mResultSet = null;

    // 出参寄存器
    private ArrayList<Object> mOutParams = new ArrayList<>();

    public ProcedureInvoker(DataSource dataSource) {

        mDataSource = dataSource;
    }

    /**
     * 建立连接，执行存储过程
     *
     * @param procedure 存储过程
     * @param params    参数列表
     * @return 当前 {@code ProcedureInvoker} 对象
     */
    public ProcedureInvoker call(String procedure, Object... params) {

        if (mErrorOccured) {
            return this;
        }

        if (mDataSource == null) {
            mErrorOccured = true;
            mErrorMessage = "DataSource 对象为空";
            return this;
        }

        if (mConnection != null || mStatement != null) {
            mErrorOccured = true;
            mErrorMessage = "该对象已经调用过一次存储过程，不能重复使用";
            return this;
        }

        try {
            mConnection = mDataSource.getConnection();
            StringBuilder arguments = new StringBuilder();
            for (int i = 0, length = params.length; i < length; i++) {
                arguments.append(i < length - 1 ? "?," : "?");
            }
            String sql = String.format("CALL %s(%s);", procedure, arguments);
            mStatement = mConnection.prepareCall(sql);

            ArrayList<Integer> outParamPositions = new ArrayList<>();
            for (int i = 0, length = params.length; i < length; i++) {
                if (params[i] == null) {
                    mStatement.setObject(i + 1, null);
                } else if (params[i] instanceof Integer) {
                    mStatement.setInt(i + 1, (Integer) params[i]);
                } else if (params[i] instanceof Float) {
                    mStatement.setFloat(i + 1, (Float) params[i]);
                } else if (params[i] instanceof String) {
                    mStatement.setString(i + 1, (String) params[i]);
                } else if (params[i] instanceof OutParam) {
                    mStatement.registerOutParameter(i + 1, ((OutParam) params[i]).getType());
                    outParamPositions.add(i + 1);
                } else if (params[i] instanceof Boolean) {
                    mStatement.setBoolean(i + 1, (Boolean) params[i]);
                } else if (params[i] instanceof Long) {
                    mStatement.setLong(i + 1, (Long) params[i]);
                } else {
                    mErrorOccured = true;
                    mErrorMessage = String.format("%s 是不支持的数据类型", params.getClass());
                    return this;
                }
            }
            if (mStatement.execute()) {
                mResultSet = mStatement.getResultSet();
            }
            for (Integer position : outParamPositions) {
                switch (((OutParam) params[position - 1]).getType()) {
                    case Types.INTEGER:
                        mOutParams.add(mStatement.getInt(position));
                        break;
                    case Types.VARCHAR:
                        mOutParams.add(mStatement.getString(position));
                        break;
                    case Types.FLOAT:
                        mOutParams.add(mStatement.getFloat(position));
                        break;
                    case Types.BIGINT:
                        mOutParams.add(mStatement.getLong(position));
                        break;
                    default:
                        mErrorOccured = true;
                        mErrorMessage = String.format("参数 %d 是不被支持的出参类型", position);
                        return this;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
            mErrorOccured = true;
            mErrorMessage = e.getLocalizedMessage();
        }

        return this;
    }

    /**
     * 迭代处理结果集
     *
     * @param executed 处理函数
     * @return 当前 {@code ProcedureInvoker} 对象
     */
    public ProcedureInvoker executed(Executed executed) {

        if (mErrorOccured) {
            return this;
        }

        try {
            executed.executed(mResultSet, mOutParams);
        } catch (Throwable e) {
            e.printStackTrace();
            mErrorOccured = true;
            mErrorMessage = e.getLocalizedMessage();
        }

        return this;
    }

    /**
     * 关闭链接，释放资源
     */
    public void close() {

        if (mResultSet != null) {
            try {
                mResultSet.close();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        if (mStatement != null) {
            try {
                mStatement.close();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        if (mConnection != null) {
            try {
                mConnection.close();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isErrorOccured() {

        return mErrorOccured;
    }

    public String getErrorMessage() {

        return mErrorMessage;
    }
}
