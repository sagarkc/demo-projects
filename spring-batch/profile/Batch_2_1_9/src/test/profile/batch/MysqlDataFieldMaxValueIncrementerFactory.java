/**
 * File :: test.profile.batch.MysqlDataFieldMaxValueIncrementerFactory
 * Date :: 25-Jul-2013
 */
package test.profile.batch;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.batch.item.database.support.DataFieldMaxValueIncrementerFactory;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.SqlServerMaxValueIncrementer;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class MysqlDataFieldMaxValueIncrementerFactory implements DataFieldMaxValueIncrementerFactory{

	private static Logger logger = Logger.getLogger(MysqlDataFieldMaxValueIncrementerFactory.class.getName());

	private DataSource dataSource;
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataFieldMaxValueIncrementer getIncrementer(String databaseType, String incrementerName) {
		MySQLMaxValueIncrementer inc=  new MySQLMaxValueIncrementer(dataSource, incrementerName,  "ID");
		inc.setCacheSize(100);
		return inc;
	}

	public String[] getSupportedIncrementerTypes() {
		return new String[]{"custom incs"};
	}

	public boolean isSupportedIncrementerType(String databaseType) {
		if (databaseType.equals("MYSQL")) {
			return true;
		}
		return false;
	}
	
}
