package com.lt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.lt.constants.SQLConstants;
import com.lt.utils.DBUtils;

public class PaymentDaoImpl implements PaymentDaoInterface {
	private static Logger logger = Logger.getLogger(PaymentDaoImpl.class);

	private static volatile PaymentDaoImpl instance = null;

	private PaymentDaoImpl() {

	}

	public static PaymentDaoImpl getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (PaymentDaoImpl.class) {
				instance = new PaymentDaoImpl();
			}
		}
		return instance;
	}

	@Override
	public int makePayment(int studentId, String mode, String amount, String cardNo, String Expiry, String CVV)
			throws SQLException {
		Connection connection = DBUtils.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.prepareStatement(SQLConstants.MAKE_PAYMENT, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, studentId);
			statement.setString(2, mode);
			statement.setString(3, amount);
			statement.setString(4, cardNo);
			statement.setString(5, Expiry);
			statement.setString(6, CVV);

			statement.executeUpdate();
			rs = statement.getGeneratedKeys();
			if (rs.next()) {
				logger.info("Payment is Succesfully Done by Student with id " + studentId + " " + "With Transaction Id "
						+ rs.getInt(1));
				return rs.getInt(1);
			} else {
				logger.error("Payment is Failed!!!!");
				return 0;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return 0;
	}

}
