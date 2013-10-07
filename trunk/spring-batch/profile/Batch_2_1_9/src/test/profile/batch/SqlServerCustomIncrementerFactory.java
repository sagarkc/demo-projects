package test.profile.batch;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.batch.item.database.support.DataFieldMaxValueIncrementerFactory;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.SqlServerMaxValueIncrementer;


public class SqlServerCustomIncrementerFactory implements DataFieldMaxValueIncrementerFactory {

	private static Logger logger = Logger.getLogger(SqlServerCustomIncrementerFactory.class.getName());

	private DataSource dataSource;
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataFieldMaxValueIncrementer getIncrementer(String databaseType, String incrementerName) {
		SqlServerMaxValueIncrementer inc=  new SqlServerMaxValueIncrementer(dataSource, incrementerName,  "ID");
		inc.setCacheSize(1000);
		return inc;
	}

	public String[] getSupportedIncrementerTypes() {
		return new String[]{"custom incs"};
	}

	public boolean isSupportedIncrementerType(String databaseType) {
		if (databaseType.equals("SQLSERVER")) {
			return true;
		} else if (databaseType.equals("MYSQL")) {
			return true;
		}
		return false;
	}
}